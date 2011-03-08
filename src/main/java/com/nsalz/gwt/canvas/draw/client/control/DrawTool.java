package com.nsalz.gwt.canvas.draw.client.control;

public interface DrawTool
{
    public void onMouseHover(int x, int y);

    public void onMouseMove(int x, int y);

    public void onMouseDown(int x, int y);

    public void onMouseUp(int x, int y);

    public void onClick(int x, int y);

    public void onRightMouseDown(int x, int y);

    public void onDoubleClick(int x, int y);
    
    public void onMouseWheel(int delta, int x, int y);
}
