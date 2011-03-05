package com.nsalz.gwt.canvas.draw.client.control;

import com.nsalz.gwt.canvas.draw.client.graphics.ProjectGraphic;
import com.nsalz.gwt.canvas.draw.client.model.DrawCommand;
import com.nsalz.gwt.canvas.draw.client.model.DrawingLayerModel;

public class PushGraphicCommand implements DrawCommand
{
    private final ProjectGraphic graphic;

    public PushGraphicCommand(ProjectGraphic graphic)
    {
        this.graphic = graphic;
    }

    @Override
    public void doCommand(DrawingLayerModel model)
    {
        model.getDrawingLayer().addGraphic(graphic);
        model.repaint();
    }

    @Override
    public void redoCommand(DrawingLayerModel model)
    {
        doCommand(model);
    }

    @Override
    public void undoCommand(DrawingLayerModel model)
    {
        model.getDrawingLayer().getGraphicList().remove(graphic);
        model.repaint();
    }

}
