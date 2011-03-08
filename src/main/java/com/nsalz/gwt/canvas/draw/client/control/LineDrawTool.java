package com.nsalz.gwt.canvas.draw.client.control;

import com.nsalz.gwt.canvas.create.client.shapes.LinePath;
import com.nsalz.gwt.canvas.create.client.tools.ShapeGraphic;
import com.nsalz.gwt.canvas.create.client.tools.Stroke;
import com.nsalz.gwt.canvas.create.client.tools.LineStyle.Cap;
import com.nsalz.gwt.canvas.draw.client.graphics.ProjectGraphic;
import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;
import com.nsalz.gwt.canvas.draw.client.model.DrawingLayerModel;

public class LineDrawTool extends BaseDrawTool
{
    private LinePath path = null;
    private ProjectGraphic graphic = null;

    private final Stroke stroke = new Stroke();
    {
        stroke.getLineStyle().setCap(Cap.ROUND);
    }

    public LineDrawTool(DrawAppModel drawingModel)
    {
        super(drawingModel);
    }

    @Override
    public void onMouseDown(int x, int y)
    {
        DrawingLayerModel model = getDrawModel().getDrawingLayerModel();
        x = model.getActualX(x);
        y = model.getActualY(y);
        
        path = new LinePath(x, y, x, y);
        model.getWorkingLayer().addGraphic(graphic = new ProjectGraphic(new ShapeGraphic(path, stroke)));
        model.repaint();
    }

    @Override
    public void onRightMouseDown(int x, int y)
    {
        if (graphic != null) {
            getDrawModel().getDrawingLayerModel().getWorkingLayer().getGraphicList().clear();
            getDrawModel().getDrawingLayerModel().repaint();
            path = null;
            graphic = null;
        } else {
            super.onRightMouseDown(x, y);
        }
    }

    @Override
    public void onMouseMove(int x, int y)
    {
        if (path != null) {
            DrawingLayerModel model = getDrawModel().getDrawingLayerModel();
            x = model.getActualX(x);
            y = model.getActualY(y);

            path.setX2(x);
            path.setY2(y);
            getDrawModel().getDrawingLayerModel().repaint();
        }
    }

    @Override
    public void onMouseUp(int x, int y)
    {
        if (graphic != null) {
            getDrawModel().getDrawingLayerModel().getWorkingLayer().getGraphicList().clear();
            getDrawModel().getDrawingLayerModel().getUndoStack().doCommand(new PushGraphicCommand(graphic));
            graphic = null;
            path = null;
        }
    }

}
