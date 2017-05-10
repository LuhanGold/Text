/**
 * 
 */
package com.luhan.entity;

/**@Description {网络请求参数实体类}
 * @author luhan
 * @date   2017年4月9日 上午10:44:27
 */
public class HttpParam {
	//参数的键
	private String key;
	//参数的对应键的值
	private String value;
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
}
