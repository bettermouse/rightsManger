package com.chen.util;

import com.chen.PageModel.PageHelper;

public class PagerUtils {
	public static String orderHql(PageHelper pageHelper) {
		String orderString = "";
		if (pageHelper.getSort() != null && pageHelper.getOrder() != null) {
			orderString = " order by t." + pageHelper.getSort() + " "
					+ pageHelper.getOrder();
		}
		return orderString;
	}
}
