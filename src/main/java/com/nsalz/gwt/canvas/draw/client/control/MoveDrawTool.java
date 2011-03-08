package com.nsalz.gwt.canvas.draw.client.control;

import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;

public class MoveDrawTool extends BaseDrawTool
{
    public MoveDrawTool(DrawAppModel drawingModel)
    {
        super(drawingModel);
    }

    @Override
    public void onMouseDown(int x, int y)
    {
        super.onRightMouseDown(x, y);
    }

    @Override
    public void onMouseUp(int x, int y)
    {
        super.onRightMouseUp(x, y);
    }

}
