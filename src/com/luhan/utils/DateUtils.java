package com.luhan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.luhan.costom.CustomException;

/** 
* @ClassName: Utils 
* @Description: (日期工具类) 
* @author Luhan 
* @date 2017年3月18日 下午4:39:44 
*  
*/
public class DateUtils {
	//设置静态的SimpleDateFormat对象
	private static SimpleDateFormat format;
	//设置日期格式的常量
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	
	/**
	 * 默认转换日期的方法，不带参数代表需要转换的格式为"yyyy-MM-dd HH:mm:ss";
	 * @return 转换完成后的日期格式，字符串类型的
	 */
	public static String formDate(){
		//获得系统当前时间
		Date date = new Date(System.currentTimeMillis());
		//获取format对象，并设置format对象要转化日期的格式
		format = new SimpleDateFormat(DATE_FORMAT);
		
		return format.format(date);
	}
	
	/**
	 * 通过传入需要转换成什么样的日期格式的方法
	 * @param dateFormat 需要转换成什么样的格式
	 * @param tolerant 默认的格式
	 * @return 返回转换成功之后的字符串
	 * @throws CustomException 会抛出null和""异常
	 */
	public static String formDate(String dateFormat,String tolerant) throws CustomException{
		if(dateFormat == null || dateFormat.equalsIgnoreCase("")){
			dateFormat = tolerant;
		}
		
		if(tolerant == null){
			throw new CustomException(CustomException.NULLPOINTEXCEPTION);
		}
		if(tolerant.equals("")){
			throw new CustomException(CustomException.NULLVALUEEXCEPTION);
		}
		
		//获得系统当前时间
		Date date = new Date(System.currentTimeMillis());
		//获取format对象，并设置format对象要转化日期的格式
		format = new SimpleDateFormat(dateFormat);
		
		return format.format(date);
	}
}
