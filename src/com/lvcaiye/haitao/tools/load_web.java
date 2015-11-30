package com.lvcaiye.haitao.tools;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lvcaiye.haitao.base.BaseActivity;
import com.lvcaiye.haitao.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class load_web extends BaseActivity {  
    private WebView webview;  
    private String help_id;
    private FlippingLoadingDialog dialog;
    @Override 
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_webview);  
        String GET_PHOTO = getIntent().getStringExtra("IMAGE");
        Intent intent = this.getIntent(); 

	    
        help_id=(String)intent.getSerializableExtra("HELP_ID");
        webview = (WebView) findViewById(R.id.webview);  
        dialog = new FlippingLoadingDialog(load_web.this, "正在加载,请稍后...");
		dialog.show();
        //设置WebView属性，能够执行Javascript脚本  
        webview.getSettings().setJavaScriptEnabled(true);  
        //加载需要显示的网页  
        webview.loadUrl(GET_PHOTO);  
        Log.i("lvcaiye", "webHELP_ID=>"+help_id);
        //设置Web视图  
        webview.setWebViewClient(new HelloWebViewClient ());  
    }  
      

      
    //Web视图  
    private class HelloWebViewClient extends WebViewClient {  
        @Override 
        public boolean shouldOverrideUrlLoading(WebView view, String url) {  
            view.loadUrl(url);  
            return true;  
        }  
        @Override 
        public void onPageFinished(WebView view,String url) 
        { 
            dialog.dismiss(); 
        } 
    }  
    public void btn_back(View v) {     //标题栏 返回按钮
    	
      	this.finish();
      } 



@Override
protected void initViews() {
	// TODO Auto-generated method stub
	
}

@Override
protected void initEvents() {
	// TODO Auto-generated method stub
	
}  

} 
		
	
