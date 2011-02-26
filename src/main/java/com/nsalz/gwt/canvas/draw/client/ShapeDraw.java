package com.nsalz.gwt.canvas.draw.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.nsalz.gwt.canvas.create.client.TestComposite;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class ShapeDraw implements EntryPoint
{

    public void onModuleLoad()
    {
        Document.get().getElementById("loading").removeFromParent();
        RootLayoutPanel.get().add(new TestComposite());
    }

}
