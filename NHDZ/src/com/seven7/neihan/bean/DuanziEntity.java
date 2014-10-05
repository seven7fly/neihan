package com.seven7.neihan.bean;
/***
 * 暂时没用
 * @author aaa
 *
 */
public class DuanziEntity {

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
	 * 文本段子的内容部分（完整的内容）
	 */
	protected String content;
}
