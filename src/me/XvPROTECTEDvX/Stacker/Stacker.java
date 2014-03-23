package me.XvPROTECTEDvX.Stacker;

import java.util.ArrayList;

/**
 * Created by joshuabetz on 3/11/14.
 */
public class Stacker {

    private static Stacker stack = new Stacker();

    public static Stacker getManager(){
        return stack;
    }

    ArrayList<String> playing = new ArrayList<String>();

    public void addPlaying(String name){
        playing.add(name);
    }

    public void removePlaying(String name){
        playing.remove(name);
    }

    public boolean isPlaying(String name){
        return playing.contains(name);
    }
}
