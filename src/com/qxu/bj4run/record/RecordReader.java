/**
 * Copyright (c) 2016,TravelSky. 
 * All Rights Reserved.
 * TravelSky CONFIDENTIAL
 * 
 * Project Name:bj4run
 * Package Name:com.qxu.bj4run.record
 * File Name:RecordReader.java
 * Date:2016-6-21 上午11:19:50
 * 
 */
package com.qxu.bj4run.record;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

 /**
 * ClassName: RecordReader <br/>
 * Description: TODO <br/>
 * Date: 2016-6-21 上午11:19:50 <br/>
 * <br/>
 * 
 * @author qxu(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */

public class RecordReader {
    String keyword = "打卡";
    ArrayList<RecordEntry> recordList = new ArrayList<RecordEntry>();
    Date dd = new Date();
    
    public RecordReader() {
        
    }
    
    public int load(String pFileName) {
        return load(new File(pFileName));
    }
    
    public int load(File pFile) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(pFile));
            RecordEntry entry = new RecordEntry();

            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                if (matchesDateLine(line)) {
                    String[] date = line.split(" ");
                    entry.date = date[1];
                }
                else if (matchesNameTimeLine(line)) {
                    String[] nameTime = line.split(" ");
                    int index = line.lastIndexOf(' ');
                    entry.name = line.substring(0, index);
                    entry.time = line.substring(index+1);
                }
                else if (matchesKeywordLine(line)) {
                    entry.content = line;
                    recordList.add(new RecordEntry(entry));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.close();
        return recordList.size();
    }
    
    private boolean matchesDateLine(String line) {
        return line.matches("^————— (\\d){4}-(\\d){2}-(\\d){2} —————$");
    }

    private boolean matchesNameTimeLine(String line) {
        return line.matches(".* (\\d){2}:(\\d){2}$");
    }

    private boolean matchesKeywordLine(String line) {
        return line.matches(".*" + keyword + ".*");
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (RecordEntry entry : recordList) {
            buffer.append(entry.toString() + "\r\n");
        }
        return buffer.toString();
    }
    
    public void toFile(String pFileName) {
        toFile(new File(pFileName));
    }
    
    public void toFile(File pFile) {
        PrintWriter outFile = null;
        try {
            outFile = new PrintWriter(pFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        for (RecordEntry entry : recordList) {
            outFile.println(entry.toString());
        }
        
        outFile.close();
    }
    
    public static void main (String[] args) {
        System.out.println("Record Reader Start...");
        RecordReader reader = new RecordReader();
        reader.load("resource/record_000.txt");
        System.out.println(reader.toString());
        reader.toFile("resource/result_000.txt");
        System.out.println("Record Reader End.");
    }
}
