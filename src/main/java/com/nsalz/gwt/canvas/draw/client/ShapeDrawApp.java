package com.nsalz.gwt.canvas.draw.client;

import com.google.gwt.user.client.ui.LayoutPanel;
import com.nsalz.gwt.canvas.create.client.ui.ShapeCanvas;
import com.nsalz.gwt.canvas.draw.client.control.CanvasMouseController;
import com.nsalz.gwt.canvas.draw.client.graphics.ProjectGraphic;
import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;
import com.nsalz.gwt.canvas.draw.client.model.DrawingLayerModel;
import com.nsalz.gwt.canvas.draw.client.ui.ShapeDrawWidget;

public class ShapeDrawApp
{
    private final ShapeDrawWidget shapeDrawWidget;

    public ShapeDrawApp() throws ShapeDrawException
    {
        ShapeCanvas<ProjectGraphic> canvas = ShapeCanvas.createIfSupported();
        if (canvas == null) {
            throw new ShapeDrawException();
        }
        DrawAppModel drawingModel = new DrawAppModel(canvas.getDrawingLayer());
        
        CanvasMouseController mouseController = new CanvasMouseController(drawingModel);       
        mouseController.addHandlers(canvas);
        
        DrawingLayerModel drawingLayerModel = drawingModel.getDrawingLayerModel();
        drawingLayerModel.setHeight(canvas.getOffsetHeight());
        drawingLayerModel.setWidth(canvas.getOffsetWidth());
        canvas.addResizeHandler(drawingLayerModel);       
        
        shapeDrawWidget = new ShapeDrawWidget(canvas, drawingModel, drawingLayerModel);
    }

    public void load(LayoutPanel panel)
    {
        panel.add(shapeDrawWidget);
    }

}
