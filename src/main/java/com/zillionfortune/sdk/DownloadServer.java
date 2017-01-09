package com.zillionfortune.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by king on 7/20/16.
 * plan to write a server for auto-downloading files, but
 * it is too hard to control timezone and time offset problem based on jdk 1.6
 */
public class DownloadServer {
    private static final Logger logger = LoggerFactory.getLogger(DownloadServer.class);

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: DownloadServer <day | hour>");
            System.exit(-1);
        }

        DownloadApi api = new GrowingDownloadApi();
        long startTime = System.currentTimeMillis();
    }

}
