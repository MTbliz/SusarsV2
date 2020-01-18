package com.susar.logging;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class LoggerSusar {
    private static final String FILE_NAME = "log.txt";


    private LoggerSusar(){}

    private static class SingletonHelper {
        private static final LoggerSusar INSTANCE = new LoggerSusar();
    }

    public static LoggerSusar getInstance(){
        return SingletonHelper.INSTANCE;
    }



    public void log(LogEnum level, String msg){
        try (FileWriter fileWriter = new FileWriter(FILE_NAME, true)) {
            fileWriter.append(LocalDate.now().toString() + ", " + LocalTime.now().toString() + ", " + level + ", " + msg + System.getProperty( "line.separator" ));

        //    PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(FILE_NAME),true));
        //    printWriter.append(LocalTime.now().toString() + ", " + level + ", " + msg + System.getProperty( "line.separator"));
        //    printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


