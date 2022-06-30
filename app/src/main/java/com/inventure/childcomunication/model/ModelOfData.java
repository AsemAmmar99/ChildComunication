package com.inventure.childcomunication.model;

import java.util.ArrayList;
import java.util.List;

public class ModelOfData {

  String names [] ;
  int [] icon  ;
  int [] voice ;

    public ModelOfData(String[] names, int[] icon, int[] voice) {
        this.names = names;
        this.icon = icon;
        this.voice = voice;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public int[] getIcon() {
        return icon;
    }

    public void setIcon(int[] icon) {
        this.icon = icon;
    }

    public int[] getVoice() {
        return voice;
    }

    public void setVoice(int[] voice) {
        this.voice = voice;
    }
}
