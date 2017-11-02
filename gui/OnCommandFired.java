package gui;


import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class OnCommandFired extends Event {
    public static final EventType<OnCommandFired> COMMAND_FIRED= new EventType<>(Event.ANY, "COMMAND_FIRED");

    public OnCommandFired(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public OnCommandFired(Object source, EventTarget target, EventType<? extends Event> eventType) {
        super(source, target, eventType);
    }

    public OnCommandFired(Object source, EventTarget target) {
        super(source, target, COMMAND_FIRED);
    }

    public OnCommandFired() {
        super(COMMAND_FIRED);
    }
}
