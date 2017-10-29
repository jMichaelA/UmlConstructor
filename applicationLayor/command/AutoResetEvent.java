package applicationLayor.command;

/**
 * based on https://www.programcreek.com/java-api-examples/index.php?source_dir=PGPAuth_Android-master/PGPAuth/src/org/lf_net/pgpunlocker/AutoResetEvent.java
 */
public class AutoResetEvent {
    private volatile boolean open = false;

    public AutoResetEvent(boolean open){
        this.open = open;
    }
}
