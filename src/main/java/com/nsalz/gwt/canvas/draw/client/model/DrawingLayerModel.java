package com.nsalz.gwt.canvas.draw.client.model;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.nsalz.gwt.canvas.create.client.tools.DrawingLayer;
import com.nsalz.gwt.canvas.create.client.tools.Transform;
import com.nsalz.gwt.canvas.draw.client.graphics.ProjectGraphic;

public class DrawingLayerModel implements ResizeHandler
{
    private final DrawingLayer<ProjectGraphic> mainLayer;
    private final DrawingLayer<ProjectGraphic> drawingLayer;
    private final DrawingLayer<ProjectGraphic> workingLayer;
    private final DrawCommandStack undoStack = new DrawCommandStack(this);

    public DrawingLayerModel(DrawingLayer<ProjectGraphic> mainLayer)
    {
        this.mainLayer = mainLayer;
        DrawingLayer<ProjectGraphic> transformedLayer = mainLayer.createChildDrawingLayer(new Transform(){
            @Override
            public void applyTransform(TransformTool transformTool)
            {
            // TODO Auto-generated method stub

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
}
