package com.nsalz.gwt.canvas.draw.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusWidget;
import com.nsalz.gwt.canvas.draw.client.control.DrawTool;
import com.nsalz.gwt.canvas.draw.client.control.LineDrawTool;
import com.nsalz.gwt.canvas.draw.client.control.MoveDrawTool;
import com.nsalz.gwt.canvas.draw.client.control.ToolChangeEvent;
import com.nsalz.gwt.canvas.draw.client.control.ToolChangeHandler;
import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;

public class ShapeDrawToolbar extends Composite
{
    private final Map<DrawTool, FocusWidget> drawTools = new HashMap<DrawTool, FocusWidget>();
    private final DrawAppModel drawingModel;
    
    public ShapeDrawToolbar(DrawAppModel drawingModel)
    {
        this.drawingModel = drawingModel;
        
        
        FlowPanel panel = new FlowPanel();
        initWidget(panel);
        
        drawingModel.setCurrentTool(new LineDrawTool(drawingModel));
        panel.add(createToolButton(drawingModel.getCurrentTool(), "Line"));
        panel.add(createToolButton(new MoveDrawTool(drawingModel), "Hand"));
        
        drawingModel.addToolChangeHandler(new ToolChangeHandler(){
            @Override
            public void onToolChange(ToolChangeEvent event)
            {
                // TODO change visuals on tools
            }
        });
        
        panel.add(new Button("Reset Size", new ClickHandler(){          
            @Override
            public void onClick(ClickEvent event)
            {
                ShapeDrawToolbar.this.drawingModel.getDrawingLayerModel().setZoomFactor(0);
                ShapeDrawToolbar.this.drawingModel.getDrawingLayerModel().repaint();
            }
        }));
    }
    
    private FocusWidget createToolButton(final DrawTool tool, String name)
    {
        FocusWidget widget = new Button(name, new ClickHandler(){
            @Override
            public void onClick(ClickEvent event)
            {
                drawingModel.setCurrentTool(tool);
            }
        });
        drawTools.put(tool, widget);
        return widget;
    }
}
