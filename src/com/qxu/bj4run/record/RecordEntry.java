/**
 * Copyright (c) 2016,TravelSky. 
 * All Rights Reserved.
 * TravelSky CONFIDENTIAL
 * 
 * Project Name:bj4run
 * Package Name:com.qxu.bj4run.record
 * File Name:RecordEntry.java
 * Date:2016-6-21 上午11:27:31
 * 
 */
package com.qxu.bj4run.record;

import java.text.SimpleDateFormat;
import java.util.Date;

 /**
 * ClassName: RecordEntry <br/>
 * Description: TODO <br/>
 * Date: 2016-6-21 上午11:27:31 <br/>
 * <br/>
 * 
 * @author qxu(邮箱)
 * 
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */

public class RecordEntry {
    public String name;
    public String date;
    public String time;
    public String content;
    
    public RecordEntry() {
        
    }
    
    public RecordEntry(RecordEntry entry) {
        name = entry.name;
        date = entry.date;
        time = entry.time;
        content = entry.content;
    }
    
    @Override
    public String toString() {
        return name + "\t" + date + " " + time + "\t" + content;
    }
}
