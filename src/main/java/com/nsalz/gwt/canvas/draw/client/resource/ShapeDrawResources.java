package com.nsalz.gwt.canvas.draw.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface ShapeDrawResources extends ClientBundle
{
    @Source("layout.css")
    public LayoutCss layoutCss();

    public interface LayoutCss extends CssResource
    {
        public int toolWidth();

        public String canvasStyle();

        public String canvasWrapStyle();

        public String toolWrapStyle();

        public String toolbarStyle();
    }
}
