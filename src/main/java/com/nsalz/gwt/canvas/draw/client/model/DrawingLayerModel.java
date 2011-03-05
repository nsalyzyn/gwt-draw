package com.nsalz.gwt.canvas.draw.client.model;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.nsalz.gwt.canvas.create.client.tools.DrawingLayer;
import com.nsalz.gwt.canvas.create.client.tools.Transform;
import com.nsalz.gwt.canvas.draw.client.graphics.ProjectGraphic;

public class DrawingLayerModel implements ResizeHandler
{
    private static final int ZOOM_MIN = -20;
    private static final int ZOOM_MAX = 30;
    private static final double SCALE_RATE = 1.05;
    
    private final DrawingLayer<ProjectGraphic> mainLayer;
    private final DrawingLayer<ProjectGraphic> drawingLayer;
    private final DrawingLayer<ProjectGraphic> workingLayer;
    private final DrawCommandStack undoStack = new DrawCommandStack(this);
    private int zoomFactor = 0;
    private double scale = 1.0;

    public DrawingLayerModel(DrawingLayer<ProjectGraphic> mainLayer)
    {
        this.mainLayer = mainLayer;
        DrawingLayer<ProjectGraphic> transformedLayer = mainLayer.createChildDrawingLayer(new Transform(){
            @Override
            public void applyTransform(TransformTool transformTool)
            {
                transformTool.scale(scale);
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

    public void setZoomFactor(int factor)
    {
        zoomFactor = Math.max(ZOOM_MIN, Math.min(ZOOM_MAX, factor));
        recalculateScale();
    }

    public void increaseZoomFactor(int factor)
    {
        zoomFactor = Math.max(ZOOM_MIN, Math.min(ZOOM_MAX, zoomFactor + factor));
        recalculateScale();
    }

    @Override
    public void onResize(ResizeEvent event)
    {
        // TODO Auto-generated method stub
        
    }

    public void setHeight(int height)
    {
        // TODO Auto-generated method stub
        
    }

    public void setWidth(int width)
    {
        // TODO Auto-generated method stub
        
    }
    
    private void recalculateScale()
    {
        scale = Math.pow(SCALE_RATE, zoomFactor);
    }
}
