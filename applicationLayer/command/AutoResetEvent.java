package applicationLayer.command;

/**
 * based on https://www.programcreek.com/java-api-examples/index.php?source_dir=PGPAuth_Android-master/PGPAuth/src/org/lf_net/pgpunlocker/AutoResetEvent.java
 */
public class AutoResetEvent {
    private volatile boolean open = false;
    private final Object target = new Object();

    public AutoResetEvent(boolean open) {
        this.open = open;
    }

    public void wait(Integer len) throws InterruptedException {
        synchronized (target) {
            while(!open) {
                target.wait(len);
            }
        }
    }

    public void set() {
        synchronized (target) {
            open = true;
            target.notify();
        }
    }

    public void reset(){
        open = false;
    }

}
