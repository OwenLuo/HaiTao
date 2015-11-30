package com.lvcaiye.haitao.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;



public class BaseApplication extends Application {
	public static List<String> mEmoticons = new ArrayList<String>();
	public static Map<String, Integer> mEmoticonsId = new HashMap<String, Integer>();
	public static List<String> mEmoticons_Zem = new ArrayList<String>();
	public static List<String> mEmoticons_Zemoji = new ArrayList<String>();
	public Map<String, SoftReference<Bitmap>> mPhotoThumbnailCache = new HashMap<String, SoftReference<Bitmap>>();
	public static final String PHOTO_THUMBNAIL_DIR = "map/photo/thumbnail/";


	public String mmac;
	public String mip;
	public String mimei;
	public String mversionName;
	public String mversionCode;
	public String mpackageNames;
	public String mshebeiname;
	public static String QUDAO="官方";
	@Override
	public void onCreate() {
		super.onCreate();
		JPushInterface.setDebugMode(false);
	    JPushInterface.init(this);
		
		   Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(this));
		   File dir = new File(BaseActivity.imgtemppath);
			if (!dir.exists())
				dir.mkdir();
		for (int i = 1; i < 64; i++) {
			String emoticonsName = "[zem" + i + "]";
			int emoticonsId = getResources().getIdentifier("zem" + i,
					"drawable", getPackageName());
			mEmoticons.add(emoticonsName);
			mEmoticons_Zem.add(emoticonsName);
			mEmoticonsId.put(emoticonsName, emoticonsId);
		}
		for (int i = 1; i < 59; i++) {
			String emoticonsName = "[zemoji" + i + "]";
			int emoticonsId = getResources().getIdentifier("zemoji_e" + i,
					"drawable", getPackageName());
			mEmoticons.add(emoticonsName);
			mEmoticons_Zemoji.add(emoticonsName);
			mEmoticonsId.put(emoticonsName, emoticonsId);
		}
	
		
		mmac=getLocalMacAddress();
		//mmac = mmac.replace(":","."); 
		mip=getLocalIpAddress();
		mip = mip.replace("%","."); 
		mip = mip.replace(":","."); 
		
		TelephonyManager tm = (TelephonyManager) this
				.getSystemService(TELEPHONY_SERVICE);
		mimei = tm.getDeviceId();// String imei
		mshebeiname = android.os.Build.MODEL;
		mshebeiname = mshebeiname.replace("\\s", "");
		mshebeiname = mshebeiname.replace(" ", "");
		
		try {  
		    PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);  
		    // 当前应用的版本名称  
		    mversionName = info.versionName;  
		    // 当前版本的版本号  
		    mversionCode = info.versionCode+"";  
		    // 当前版本的包名  
		    mpackageNames = info.packageName;  
		} catch (NameNotFoundException e) {  
		    // TODO Auto-generated catch block
		    e.printStackTrace();  
		}
		
	}
	
		public String getLocalMacAddress() {  
	        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);  
	        WifiInfo info = wifi.getConnectionInfo();  
	        return info.getMacAddress();  
	    }  
		
		public String getLocalIpAddress() {
			 //获取wifi服务  
	        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);  
	        //判断wifi是否开启  
	        if (!wifiManager.isWifiEnabled()) {  
	        	  // wifiManager.setWifiEnabled(true);  
	        	return "0.0.0.0";  
	        }  
	        WifiInfo wifiInfo = wifiManager.getConnectionInfo();       
	        int ipAddress = wifiInfo.getIpAddress();   
	        String ip = intToIp(ipAddress);   
	        return ip;
		}
		
		private String intToIp(int i) {       
	         
	          return (i & 0xFF ) + "." +       
	        ((i >> 8 ) & 0xFF) + "." +       
	        ((i >> 16 ) & 0xFF) + "." +       
	        ( i >> 24 & 0xFF) ;  
	     }   
		
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		Log.e("BaseApplication", "onLowMemory");
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		Log.e("BaseApplication", "onTerminate");
	}
	

	
	public Bitmap getPhotoThumbnail(String imageName) {
		if (mPhotoThumbnailCache.containsKey(imageName)) {
			Reference<Bitmap> reference = mPhotoThumbnailCache.get(imageName);
			if (reference.get() == null || reference.get().isRecycled()) {
				mPhotoThumbnailCache.remove(imageName);
			} else {
				return reference.get();
			}
		}
		InputStream is = null;
		Bitmap bitmap = null;
		try {
			is = getAssets().open(PHOTO_THUMBNAIL_DIR + imageName);
			bitmap = BitmapFactory.decodeStream(is);
			if (bitmap == null) {
				throw new FileNotFoundException(imageName + "is not find");
			}
			mPhotoThumbnailCache.put(imageName, new SoftReference<Bitmap>(
					bitmap));
			return bitmap;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (IOException e) {

			}
		}
	}
		
}
