package applicationLayer.command;

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

    public void start() {
        isGoing = true;
        worker = new Thread();
        worker.start();
    }

    public void stop() {
        isGoing = false;
    }

    private void run(){
        while (isGoing){
            Command cmd = null;
            cmd = doQueue.remove();
            if (cmd == null){
                if(cmd instanceof Undo) {
                    undo();
                } else if(cmd instanceof Redo) {
                    redo();
                } else {
                    if (cmd.execute()){
                        undoStack.add(cmd);
                    }
                }
            }
            else {
                try {
                    enqueEvent.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void executeUndo() {
        if(undoStack.size() > 0) {
            int last = undoStack.size()-1;
            Command cmd = undoStack.get(last);
            undoStack.remove(last);
            cmd.undo();
            redoStack.add(cmd);
        }

    }

    private void executeRedo() {
        if(redoStack.size() > 0) {
            int last = redoStack.size()-1;
            Command cmd = redoStack.get(last);
            redoStack.remove(last);
            cmd.redo();
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
