package gui;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public interface Component {
    String getType();
    AtomicInteger getComponentId();
    void setComponentId(AtomicInteger id);
    Double getX();
    Double getY();
}
