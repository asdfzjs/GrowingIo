package com.zillionfortune.app;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetData {
	private static String secret = "2dd5d6e26f544d7ca343536b53fca231";
	private static String pub = "b702c3c4916b4104b7c240d31695a825";
	private static String project = "5RpYxlPN";
	private static String ai = "988386d1bd106017";
	private static Long tm = System.currentTimeMillis();
	private static String base = "https://www.growingio.com/insights/988386d1bd106017/2017010520.json";
	
	
	public static void main(String[] args) throws Exception {
		HttpGet httpget = new HttpGet(base);
		httpget.setHeader("X-Client-Id",pub);
		httpget.setHeader("Content-Type","application/x-www-form-urlencoded");
		httpget.setHeader("Authorization","EhASqLnKdWNkbcWLW2btERfwIxg3JrFW0HSIWApG83IylgApNWDUe4Kf1eTZVq42");
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpResponse resp = null;
		resp = httpclient.execute(httpget);
		HttpEntity httpentity = resp.getEntity();
		System.out.println(EntityUtils.toString(httpentity));
	}

}
