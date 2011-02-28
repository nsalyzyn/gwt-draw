package com.nsalz.gwt.canvas.draw.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.nsalz.gwt.canvas.create.client.ui.ShapeCanvas;
import com.nsalz.gwt.canvas.draw.client.control.CanvasMouseController;
import com.nsalz.gwt.canvas.draw.client.control.DrawCommandStack;
import com.nsalz.gwt.canvas.draw.client.model.DrawingModel;
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
        DrawingModel drawingModel = new DrawingModel(canvas.getDrawingBoard());
        
        DrawCommandStack undoStack = new DrawCommandStack(drawingModel);
        new CanvasMouseController(canvas, undoStack, drawingModel);
        shapeDrawWidget = new ShapeDrawWidget(canvas, resources.getLayoutCss());
    }

    public void load(LayoutPanel panel)
    {
        panel.add(shapeDrawWidget);
    }

}
