package applicationLayer.command;

import gui.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/* based on forests example Version 4 */

public class Invoker {
    private Thread worker;
    private boolean isGoing;
    private ConcurrentLinkedQueue<Command> doQueue = new ConcurrentLinkedQueue<Command>();
    private List<Command> undoStack = Collections.synchronizedList(new ArrayList<Command>());
    private List<Command> redoStack = Collections.synchronizedList(new ArrayList<Command>());
    private AutoResetEvent enqueEvent = new AutoResetEvent(false);
    private Canvas canvas;

    public Invoker(Canvas canvas){
        this.canvas = canvas;
    }

    public Invoker (){
        canvas = new Canvas();
    }

    public void start() {
        isGoing = true;
        worker = new Thread(this::run);
        worker.start();
    }

    public void stop() {
        isGoing = false;
    }

    private void run(){
        while (isGoing){
            Command cmd = null;
            if(doQueue.size() > 0){
                cmd = doQueue.remove();
            }

            if (cmd != null){
                if(cmd instanceof Undo) {
                    executeUndo();
                } else if(cmd instanceof Redo) {
                    executeRedo();
                } else {
                    if (cmd.execute(canvas)){
                        undoStack.add(cmd);
                    }
                }
            } else {
                try {
                    enqueEvent.waitOne(100);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private void executeUndo() {
        if(undoStack.size() > 0) {
            int last = undoStack.size()-1;
            Command cmd = undoStack.get(last);
            undoStack.remove(last);
            cmd.undo(canvas);
            redoStack.add(cmd);
        }

    }

    private void executeRedo() {
        if(redoStack.size() > 0) {
            int last = redoStack.size()-1;
            Command cmd = redoStack.get(last);
            redoStack.remove(last);
            cmd.redo(canvas);
            undoStack.add(cmd);
        }
    }

    public void undo() {
        enqueueCommand(new Undo());
    }

    public void redo() {
        enqueueCommand(new Redo());
    }

    public void enqueueCommand(Command cmd) {
        if (cmd != null) {
            doQueue.add(cmd);
            enqueEvent.set();
        }
    }

}
