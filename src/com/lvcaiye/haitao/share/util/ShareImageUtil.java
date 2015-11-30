package com.lvcaiye.haitao.share.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lvcaiye.haitao.R;

public class ShareImageUtil {

	public static Bitmap getNetWorkBitmap(Context mContext, String urlString) {
		URL imgUrl;
		Bitmap bitmap;
		try {
			String urlSpliceString = urlString.contains("http://www.edealdeal.com") ? urlString
					: "http://www.edealdeal.com" + urlString;
			imgUrl = new URL(urlSpliceString);
			// 使用HttpURLConnection打开连接
			HttpURLConnection urlConn = (HttpURLConnection) imgUrl.openConnection();
			urlConn.setDoInput(true);
			urlConn.connect();
			// 将得到的数据转化成InputStream
			InputStream is = urlConn.getInputStream();
			// 将InputStream转换成Bitmap
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (Exception e) {
			bitmap = BitmapFactory.decodeResource(mContext.getResources(),
					R.drawable.app_desktop);
		}
		return bitmap;
	}

}
