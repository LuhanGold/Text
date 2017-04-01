/**
 * 
 */
package com.luhan.utils;

import java.io.File;
import java.io.FileFilter;

import com.luhan.costom.CustomException;

/**@effect {文件相关的工具类}
 * @author luhan
 * @date   2017年4月1日 下午3:06:46
 */
public class FileUtils {

	//私有的构造方法
	private FileUtils(){};
	
	/**
	 * 通过指定文件夹下的的指定后缀名称的文件
	 * @param filePath 文件夹路径
	 * @param suffixName 后缀名称
	 * @return	返回装有条件满足的文件的数组
	 * @throws CustomException 抛出不是文件夹的异常
	 */
	public static File[] getFilesBySuffixName(String filePath,String suffixName) throws CustomException{
		//TODO 参数校验
		
		//创建一个空的文件的数组用来装查找到满足条件的文件
		File[] files = new File[0];
		
		//通过文件路径获取文件
		File folder = new File(filePath);
		//判断这个文件是不是文件夹，如果是文件夹就继续，否则直接return
		if(!folder.isDirectory()){
			throw new CustomException("请输入文件夹的路径");
		}else{
			files = folder.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					//判断是不是文件，并且后缀是不是满足要求
					if(pathname.isFile() && pathname.getName().indexOf(suffixName) > -1){
						return true;
					}
					return false;
				}
			});
			return files;
		}
	}
}
