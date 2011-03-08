package com.nsalz.gwt.canvas.draw.client.control;

import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;

public class LineDragTool extends BaseDrawTool
{
    private final LineTool lineTool;
    
    public LineDragTool(DrawAppModel drawingModel)
    {
        super(drawingModel);
        this.lineTool = new LineTool(drawingModel);
    }

    @Override
    public void onMouseDown(int x, int y)
    {
        super.onMouseDown(x, y);
        lineTool.startLine(x, y);
    }

    @Override
    public void onCancelKey()
    {
        super.onCancelKey();
        lineTool.cancelLine();
    }

    @Override
    public void onMouseMove(int x, int y)
    {
        super.onMouseMove(x, y);
        lineTool.moveLine(x, y);
    }
    
    @Override
    public void onMouseUp(int x, int y)
    {
        super.onMouseUp(x, y);
        lineTool.finishLine(x, y);
    }

}
