package com.zillionfortune.app;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.growingio.growingapi.GrowingDownloadApi;


//AI：988386d1bd106017
//公钥：b702c3c4916b4104b7c240d31695a825
//私钥：2dd5d6e26f544d7ca343536b53fca231
//项目ID：988386d1bd106017
public class main {
	private static String secret = "2dd5d6e26f544d7ca343536b53fca231";
	private static String pub = "b702c3c4916b4104b7c240d31695a825";
	private static String project = "5RpYxlPN";
	private static String ai = "988386d1bd106017";
	private static Long tm = System.currentTimeMillis();
	private static String base = "https://www.growingio.com/auth/token";
	
	
	public static void main(String[] args) throws Exception {
		String auth = authToken(secret, project, ai, tm);
		System.out.println(auth);
		HttpPost httppost = new HttpPost(base);
		httppost.setHeader("X-Client-Id",pub);
		httppost.setHeader("Content-Type","application/x-www-form-urlencoded");
		
		List<NameValuePair> params= new ArrayList<>();
		params.add(new BasicNameValuePair("project",project));
		params.add(new BasicNameValuePair("ai",ai));
		params.add(new BasicNameValuePair("tm",""+tm));
		params.add(new BasicNameValuePair("auth",auth));
		
		UrlEncodedFormEntity entity  = new UrlEncodedFormEntity(params,Consts.UTF_8);
		httppost.setEntity(entity);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpResponse resp = null;
		resp = httpclient.execute(httppost);
		HttpEntity httpentity = resp.getEntity();
		System.out.println(EntityUtils.toString(httpentity));
	}
	
	public static String authToken(String secret, String project, String ai, Long tm) throws Exception {
		 String message = "POST\n/auth/token\nproject="+project+"&ai="+ai+"&tm="+tm;
		 Mac hmac = Mac.getInstance("HmacSHA256");
		 hmac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
		 byte[] signature = hmac.doFinal(message.getBytes("UTF-8"));
		 return Hex.encodeHexString(signature);
		}
}
