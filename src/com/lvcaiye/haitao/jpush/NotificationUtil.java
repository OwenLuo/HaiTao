package com.lvcaiye.haitao.jpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class NotificationUtil {
	@SuppressWarnings("deprecation")
	public static void showNormalNotification(Context context, int drawableId,
			String ticker, String contentTitle, String contentText,
			Intent notificationIntent, int notificationId, String isPlaySound) {

		// 创建一个NotificationManager的引用
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);

		// 定义Notification的各种属性
		// Notification notification =new Notification(drawableId,
		// "督导系统", System.currentTimeMillis());
		Notification notification = new Notification(drawableId, ticker,
				System.currentTimeMillis());
		// FLAG_AUTO_CANCEL 该通知能被状态栏的清除按钮给清除掉
		// FLAG_NO_CLEAR 该通知不能被状态栏的清除按钮给清除掉
		// FLAG_ONGOING_EVENT 通知放置在正在运行
		// FLAG_INSISTENT 是否一直进行，比如音乐一直播放，知道用户响应
		notification.flags |= Notification.FLAG_AUTO_CANCEL; // 将此通知放到通知栏的"Ongoing"即"正在运行"组中
		// DEFAULT_ALL 使用所有默认值，比如声音，震动，闪屏等等
		// DEFAULT_LIGHTS 使用默认闪光提示
		// DEFAULT_SOUNDS 使用默认提示声音
		// DEFAULT_VIBRATE 使用默认手机震动，需加上<uses-permission
		// android:name="android.permission.VIBRATE" />权限
		if (isPlaySound.equals("1")) {
			notification.defaults = Notification.DEFAULT_ALL;
		} else {
			notification.defaults = Notification.DEFAULT_VIBRATE;
		}
		// 叠加效果常量
		// notification.defaults=Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND;
		notification.ledARGB = Color.BLUE;
		notification.ledOnMS = 5000; // 闪光时间，毫秒

		if (notificationIntent != null) {
			// 设置通知的事件消息
			PendingIntent contentItent = PendingIntent.getActivity(context, 0,
					notificationIntent, 0);
			notification.setLatestEventInfo(context, contentTitle, contentText,
					contentItent);
		} else {
			notification.setLatestEventInfo(context, contentTitle, contentText, null);
		}

		notificationManager.notify(notificationId, notification);
	}
}
