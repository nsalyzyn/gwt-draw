package com.nsalz.gwt.canvas.draw.client.control;

import com.nsalz.gwt.canvas.draw.client.graphics.ProjectGraphic;
import com.nsalz.gwt.canvas.draw.client.model.DrawCommand;
import com.nsalz.gwt.canvas.draw.client.model.DrawingLayerModel;

public class AlreadyPushedGraphicCommand implements DrawCommand
{
    private final ProjectGraphic graphic;

    public AlreadyPushedGraphicCommand(ProjectGraphic graphic)
    {
        this.graphic = graphic;
    }

    @Override
    public void doCommand(DrawingLayerModel model)
    {}

    @Override
    public void redoCommand(DrawingLayerModel model)
    {
        model.getDrawingLayer().getGraphicList().add(graphic);
        model.getDrawingLayer().repaint();
    }

    @Override
    public void undoCommand(DrawingLayerModel model)
    {
        model.getDrawingLayer().getGraphicList().remove(graphic);
        model.getDrawingLayer().repaint();
    }

}
