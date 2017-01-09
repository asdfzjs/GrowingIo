package com.zillionfortune.sdk;


/**
 * Hello world!
 *
 *
 */
public class AppDemo
{
    public static void main( String[] args ) throws Exception
    {
        GrowingDownloadApi api = new GrowingDownloadApi();
        api.download("2017010521");
    }
}
