package com.ia.planda;

public class Model {
    //holds data to be used and transferred between classes
    private static int initFocusTime;
    //also maybe save the loaders/controller classes or something so maybe the data can be carried over?? but then that's not very long term so prob not

    public Model() {
    }

    public int getInitFocusTime() {
        return initFocusTime;
    }

    public void setInitFocusTime(int initFocusTime) {
        Model.initFocusTime = initFocusTime;
    }
}
