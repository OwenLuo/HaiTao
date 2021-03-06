package com.lvcaiye.haitao.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

/**
 * @fileName NetWorkUtils.java
 * @package com.immomo.momo.android.util
 * @description 网络工具类
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public class NetWorkUtils {
	private Context mContext;
	public State wifiState = null;
	public State mobileState = null;

	public NetWorkUtils(Context context) {
		mContext = context;
	}

	public enum NetWorkState {
		WIFI, MOBILE, NONE;

	}

	
	
	  /**
     * 检查当前是否连接
     * 
     * @param context
     * @return true表示当前网络处于连接状态，否则返回false
     */
	public NetWorkState getConnectState() {
        ConnectivityManager cm = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
        	return NetWorkState.MOBILE;
        }
        return NetWorkState.NONE;
    }
 
	/**
	 * 获取当前的网络状态
	 * 
	 * @return
	 */
	public NetWorkState getConnectState1() {
		ConnectivityManager manager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		manager.getActiveNetworkInfo();
		wifiState = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		mobileState = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		if (wifiState != null && mobileState != null
				&& State.CONNECTED != wifiState
				&& State.CONNECTED == mobileState) {
			return NetWorkState.MOBILE;
		} else if (wifiState != null && mobileState != null
				&& State.CONNECTED != wifiState
				&& State.CONNECTED != mobileState) {
			return NetWorkState.NONE;
		} else if (wifiState != null && State.CONNECTED == wifiState) {
			return NetWorkState.WIFI;
		}
		return NetWorkState.NONE;
	}

}
