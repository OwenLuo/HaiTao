package com.lvcaiye.haitao.share.commom;

import android.content.Context;
import android.widget.Toast;

import com.lvcaiye.haitao.share.core.ShareParams;
import com.lvcaiye.haitao.share.util.ShareImageUtil;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

/**
 * @author owen 微信分享操作类
 */
public class WXCommom {
	private Context mContext;
	private static IWXAPI api;
	public final static String appId = "wx36692b472a1eb3f8";
	public final static String appSecret = "a66e6237942de1fa429fecb7cfcd3b61";
	private static UMWXHandler wxHandler = null;
	private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;

	public WXCommom(Context context) {
		mContext = context;
		checkInstallWX(mContext);
		wxHandler = new UMWXHandler(mContext, appId, appSecret);
	}

	public static void checkInstallWX(Context mContext) {
		// 检测微信版本是否支持分享到朋友圈
		int wxSdkVersion = getApi(mContext).getWXAppSupportAPI();
		if (wxSdkVersion < TIMELINE_SUPPORTED_VERSION) {
			Toast.makeText(mContext, "请先安装或升级您的微信客户端", Toast.LENGTH_LONG).show();
			return;
		}
	}

	public static IWXAPI getApi(Context mContext) {
		if (api == null) {
			api = WXAPIFactory.createWXAPI(mContext, appId, true);
			api.registerApp(appId);
		}
		return api;
	}

	/**
	 * Description: 微信分享 <BR>
	 */
	public void sendToWx(final UMSocialService mController, ShareParams params) {
		// 添加微信平台
		wxHandler.addToSocialSDK();
		// 创建微信分享对象
		WeiXinShareContent weixinContent = new WeiXinShareContent();
		// 设置title
		weixinContent.setTitle(params.getTitle());
		// 设置微信好友分享内容
		weixinContent.setShareContent(params.getContent());
		// 设置分享内容跳转URL
		weixinContent.setTargetUrl(checkShareUrl(params.getShareUrl()));
		// 设置分享图片
		UMImage image = new UMImage(mContext, ShareImageUtil.getNetWorkBitmap(mContext,
				params.getImageUrl()));
		weixinContent.setShareImage(image);
		mController.setShareMedia(weixinContent);
		mController.postShare(mContext, SHARE_MEDIA.WEIXIN, null);

	}

	/**
	 * Description: 朋友圈分享 <BR>
	 */
	public void sendToWxCircle(final UMSocialService mController, ShareParams params) {
		// 设置朋友圈可用
		wxHandler.setToCircle(true);
		// 添加微信平台
		wxHandler.addToSocialSDK();
		// 创建朋友圈分享对象
		CircleShareContent circleMedia = new CircleShareContent();
		// 设置朋友圈title
		circleMedia.setTitle(params.getTitle() + params.getContent());
		// 设置微信朋友圈分享内容
		circleMedia.setShareContent(params.getContent());
		// 设置分享内容跳转URL
		circleMedia.setTargetUrl(checkShareUrl(params.getShareUrl()));
		// 设置分享图片
		UMImage image = new UMImage(mContext, ShareImageUtil.getNetWorkBitmap(mContext,
				params.getImageUrl()));
		circleMedia.setShareImage(image);
		mController.setShareMedia(circleMedia);
		mController.postShare(mContext, SHARE_MEDIA.WEIXIN_CIRCLE, null);
	}

	private String checkShareUrl(String shareUrl) {
		return shareUrl == null || "".equals(shareUrl) ? "http://www.edealdeal.com"
				: shareUrl;
	}

}
