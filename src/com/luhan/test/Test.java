package com.luhan.test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.luhan.Constant;
import com.luhan.costom.CustomException;
import com.luhan.entity.TestDatePagingEntity;
import com.luhan.utils.DataUtils;
import com.luhan.utils.DateUtils;
import com.luhan.utils.FileUtils;

/** 
* @ClassName: Test 
* @Description: (用于测试使用) 
* @author Luhan 
* @date 2017年3月17日 上午11:18:06
*/
public class Test {
	
	private static int displayDataSize = 4;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			 FileUtils.writeFile("E:\\1.txt","haha");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		//读取文件下img文件的数量
//		String filePath = "E:\\Web Workspace\\LuhanBlog-web\\img";
//		File[] files;
//		try {
//			files = FileUtils.getFilesBySuffixName(filePath, ".txt");
//			System.out.println("jpg的数量是:"+files.length);
//		} catch (CustomException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//百分比值
//		try {
//			System.out.println(DataUtils.countPercentReturnString(1, 6,-2));
//		} catch (CustomException e) {
//			e.printStackTrace();
//		}
//		testListPaging();//测试集合分页
//		testTimesTampToDate();//测试时间戳转化为日期
//		testDateToTimesStamp();//测试日期转化为时间戳
		
	}
	/**
	 * 使用最有效率的算出 2*8
	 */
	@SuppressWarnings("unused")
	private static void testTwoRideEight(){
		//使用最有效率的算出2*8的结果
		System.out.println(2<<3);
	}
	/**
	 * Gc的调用方法
	 */
	@SuppressWarnings("unused")
	private static void testGc(){
		//请求垃圾收集
		Runtime.getRuntime().gc();
	}
	
	/**
	 * 测试List集合分页的方法
	 */
	@SuppressWarnings(value = "unused" )
	private static void testListPaging(){
		//先创建一个装有数据的list集合,先进行测试String和基本数据类型
		List<TestDatePagingEntity> list_str = new ArrayList<TestDatePagingEntity>();
		//因为list集合现在是空的，所以我们需要先进行填充值，填充10条吧，一页显示4条，测试用，尽量不弄那么大
		for (int i = 0; i < Constant.DATA_SIZE; i++) {
			//实例化实体类
			TestDatePagingEntity entity = new TestDatePagingEntity();
			entity.setName("我叫"+i);
			String sex = i%2 == 0 ? "男":"女";
			entity.setSex(sex);
			entity.setAge(i);
			
			//向list里面添加数据
			list_str.add(entity);
		}
		//添加完后调用数据工具类写的分页方法进行输出测试
		List<TestDatePagingEntity> data = DataUtils.datepaging(list_str, 1, displayDataSize);
		//进行循环打印,使用迭代来进行打印
		for (TestDatePagingEntity entity : data) {
			System.out.println(entity.getName()+","+"我是"+entity.getSex()+"的,"+"今年"+entity.getAge()+"岁");
		}
	}
	
	/**
	 * 用于测试时间戳转化为日期
	 */
	@SuppressWarnings("unused")
	private static void testTimesTampToDate(){
		Date date = new Date();
		Long timesStamp = date.getTime();
		System.out.println("转化之前:" + timesStamp);
		String result = DateUtils.timesStampToDate(timesStamp);
		System.out.println("转化之后:" + result);
	}
	/**
	 * 用于测试日期转化为时间戳
	 */
	@SuppressWarnings("unused")
	private static void testDateToTimesStamp(){
		String time = "2017-03-18 19:30:19";
		System.out.println("转化之前:" + time);
		try {
			Long result = DateUtils.dateToTimestamp(time);
			System.out.println("转化之后:" + result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}