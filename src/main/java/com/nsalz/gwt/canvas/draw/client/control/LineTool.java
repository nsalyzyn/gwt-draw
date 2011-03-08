package com.nsalz.gwt.canvas.draw.client.control;

import com.nsalz.gwt.canvas.create.client.shapes.LinePath;
import com.nsalz.gwt.canvas.create.client.tools.ShapeGraphic;
import com.nsalz.gwt.canvas.create.client.tools.Stroke;
import com.nsalz.gwt.canvas.create.client.tools.LineStyle.Cap;
import com.nsalz.gwt.canvas.draw.client.graphics.ProjectGraphic;
import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;
import com.nsalz.gwt.canvas.draw.client.model.DrawingLayerModel;

public class LineTool
{
    private final DrawAppModel drawingModel;

    private LinePath path = null;
    private ProjectGraphic graphic = null;

    private final Stroke stroke = new Stroke();
    {
        stroke.getLineStyle().setCap(Cap.ROUND);
    }

    public LineTool(DrawAppModel drawingModel)
    {
        this.drawingModel = drawingModel;
    }
    
    protected DrawAppModel getDrawModel()
    {
        return drawingModel;
    }
    
    public boolean isLineStarted()
    {
        return graphic != null;
    }

    public void startLine(int x, int y)
    {
        if (!isLineStarted()) {
            DrawingLayerModel model = getDrawModel().getDrawingLayerModel();
            x = model.getActualX(x);
            y = model.getActualY(y);

            path = new LinePath(x, y, x, y);
            model.getWorkingLayer().addGraphic(graphic = new ProjectGraphic(new ShapeGraphic(path, stroke)));
            model.repaint();
        }
    }

    public void cancelLine()
    {
        if (isLineStarted()) {
            getDrawModel().getDrawingLayerModel().getWorkingLayer().getGraphicList().clear();
            getDrawModel().getDrawingLayerModel().repaint();
            path = null;
            graphic = null;
        }
    }

    public void moveLine(int x, int y)
    {
        if (isLineStarted()) {
            DrawingLayerModel model = getDrawModel().getDrawingLayerModel();
            x = model.getActualX(x);
            y = model.getActualY(y);

            path.setX2(x);
            path.setY2(y);
            getDrawModel().getDrawingLayerModel().repaint();
        }
    }
    
    public void finishLine(int x, int y)
    {
        if (isLineStarted()) {
            DrawingLayerModel model = getDrawModel().getDrawingLayerModel();
            model.getWorkingLayer().getGraphicList().clear();
            model.getUndoStack().doCommand(new PushGraphicCommand(graphic));
            graphic = null;
            path = null;
        }
    }
}
