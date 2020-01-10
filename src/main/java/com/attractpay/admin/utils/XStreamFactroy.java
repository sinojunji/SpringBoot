package com.attractpay.admin.utils;

import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;


public class XStreamFactroy {
	
	private static final String START_CADA = "<![CDATA[";
	private static final String END_CADA = "]]>";

	/**
	 * 是否启用<![CDATA[]]>
	 * 
	 * @param flag
	 *            true 表示启用 false表示不启用
	 * @return
	 */
	public static XStream init(boolean flag) {
		XStream xstream = null;
		if (flag) {
			xstream = new XStream(new XppDriver() {
				public HierarchicalStreamWriter createWriter(Writer out) {
					return new PrettyPrintWriter(out) {
						@Override
						protected void writeText(QuickWriter writer, String text) {
							if (!text.startsWith(START_CADA)) {
								text = START_CADA + text + END_CADA;
							}
							writer.write(text);
						}
					};
				}
			});
		} else {
			xstream = new XStream();
		}
		return xstream;
	}

	/**
	 * 用于处理在实体对象中带有_的属性,如果用上述方法，会出现有两个__,导致结果不正确! 属性中有_的属性一定要有改方法
	 * 
	 * @return 返回xstream 对象 new DomDriver("UTF-8", new
	 *         XmlFriendlyNameCoder("-_", "_")
	 */
	public static XStream initSplitLine() {
		XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
		return xstream;
	}
	
	/**
	 * 实体转xml字符串
	 * @param obj
	 * @return
	*/
	 public static String toXml(Object obj) {
		 XStream xstream = new XStream(new DomDriver("UTF-8"));
	     xstream.processAnnotations(obj.getClass()); // 识别obj类中的注解
	     return xstream.toXML(obj);
	 }

	 /**
	      * 字符串转实体
	  * @param xmlStr
 	  * @param cls
 	  * @return
 	  */
	 public static <T> T toBean(String xmlStr, Class<T> cls) {
		 XStream xstream = new XStream(new DomDriver());
		 xstream.processAnnotations(cls);
		 @SuppressWarnings("unchecked")
		 T t = (T) xstream.fromXML(xmlStr);
		 return t;
	 }
	 
}