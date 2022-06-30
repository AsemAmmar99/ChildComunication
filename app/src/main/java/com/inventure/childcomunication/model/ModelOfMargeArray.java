package com.inventure.childcomunication.model;

public class ModelOfMargeArray {


    boolean check ;
    String imgOut ;
    int imgIn ;
    String name ;
    int soundIn ;
    String soundOut ;

    public ModelOfMargeArray(boolean check, String imgOut, int imgIn, String name, int soundIn, String soundOut) {
        this.check = check;
        this.imgOut = imgOut;
        this.imgIn = imgIn;
        this.name = name;
        this.soundIn = soundIn;
        this.soundOut = soundOut;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getImgOut() {
        return imgOut;
    }

    public void setImgOut(String imgOut) {
        this.imgOut = imgOut;
    }

    public int getImgIn() {
        return imgIn;
    }

    public void setImgIn(int imgIn) {
        this.imgIn = imgIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSoundIn() {
        return soundIn;
    }

    public void setSoundIn(int soundIn) {
        this.soundIn = soundIn;
    }

    public String getSoundOut() {
        return soundOut;
    }

    public void setSoundOut(String soundOut) {
        this.soundOut = soundOut;
    }
}
