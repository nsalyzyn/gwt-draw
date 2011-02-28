package com.nsalz.gwt.canvas.draw.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class ShapeDraw implements EntryPoint
{

    public void onModuleLoad()
    {
        try {
            new ShapeDrawApp().load(RootLayoutPanel.get());
        } catch (ShapeDrawException e) {
            RootLayoutPanel.get().add(new Label("This application is not supported by your browser"));
        }
        
        Document.get().getElementById("loading").removeFromParent();
    }

}
