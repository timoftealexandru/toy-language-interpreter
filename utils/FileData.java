package utils;

import java.io.*;

public class FileData{
    private String fName;
    private BufferedReader br;

    public FileData(String name, BufferedReader b){
        this.fName=name;
        br=b;
    }
    //getters+setters

    public BufferedReader getReader(){
        return br;
    }
    public String getFileName(){
        return fName;
    }
    public String toString(){
        return fName;
    }
}