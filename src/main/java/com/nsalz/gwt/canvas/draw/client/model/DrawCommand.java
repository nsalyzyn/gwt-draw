package com.nsalz.gwt.canvas.draw.client.model;


public interface DrawCommand
{
    public void doCommand(DrawingBoardModel model);
    
    public void undoCommand(DrawingBoardModel model);
    
    public void redoCommand(DrawingBoardModel model);
}
