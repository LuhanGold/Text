package com.luhan.text;

import java.util.ArrayList;
import java.util.List;

/** 
* @ClassName: Text2 
* @Description: (集合的分页算法) 
* @author Luhan 
* @date 2017年3月16日 下午17:18:06
*/
public class Text1 {
	private static List<Integer> date;
	private static Integer PaseSize = 6;//显示多少条
	private static Integer mPageNum = 30;//总条数

	public static void main(String[] args) {
		//传入的是显示第几页
		showList(2);
	}
	/**
	 * 显示数据，分页显示list里面的数据
	 * @param pageNum 页数
	 */
	public static void showList(int pageNum){
		date = new ArrayList<Integer>();
		for (int i = 0; i < mPageNum; i++) {
			date.add(i+1);
		}
		/**这里是决定一页只会显示6条
		 * 
		 * for循环的左边 i等于的值
		 * 当是第一页的时候 1  对  0;索引应该从0开始
		 * 当是第二页的时候 2  对  6;索引应该从6开始
		 * 当是第三页的时候 3  对  12;索引应该从12开始
		 * 当是第四页的时候 4  对  18;索引应该从18开始
		 * 			  .
		 * 			  .
		 * 			  .
		 * 			以此类推
		 * 当是第n页的时候索引就应该是从(当前页数-1)*显示条数
		 * 
		 * 
		 * 
		 * for循环的右边 i小于的值,这里假设只有20条数据
		 * 当是第一页的时候 1  对  0~6;右边的数应该为6
		 * 当是第一页的时候 2  对  6~12;右边的数应该为6
		 * 当是第一页的时候 3  对  12~18;右边的数应该为6
		 * 当是第一页的时候 4  对  18~20;右边的数应该为20,这里为什么会是20而不是24是因为我们只有20条数据,
		 * 显然i不能小于24,这样后面21、22、23根本就没有值,所以这应该是20
		 * 设 x = (当前页数-1)*显示条数;
		 * 最后得出右边的数值应该为:判断x是不是大于数据的总条数,如果大于就显示总条数,如果小于就显示x
		 * 所以这里我们可以使用三元运算符来解决就是下方的表达式
		 * ((pageNum-1)*PaseSize)+PaseSize > mPageNum ? mPageNum:((pageNum-1)*PaseSize)+PaseSize)
		 * 先判断下(当前是第几页-1乘以显示条数)是不是大于总条数,如果大于总条数就返回总条数,如果小于总条数则返回(当前是第几页-1乘以显示条数)
		 */
		List<Integer> newDate = new ArrayList<Integer>();
		for (int i = (pageNum-1)*PaseSize; i < (
				((pageNum-1)*PaseSize)+PaseSize > mPageNum ? mPageNum:((pageNum-1)*PaseSize)+PaseSize); i++) {
			newDate.add(date.get(i));
		}
		System.out.println(">>>>>>>>>>第"+pageNum+"页的数据>>>>>>>>>>>>");
		for (int i = 0; i < newDate.size(); i++) {
			System.out.println(newDate.get(i));
		}
	}	
}
