package com.nsalz.gwt.canvas.draw.client.control;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasToolChangeHandler extends HasHandlers
{
    public HandlerRegistration addToolChangeHandler(ToolChangeHandler handler);
}
