package com.nsalz.gwt.canvas.draw.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;
import com.nsalz.gwt.canvas.create.client.ui.ShapeCanvas;
import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;
import com.nsalz.gwt.canvas.draw.client.model.DrawingBoardModel;
import com.nsalz.gwt.canvas.draw.client.resource.ShapeDrawResources;

public class ShapeDrawWidget extends Composite implements RequiresResize, ProvidesResize
{
    interface ShapeDrawUiBinder extends UiBinder<DockLayoutPanel, ShapeDrawWidget>
    {}
    private static ShapeDrawUiBinder BINDER = GWT.create(ShapeDrawUiBinder.class);

    @UiField(provided = true)
    ShapeDrawToolbar toolbar;
    @UiField(provided = true)
    ShapeCanvas canvas;
    @UiField(provided = true)
    ShapeDrawResources resources;
    
    private final DockLayoutPanel mainPanel;
    

    public ShapeDrawWidget(ShapeCanvas canvas, ShapeDrawResources resources, DrawAppModel drawingModel, DrawingBoardModel drawingBoardModel)
    {
        toolbar = new ShapeDrawToolbar(drawingModel);
        this.canvas = canvas;
        this.resources = resources;
        
        initWidget(mainPanel = BINDER.createAndBindUi(this));
    }

    @Override
    public void onResize()
    {
        mainPanel.onResize();
    }
}
