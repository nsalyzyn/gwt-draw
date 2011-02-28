package com.nsalz.gwt.canvas.draw.client.control;

import java.util.Stack;

import com.nsalz.gwt.canvas.draw.client.model.DrawingModel;

public class DrawCommandStack
{
    private final DrawingModel drawingModel;
    private final Stack<DrawCommand> commands = new Stack<DrawCommand>();
    private final Stack<DrawCommand> undoneCommands = new Stack<DrawCommand>();

    public DrawCommandStack(DrawingModel drawingModel)
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
