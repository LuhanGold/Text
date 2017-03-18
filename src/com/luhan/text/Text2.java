package com.luhan.text;

/** 
* @ClassName: Text2 
* @Description: (用于测试使用) 
* @author Luhan 
* @date 2017年3月17日 上午11:18:06
*/
public class Text2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//使用最有效率的算出2*8的结果
		System.out.println(2<<3);
		//请求垃圾收集
		Runtime.getRuntime().gc();
	}
	
	/**
	 * 用来截取字符串
	 */
	public void subString(){}
}