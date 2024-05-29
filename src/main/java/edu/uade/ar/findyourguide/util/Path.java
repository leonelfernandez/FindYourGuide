package edu.uade.ar.findyourguide.util;

import java.io.File;

public class Path {

    public static String getPathOutModel(String name){
        String dir = "../FindYourGuide/";
        return  new File(dir+name+".json").getPath();
    }

}
