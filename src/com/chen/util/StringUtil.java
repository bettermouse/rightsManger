package com.chen.util;

public class StringUtil {
	/**
	 * 判断字符串是否为空  不为空时返回true
	 * @param temp
	 * @return
	 */
	public  static Boolean  isNull(String temp){
			if(temp!=null&&!temp.equals("")){
				return true;
			}
			return false;
	} 
}
