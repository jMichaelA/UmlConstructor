package gui;

import java.util.HashMap;

public class ComponentFactory {
    private static ComponentFactory ourInstance = new ComponentFactory();
    private static final HashMap<String, Component> component = new HashMap<String, Component>();

    public static ComponentFactory getInstance() {
        return ourInstance;
    }

    private ComponentFactory() {
        System.out.println("gasp");
    }

    public static Component createComponent(String type){

        Component component = new Table(0,0, "table");

        return component;
    }
}
