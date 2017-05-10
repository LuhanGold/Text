package com.luhan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.luhan.Constant;
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
	
	
	
	/**
	 * 默认转换日期的方法，不带参数代表需要转换的格式为"yyyy-MM-dd HH:mm:ss";
	 * @return 转换完成后的日期格式，字符串类型的
	 */
	public static String formDate(){
		//获得系统当前时间
		Date date = new Date(System.currentTimeMillis());
		//获取format对象，并设置format对象要转化日期的格式
		format = new SimpleDateFormat(Constant.DATE_FORMAT);
		
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
	
	/**
	 * 将时间戳转化为日期
	 * @param timesStamp
	 * @return 转化之后的日期，格式为String
	 */
	public static String timesStampToDate(Long timesStamp){
		//需要转化为什么类型
		format = new SimpleDateFormat(Constant.DATE_FORMAT);
		//将参数时间戳进行转换
		String time = format.format(timesStamp);
		return time;
	}
	
	/**
	 * 将指定的日期转化为时间戳
	 * @param date 指定的日期
	 * @return 转化之后的结果
	 * @throws ParseException 可能会抛出传入的不是正确格式的日期
	 */
	public static Long dateToTimestamp(String date) throws ParseException{
		format = new SimpleDateFormat(Constant.DATE_FORMAT);
		Date d = format.parse(date);
		return d.getTime()/1000;
	}
	/**将制定日期，制定日期的格式转化为时间戳
	 * @author luhan
	 * @param date 时间
	 * @param dataFormat 时间的格式
	 * @return 返回转化为时间戳的字符串
	 * @throws ParseException
	 */
	public static Long dateToTimestamp(String date,String dataFormat) throws ParseException{
		format = new SimpleDateFormat(dataFormat);
		Date d = format.parse(date);
		return d.getTime()/1000;
	}
}
