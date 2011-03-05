package com.nsalz.gwt.canvas.draw.client.graphics;

import com.nsalz.gwt.canvas.create.client.tools.Graphic;

public class ProjectGraphic implements Graphic
{
    private final Graphic graphic;
    
    public ProjectGraphic(Graphic graphic)
    {
        this.graphic = graphic;
    }

    @Override
    public void draw(GraphicTool tool)
    {
        graphic.draw(tool);
    }

}
