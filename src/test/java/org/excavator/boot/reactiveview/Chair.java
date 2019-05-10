package org.excavator.boot.reactiveview;

public class Chair {

    public int calcHeight(Size size){
        int height = switch (size){
            case S -> 10;
            case L -> 20;
            case M -> 25;
        };

        return height;
    }
}
