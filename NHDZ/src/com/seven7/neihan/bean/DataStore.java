package com.seven7.neihan.bean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 单例模式的存储数据
 * @author aaa
 *
 */
public class DataStore implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4178142563063372022L;

	private static DataStore outInstance;
	
	public static DataStore getInstance(){
		if (outInstance==null) {
			outInstance=new DataStore();
		}
		return outInstance;
	}
	
	
	private List<TextEntity> textEntities;
	
	private List<TextEntity> imageEntities;
	
	public DataStore() {
		// TODO Auto-generated constructor stub
		textEntities=new LinkedList<TextEntity>();
		imageEntities=new LinkedList<TextEntity>();
	}
	/**
	 * 把获取到的文本段子列表放到最前面，这个方法针对的是下拉刷新的操作
	 * @param entities
	 */
	public void addTextEntities(List<TextEntity> entities){
		if (entities!=null) {
			textEntities.addAll(0, entities);
		}
	}
	
	/**
	 * 把获取到的文本列表放到最后面，这个方法针对的是上拉查看旧数据的操作
	 * @param entities
	 */
	public void appendTextEntities(List<TextEntity> entities){
		if (entities!=null) {
			textEntities.addAll(entities);
		}
	}
//  //////////////////////////////////////////////////////
	
	/**
	 * 把获取到的文本段子列表放到最前面，这个方法针对的是下拉刷新的操作
	 * @param entities
	 */
	public void addIamgeEntities(List<TextEntity> entities){
		if (entities!=null) {
			imageEntities.addAll(0, entities);
		}
	}
	
	/**
	 * 把获取到的文本列表放到最后面，这个方法针对的是上拉查看旧数据的操作
	 * @param entities
	 */
	public void appendIamgeEntities(List<TextEntity> entities){
		if (entities!=null) {
			imageEntities.addAll(entities);
		}
	}
	
	/**
	 * 获取文本段子的列表
	 * @return
	 */
	public List<TextEntity> getTextEntities(){
		return textEntities;
	}
	
	/**
	 * 获取图片段子的列表
	 * @return
	 */
	public List<TextEntity> getImageEntities(){
		return imageEntities;
		
	}
}
