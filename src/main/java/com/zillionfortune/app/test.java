package com.zillionfortune.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class test {

	public static void main(String[] args) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//将毫秒级long值转换成日期格式
		GregorianCalendar gc = new GregorianCalendar(); 
		gc.setTimeInMillis(Long.valueOf("1484288212224"));
		String dateStr = dateformat.format(gc.getTime());
		System.out.println(dateStr);
		
//		System.out.println(System.currentTimeMillis());
		
//		 final ExecutorService pool = Executors
//			        .newFixedThreadPool(25,
//			            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("MSCK-GetPaths-%d").build());
//		 if (pool == null) {
//			 System.out.println("111111111111");
//		    } else {
//		    	 System.out.println("222222222222");
//		    }
//		 
	}
}
