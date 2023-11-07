package org.example;

public class Poketmon {
    private String name;
    private PoketmonType type;

    public Poketmon(String name, PoketmonType type) {
        this.name = name;
        this.type = type;
        System.out.println("내이름은 " + name + "이야.");
    }

    public String getName() {
        return name;
    }

    public PoketmonType getType() {
        return type;
    }
}
