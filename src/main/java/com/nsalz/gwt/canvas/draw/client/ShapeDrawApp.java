package com.nsalz.gwt.canvas.draw.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.nsalz.gwt.canvas.create.client.ui.ShapeCanvas;
import com.nsalz.gwt.canvas.draw.client.control.CanvasMouseController;
import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;
import com.nsalz.gwt.canvas.draw.client.model.DrawingBoardModel;
import com.nsalz.gwt.canvas.draw.client.resource.ShapeDrawResources;
import com.nsalz.gwt.canvas.draw.client.ui.ShapeDrawWidget;

public class ShapeDrawApp
{
    private final ShapeDrawResources resources = GWT.create(ShapeDrawResources.class);
    private final ShapeDrawWidget shapeDrawWidget;

    public ShapeDrawApp() throws ShapeDrawException
    {
        ShapeCanvas canvas = ShapeCanvas.createIfSupported();
        if (canvas == null) {
            throw new ShapeDrawException();
        }
        DrawAppModel drawingModel = new DrawAppModel(canvas.getDrawingBoard());
        
        CanvasMouseController mouseController = new CanvasMouseController(drawingModel);       
        mouseController.addHandlers(canvas);
        
        DrawingBoardModel drawingBoardModel = drawingModel.getDrawingBoardModel();
        drawingBoardModel.setHeight(canvas.getOffsetHeight());
        drawingBoardModel.setWidth(canvas.getOffsetWidth());
        canvas.addResizeHandler(drawingBoardModel);       
        
        shapeDrawWidget = new ShapeDrawWidget(canvas, resources, drawingModel, drawingBoardModel);
    }

    public void load(LayoutPanel panel)
    {
        panel.add(shapeDrawWidget);
    }

}
