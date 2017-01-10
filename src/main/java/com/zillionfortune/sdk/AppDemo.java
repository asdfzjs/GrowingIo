package com.zillionfortune.sdk;


/**
 * Hello world!
 * 数据是从20170105天开始的
 */
public class AppDemo
{
    public static void main( String[] args ) throws Exception
    {
        GrowingDownloadApi api = new GrowingDownloadApi();
        api.download("2017010521");
    }
}
