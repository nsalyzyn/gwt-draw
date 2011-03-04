package com.nsalz.gwt.canvas.draw.client.model;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.nsalz.gwt.canvas.create.client.tools.DrawingBoard;

public class DrawingBoardModel implements ResizeHandler
{
    private final DrawingBoard mainDrawingBoard;
    private final DrawCommandStack undoStack = new DrawCommandStack(this);

    public DrawingBoardModel(DrawingBoard mainDrawingBoard)
    {
        this.mainDrawingBoard = mainDrawingBoard;
    }
    
    public DrawCommandStack getUndoStack()
    {
        return undoStack;
    }
    
    public DrawingBoard getDrawingBoard()
    {
        // TODO return the sub drawing board where all the images actually go
        return mainDrawingBoard;
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
