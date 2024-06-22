package edu.uade.ar.findyourguide.audit;

import java.io.File;

public class Path {

    public static String getPathOutModel(String name){
        String dir = "../FindYourGuide/";
        return  new File(dir+name+".json").getPath();
    }

}
