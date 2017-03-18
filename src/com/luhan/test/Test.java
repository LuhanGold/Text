package com.luhan.test;

import java.util.ArrayList;
import java.util.List;
import com.luhan.Constant;
import com.luhan.utils.DataUtils;

/** 
* @ClassName: Test2 
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
		testListPaging();
	}
	/**
	 * 使用最有效率的算出 2*8
	 */
	private static void testTwoRideEight(){
		//使用最有效率的算出2*8的结果
		System.out.println(2<<3);
	}
	/**
	 * Gc的调用方法
	 */
	private static void testGc(){
		//请求垃圾收集
		Runtime.getRuntime().gc();
	}
	
	/**
	 * 测试List集合分页的方法
	 */
	private static void testListPaging(){
		//先创建一个装有数据的list集合,先进行测试String和基本数据类型
		List<Entity> list_str = new ArrayList<Entity>();
		//因为list集合现在是空的，所以我们需要先进行填充值，填充10条吧，一页显示4条，测试用，尽量不弄那么大
		for (int i = 0; i < Constant.DATA_SIZE; i++) {
			//实例化实体类
			Entity entity = new Entity();
			entity.setName("我叫"+i);
			String sex = i%2 == 0 ? "男":"女";
			entity.setSex(sex);
			entity.setAge(i);
			//我们直接进行添加索引吧
			list_str.add(entity);
		}
		//添加完后调用数据工具类写的分页方法进行输出测试
		List<Entity> data = DataUtils.datepaging(list_str, 15555, displayDataSize);
		//进行循环打印,使用迭代来进行打印
		for (Entity entity : data) {
			System.out.println(entity.getName()+","+"我是"+entity.getSex()+"的,"+"今年"+entity.getAge()+"岁");
		}
	}
	
	
	/**
	* @ClassName: entity 
	* @Description: (实体类) 
	* @author Luhan 
	* @date 2017年3月18日 下午6:40:09 
	*
	 */
	public static class Entity{
		
		private String name;
		private String sex;
		private int age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	}
	
	
	
	
	
	
}