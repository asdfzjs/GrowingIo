package com.zillionfortune.sdk;


/**
 * Hello world!
 * 数据是从20170105天开始的
 */
public class AppDemo
{
    public static void main( String[] args ) throws Exception
    {
    	    if(args.length==0){
    	    	GrowingDownloadApi api = new GrowingDownloadApi();
        		api.download("2017010522");
    	    }else{
    	    	GrowingDownloadApi api = new GrowingDownloadApi();
        		api.download(args[0]);
    	    }
    		
    }
}
