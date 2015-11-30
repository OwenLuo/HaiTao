package com.lvcaiye.haitao.share.core;

/**
 * Created by owen on 2015/7/17.
 */
public class ShareParams {

	private String title;
	private String content;
	private String imageUrl;
	private String shareUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageUrl() {
		return imageUrl == null ? "" : imageUrl.trim();
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getShareUrl() {
		return shareUrl.trim();
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	@Override
	public String toString() {
		return "ShareParams{" + ", title='" + title + '\'' + ", content='" + content
				+ '\'' + ", imageUrl='" + imageUrl + '\'' + ", shareUrl='" + shareUrl
				+ '\'' + '}';
	}
}
