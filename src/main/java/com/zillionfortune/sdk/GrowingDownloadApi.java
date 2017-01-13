package com.zillionfortune.sdk;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

/**
 * Created by king on 7/19/16.
 * 若是使用GrowingDownApi,则可以
 */
public class GrowingDownloadApi extends DownloadApi {
    private static final Logger logger = LoggerFactory.getLogger(GrowingDownloadApi.class);

    private final String baseStorePath;
    private final boolean uncompress;

    public GrowingDownloadApi() {
        this("growingApi");
    }

    public GrowingDownloadApi(String configName) {
        super(configName);
        baseStorePath = config.baseStorePath();
        uncompress = config.uncompress();
    }

    /**
     * 将获取的下载链接存储在指定的目录下,同时可以选择是否解压
     * @param links 下载链接数组
     * @throws IOException 
     */
    public void store(String[] links,String date) throws IOException {
//    	
//    	for (int i = 0; i < links.length; i++) {
//            String[] linkParts = splitLink(links[i]);
//            String filename = linkParts[2];
//            if (uncompress) {
//                filename = filename.substring(0, 10);
//            }
//
//            String storePath = baseStorePath + File.separator +
//                    linkParts[0] + File.separator + date;
//            File dir = new File(storePath);
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//
//            BufferedOutputStream bout = null;
//            BufferedInputStream bin = null;
//            try {
//                bout = new BufferedOutputStream(new FileOutputStream(storePath + File.separator + filename));
//
//                HttpResponse response = get(links[i], null, null, Charset.forName("utf-8"));
//                InputStream in = response.getEntity().getContent();
//                if (uncompress) {
//                    in = new GZIPInputStream(in);
//                }
//                bin = new BufferedInputStream(in);
//                byte[] chunk = new byte[4096];
//                int size = -1;
//                while ((size = bin.read(chunk)) > -1) {
//                    bout.write(chunk, 0, size);
//                    bout.flush();
//                }
//                bout.close();
//                bin.close();
//            } catch (IOException e) {
//                logger.error("Failed to store the download file: " + links[i]);
//            }
//            logger.info("Successfully download the file: " + storePath + File.separator + filename);
//        }
    	
        for (int i = 0; i < links.length; i++) {
            String[] linkParts = splitLink(links[i]);
            String filename = linkParts[2];
            if (uncompress) {
                filename = filename.substring(0, 10);
            }

            String storePath = baseStorePath + File.separator +
                    linkParts[0] + File.separator + date;
            File dir = new File(storePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            BufferedOutputStream bout = null;
            BufferedInputStream bin = null;
            FileWriter writer = new FileWriter(storePath + File.separator + filename+date);
            BufferedWriter bw = new BufferedWriter(writer);
            try {
                System.out.println(links[i]);
                HttpResponse response = get(links[i], null, null, Charset.forName("utf-8"));
                InputStream in = response.getEntity().getContent();
                if (uncompress) {
                    in = new GZIPInputStream(in);
                }
                BufferedReader br =new BufferedReader(new InputStreamReader(in));    
                String line;
                StringBuilder sb = new StringBuilder("");
                int n = 0 ;
                Long a = System.currentTimeMillis();
                System.out.println("start appending"+System.currentTimeMillis());
                while((line=br.readLine()) != null){
                	n++;
                	if(n!=1){//为了删除表头
                		sb.append(line+"\n");
                	}
                }
                System.out.println("end appending"+(System.currentTimeMillis()-a));
                bw.write(sb.toString());
                br.close();
            } catch (IOException e) {
                logger.error("Failed to store the download file: " + links[i]);
            }finally{
            	bw.flush();
                bw.close();
                writer.close();
            }
            logger.info("Successfully download the file: " + storePath + File.separator + filename);
        }
    }

    /**
     * extract download link type(visit, page, action, etc), date and filename(part-xxxxx.gz)
     * it is a hard coding function, maybe changed
     * @param link the insights download link
     * @return array with three elements: [type, date, filename]
     */
    public String[] splitLink(String link) {
        String path = link.split("\\?")[0];
        // 跳过https://growing-insights.s3.cn-north-1.amazonaws.com.cn/37bd_xxx
        int first = path.indexOf('_', 64);
        String info = path.substring(first + 1);

        String[] parts = info.split("/");
        if (parts.length != 2) {
            logger.error("wrong download link: " + link);
        }
        String filename = parts[1];
        int pos = parts[0].lastIndexOf("_");
        String tp = parts[0].substring(0, pos);
        String date = parts[0].substring(pos + 1);
        return new String[]{tp, date, filename};
    }

	@Override
	public void store(String[] links) {
		
	}

}
