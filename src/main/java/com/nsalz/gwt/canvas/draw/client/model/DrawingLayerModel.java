package com.nsalz.gwt.canvas.draw.client.model;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.nsalz.gwt.canvas.create.client.tools.DrawingLayer;
import com.nsalz.gwt.canvas.draw.client.graphics.ProjectGraphic;

public class DrawingLayerModel implements ResizeHandler
{
    private final DrawingLayer<ProjectGraphic> mainLayer;
    private final DrawCommandStack undoStack = new DrawCommandStack(this);

    public DrawingLayerModel(DrawingLayer<ProjectGraphic> mainLayer)
    {
        this.mainLayer = mainLayer;
    }
    
    public DrawCommandStack getUndoStack()
    {
        return undoStack;
    }
    
    public DrawingLayer<ProjectGraphic> getDrawingLayer()
    {
        // TODO return the sub drawing board where all the images actually go
        return mainLayer;
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
