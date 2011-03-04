package com.nsalz.gwt.canvas.draw.client.control;

import com.nsalz.gwt.canvas.create.client.shapes.LinePath;
import com.nsalz.gwt.canvas.create.client.tools.ShapeGraphic;
import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;

public class BaseDrawTool implements DrawTool
{
    private DrawAppModel drawingModel;

    private LinePath path = null;
    private ShapeGraphic graphic = null;

    public BaseDrawTool(DrawAppModel drawingModel)
    {
        this.drawingModel = drawingModel;
    }

    protected DrawAppModel getDrawModel()
    {
        return drawingModel;
    }

    @Override
    public void onClick(int x, int y)
    {}

    @Override
    public void onDoubleClick(int x, int y)
    {}

    @Override
    public void onMouseDown(int x, int y)
    {
        path = new LinePath(x, y, x, y);
        drawingModel.getDrawingBoardModel().getDrawingBoard().addGraphic(graphic = new ShapeGraphic(path));
        drawingModel.getDrawingBoardModel().getDrawingBoard().repaint();
    }

    @Override
    public void onMouseHover(int x, int y)
    {}

    @Override
    public void onMouseWheel(int delta)
    {}

    @Override
    public void onRightMouseDown(int x, int y)
    {
        if (graphic != null) {
            drawingModel.getDrawingBoardModel().getDrawingBoard().getGraphicList().remove(graphic);
            drawingModel.getDrawingBoardModel().getDrawingBoard().repaint();
            path = null;
            graphic = null;
        }
    }

    @Override
    public void onMouseMove(int x, int y)
    {
        if (path != null) {
            path.setX2(x);
            path.setY2(y);
            drawingModel.getDrawingBoardModel().getDrawingBoard().repaint();
        }
    }

    @Override
    public void onMouseUp(int x, int y)
    {
        drawingModel.getDrawingBoardModel().getUndoStack().doCommand(new AlreadyPushedGraphicCommand(graphic));
        graphic = null;
        path = null;
    }

}
