package com.qbtiempo.qbtiempo.qbtiempo.MultiColumnasListview;

/**
 * Created by Programador on 22/04/2015.
 */
public class Model{
    String name;
    int value; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */

    public Model(String name, int value){
        this.name = name;
        this.value = value;
    }
    public String getName(){
        return this.name;
    }
    public int getValue(){
        return this.value;
    }

}