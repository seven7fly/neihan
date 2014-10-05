package com.seven7.neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;
/***
 * 文本段子实体
 * @author aaa
 *
 */
public class TextEntity{

protected int type;
	
	protected long createTime;
	
	/**
	 * 上线时间
	 */
	protected long onlineTime;
	/**
	 * 显示时间
	 */
	protected long displayTime;
	/**
	 * 评论的个数comment_count
	 */
	protected int commentCount;	
	/**
	 * digg的个数
	 */
	protected int diggCount;
	/**
	 * 状态，其中的可选值3需要分析是什么类型
	 */
	protected int status;
	
	/**
	 * TODO需要了解digg到底是什么含义
	 * user_digg
	 */
	protected int userDigg;
	/**
	 * 段子的ID
	 */
	protected long groupId;
	/**
	 * 内容分类类型，1文本，2图片
	 */
	protected int categoryId;
	/**
	 * 代表赞的个数
	 */
	protected int favoriteCount;
	/**
	 * 代表用户是否repin，0代表没有
	 */
	protected int userRepin;
	/**
	 * TODO 需要分析一下含义，当前在两个地方出现。
	 * 1、获取列表接口里面有一个level=6
	 * 2、文本段子实体中有一个 level=4
	 */
	protected int level;
	/**
	 * 用于第三方分享，提交的网址参数
	 */
	protected String shareUrl;
	/**
	 *代表当前用户是否踩了。是为1，否为0
	 */
	private int userBury;
	/**
	 * 代表当前用户是否赞了，是为1，否为0
	 */
	private int userFavorite;
	/**
	 * 代表踩的个数
	 */
	private int buryCount;
	
	//TODO 分析这个字段的含义
	private int label;
	/**
	 * 文本段子的内容部分（完整的内容）
	 */
	private String content;
	/**
	 * has_comments当前用户是否评论，是为1，否为0
	 */
	private int hasComments;
	
	//TODO 需要分析这个字段的含义
	private int goDetailCount;
	//TODO 分析含义
	private int repinCount;
	/**
	 * 状态的描述信息<br/>
	 * 可选值<br/>
	 * <ul>
	 * 	<li>"已发表到热门列表"</li>
	 * </ul>
	 */
	private String statusDesc;
	/**
	 * 是否有热门评论
	 */
	private int hasHotComments;
	
	
	//TODO 需要去分析comments这个JSON数组中的内容是什么？
	
	
	private UserEntity user;
	
	public void parseJson(JSONObject json) throws JSONException{
		if (json!=null) {
			this.onlineTime=json.getLong("online_time");
			this.displayTime=json.getLong("display_time");
			
			JSONObject group=json.getJSONObject("group");
			this.createTime=group.getLong("create_time");
			this.favoriteCount=group.getInt("favorite_count");
			this.buryCount=group.getInt("bury_count");
			this.userBury=group.getInt("user_bury");
			this.userFavorite=group.getInt("user_favorite");
			this.shareUrl=group.getString("share_url");
			this.label=group.optInt("label",0);
			this.content=group.getString("content");
			this.status=group.getInt("status");
			this.statusDesc=group.getString("status_desc");
			this.hasComments=group.optInt("has_comments",0);
			this.goDetailCount=group.getInt("go_detail_count");
			
//			//我自己的写法
//			user=new UserEntity();
//			user.parseJson(group.getJSONObject("user"));
			//老师的写法
			JSONObject uobj=group.getJSONObject("user");
			user=new UserEntity();
			user.parseJson(uobj);
			
			this.userDigg=group.getInt("user_digg");
			this.diggCount=group.getInt("digg_count");
			this.groupId=group.getLong("group_id");
			this.level=group.getInt("level");
			this.repinCount=group.getInt("repin_count");
			this.userRepin=group.getInt("user_repin");
			this.categoryId=group.getInt("category_id");
			
		}
		
	}

	public int getType() {
		return type;
	}

	public long getCreateTime() {
		return createTime;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public int getUserBury() {
		return userBury;
	}

	public int getUserFavorite() {
		return userFavorite;
	}

	public int getBuryCount() {
		return buryCount;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public int getLabel() {
		return label;
	}

	public String getContent() {
		return content;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public int getStatus() {
		return status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public int getHasComments() {
		return hasComments;
	}

	public int getGoDetailCount() {
		return goDetailCount;
	}

	public int getUserDigg() {
		return userDigg;
	}

	public int getDiggCount() {
		return diggCount;
	}

	public long getGroupId() {
		return groupId;
	}

	public int getLevel() {
		return level;
	}

	public int getUserRepin() {
		return userRepin;
	}

	public int getRepinCount() {
		return repinCount;
	}

	public int getHasHotComments() {
		return hasHotComments;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public long getOnlineTime() {
		return onlineTime;
	}

	public long getDisplayTime() {
		return displayTime;
	}

	public UserEntity getUser() {
		return user;
	}
	
	
	/*
	 * {
        "online_time": 1411878957,
        "display_time": 1411878957,
        "group": {
            "create_time": 1411718218.0,
            "favorite_count": 1209,
            "user_bury": 0,
            "user_favorite": 0,
            "bury_count": 1516,
            "share_url": "http://toutiao.com/group/3560859160/?iid=2337593504&app=joke_essay",
            "label": 1,
            "content": "甲:昨天碰到抢劫的，被打了两顿。乙:为啥啊？甲:他问我有钱吗我说没有，他从我身上搜出一包软中华然后就被打了一顿。等他走了，不一会儿又回来打了我一顿，因为他发现里面塞的是白红梅，劫匪走时还留下一句‘没钱还装B’",
            "comment_count": 177,
            "status": 3,
            "has_comments": 0,
            "go_detail_count": 4370,
            "status_desc": "已发表到热门列表",
            "user": {
                "avatar_url": "http://p1.pstatp.com/thumb/1367/2213311454",
                "user_id": 3080520868,
                "name": "请叫我梓安哥",
                "user_verified": false
            },
            "user_digg": 0,
            "group_id": 3560859160,
            "level": 4,
            "repin_count": 1209,
            "digg_count": 18424,
            "has_hot_comments": 1,
            "user_repin": 0,
            "category_id": 1
        },
        "comments": [],
        "type": 1
    },
	 */
	
}
