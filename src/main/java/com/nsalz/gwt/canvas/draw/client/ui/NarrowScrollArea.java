package com.nsalz.gwt.canvas.draw.client.ui;

import com.google.gwt.event.dom.client.HasScrollHandlers;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;

public class NarrowScrollArea extends FocusPanel implements ProvidesResize, RequiresResize, HasScrollHandlers
{
    private static final int MOUSE_WHEEL_MULTIPLIER = 10;
    
    private Element containerElem;

    public NarrowScrollArea()
    {
        setHeight("100%");
        DOM.setStyleAttribute(getElement(), "overflow", "hidden");
        DOM.setStyleAttribute(getElement(), "outline", "none");

        containerElem = DOM.createDiv();
        getElement().appendChild(containerElem);
        
        addMouseWheelHandler(new MouseWheelHandler(){
            @Override
            public void onMouseWheel(MouseWheelEvent event)
            {
                setScrollPosition(getScrollPosition() + event.getDeltaY()*MOUSE_WHEEL_MULTIPLIER);
            }
        });
    }

    public NarrowScrollArea(Widget child)
    {
        this();
        setWidget(child);
    }

    /**
     * Gets the vertical scroll position.
     * 
     * @return the vertical scroll position, in pixels
     */
    public int getScrollPosition()
    {
        return DOM.getElementPropertyInt(getElement(), "scrollTop");
    }

    /**
     * Scroll to the bottom of this panel.
     */
    public void scrollToBottom()
    {
        setScrollPosition(DOM.getElementPropertyInt(getElement(), "scrollHeight"));
    }

    /**
     * Scroll to the top of this panel.
     */
    public void scrollToTop()
    {
        setScrollPosition(0);
    }

    /**
     * Sets the vertical scroll position.
     * 
     * @param position
     *            the new vertical scroll position, in pixels
     */
    public void setScrollPosition(int position)
    {
        DOM.setElementPropertyInt(getElement(), "scrollTop", position);
    }

    @Override
    public HandlerRegistration addScrollHandler(ScrollHandler handler)
    {
        return addDomHandler(handler, ScrollEvent.getType());
    }

    @Override
    public void onResize()
    {
        Widget child = getWidget();
        if ((child != null) && (child instanceof RequiresResize)) {
            ((RequiresResize) child).onResize();
        }
    }

    @Override
    protected Element getContainerElement()
    {
        return containerElem;
    }
}
