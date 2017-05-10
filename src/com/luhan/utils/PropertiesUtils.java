package com.luhan.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**@Description {properties文件的工具类}
 * @author luhan
 * @date   2017年5月4日 上午11:09:12
 */
public class PropertiesUtils {
	
	/**
	 * @desic  {读取pro文件内容}
	 * @author luhan
	 * @param proPath pro文件的地址
	 * @return 返回装有key值和value值的map集合
	 */
	public static  Map<String, String> parseProperties(String proPath){
		Properties pps = new Properties();
		InputStreamReader reader = null;
		FileInputStream stream = null;
		Map<String, String> result = new LinkedHashMap<String, String>();
		          try {
		        	  stream = new FileInputStream(proPath);
		        	  reader = new InputStreamReader(stream, "utf-8");
					pps.load(reader);
					Enumeration<?> enum1 = pps.propertyNames();//得到配置文件的名字
			          while(enum1.hasMoreElements()) {
			             String strKey = (String) enum1.nextElement();
			             String strValue = pps.getProperty(strKey);
			             
			             result.put(strKey, strValue);
			         }
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}finally{
					if(stream != null){
						try {
							stream.close();
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		     
		return result;
	  }
}
