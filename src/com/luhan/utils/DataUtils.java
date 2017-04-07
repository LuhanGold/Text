package com.luhan.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import com.luhan.costom.CustomException;

/** 
* @ClassName: DataUtils 
* @Description: (数据工具类) 
* @author Luhan 
* @date 2017年3月18日 下午5:14:48 
*/
public class DataUtils {
	
	//将构造方法设为私有的
	private DataUtils(){
	}
	/**
	 * 数据集合的分页方法，根据传入总共的数据跟页码，返回页码所需要显示多少条的数据
	 * <BR/>采用泛型的方法，即为，list中为什么类型的数据就返回什么类型的数据
	 * @param f 带有需要进行分页的数据集合
	 * @param pageNo 第几页
	 * @param dataSize 显示多少条数据
	 * @return 进过分页之后返回的数据
	 */
	public static <F> List<F> datepaging(List<F> f,int pageNo,int dataSize){
		/*
		 * 经过测试发现当pageNo为0或者小于时，也就是第0页时，程序会报错，所以需要处理一下pageNo的值
		 * 
		 * 先进行空值的判断，避免程序出现null异常
		 * 
		 * 当pageNo的值小于等于0时，我们让它的值为1
		 */
		//参数的校验
		if(f == null){//当传入过来的list集合为null时，先进行实例化
			f = new ArrayList<F>();
		}
		if((Object)pageNo == null){//当传入过来的pageNo为null时，先进行赋值操作
			pageNo = 1;
		}
		if((Object)dataSize == null){//当传入过来的dataSize为null时，先进行赋值操作
			dataSize = 1;
		}
		//判断下传入过来的页码是小于等于0的时候，就讲页码设为第一页
		if(pageNo <= 0){
			pageNo = 1;
		}
		//记录一下数据一共有多少条
		int totalitems = f.size();
		//实例化一个接受分页处理之后的数据
		List<F> afterList = new ArrayList<F>();
		/*
		 * 进行分页处理,采用for循环的方式来进行处理
		 * 
		 * 首先for循环中，i应该从哪里开始:i应该从 (当前是第几页 -1 乘以 条数) 开始 也就是开始的索引
		 * 
		 * 然后for循环应该到哪里结束，也就是i应该小于:判断(开始的索引+显示条数)是不是大于总条数，如果大于就是总条数，如果小于就是(开始的索引+显示条数)
		 * 
		 * 然后让i++
		 */
		for( int i = (pageNo-1)*dataSize; 
		  i < (((pageNo -1)*dataSize) + dataSize > 
		  totalitems ? totalitems:((pageNo -1)*dataSize) +dataSize);
				i++) {
			//然后将数据存入afterList中
			
			afterList.add(f.get(i));
		}
		//然后将处理后的数据集合进行返回
		return afterList;
	}
	
	/**
	 * 求出一共有多少页
	 * @param dataSize 需要显示多少条数据
	 * @param totalTiems 一共有多少条数据
	 * @return 一共有多少页,int类型
	 */
	public static int getPageSize(int dataSize,int totalTiems){
		
		/*
		 * 使用总条数除以显示条数然后向上取整数就是一共有多少页了
		 */
		double result = (double)totalTiems / (double)dataSize;
		//进行向上取整
		result = Math.ceil(result);
		
		return (int)result;
	}
	/**
	 * 求出两数的百分比值，默认精确到百分比的后两位
	 * @param num1 需要计算的值1
	 * @param num2 需要计算的值2
	 * @return 返回两数的百分比 带有%符号的，为String类型
	 */
	public static String countPercentReturnString(double num1,double num2){
		String result = "";//定义接收最后两数百分比的结果
		//TODO 进行null判断
		
		//方法一:
//		DecimalFormat format = new DecimalFormat("##%");
		
		//方法二:
		NumberFormat format = NumberFormat.getInstance();
		//设置保留小数点后几位
		format.setMaximumFractionDigits(2);
		result = format.format((num1 / num2) * 100);
		return result + "%";
	}
	/**
	 * 求出两数的百分比值
	 * @param num1 需要计算的值1
	 * @param num2 需要计算的值2
	 * @param digits 精确到多少位
	 * @return 返回两数的百分比 带有%符号的，为String类型
	 * @throws CustomException 可能会抛出null异常
	 */
	public static String countPercentReturnString(double num1,double num2,int digits) throws CustomException{
		String result = "";//定义接收最后两数百分比的结果
		//进行null判断
		if((Object)num1 == null || (Object)num2 == null || (Object)digits == null){
			throw new CustomException(CustomException.NULLVALUEEXCEPTION);
		}
		if(digits < 0){
			digits = 0;
		}
		//方法一:
//		DecimalFormat format = new DecimalFormat("##%");
		//方法二:
		NumberFormat format = NumberFormat.getInstance();
		//设置保留小数点后几位
		format.setMaximumFractionDigits(digits);
		result = format.format((num1 / num2) * 100);
		return result + "%";
	}
	/**
	 * 求出两数的百分比值
	 * @param num1 需要计算的值1
	 * @param num2 需要计算的值2
	 * @return 返回两数的百分比不带有%符号的，为Int类型
	 */
	public static int countPercentReturnInt(double num1,double num2){
		String result = "";//定义接收最后两数百分比的结果
		//TODO 进行null判断
		
		DecimalFormat format = new DecimalFormat("##%");
		result = format.format(num1 / num2);
		
		//进行截取字符串，去掉最后的%，然后进行转换成int类型
		result = result.substring(0, result.length()-1);
		
		return Integer.parseInt(result);
	}
	
	/**判断cardID是不是身份证
	 * 身份证作为居民的唯一标识。在很多系统中需要用户输入身份证号信息，今天我们就来编写一个方法验证身份证号的合法性。
	 * 首先我们来看看身份证号的编码规则： 
	 * 前1-2位数字表示：所在省（直辖市、自治区）的代码； 
	 * 第3-4位数字表示：所在地级市（自治州）的代码； 
	 * 第5-6位数字表示：所在区（县、自治县、县级市）的代码； 
	 * 第7-14位数字表示：出生年、月、日； 
	 * 第15-16位数字表示：所在地的派出所的代码； 
	 * 第17位数字表示性别：奇数表示男性，偶数表示女性； 
	 * 第18位数字是校检码：也有的说是个人信息码，不是随计算机的随机产生，它是 用来检验身份证的正确性。校检码可以是0-9的数字，有时也用X表示。

	 * 知道了规则之后，我们再来看看算法： 
	 * 第一步: 将身份证号码的第1位数字与7相乘；
	                        将身份证号码的第2位数字与9相乘；
	                        将身份证号码的第3位数字与10相乘；
	                        将身份证号码的第4位数字与5相乘；
	                        将身份证号码的第5位数字与8相乘；
	                        将身份证号码的第6位数字与4相乘；
	                        将身份证号码的第7位数字与2相乘；
	                        将身份证号码的第8位数字与1相乘；
	                        将身份证号码的第9位数字与6相乘；
	                        将身份证号码的第10位数字与3相乘；
	                        将身份证号码的第11位数字与7相乘；
	                        将身份证号码的第12位数字与9相乘；
	                        将身份证号码的第13位数字与10相乘；
	                        将身份证号码的第14位数字与5相乘；
	                        将身份证号码的第15位数字与8相乘；
	                        将身份证号码的第16位数字与4相乘；
	                        将身份证号码的第17位数字与2相乘。 
	 * 第二步: 将第一步身份证号码1~17位相乘的结果求和，全部加起来。 
	 * 第三步: 用第二步计算出来的结果除以11，这样就会出现余数为0，余数为1，余数为2，余数为3，余数为4，余数为5，余数为6，余数为7，余数为8，余数为9，余数为10共11种可能性。 
	 * 第四步: 如果余数为0，那对应的最后一位身份证的号码为1；
                                  如果余数为1，那对应的最后一位身份证的号码为0；
                                  如果余数为2，那对应的最后一位身份证的号码为X；
                                  如果余数为3，那对应的最后一位身份证的号码为9；
                                  如果余数为4，那对应的最后一位身份证的号码为8；
                                  如果余数为5，那对应的最后一位身份证的号码为7；
                                  如果余数为6，那对应的最后一位身份证的号码为6；
                                  如果余数为7，那对应的最后一位身份证的号码为5；
                                  如果余数为8，那对应的最后一位身份证的号码为4；
                                  如果余数为9，那对应的最后一位身份证的号码为3；
                                  如果余数为10，那对应的最后一位身份证的号码为2。

	 * 了解了身份证号的规则之后，我们就可以对其进行校验：
	 * @author luhan
	 * @param cardID 身份证字符串
	 * @return true为是身份证，false为不是身份证
	 */
	public static boolean isCordID(String cardID){
	      // 对身份证号进行长度等简单判断
	      if (cardID == null || cardID.length() != 18 || !cardID.matches("\\d{17}[0-9X]")){
	         return false;
	      }
	      // 1-17位相乘因子数组
	      int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
	      // 18位随机码数组
	      char[] random = "10X98765432".toCharArray();
	      // 计算1-17位与相应因子乘积之和
	      int total = 0;
	      for (int i = 0; i < 17; i++){
	         total += Character.getNumericValue(cardID.charAt(i)) * factor[i];
	      }
	      // 判断随机码是否相等
	      return random[total % 11] == cardID.charAt(17);
	}
}
