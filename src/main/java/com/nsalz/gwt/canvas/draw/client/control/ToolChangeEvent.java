package com.nsalz.gwt.canvas.draw.client.control;

import com.google.gwt.event.shared.GwtEvent;

public class ToolChangeEvent extends GwtEvent<ToolChangeHandler>
{
    @SuppressWarnings("unchecked")
    public static final GwtEvent.Type<ToolChangeHandler> TYPE = (GwtEvent.Type<ToolChangeHandler>)new GwtEvent.Type();
    
    public static void fire(HasToolChangeHandler hasHandler, DrawTool tool)
    {
        hasHandler.fireEvent(new ToolChangeEvent(tool));
    }
    
    private DrawTool tool;
    
    protected ToolChangeEvent(DrawTool tool)
    {
        this.tool = tool;
    }
    
    public DrawTool getTool()
    {
        return tool;
    }

    @Override
    protected void dispatch(ToolChangeHandler handler)
    {
        handler.onToolChange(this);
    }

    @Override
    public GwtEvent.Type<ToolChangeHandler> getAssociatedType()
    {
        return TYPE;
    }

}
