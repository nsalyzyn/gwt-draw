package com.nsalz.gwt.canvas.draw.client.control;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.FocusWidget;
import com.nsalz.gwt.canvas.create.client.shapes.LinePath;
import com.nsalz.gwt.canvas.create.client.tools.ShapeGraphic;
import com.nsalz.gwt.canvas.draw.client.model.DrawingModel;

public class CanvasMouseController
{
    private final DrawCommandStack undoStack;
    private final DrawingModel drawingModel;

    private LinePath path = null;

    public CanvasMouseController(FocusWidget canvas, DrawCommandStack undoStack, DrawingModel drawingModel)
    {
        this.undoStack = undoStack;
        this.drawingModel = drawingModel;

        addHandlers(canvas);
    }

    public void addHandlers(FocusWidget canvas)
    {
        canvas.addMouseDownHandler(new MouseDownHandler(){
            @Override
            public void onMouseDown(MouseDownEvent event)
            {
                path = new LinePath(event.getX(), event.getY(), event.getX(), event.getY());
                drawingModel.addGraphic(new ShapeGraphic(path));
                drawingModel.repaint();
            }
        });
        
        canvas.addMouseMoveHandler(new MouseMoveHandler() {
            
            @Override
            public void onMouseMove(MouseMoveEvent event)
            {
                if (path != null) {
                    path.setX2(event.getX());
                    path.setY2(event.getY());
                    drawingModel.repaint();
                }
            }
        });
        
        canvas.addMouseUpHandler(new MouseUpHandler(){
            
            @Override
            public void onMouseUp(MouseUpEvent event)
            {
                path = null;
            }
        });
    }
}
