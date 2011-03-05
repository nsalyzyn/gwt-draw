package com.nsalz.gwt.canvas.draw.client.model;

import java.util.Stack;


public class DrawCommandStack
{
    private final DrawingLayerModel drawingModel;
    private final Stack<DrawCommand> commands = new Stack<DrawCommand>();
    private final Stack<DrawCommand> undoneCommands = new Stack<DrawCommand>();

    public DrawCommandStack(DrawingLayerModel drawingModel)
    {
        this.drawingModel = drawingModel;
    }

    public void doCommand(DrawCommand command)
    {
        commands.push(command);
        command.doCommand(drawingModel);
        undoneCommands.clear();
    }

    public void undo()
    {
        if (!commands.isEmpty()) {
            DrawCommand command = commands.pop();
            command.undoCommand(drawingModel);
            undoneCommands.push(command);
        }
    }

    public void redo()
    {
        if (!undoneCommands.isEmpty()) {
            DrawCommand command = undoneCommands.pop();
            command.redoCommand(drawingModel);
            commands.push(command);
        }
    }
}
