package com.nsalz.gwt.canvas.draw.client.model;


public interface DrawCommand
{
    public void doCommand(DrawingLayerModel model);
    
    public void undoCommand(DrawingLayerModel model);
    
    public void redoCommand(DrawingLayerModel model);
}
