/**
 * 
 */
package com.luhan.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.luhan.entity.HttpParam;

/**@Description {网络工具类}
 * @author luhan
 * @date   2017年4月9日 上午10:33:54
 */
public class NetWorkUtil {
	
	private static URL httpUrl = null;
	private static URLConnection connection = null;
	//拼接请求参数
	private static StringBuffer wBuffer = new StringBuffer();
	//接收返回结果
	private static StringBuffer result = new StringBuffer();
	//创建读取对象
	private static BufferedReader reader = null;
	
	
	
	/**
	 * 初始化网络请求类
	 * @author luhan
	 * @param url 请求的地址
	 * @return 返回建立连接的connection
	 * @throws IOException
	 */
	private static URLConnection init(String url) throws IOException{
		if(httpUrl == null){
			httpUrl = new URL(url);
		}
		if(connection == null && httpUrl != null){
			connection = httpUrl.openConnection();
			connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	        connection.setRequestProperty("connection", "keep-alive");
	        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
	        connection.setDoOutput(true);
	        connection.setDoInput(true);
		}
		
		return connection;
	}
	
	public static String getJsonByURL(String url,List<HttpParam> params) throws IOException{
		//调用初始化方法获得连接对象
		connection = init(url);
		//创建参数写对象,通过连接对象的输出流获得
		PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
		if(params == null){
			params = new ArrayList<>();
		}
		//将参数以post方式写入连接对象中
		if(params.size() > 0){
			for (int i = 0; i < params.size(); i++) {
				wBuffer.append(params.get(i).getKey())
				.append("=")
				.append(URLEncoder.encode(params.get(i).getValue(),"utf-8"));
				//如果不是最后一个参数就拼接一个&
				if((i+1) < params.size()){
					wBuffer.append("&");
				}
			}
		}
		printWriter.println(wBuffer.toString());
		printWriter.flush();
		connection.connect();
		//接收服务器返回的结果
		reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		//读取到的行
		String line;
		while((line = reader.readLine())!= null){
			result.append(line);
		}
		reader.close();
		return result.toString();
	}
}