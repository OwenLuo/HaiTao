package com.lvcaiye.haitao.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

import com.lvcaiye.haitao.db.PreferenceConstants;
import com.lvcaiye.haitao.db.PreferenceUtils;
import com.lvcaiye.haitao.http.NetWorkUtils;
import com.lvcaiye.haitao.tools.FlippingLoadingDialog;
import com.lvcaiye.haitao.tools.HandyTextView;
import com.lvcaiye.haitao.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;



public abstract class BaseActivity extends Activity {
	protected BaseApplication mApplication;
	protected NetWorkUtils mNetWorkUtils;
	public static FlippingLoadingDialog mLoadingDialog;
	/**
	 * 屏幕的宽度、高度、密度
	 */
	protected int mScreenWidth;
	protected int mScreenHeight;
	//protected static String imgtemppath="/sdcard/hst/img/temp/";//sd路径
	protected static String imgtemppath= Environment.getExternalStorageDirectory()
	.getAbsolutePath() + File.separator + "hst"+ File.separator;
	protected String muid;
	protected String muname;

	protected float mDensity;
	protected List<AsyncTask<Void, Void, Integer>> mAsyncTasks = new ArrayList<AsyncTask<Void, Void, Integer>>();

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
	//	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //设置为横屏
		mApplication = (BaseApplication) getApplication();
		mNetWorkUtils = new NetWorkUtils(this);
	//	mLoadingDialog = new FlippingLoadingDialog(this, "请求提交中");
		muid = PreferenceUtils.getPrefString(this,PreferenceConstants.UID, "");
		muname = PreferenceUtils.getPrefString(this,PreferenceConstants.REALNAME, "");
	}

	@Override
	protected void onDestroy() {
		clearAsyncTask();
		super.onDestroy();
	}
	protected void putAsyncTask(AsyncTask<Void, Void, Integer> asyncTask) {
		mAsyncTasks.add(asyncTask.execute());
	}

	protected void clearAsyncTask() {
		Iterator<AsyncTask<Void, Void, Integer>> iterator = mAsyncTasks
				.iterator();
		while (iterator.hasNext()) {
			AsyncTask<Void, Void, Integer> asyncTask = iterator.next();
			if (asyncTask != null && !asyncTask.isCancelled()) {
				asyncTask.cancel(true);
			}
		}
		mAsyncTasks.clear();
	}
	
	/** 初始化视图 **/
	protected abstract void initViews();

	/** 初始化事件 **/
	protected abstract void initEvents();

	protected void showLoadingDialog(String text) {
		mLoadingDialog = new FlippingLoadingDialog(this, "请求提交中");
		if (text != null) {
			mLoadingDialog.setText(text);
		}
		mLoadingDialog.show();
	}

	protected void dismissLoadingDialog() {
		if (mLoadingDialog.isShowing()) {
			mLoadingDialog.dismiss();
		}
	}
	/** 短暂显示Toast提示(来自res) **/
	protected void showShortToast(int resId) {
		Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
	}

	/** 短暂显示Toast提示(来自String) **/
	protected void showShortToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	/** 长时间显示Toast提示(来自res) **/
	protected void showLongToast(int resId) {
		Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
	}

	/** 长时间显示Toast提示(来自String) **/
	protected void showLongToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	/** 显示自定义Toast提示(来自res) **/
	protected void showCustomToast(int resId) {
		View toastRoot = LayoutInflater.from(BaseActivity.this).inflate(
				R.layout.common_toast, null);
		((HandyTextView) toastRoot.findViewById(R.id.toast_text))
				.setText(getString(resId));
		Toast toast = new Toast(BaseActivity.this);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(toastRoot);
		toast.show();
	}

	/** 显示自定义Toast提示(来自String) **/
	protected void showCustomToast(String text) {
		View toastRoot = LayoutInflater.from(BaseActivity.this).inflate(
				R.layout.common_toast, null);
		((HandyTextView) toastRoot.findViewById(R.id.toast_text)).setText(text);
		Toast toast = new Toast(BaseActivity.this);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(toastRoot);
		toast.show();
	}

	/** Debug输出Log日志 **/
	protected void showLogDebug(String tag, String msg) {
		Log.d(tag, msg);
	}

	/** Error输出Log日志 **/
	protected void showLogError(String tag, String msg) {
		Log.e(tag, msg);
	}
	protected boolean isNull(EditText editText) {
		String text = editText.getText().toString().trim();
		if (text != null && text.length() > 0) {
			return false;
		}
		return true;
	}
	
	
	protected void showAlert(String tit, String msg) {
		 final AlertDialog dlg = new AlertDialog.Builder(this).create();
		 dlg.show();
		 Window window = dlg.getWindow();
		        // *** 主要就是在这里实现这种效果的.
		        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
		 window.setContentView(R.layout.common_showdialog);
		        // 为确认按钮添加事件,执行退出应用操作
		((TextView) window.findViewById(R.id.tv_tit)).setText(tit);
		((TextView) window.findViewById(R.id.tv_msg)).setText(msg);
		        // 关闭alert对话框架
		 Button cancel = (Button) window.findViewById(R.id.exitBtn0);
		        cancel.setOnClickListener(new View.OnClickListener() {
		   public void onClick(View v) {
		    dlg.cancel();
		  }
		   });
	}
	
	
	protected void showAlert(String tit,int resId) {
		 final AlertDialog dlg = new AlertDialog.Builder(this).create();
		 dlg.show();
		 Window window = dlg.getWindow();
		        // *** 主要就是在这里实现这种效果的.
		        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
		 window.setContentView(R.layout.common_showdialog);
		        // 为确认按钮添加事件,执行退出应用操作
		((TextView) window.findViewById(R.id.tv_tit)).setText(tit);
		((TextView) window.findViewById(R.id.tv_msg)).setText(getString(resId));
		
		        // 关闭alert对话框架
		 Button cancel = (Button) window.findViewById(R.id.exitBtn0);
		        cancel.setOnClickListener(new View.OnClickListener() {
		   public void onClick(View v) {
		    dlg.cancel();
		  }
		   });
	}
	
	
	protected void showAlertfinish(String tit, String msg) {
		 final AlertDialog dlg = new AlertDialog.Builder(this).create();
		 dlg.show();
		 Window window = dlg.getWindow();
		 window.setContentView(R.layout.common_showdialog);
		((TextView) window.findViewById(R.id.tv_tit)).setText(tit);
		((TextView) window.findViewById(R.id.tv_msg)).setText(msg);
		 Button cancel = (Button) window.findViewById(R.id.exitBtn0);
		        cancel.setOnClickListener(new View.OnClickListener() {
		   public void onClick(View v) {
		    dlg.cancel();
		    finish();
		  }
		   });
	}
	
	protected void showAlertfinish(String tit,int resId) {
		 final AlertDialog dlg = new AlertDialog.Builder(this).create();
		 dlg.show();
		 Window window = dlg.getWindow();
		 window.setContentView(R.layout.common_showdialog);
		((TextView) window.findViewById(R.id.tv_tit)).setText(tit);
		((TextView) window.findViewById(R.id.tv_msg)).setText(getString(resId));
		 Button cancel = (Button) window.findViewById(R.id.exitBtn0);
		        cancel.setOnClickListener(new View.OnClickListener() {
		   public void onClick(View v) {
		    dlg.cancel();
		    finish();
		  }
		   });
	}
	
	
	  public void showAlertww(String tit, String msg) {
			 final AlertDialog dlg = new AlertDialog.Builder(this).create();
			 dlg.show();
			 Window window = dlg.getWindow();
			        // *** 主要就是在这里实现这种效果的.
			        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
			// View toastRoot = LayoutInflater.from(BaseActivity.this).inflate(R.layout.common_showdialog, null);
			 window.setContentView(R.layout.common_showdialog);
			        // 为确认按钮添加事件,执行退出应用操作
			// ((TextView) toastRoot.findViewById(R.id.toast_text)).setText(tit);
			((TextView) window.findViewById(R.id.tv_tit)).setText(tit);
			((TextView) window.findViewById(R.id.tv_msg)).setText(msg);
			
			        // 关闭alert对话框架
			 Button cancel = (Button) window.findViewById(R.id.exitBtn0);
			        cancel.setOnClickListener(new View.OnClickListener() {
			   public void onClick(View v) {
			    dlg.cancel();
			    finish();
			  //  dlg.dismiss();
			  }
			   });
		}
	
	
	

	
	
	/** 通过Class跳转界面 *
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}*/

	/** 含有Bundle通过Class跳转界面 *
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}*/

	/** 通过Action跳转界面 *
	protected void startActivity(String action) {
		startActivity(action, null);
	}*/

	/** 含有Bundle通过Action跳转界面 *
	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}*/

	/** 含有标题和内容的对话框 *
	protected AlertDialog showAlertDialog(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).show();
		return alertDialog;
	}*/

	//* 含有标题、内容、两个按钮的对话框 *
	protected AlertDialog showAlertDialog(String title, String message,
			String positiveText,
			DialogInterface.OnClickListener onPositiveClickListener,
			String negativeText,
			DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}

	/** 含有标题、内容、图标、两个按钮的对话框 *
	protected AlertDialog showAlertDialog(String title, String message,
			int icon, String positiveText,
			DialogInterface.OnClickListener onPositiveClickListener,
			String negativeText,
			DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).setIcon(icon)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}*/

	/** 默认退出 *
	protected void defaultFinish() {
		super.finish();
	}*/
	
	
	@Override
	public void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}
}
