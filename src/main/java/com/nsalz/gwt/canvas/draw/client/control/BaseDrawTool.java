package com.nsalz.gwt.canvas.draw.client.control;

import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;

public class BaseDrawTool implements DrawTool
{
    private DrawAppModel drawingModel;

    public BaseDrawTool(DrawAppModel drawingModel)
    {
        this.drawingModel = drawingModel;
    }

    protected DrawAppModel getDrawModel()
    {
        return drawingModel;
    }

    @Override
    public void onClick(int x, int y)
    {}

    @Override
    public void onDoubleClick(int x, int y)
    {}

    @Override
    public void onMouseDown(int x, int y)
    {}

    @Override
    public void onMouseHover(int x, int y)
    {}

    @Override
    public void onMouseWheel(int delta)
    {
        getDrawModel().getDrawingLayerModel().increaseZoomFactor(-delta);
        getDrawModel().getDrawingLayerModel().repaint();
    }

    @Override
    public void onRightMouseDown(int x, int y)
    {}

    @Override
    public void onMouseMove(int x, int y)
    {}

    @Override
    public void onMouseUp(int x, int y)
    {}

}
