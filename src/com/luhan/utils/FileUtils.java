/**
 * 
 */
package com.luhan.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

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
	/**
	 * 读取txt文件里面的内容
	 * @param filePath 文件地址
	 * @return 返回读取后的内容
	 * @throws CustomException 抛出不是文件异常
	 * @throws IOException 
	 */
	public static String readFile(String filePath) throws CustomException, IOException{
		File file = new File(filePath);
		if(!file.exists()){
			throw new CustomException("没有这个文件");
		}
		//用来存储文件里面读取的内容
		StringBuffer content = new StringBuffer();
		//用来保存文件读取的一行内容
		String tempstr = "";
		//文件读取流
		FileInputStream inputStream = new FileInputStream(file);
		//文件读取操作对象流,设置读取的编码格式为GBK
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
		//循环读取，如果当读取到的内容为null是就停止读取
		while((tempstr=reader.readLine()) != null){
			content.append(tempstr);
		}
		
		//关闭读取流
		reader.close();
		inputStream.close();
		
		//最后返回读取的内容
		return content.toString();
	}
	/**
	 * 写文件的方法
	 * @param filePath 需要写到那个文件上
	 * @param content 写的内容是什么
	 * @param isReplace 是否追加到已经有的内容后面，默认是的
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static void writeFile(String filePath,String content,boolean isReplace) throws UnsupportedEncodingException, IOException{
		//通过传入的文件路径来新建一个文件
		File file = new File(filePath);
		//判断有没有这个文件，没有则则创建
		if(!file.exists()){
			file.createNewFile();
		}
		//获取文件写流
		FileOutputStream outputStream = new FileOutputStream(file,isReplace);
		//获取文件写的操作对象
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
		//写文件内容
		writer.write(content);

		//关闭流
		writer.flush();
		writer.close();
	}
	/**
	 * 文件写内容
	 * @param filePath 需要写到那个文件上
	 * @param content 写的内容是什么
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static void writeFile(String filePath,String content) throws UnsupportedEncodingException, IOException{
		writeFile(filePath,content,true);
	}
}
