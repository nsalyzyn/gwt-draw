package com.nsalz.gwt.canvas.draw.client.model;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.nsalz.gwt.canvas.create.client.tools.DrawingLayer;
import com.nsalz.gwt.canvas.create.client.tools.Transform;
import com.nsalz.gwt.canvas.draw.client.graphics.ProjectGraphic;

public class DrawingLayerModel implements ResizeHandler
{
    private static final int ZOOM_MIN = -15;
    private static final int ZOOM_MAX = 60;
    private static final double SCALE_RATE = 1.05;
    
    private final DrawingLayer<ProjectGraphic> mainLayer;
    private final DrawingLayer<ProjectGraphic> drawingLayer;
    private final DrawingLayer<ProjectGraphic> workingLayer;
    private final DrawCommandStack undoStack = new DrawCommandStack(this);

    private int zoomFactor = 0;
    private double scale = 1.0;
    private int currentHeight = 0;
    private int currentWidth = 0;
    private double translateX = 0.0;
    private double translateY = 0.0;

    public DrawingLayerModel(DrawingLayer<ProjectGraphic> mainLayer)
    {
        this.mainLayer = mainLayer;
        DrawingLayer<ProjectGraphic> transformedLayer = mainLayer.createChildDrawingLayer(new Transform(){
            @Override
            public void applyTransform(TransformTool transformTool)
            {
                transformTool.scale(scale);
                transformTool.translate(translateX, translateY);
            }
        });
        
        drawingLayer = transformedLayer.createChildDrawingLayer();
        workingLayer = transformedLayer.createChildDrawingLayer();
    }
    
    public DrawCommandStack getUndoStack()
    {
        return undoStack;
    }
    
    public void repaint()
    {
        mainLayer.repaint();
    }
    
    public DrawingLayer<ProjectGraphic> getWorkingLayer()
    {
        return workingLayer;
    }
    
    public DrawingLayer<ProjectGraphic> getDrawingLayer()
    {
        return drawingLayer;
    }
    
    public void center()
    {
        translateX = currentWidth/2.0;
        translateY = currentHeight/2.0;
    }

    public void setZoomFactor(int factor)
    {
        setZoomFactor(factor, currentWidth/2, currentHeight/2);
    }
    
    public void setZoomFactor(int factor, int x, int y)
    {
        zoomFactor = Math.max(ZOOM_MIN, Math.min(ZOOM_MAX, factor));
        recalculateScale(x, y);
    }

    public void increaseZoomFactor(int factor)
    {
        increaseZoomFactor(factor, currentWidth/2, currentHeight/2);
    }

    public void increaseZoomFactor(int factor, int x, int y)
    {
        zoomFactor = Math.max(ZOOM_MIN, Math.min(ZOOM_MAX, zoomFactor + factor));
        recalculateScale(x, y);
    }

    @Override
    public void onResize(ResizeEvent event)
    {
        setDimensions(event.getHeight(), event.getWidth());
    }

    public void setDimensions(int height, int width)
    {
        if (height != currentHeight || width != currentWidth) {
            translateX += (width - currentWidth)/(2*scale);
            translateY += (height - currentHeight)/(2*scale);
            currentWidth = width;
            currentHeight = height;
            repaint();
        }
    }
    
    public int getActualX(int x)
    {
        return getActualCoordinate(x, translateX, scale);
    }
    
    public int getActualY(int y)
    {
        return getActualCoordinate(y, translateY, scale);
    }
    
    private void recalculateScale(int x, int y)
    {
        double oldScale = scale;
        scale = Math.pow(SCALE_RATE, zoomFactor);
        
        translateX = maintainTranslationOnScale(x, translateX, scale, oldScale);
        translateY = maintainTranslationOnScale(y, translateY, scale, oldScale);
    }
    
    /**
     * <pre>
     * |------|--------|----|
     *        0       act  clicked    
     * <-------------------->
     *        coord
     * <------>
     *    tr
     * </pre>
     */
    private static int getActualCoordinate(int coordinate, double translation, double scale)
    {
        return (int)Math.round((coordinate - translation)/scale);
    }
    
    /**
     * <pre>
     * |------|-------|------|              |-----|-------|-------|
     *        0      act   clicked                0      act     newpos
     * <-------------------->         ====> <--------------------->
     *        coord                                   coord
     * <------><------------->              <-----><-------------->
     *    tr1        a1                       tr2         a2
     * </pre>
     * 
     * clicked = tr1 + a1
     * newpos = clicked
     * newpos = tr2 + a2
     * a2 = (newscale/oldscale)*a1
     * a2 = (newscale/oldscale)*(clicked - tr1)
     * tr2 = clicked - a2
     * tr2 = clicked - (newscale/oldscale)*(clicked - tr1)
     */
    private static double maintainTranslationOnScale(int coordinate, double translation, double newScale, double oldScale)
    {
        return (double)coordinate - (newScale/oldScale) * ((double)coordinate - translation);
    }
}
