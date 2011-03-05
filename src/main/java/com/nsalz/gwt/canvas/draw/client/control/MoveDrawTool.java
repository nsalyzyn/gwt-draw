package com.nsalz.gwt.canvas.draw.client.control;

import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;

public class MoveDrawTool extends BaseDrawTool
{
    private boolean mouseDown = false;

    public MoveDrawTool(DrawAppModel drawingModel)
    {
        super(drawingModel);
    }

    @Override
    public void onMouseDown(int x, int y)
    {
        mouseDown = true;
    }

    @Override
    public void onMouseMove(int x, int y)
    {
        if (mouseDown) {
            
        }
    }

    @Override
    public void onMouseUp(int x, int y)
    {
        mouseDown = false;
    }

}
