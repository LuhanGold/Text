package com.luhan.test;
/** 
* @ClassName: IntegerAndIntTest 
* @Description: (用于对比int和Intger的区别) 
* @author Luhan 
* @date 2017年3月20日 上午11:12:56 
*  
*/
public class IntegerAndIntTest{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int i1 = 128;
		Integer i2 = 128;
		System.out.println(i1 == i2);//输出true,只要是int和Integer进行对比都为true
		
		System.out.println(">>>>>>>>>>>这是一条分割线>>>>>>>>>>>>");
		
		Integer i3 = 126;
		Integer i4 = new Integer(126);
		System.out.println(i3 == i4);//输出false,因为i4new了一个新的对象，而i3是存到堆里面了,而i4存到内存中，所以两个地址不一致
		
		System.out.println(">>>>>>>>>>>这是一条分割线>>>>>>>>>>>>");
		
		
		//示例一:
		Integer i5 = 126;
		Integer i6 = 126;
		System.out.println(i5 == i6);//输出true
		
		System.out.println(">>>>>>>>>>>这是一条分割线>>>>>>>>>>>>");
		
		//示例二:
		Integer i7 = 128;
		Integer i8 = 128;
		System.out.println(i7 == i8);//输出false
		//示例一和示例二的为什么打印的结果不同,在Integer类中对于赋值时使用的则是Integer.valueOf()方法
		/**				Integer.valueOf()方法为:
		 * public static Integer valueOf(int i) {//其中low的值为-128,height为127
        	if (i >= IntegerCache.low && i <= IntegerCache.high)
            	return IntegerCache.cache[i + (-IntegerCache.low)];
        	return new Integer(i);
           }
           	表明的意思为:Integer的值为如果在-128~127之间，就会从缓存之间取出来，而不再范围之间的话则会重新new一个
		 */
		/**
		 * 总结:int和integer的区别
		 * 1.integer为int的包装类
		 * 2.int和integer进行比对的时候都为true，当然是赋值为同一个数字时
		 * 3.integer直接赋值和new出来进行比对为false，前者存到堆后者存到内存
		 * 4.integer进行比对在-128~127之间为true其余为false，在范围内integer会进行缓存，不在范围内不会进行缓存而是直接new
		 */
	}
}
