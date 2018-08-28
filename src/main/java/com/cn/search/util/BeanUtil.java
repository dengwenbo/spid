package com.cn.search.util;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtil {
	
	
	public static <E> E  mapToBean(Map map, Class<E> beanClass) {
		if(map == null) {
			return null;
		}
		E obj =null;
		try {
			 obj= beanClass.newInstance();
			BeanUtils.populate(obj, map);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
