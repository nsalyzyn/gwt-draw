package com.nsalz.gwt.canvas.draw.client.model;

import com.nsalz.gwt.canvas.create.client.tools.DrawingBoard;
import com.nsalz.gwt.canvas.create.client.tools.Graphic;

public class DrawingModel
{
    private final DrawingBoard mainDrawingBoard;

    public DrawingModel(DrawingBoard mainDrawingBoard)
    {
        this.mainDrawingBoard = mainDrawingBoard;
    }

    // TODO remove
    public void addGraphic(Graphic graphic)
    {
        mainDrawingBoard.addGraphic(graphic);
    }

    public void repaint()
    {
        mainDrawingBoard.repaint();
    }
}
