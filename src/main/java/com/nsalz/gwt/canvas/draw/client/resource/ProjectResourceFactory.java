package com.nsalz.gwt.canvas.draw.client.resource;

import com.google.gwt.core.client.GWT;

public final class ProjectResourceFactory
{
    private static ProjectResourceFactory factory;

    public static ProjectResourceFactory get()
    {
        if (factory == null) {
            factory = new ProjectResourceFactory();
        }
        return factory;
    }

    private ShapeDrawResources resources;

    private ProjectResourceFactory()
    {}

    public ShapeDrawResources getResources()
    {
        if (resources == null) {
            resources = GWT.create(ShapeDrawResources.class);
        }
        return resources;
    }
}
