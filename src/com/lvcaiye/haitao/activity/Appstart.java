package com.lvcaiye.haitao.activity;

import com.lvcaiye.haitao.R;
import com.lvcaiye.haitao.base.BaseActivity;
import com.lvcaiye.haitao.db.PreferenceConstants;
import com.lvcaiye.haitao.db.PreferenceUtils;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

public class Appstart extends BaseActivity {
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appstart);
		
		if("".equals(PreferenceUtils.getPrefString(this,
				PreferenceConstants.APP_V, "")) ){
			Intent intent = new Intent();
			intent.setClass(Appstart.this, Whatsnew.class);
			startActivity(intent);
			this.finish();
		}else if(!mApplication.mversionName.equals(PreferenceUtils.getPrefString(this,
				PreferenceConstants.APP_V, ""))){
			Intent intent = new Intent();
			intent.setClass(Appstart.this, Whatsnew.class);
			startActivity(intent);
			this.finish();
		}else{
		
		startwelcome();
		}
	}

	public void ckeckuser() {
/*		if (mNetWorkUtils.getConnectState() == NetWorkState.NONE) {
			showAlert("登录失败", "当前网络不可用,请检查");
		} else if ("".equals(muid)) {
			Intent intent = new Intent();
			intent.setClass(Appstart.this, LoginActivity.class);
			startActivity(intent);
			this.finish();
		} else {
	*/		
			Intent intent = new Intent();
			intent.setClass(Appstart.this, firstActivity.class);
			startActivity(intent);
			this.finish();
			
//		}

	}


	public void startwelcome() {

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				ckeckuser();
			}
		}, 2000);
	}



	@Override
	protected void initViews() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub

	}
	@Override
	public void onResume() {
		super.onResume();
		}
	@Override
		public void onPause() {
		super.onPause();
		}
	

}