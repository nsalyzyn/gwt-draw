package com.nsalz.gwt.canvas.draw.client.control;

import com.nsalz.gwt.canvas.create.client.tools.Graphic;
import com.nsalz.gwt.canvas.draw.client.model.DrawCommand;
import com.nsalz.gwt.canvas.draw.client.model.DrawingBoardModel;

public class AlreadyPushedGraphicCommand implements DrawCommand
{
    private final Graphic graphic;

    public AlreadyPushedGraphicCommand(Graphic graphic)
    {
        this.graphic = graphic;
    }

    @Override
    public void doCommand(DrawingBoardModel model)
    {}

    @Override
    public void redoCommand(DrawingBoardModel model)
    {
        model.getDrawingBoard().getGraphicList().add(graphic);
        model.getDrawingBoard().repaint();
    }

    @Override
    public void undoCommand(DrawingBoardModel model)
    {
        model.getDrawingBoard().getGraphicList().remove(graphic);
        model.getDrawingBoard().repaint();
    }

}
