package com.nsalz.gwt.canvas.draw.client.control;

import com.nsalz.gwt.canvas.draw.client.model.DrawingModel;

public interface DrawCommand
{
    public void doCommand(DrawingModel model);
    
    public void undoCommand(DrawingModel model);
    
    public void redoCommand(DrawingModel model);
}
