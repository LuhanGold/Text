package com.luhan.costom;

/** 
* @ClassName: CustomException 
* @Description: (自定义异常类，用来实现各种数据的异常) 
* @author Luhan 
* @date 2017年3月18日 下午4:58:48 
*  
*/
public class CustomException extends Exception{

	private static final long serialVersionUID = 5566052606355516997L;
	
	
	/**
	 * 对象不能为null异常
	 */
	public static final String NULLPOINTEXCEPTION = "对象不能为空";
	/**
	 * 对象不能为""值异常
	 */
	public static final String NULLVALUEEXCEPTION = "不能为空值";
	
	/**
	 * 异常类的构造方法，传入是什么异常信息
	 */
	public CustomException(String meg){
		super(meg);
	}

}
