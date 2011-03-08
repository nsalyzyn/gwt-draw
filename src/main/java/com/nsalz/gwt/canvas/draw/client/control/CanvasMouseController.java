package com.nsalz.gwt.canvas.draw.client.control;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.FocusWidget;
import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;

public class CanvasMouseController
{
    private static final int HOVER_TIMING = 400;
    
    private final DrawAppModel drawingModel;

    public CanvasMouseController(DrawAppModel drawingModel)
    {
        this.drawingModel = drawingModel;
        addGlobalHandlers();
    }

    public void addHandlers(FocusWidget canvas)
    {
        new CanvasHandlerHandler(canvas);
        
    }
    
    private void addGlobalHandlers()
    {
        Event.addNativePreviewHandler(new NativePreviewHandler(){
            @Override
            public void onPreviewNativeEvent(NativePreviewEvent event)
            {
                if (event.getTypeInt() == Event.ONKEYDOWN) {
                    NativeEvent nativeEvent = event.getNativeEvent();

                    if (nativeEvent.getCtrlKey()) {
                        if (!nativeEvent.getShiftKey() && !nativeEvent.getAltKey()) {
                            char keyCode = (char) nativeEvent.getKeyCode();
                            if (keyCode == 'Z') {
                                if (!willTargetAcceptUndo(nativeEvent.getEventTarget())) {
                                    drawingModel.getDrawingLayerModel().getUndoStack().undo();
                                }
                            } else if (keyCode == 'Y') {
                                if (!willTargetAcceptUndo(nativeEvent.getEventTarget())) {
                                    drawingModel.getDrawingLayerModel().getUndoStack().redo();
                                }
                            }
                        }
                    }
                    else if (nativeEvent.getKeyCode() == KeyCodes.KEY_ESCAPE) {
                        DrawTool tool = drawingModel.getCurrentTool();
                        if (tool != null) {
                            tool.onCancelKey();
                        }
                    }
                }
            }
        });
    }
    
    private boolean willTargetAcceptUndo(EventTarget target)
    {
        if (Element.is(target)) {
            String nodeName = Element.as(target).getNodeName();
            if ("textarea".equalsIgnoreCase(nodeName)) {
                return true;
            }
            else if ("input".equalsIgnoreCase(nodeName)) {
                String type = InputElement.as(Element.as(target)).getType();
                
                if ("text".equals(type) || "password".equals(type)) {
                    return true;
                } else {
                    return false;
                }
            }
            else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    private static native void disableContextMenu() /*-{
        $doc.oncontextmenu = function() { return false; }
    }-*/;
    private static native void enableContextMenu() /*-{
        $doc.oncontextmenu = function() { return true; }
    }-*/;
    
    private class CanvasHandlerHandler {
        private final FocusWidget canvas;
        private final HoverTimer timer = new HoverTimer();
        private boolean mouseDown = false;
        
        public CanvasHandlerHandler (FocusWidget canvas) {
            this.canvas = canvas;
            setup();
        }
        
        private void setup()
        {
            canvas.addClickHandler(new ClickHandler(){
                @Override
                public void onClick(ClickEvent event)
                {
                    DrawTool tool = drawingModel.getCurrentTool();
                    if (tool != null) {
                        switch (event.getNativeButton()) {
                        case NativeEvent.BUTTON_LEFT:
                            tool.onClick(event.getX(), event.getY());
                            break;
                        }
                     }
                }
            });
            
            canvas.addDoubleClickHandler(new DoubleClickHandler(){
                @Override
                public void onDoubleClick(DoubleClickEvent event)
                {
                    DrawTool tool = drawingModel.getCurrentTool();
                    if (tool != null) {
                        tool.onClick(event.getX(), event.getY());
                    }
                }
            });

            canvas.addMouseDownHandler(new MouseDownHandler(){
                @Override
                public void onMouseDown(MouseDownEvent event)
                {
                    mouseDown = true; 
                    DrawTool tool = drawingModel.getCurrentTool();
                    if (tool != null) {
                        switch (event.getNativeButton()) {
                        case NativeEvent.BUTTON_LEFT:
                            tool.onMouseDown(event.getX(), event.getY());
                            event.preventDefault();
                            break;
                        case NativeEvent.BUTTON_RIGHT:
                            tool.onRightMouseDown(event.getX(), event.getY());
                            break;
                        }
                    }
                }
            });
            
            canvas.addMouseMoveHandler(new MouseMoveHandler() {
                @Override
                public void onMouseMove(MouseMoveEvent event)
                {
                    DrawTool tool = drawingModel.getCurrentTool();
                    if (tool != null) {
                        tool.onMouseMove(event.getX(), event.getY());
                    }
                    timer.cancel();
                    timer.setX(event.getX());
                    timer.setY(event.getY());
                    timer.schedule(HOVER_TIMING);
                }
            });
            
            canvas.addMouseOverHandler(new MouseOverHandler(){
                @Override
                public void onMouseOver(MouseOverEvent event)
                {
                    disableContextMenu();
                }
            });
            
            canvas.addMouseOutHandler(new MouseOutHandler(){
                
                @Override
                public void onMouseOut(MouseOutEvent event)
                {
                    enableContextMenu();
                    timer.cancel();
                }
            });
            
            canvas.addMouseWheelHandler(new MouseWheelHandler(){
                @Override
                public void onMouseWheel(MouseWheelEvent event)
                {
                    DrawTool tool = drawingModel.getCurrentTool();
                    if (tool != null) {
                        tool.onMouseWheel(event.getDeltaY(), event.getX(), event.getY());
                    }
                }
            });
            
            Event.addNativePreviewHandler(new NativePreviewHandler(){
                
                @Override
                public void onPreviewNativeEvent(NativePreviewEvent event)
                {
                    if (mouseDown && event.getTypeInt() == Event.ONMOUSEUP) {
                        DrawTool tool = drawingModel.getCurrentTool();
                        if (tool != null) {
                            int x = event.getNativeEvent().getClientX() - canvas.getElement().getAbsoluteLeft();
                            int y = event.getNativeEvent().getClientY() - canvas.getElement().getAbsoluteTop();
                            switch (event.getNativeEvent().getButton()) {
                            case NativeEvent.BUTTON_LEFT:
                                tool.onMouseUp(x, y);
                                break;
                            case NativeEvent.BUTTON_RIGHT:
                                tool.onRightMouseUp(x, y);
                                break;
                            }
                        }
                    }
                }
            });
        }
    }
    
    private class HoverTimer extends Timer {
        
        private int x = 0;
        private int y = 0;
        
        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public void run()
        {
            DrawTool tool = drawingModel.getCurrentTool();
            if (tool != null) {
                tool.onMouseHover(x, y);
            }
        }
        
    }
}
