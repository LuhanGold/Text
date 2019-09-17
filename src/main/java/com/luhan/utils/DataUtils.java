package com.luhan.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.luhan.Constant;
import com.luhan.custom.CustomException;
import com.luhan.enums.ObjType;

/** 
* @ClassName: DataUtils 
* @Description: (数据工具类) 
* @author Luhan 
* @date 2017年3月18日 下午5:14:48 
*/
public class DataUtils {
	
	/*
	 * 用于将字母对应的数字集
	 */
	private static final String[] SHIFTINT = {
			"356","156","845","548","624","138","454","666","715","056",
			"987","512","414","524","124","645","123","654","423","145",
			"002","988","667","998","555","222"
			};
	/*
	 * 用于数字对应的字母集
	 */
	private static final String[] SHIFTCHAR = {
			"agf","jnn","cad","ewe","vxc","asd","iui","klk","uil","xxz"
	}; 
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
	/**将多个数组合并成一个数组
	 * @author luhan
	 * @param firstAry 
	 * @param ary
	 * @return 返回合并之后的数组
	 */
	@SuppressWarnings("all")
	public static <F> F[] concat(F[] firstAry,F[]... ary){
		//定义需要合并数组的总长度
		int aryLength = firstAry.length;
		for (F[] fs : ary) {
			aryLength += fs.length;
		}
		//定义合并之后的数组
		F[] result = Arrays.copyOf(firstAry,aryLength);
		int offset = firstAry.length;  
		  for (F[] array : ary) {  
		    System.arraycopy(array, 0, result, offset, array.length);
		    offset += array.length;  
		}
		return result;
	}
	
	/**解析properties文件获得里面的内容
	 * @author luhan
	 * @param propertiesPath
	 * @return 返回键值对的map集合，key就是pro文件左边的值，value就是pro文件右边的值
	 * @throws IOException 
	 */
	public static Map<String, String> parseProperties(String propertiesPath) throws IOException{
		//创建一个返回结果的map集合
		Map<String, String> result = new LinkedHashMap<String,String>();
		//参数校验
		if(isEmpty(propertiesPath)){
			return result;
		}
		//新建一个pro文件对象
		Properties properties = new Properties();
		//新建一个输入流
		FileInputStream in = new FileInputStream(propertiesPath);
		//新建一个输入读流
		InputStreamReader reader = new InputStreamReader(in,Constant.ENCODING_UTF8);
		//调用加载方法，需要一个输入流
		properties.load(reader);
		//得到pro文件里面的每一行内容
		Enumeration<?> en = properties.propertyNames();
		//根据读到的内容循环获取数据
		while(en.hasMoreElements()){
			//得到每一行的键
			String key = (String) en.nextElement();
			//得到每一行键对应的值
			String value = properties.getProperty(key);
			
			//存入map中
			result.put(key, value);
		}
		//最后将result返回
		return result;
	}
	
	/**将字符串进行加密
	 * @author luhan
	 * @param orgStr 加密的字符串
	 * @return 返回加密后的字符串
	 */
	public static String encrypt(String orgStr){
		StringBuffer buffer = new StringBuffer();
		char[] chars = orgStr.toCharArray();
		for(char c : chars){
			switch (c) {
			case 'a':
				buffer.append(SHIFTINT[0]);
				break;
			case 'b':
				buffer.append(SHIFTINT[1]);
				break;
			case 'c':
				buffer.append(SHIFTINT[2]);
				break;
			case 'd':
				buffer.append(SHIFTINT[3]);
				break;
			case 'e':
				buffer.append(SHIFTINT[4]);
				break;
			case 'f':
				buffer.append(SHIFTINT[5]);
				break;
			case 'g':
				buffer.append(SHIFTINT[6]);
				break;
			case 'h':
				buffer.append(SHIFTINT[7]);
				break;
			case 'i':
				buffer.append(SHIFTINT[8]);
				break;
			case 'j':
				buffer.append(SHIFTINT[9]);
				break;
			case 'k':
				buffer.append(SHIFTINT[10]);
				break;
			case 'l':
				buffer.append(SHIFTINT[11]);
				break;
			case 'm':
				buffer.append(SHIFTINT[12]);
				break;
			case 'n':
				buffer.append(SHIFTINT[13]);
				break;
			case 'o':
				buffer.append(SHIFTINT[14]);
				break;
			case 'p':
				buffer.append(SHIFTINT[15]);
				break;
			case 'q':
				buffer.append(SHIFTINT[16]);
				break;
			case 'r':
				buffer.append(SHIFTINT[17]);
				break;
			case 's':
				buffer.append(SHIFTINT[18]);
				break;
			case 't':
				buffer.append(SHIFTINT[19]);
				break;
			case 'u':
				buffer.append(SHIFTINT[20]);
				break;
			case 'v':
				buffer.append(SHIFTINT[21]);
				break;
			case 'w':
				buffer.append(SHIFTINT[22]);
				break;
			case 'x':
				buffer.append(SHIFTINT[23]);
				break;
			case 'y':
				buffer.append(SHIFTINT[24]);
				break;
			case 'z':
				buffer.append(SHIFTINT[25]);
				break;
			case '0':
				buffer.append(SHIFTCHAR[0]);
				break;
			case '1':
				buffer.append(SHIFTCHAR[1]);
				break;
			case '2':
				buffer.append(SHIFTCHAR[2]);
				break;
			case '3':
				buffer.append(SHIFTCHAR[3]);
				break;
			case '4':
				buffer.append(SHIFTCHAR[4]);
				break;
			case '5':
				buffer.append(SHIFTCHAR[5]);
				break;
			case '6':
				buffer.append(SHIFTCHAR[6]);
				break;
			case '7':
				buffer.append(SHIFTCHAR[7]);
				break;
			case '8':
				buffer.append(SHIFTCHAR[8]);
				break;
			case '9':
				buffer.append(SHIFTCHAR[9]);
				break;
			default:
				buffer.append(c);
			}
			
		}
		
		return buffer.toString();
	}
	
	@SuppressWarnings("unused")
	/**判断字符串是不是空值
	 * @author luhan
	 * @param str
	 * @return true为是空值 false不是空值
	 */
	public static boolean isEmpty(String str){
		//默认str不是为空的
		boolean result = false;
		//进行判断校验，改版result的值
		if(str.equals("")){
			result = true;
		}else if(str == null){
			result = true;
		}
		
		return result;
	}
	/**解密操作
	 * @author luhan
	 * @param orgStr 需要解密的字符串
	 * @return 返回解密后真实的字符串
	 */
	public static String decode(String orgStr){
		char[] chars = orgStr.toCharArray();
		int index = 0;
		String str = "";
		String [] strs = new String[orgStr.length()/3];
		for(int i = 0;i<chars.length;i++){
			str += chars[i]; 
			if(((i+1)%3) == 0){
				strs[index] = str;
				index++;
				str = "";
			}
		}
		return shift(strs);
	}
	/**将加密后的字符串进行解密
	 * @author luhan
	 * @param strs 加密后的字符串
	 * @return 返回经过解密操作的字符串
	 */
	private static String shift(String[] strs){
		StringBuffer buffer = new StringBuffer();
		for(String s : strs){
			switch (s) {
			case "356":
				buffer.append("a");
				break;
			case "156":
				buffer.append("b");
				break;
			case "845":
				buffer.append("c");
				break;
			case "548":
				buffer.append("d");
				break;
			case "624":
				buffer.append("e");
				break;
			case "138":
				buffer.append("f");
				break;
			case "454":
				buffer.append("g");
				break;
			case "666":
				buffer.append("h");
				break;
			case "715":
				buffer.append("i");
				break;
			case "056":
				buffer.append("j");
				break;
			case "987":
				buffer.append("k");
				break;
			case "512":
				buffer.append("l");
				break;
			case "414":
				buffer.append("m");
				break;
			case "524":
				buffer.append("n");
				break;
			case "124":
				buffer.append("o");
				break;
			case "645":
				buffer.append("p");
				break;
			case "123":
				buffer.append("q");
				break;
			case "654":
				buffer.append("r");
				break;
			case "423":
				buffer.append("s");
				break;
			case "145":
				buffer.append("t");
				break;
			case "002":
				buffer.append("u");
				break;
			case "988":
				buffer.append("v");
				break;
			case "667":
				buffer.append("w");
				break;
			case "998":
				buffer.append("x");
				break;
			case "555":
				buffer.append("y");
				break;
			case "222":
				buffer.append("z");
				break;
			case "agf":
				buffer.append("0");
				break;
			case "jnn":
				buffer.append("1");
				break;
			case "cad":
				buffer.append("2");
				break;
			case "ewe":
				buffer.append("3");
				break;
			case "vxc":
				buffer.append("4");
				break;
			case "asd":
				buffer.append("5");
				break;
			case "iui":
				buffer.append("6");
				break;
			case "klk":
				buffer.append("7");
				break;
			case "uil":
				buffer.append("8");
				break;
			case "xxz":
				buffer.append("9");
				break;
			default:
				buffer.append(s);
				break;
			}
		}
		return buffer.toString();
	}
	
	/**
	 * @disc  {通过制定需要多少长度去截取对应的数据}
	 * @author luHan
	 * @param obj 需要截取的数据
	 * @param cutLength 截取多少的长度
	 * @return 返回截取之后的数据
	 */
	@SuppressWarnings("unchecked")
	public static <T> T cutData(T obj,int cutLength){
		//获取传入类型的简写名称
		String simpleName = obj.getClass().getSimpleName();
//		System.out.println(simpleName);
		switch (simpleName) {
		case "String":
			String result = (String) obj;
			if(result.length() <= cutLength) return (T) result;
			result = result.substring(0, cutLength);
			return (T) result;
		case "Object[]":
			return (T) cutObjects((Object[]) obj,cutLength,ObjType.O);
		case "String[]":
			return (T) cutObjects((String[]) obj,cutLength,ObjType.S);
		case "int[]":
			return (T) cutObjects((int[]) obj,cutLength,ObjType.I);
		case "boolean[]":
			return (T) cutObjects((boolean[]) obj,cutLength,ObjType.B);
		case "char[]":
			return (T) cutObjects((char[]) obj,cutLength,ObjType.C);
		case "double[]":
			return (T) cutObjects((double[]) obj,cutLength,ObjType.D);
		case "ArrayList":
			List<Object> ary_list = (List<Object>) obj;
			return (T) cutList(ary_list,cutLength);
		case "LinkedList":
			List<Object> link_list = (List<Object>) obj;
			return (T) cutList(link_list,cutLength);
		case "Vector":
			List<Object> vct_list = (List<Object>) obj;
			return (T) cutList(vct_list,cutLength);
		default:
			return obj;
		}
	}
	/**
	 * @disc  {将list集合截取成需要多少的数据}
	 * @author luHan
	 * @param list 需要截取的集合
	 * @param cutLength 需要的size的值
	 * @return 返回截取后的list
	 */
	private static List<Object> cutList(List<Object> list,int cutLength){
		//判断传入的list的长度是否小于等于要截取的长度，如果满足就直接return
		if(list.size() <= cutLength) return list;
		//调用Arrays的copyOfRange对list进行截取操作
		Object[] objs = Arrays.copyOfRange(list.toArray(), 0, cutLength);
		//截取完之后将list集合进行清空，然后重新赋值
		list.clear();
		//循环Arrays截取的内容，再将内容一一添加到list中
		for(Object obj : objs){
			list.add(obj);
		}
		return list;
	}
	/**
	 * @disc  {将数组类型进行截取,目前只支持Object和String类型的数组}
	 * @author luHan
	 * @param obj
	 * @param cutLength 需要截取的长度
	 * @param objType 数组类型
	 * @return 返回截取后的长度
	 */
	private static Object cutObjects(Object obj,int cutLength,ObjType objType){
		switch (objType) {
		case S:
			String[] result_S = new String[cutLength];
			String[] value_S = (String[]) obj;
			if(value_S.length <= cutLength) return value_S;
			for(int i =0;i<cutLength;i++){
				result_S[i] = (String) value_S[i];
			}
			return result_S;
		case O:
			Object[] result_O = new Object[cutLength];
			Object[] value_O = (Object[]) obj;
			if(value_O.length <= cutLength) return value_O;
			for(int i =0;i<cutLength;i++){
				result_O[i] = value_O[i];
			}
			return result_O;
		case I:
			int[] result_I = new int[cutLength];
			int[] value_I = (int[]) obj;
			if(value_I.length <= cutLength) return value_I;
			for(int i =0;i<cutLength;i++){
				result_I[i] = value_I[i];
			}
			return result_I;
		case B:
			boolean[] result_B = new boolean[cutLength];
			boolean[] value_B = (boolean[]) obj;
			if(value_B.length <= cutLength) return value_B;
			for(int i =0;i<cutLength;i++){
				result_B[i] = value_B[i];
			}
			return result_B;
		case C:
			char[] result_C = new char[cutLength];
			char[] value_C = (char[]) obj;
			if(value_C.length <= cutLength) return value_C;
			for(int i =0;i<cutLength;i++){
				result_C[i] = value_C[i];
			}
			return result_C;
		case D:
			double[] result_D = new double[cutLength];
			double[] value_D = (double[]) obj;
			if(value_D.length <= cutLength) return value_D;
			for(int i =0;i<cutLength;i++){
				result_D[i] = value_D[i];
			}
			return result_D;
		}
		return obj;
	}
}
