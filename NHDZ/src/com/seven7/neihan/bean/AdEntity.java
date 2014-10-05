package com.seven7.neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class AdEntity {

	private int type;
	private long displayTime;
	private long onlimeTime;
	private int displayImageHeight;
	private long adId;
	private int displayImageWidth;
	private String source;
	private String packageName;
	private String title;
	private String openUrl;
	private String downloadUrl;
	private int isAd;
	private String displayInfo;
	private String webUrl;
	private int displayType;
	private String buttonText;
	private String appleid;
	private String trackUrl;
	private String label;
	private String idType;
	private long id;
	private String ipaUrl;
	private String displayImage;

	public void parseJson(JSONObject json) throws JSONException{
		if(json!=null){
			type = json.getInt("type");
			displayTime = json.getLong("display_time");
			onlimeTime = json.getLong("online_time");
			
			JSONObject ad=json.getJSONObject("ad");
			displayImageHeight = ad.getInt("display_image_height");
			adId = ad.getLong("ad_id");
			displayImageWidth = ad.getInt("display_image_width");
			source = ad.getString("source");
			packageName = ad.getString("package");
			title = ad.getString("title");
			openUrl = ad.getString("open_url");
			downloadUrl = ad.getString("download_url");
			isAd = ad.getInt("is_ad");
			displayInfo = ad.getString("display_info");
			webUrl = ad.getString("web_url");
			displayType = ad.getInt("display_type");
			buttonText = ad.getString("button_text");
			appleid = ad.getString("appleid");
			trackUrl = ad.getString("track_url");
			label = ad.getString("label");
			idType = ad.getString("type");
			id = ad.getLong("id");
			ipaUrl = ad.getString("ipa_url");
			displayImage = ad.getString("display_image");
		}
	}

	public int getType() {
		return type;
	}

	public long getDisplayTime() {
		return displayTime;
	}

	public long getOnlimeTime() {
		return onlimeTime;
	}

	public int getDisplayImageHeight() {
		return displayImageHeight;
	}

	public long getAdId() {
		return adId;
	}

	public int getDisplayImageWidth() {
		return displayImageWidth;
	}

	public String getSource() {
		return source;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getTitle() {
		return title;
	}

	public String getOpenUrl() {
		return openUrl;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public int getIsAd() {
		return isAd;
	}

	public String getDisplayInfo() {
		return displayInfo;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public int getDisplayType() {
		return displayType;
	}

	public String getButtonText() {
		return buttonText;
	}

	public String getAppleid() {
		return appleid;
	}

	public String getTrackUrl() {
		return trackUrl;
	}

	public String getLabel() {
		return label;
	}

	public String getIdType() {
		return idType;
	}

	public long getId() {
		return id;
	}

	public String getIpaUrl() {
		return ipaUrl;
	}

	public String getDisplayImage() {
		return displayImage;
	}
	
	
	/*
	 *  {
                "type": 5,
                "display_time": 1411878658,
                "online_time": 1411878658,
                "ad": {
                    "display_image_height": 400,
                    "ad_id": 3561092485,
                    "display_image_width": 600,
                    "source": "",
                    "package": "",
                    "title": "霜霜和阿伟都爱玩的游戏，还有iphone6等你拿哦！",
                    "open_url": "",
                    "download_url": "http://yihua.b0.upaiyun.com/neihan.apk",
                    "is_ad": 1,
                    "display_info": "霜霜和阿伟都爱玩的游戏，还有iphone6等你拿哦！",
                    "web_url": "http://yihua.b0.upaiyun.com/neihan.apk",
                    "display_type": 3,
                    "button_text": "立即下载",
                    "appleid": "",
                    "track_url": "",
                    "label": "推广",
                    "type": "app",
                    "id": 3561092485,
                    "ipa_url": "",
                    "display_image": "http://p2.pstatp.com/large/1362/1075506622"
                }
            },
	 */
}
