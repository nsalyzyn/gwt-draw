package com.nsalz.gwt.canvas.draw.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface ShapeDrawResources extends ClientBundle
{
    @Source("layout.css")
    public LayoutCss getLayoutCss();

    public interface LayoutCss extends CssResource
    {

    }
}
