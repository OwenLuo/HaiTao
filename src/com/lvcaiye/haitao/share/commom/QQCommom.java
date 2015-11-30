package com.lvcaiye.haitao.share.commom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.lvcaiye.haitao.share.core.ShareParams;
import com.lvcaiye.haitao.share.util.ShareImageUtil;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;

/**
 * @author owen QQ分享操作类
 */
public class QQCommom {

	private Context mContext;
	private static String appId = "1104689727";
	private static String appKey = "4xlLhIOGM2DFTBQB";
	private static UMQQSsoHandler qqHandler = null;
	private static QZoneSsoHandler qzHandler = null;

	byte[] thumbData = null;
	Bitmap bitmap = null;

	public QQCommom(Context context) {
		mContext = context;
		qqHandler = new UMQQSsoHandler((Activity) mContext, appId, appKey);
		qzHandler = new QZoneSsoHandler((Activity) mContext, appId, appKey);
	}

	/**
	 * qq分享
	 */
	public void sendToQq(final UMSocialService mController, final ShareParams params,
			SnsPostListener callBack) {
		// 添加QQ平台
		qqHandler.addToSocialSDK();
		// 创建qq分享对象
		QQShareContent qq = new QQShareContent();
		// 设置分享title
		qq.setTitle(params.getTitle());
		// 设置分享内容
		qq.setShareContent(params.getContent());
		// 设置点击分享内容的跳转链接
		qq.setTargetUrl(checkShareUrl(params.getShareUrl()));
		// 设置分享图片
		UMImage image = new UMImage(mContext, ShareImageUtil.getNetWorkBitmap(mContext,
				params.getImageUrl()));
		qq.setShareImage(image);
		mController.setShareMedia(qq);
		mController.postShare(mContext, SHARE_MEDIA.QQ, callBack);
	}

	/**
	 * qqz分享
	 */
	public void sendToQz(final UMSocialService mController, ShareParams params,
			SnsPostListener callBack) {
		// 添加QQ空间平台
		qzHandler.addToSocialSDK();
		// 创建qqz分享对象
		QZoneShareContent qzone = new QZoneShareContent();
		// 设置分享标题
		qzone.setTitle(params.getTitle());
		// 设置分享内容
		qzone.setShareContent(params.getContent());
		// 设置点击消息的跳转URL
		qzone.setTargetUrl(checkShareUrl(params.getShareUrl()));
		// 设置分享图片
		UMImage image = new UMImage(mContext, ShareImageUtil.getNetWorkBitmap(mContext,
				params.getImageUrl()));
		qzone.setShareImage(image);
		mController.setShareMedia(qzone);
		mController.postShare(mContext, SHARE_MEDIA.QZONE, callBack);
	}

	private String checkShareUrl(String shareUrl) {
		return shareUrl == null || "".equals(shareUrl) ? "http://www.edealdeal.com"
				: shareUrl.contains("http") ? shareUrl : "http://" + shareUrl;
	}

}
