package com.nsalz.gwt.canvas.draw.client.model;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.nsalz.gwt.canvas.create.client.tools.DrawingBoard;
import com.nsalz.gwt.canvas.draw.client.control.DrawTool;
import com.nsalz.gwt.canvas.draw.client.control.HasToolChangeHandler;
import com.nsalz.gwt.canvas.draw.client.control.ToolChangeEvent;
import com.nsalz.gwt.canvas.draw.client.control.ToolChangeHandler;

public class DrawAppModel implements HasToolChangeHandler
{
    private final DrawingBoardModel mainDrawingBoardModel;
    private final EventBus eventBus;
    private DrawTool currentTool;

    public DrawAppModel(DrawingBoard mainDrawingBoard)
    {
        this.mainDrawingBoardModel = new DrawingBoardModel(mainDrawingBoard);
        this.eventBus = new SimpleEventBus();
    }
    
    @Override
    public HandlerRegistration addToolChangeHandler(ToolChangeHandler handler) {
        return eventBus.addHandler(ToolChangeEvent.TYPE, handler);
    }
    
    public void setCurrentTool(DrawTool newTool)
    {
        currentTool = newTool;
        ToolChangeEvent.fire(this, currentTool);
    }
    
    public DrawTool getCurrentTool()
    {
        return currentTool;
    }
    
    public DrawingBoardModel getDrawingBoardModel()
    {
        return mainDrawingBoardModel;
    }

    @Override
    public void fireEvent(GwtEvent<?> event)
    {
        eventBus.fireEvent(event);
    }
}
