package com.lvcaiye.haitao.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.jpush.android.api.JPushInterface;

import com.lvcaiye.haitao.R;
import com.lvcaiye.haitao.base.BaseActivity;
import com.lvcaiye.haitao.http.NetWorkUtils.NetWorkState;
import com.lvcaiye.haitao.share.ShareContorller;
import com.lvcaiye.haitao.share.core.ShareParams;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

public class firstActivity extends BaseActivity {
	private WebView webview; // 设置浏览器
	private ValueCallback<Uri> mUploadMessage;
	private final static int FILECHOOSER_RESULTCODE = 1;

	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tianqi);
		JPushInterface.resumePush(this);
		checkUpdate();
		webview = (WebView) findViewById(R.id.webview);

		if (mNetWorkUtils.getConnectState() == NetWorkState.NONE) {
			showAlert("查询失败", "当前网络不可用,请检查");
		} else {
			showLoadingDialog("正在加载,请稍后...");
			// 设置WebView属性，能够执行Javascript脚本
			// webview.getSettings().setJavaScriptEnabled(true);
			// webview.addJavascriptInterface(new ProxyBridge(), "proxyBridge");

			// 重新设置
			WebSettings s = webview.getSettings();
			s.setBuiltInZoomControls(true);
			s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
			s.setUseWideViewPort(true);
			s.setLoadWithOverviewMode(true);
			s.setSavePassword(true);
			s.setSaveFormData(true);
			s.setJavaScriptEnabled(true);
			// enable navigator.geolocation
			s.setGeolocationEnabled(true);
			s.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
			// enable Web Storage: localStorage, sessionStorage
			s.setDomStorageEnabled(true);

			webview.requestFocus();
			webview.setScrollBarStyle(0);

			// 加载需要显示的网页
			webview.loadUrl("http://m.edealdeal.com");
			// 设置Web视图
			webview.setWebViewClient(new HelloWebViewClient());
			// 设置与js交互分享方法
			webview.addJavascriptInterface(this, "share");

			webview.setWebChromeClient(new MyWebChromeClient());

		}
	}

	class ProxyBridge {
		Handler handler = new Handler();

		public void getDateTime() {
			handler.post(new Runnable() {

				@Override
				public void run() {
					System.out.println("-------------->>>>");
				}
			});
		}
	}

	// Web视图
	private class HelloWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			dismissLoadingDialog();
		}
	}

	@JavascriptInterface
	public void qqShare(final String shareTitle, final String shareContent,
			final String shareImage, final String shareUrl) {

		ShareParams params = new ShareParams();
		params.setTitle(shareTitle);
		params.setContent(shareContent);
		params.setImageUrl(shareImage);
		params.setShareUrl(shareUrl);
		ShareContorller.shareQq(firstActivity.this, params, null);

		webview.loadUrl("javascript:closeShare()");

	}

	@JavascriptInterface
	public void wxShare(final String shareTitle, final String shareContent,
			final String shareImage, final String shareUrl) {

		ShareParams params = new ShareParams();
		params.setTitle(shareTitle);
		params.setContent(shareContent);
		params.setImageUrl(shareImage);
		params.setShareUrl(shareUrl);
		ShareContorller.shareWx(firstActivity.this, params);

		webview.loadUrl("javascript:closeShare()");

	}

	// 选择系统文件上传
	private class MyWebChromeClient extends WebChromeClient {
		// 关键代码，以下函数是没有API文档的，所以在Eclipse中会报错，如果添加了@Override关键字在这里的话。

		@SuppressWarnings("unused")
		public void openFileChooser(ValueCallback<Uri> uploadMsg) {

			mUploadMessage = uploadMsg;
			Intent i = new Intent(Intent.ACTION_GET_CONTENT);
			i.addCategory(Intent.CATEGORY_OPENABLE);
			i.setType("image/*");
			startActivityForResult(Intent.createChooser(i, "File Chooser"),
					FILECHOOSER_RESULTCODE);

		}

		@SuppressWarnings("unused")
		public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
			mUploadMessage = uploadMsg;
			Intent i = new Intent(Intent.ACTION_GET_CONTENT);
			i.addCategory(Intent.CATEGORY_OPENABLE);
			i.setType("*/*");
			startActivityForResult(Intent.createChooser(i, "File Browser"),
					FILECHOOSER_RESULTCODE);
		}

		@SuppressWarnings("unused")
		public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType,
				String capture) {
			mUploadMessage = uploadMsg;
			Intent i = new Intent(Intent.ACTION_GET_CONTENT);
			i.addCategory(Intent.CATEGORY_OPENABLE);
			i.setType("image/*");
			startActivityForResult(Intent.createChooser(i, "File Chooser"),
					FILECHOOSER_RESULTCODE);

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == FILECHOOSER_RESULTCODE) {
			if (null == mUploadMessage)
				return;
			Uri result = intent == null || resultCode != RESULT_OK ? null : intent
					.getData();
			mUploadMessage.onReceiveValue(result);
			mUploadMessage = null;
		}
	}

	public void btn_back(View v) { // 标题栏 返回按钮

		this.finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (webview.canGoBack()) {
				webview.goBack();// 返回上一页面
				return true;
			} else {
				System.exit(0);// 退出程序
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub

	}

	public void checkUpdate() {
		UmengUpdateAgent.setDefault();
		UmengUpdateAgent.setUpdateCheckConfig(false);
		UmengUpdateAgent.forceUpdate(this);
		UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {

			@Override
			public void onUpdateReturned(int updateStatus, final UpdateResponse response) {
				switch (updateStatus) {
				// 如果无更新
				case UpdateStatus.No:
					// ToastUtil.showToastShort("当前已是最新版本");
					break;
				// 如果有更新
				case UpdateStatus.Yes:
					UmengUpdateAgent.showUpdateDialog(firstActivity.this, response);
					break;
				// 如果是非wifi
				case UpdateStatus.NoneWifi:
					// ToastUtil.showToastShort("2/3/4G网络");
					break;
				case UpdateStatus.Timeout:
					// ToastUtil.showToastShort("网络异常，请稍后再试!");
					break;
				}
			}
		});
	}

}
