package com.adventofcode.day02;

public class GameSet {
    int red;
    int blue;

    public void setRed(String red) {
        this.red = Integer.parseInt(red);
    }

    public void setBlue(String blue) {
        this.blue = Integer.parseInt(blue);;
    }

    public void setGreen(String green) {
        this.green = Integer.parseInt(green);
    }

    @Override
    public String toString() {
        return "{" +
                "red=" + red +
                ", blue=" + blue +
                ", green=" + green +
                '}';
    }

    public int getRed() {
        return red;
    }

    public int getBlue() {
        return blue;
    }

    public int getGreen() {
        return green;
    }

    int green;
    public GameSet() {
        this.red=0;
        this.blue= 0;
        this.green= 0;
    }
}
