package com.lvcaiye.haitao.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



public class HttpUtil {

	public static final String BASE_URL = "http://app.seaares.com/api/android/";  //通讯部分网址
	public static final String BASE_URL2 = "http://app.seaares.com/";   //上传部分网址
	public static HttpGet getHttpGet(String url) {
		HttpGet request = new HttpGet(url);
		return request;
	}

	public static HttpPost getHttpPost(String url) {
		HttpPost request = new HttpPost(url);
		return request;
	}

	public static HttpResponse getHttpResponse(HttpGet request)
			throws ClientProtocolException, IOException {
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}

	public static HttpResponse getHttpResponse(HttpPost request)
			throws ClientProtocolException, IOException {
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}

	public static String queryStringForPost(String url) {
		HttpPost request = HttpUtil.getHttpPost(url);
		String result = null;
		try {
			HttpResponse response = HttpUtil.getHttpResponse(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		}
		return null;
	}

	public static String queryStringForPost(HttpPost request) {
		String result = null;
		try {
			HttpResponse response = HttpUtil.getHttpResponse(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		}
		return null;
	}

	public static String queryStringForGet(String url) {
		HttpGet request = HttpUtil.getHttpGet(url);
		String result = null;
		try {
			HttpResponse response = HttpUtil.getHttpResponse(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "网络异常！";
			return result;
		}
		return null;
	}
	 public static InputStream GetInputStreamFromURL(String urlstr){
	    	HttpURLConnection connection;
	    	URL url;
	    	InputStream stream=null;
	    	try{
	    		url=new URL(urlstr);
	    		connection =(HttpURLConnection)url.openConnection();
	    		connection.connect();
	    		stream = connection.getInputStream();
	    	}catch(MalformedURLException e){
	    		e.printStackTrace();
	    	}catch(IOException e1){
	    		e1.printStackTrace();
	    	}
	    	return stream;
	    }
}
