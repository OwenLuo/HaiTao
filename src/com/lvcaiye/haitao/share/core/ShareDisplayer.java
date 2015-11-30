package com.lvcaiye.haitao.share.core;

import android.content.Context;

import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;

/**
 * Created by owen on 2015/7/17.
 */
public interface ShareDisplayer {

    void shareWx(Context mContext, ShareParams params);

    void shareWxCircle(Context mContext, ShareParams params);

    void shareQq(Context mContext, ShareParams params, SnsPostListener callBack);

    void shareQzone(Context mContext, ShareParams params, SnsPostListener callBack);

}
