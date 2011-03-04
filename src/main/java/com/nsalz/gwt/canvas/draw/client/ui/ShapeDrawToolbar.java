package com.nsalz.gwt.canvas.draw.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Label;
import com.nsalz.gwt.canvas.draw.client.control.BaseDrawTool;
import com.nsalz.gwt.canvas.draw.client.control.DrawTool;
import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;

public class ShapeDrawToolbar extends Composite
{
    public final Map<DrawTool, FocusWidget> drawTools = new HashMap<DrawTool, FocusWidget>(); 
    
    public ShapeDrawToolbar(DrawAppModel drawingModel)
    {
        drawingModel.setCurrentTool(new BaseDrawTool(drawingModel));
        initWidget(new Label("Coming soon"));
    }
}
