package applicationLayor.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Invoker {
    private Thread worker;
    private boolean going;

    private ConcurrentLinkedQueue<Command> doQueue = new ConcurrentLinkedQueue<Command>();
    private List undoStack = Collections.synchronizedList(new ArrayList());
    private List redoStack = Collections.synchronizedList(new ArrayList());
    private AutoResetEvent event = new AutoResetEvent(false);

    public void start(){
        going = true;
        worker = new Thread();
    }

    private void run(){
        while (going){
            Command cmd = null;
            cmd = doQueue.remove();
            if (cmd == null){
                if(cmd instanceof Undo){
                    enque(new Undo());
                } else if(cmd instanceof Redo){
                    enque(new Redo());
                } else{
                    if (cmd.execute()){
                        undoStack.add(cmd);
                    }
                }
            }
            else{
//                _enqueueOccurred.WaitOne(100);
            }
        }
    }

    public void enque(Command cmd){
        enqueueCommand(cmd);
    }

    public void enqueueCommand(Command cmd){
        if (cmd != null) {
            doQueue.add(cmd);
//            _enqueueOccurred.Set();
        }
    }

}
