package com.cheshun.common.tools.utils;

import com.cheshun.common.component.ComponentRegist;
import org.springframework.context.ApplicationContext;

public class SpringBootUtil {
	
	public static <C> int regist(ApplicationContext context, Class<C> cls, ComponentRegist<C> regist) {
		String[] cs = context.getBeanNamesForType(cls);
		int num = 0;
		if(cs != null && cs.length > 0) {
			for(String c : cs) {
				Object obj = context.getBean(c);
				if(obj != null) {
					@SuppressWarnings("unchecked")
					//不检测转化
					C si = (C) obj;
					regist.regist(si);
					num++;
				}
			}
		}
		return num;
	}
	
//	public static <T, C> int registByType(ApplicationContext context, Class<C> cls, ComponentRegistType<T, C> type, ComponentRegistByType<T, C> regist) {
//		String[] cs = context.getBeanNamesForType(cls);
//		int num = 0;
//		if(cs != null && cs.length > 0) {
//			for(String c : cs) {
//				Object obj = context.getBean(c);
//				if(obj != null) {
//					@SuppressWarnings("unchecked")
//					//不检测转化
//					C si = (C) obj;
//					regist.regist(type.getRegistType(si), si);
//					num++;
//				}
//			}
//		}
//		return num;
//	}
}
