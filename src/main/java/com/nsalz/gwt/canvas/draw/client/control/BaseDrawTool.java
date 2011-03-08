package com.nsalz.gwt.canvas.draw.client.control;

import com.nsalz.gwt.canvas.draw.client.model.DrawAppModel;

public class BaseDrawTool implements DrawTool
{
    private DrawAppModel drawingModel;
    private boolean rightDown = false;
    private int lastX, lastY;

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
    public void onMouseWheel(int delta, int x, int y)
    {
        getDrawModel().getDrawingLayerModel().increaseZoomFactor(-delta, x, y);
        getDrawModel().getDrawingLayerModel().repaint();
    }

    @Override
    public void onRightMouseDown(int x, int y)
    {
        rightDown = true;
        lastX = x;
        lastY = y;
    }

    @Override
    public void onMouseMove(int x, int y)
    {
        if (rightDown) {
            getDrawModel().getDrawingLayerModel().shiftByPixels(x - lastX, y - lastY);
            
            lastX = x;
            lastY = y;
            getDrawModel().getDrawingLayerModel().repaint();
        }
    }

    @Override
    public void onMouseUp(int x, int y)
    {}

    @Override
    public void onCancelKey()
    {}

    @Override
    public void onRightMouseUp(int x, int y)
    {
        rightDown = false;
    }

}
