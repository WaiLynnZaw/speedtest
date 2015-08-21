package com.wlz.speedtest;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by WaiLynnZaw on 8/16/15.
 */
public class CSVWriter {
        Context ctx;
        public CSVWriter(Context ctx){
            this.ctx = ctx;
        }

        private static final String COMMA_DELIMITER = ",";
        private static final String NEW_LINE_SEPARATOR = "\n";

        private static final String FILE_HEADER = "hour,date,upload_speed,download_speed,ping";

        public void writeCsvFile() {
            //Create new students objects
            BenchMark benchMark = new BenchMark(ctx);
            FileWriter fileWriter = null;
            File sdCardFile = new File(Environment.getExternalStorageDirectory() + "/speedtest.csv");
            ArrayList<BenchMarkObject> benchMarkObjects = new ArrayList<>();
            benchMarkObjects.addAll(benchMark.getAllResults());
            try {
                fileWriter = new FileWriter(sdCardFile,true);

                //Write the CSV file header
                fileWriter.append(FILE_HEADER.toString());

                //Add a new line separator after the header
                fileWriter.append(NEW_LINE_SEPARATOR);

                //Write a new student object list to the CSV file
                for (BenchMarkObject benchMarkObj : benchMarkObjects) {
                    fileWriter.append(String.valueOf(benchMarkObj.getHour()));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(benchMarkObj.getDate());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(benchMarkObj.getUpload_speed()));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(benchMarkObj.getDownload_speed()));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(benchMarkObj.getPing()));
                    fileWriter.append(NEW_LINE_SEPARATOR);
                }
                Toast.makeText(ctx,"CSV file was created successfully !!!",Toast.LENGTH_LONG).show();
                System.out.println("CSV file was created successfully !!!");

            } catch (Exception e) {
                System.out.println("Error in CsvFileWriter !!!");
                e.printStackTrace();
            } finally {

                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error while flushing/closing fileWriter !!!");
                    e.printStackTrace();
                }

            }
        }
    }