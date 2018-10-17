package com.datacenter.core.utils.excel;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtils {

	public static Map<String, String> strtingTomap(String string,
			String split_1, String split_2) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if ("".equals(string) || null == string || string.length() < 1) {
			return map;
		}
		String[] sl = string.split(split_1);
		if (sl.length >= 1) {
			for (String s : sl) {
				String[] sl_ = s.split(split_2);
				String key = sl_[0];
				String value = "";
				try {
					value = sl_[1];
				} catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
				if (null != key && null != value) {
					map.put(key, value);
				}
			}
		}
		return map;
	}

	public static String toURL(Map<String, String> map) {
		StringBuffer buf = new StringBuffer();
		boolean logo = false;
		for (String key : map.keySet()) {
			if (logo) {
				buf.append("&");
			}
			buf.append(key);
			buf.append("=");
			buf.append(map.get(key));
			logo = true;
		}
		return buf.toString();
	}

	public static String getRandomfor6() {
		int tmp = Math.abs(ThreadLocalRandom.current().nextInt());
		return String.valueOf(tmp % (999999 - 100000 + 1) + 100000);
	}

	public static int getRandomfor1() {
		int tmp = Math.abs(ThreadLocalRandom.current().nextInt());
		return Integer.valueOf(String.valueOf(tmp % (9 - 1 + 1) + 2));
	}

	public static boolean isEmpty(Collection<?> collection) {
		return collection != null && collection.size() > 0 ? false : true;
	}

	public static int getRandomNum(int low, int up) {
		int diff = up - low;
		if (diff > 0) {
			return low + ThreadLocalRandom.current().nextInt(diff);
		}
		return up;
	}

	/**
	 * 方法名称:getFactorial TODO(阶乘)
	 * 
	 * @param n
	 * @return
	 */
	public static long getFactorial(int n) {
		/*
		 * 数学上没有负数的阶乘的概念，因此n必须大于等于0
		 */
		if ((n < 0)) {
			return -1;
		} else if (n == 0) {
			return 1; // 0！是1
		} else {
			long result = 1;
			for (; n > 0; n--) {
				result *= n;
			}
			return result;
		}
	}

	public static int getInteger(float f) {
		return Math.round(f);
	}

	/**
	 * 保留小数点后2位
	 */
	public static float formatFloatNum(double f) {
		DecimalFormat df = new DecimalFormat("###.00");
		return Float.parseFloat(df.format(f));
	}

	/**
	 * 保留小数点1位
	 * 
	 * @param f
	 * @return
	 */
	public static float formatFloatNum2(float f) {
		DecimalFormat df = new DecimalFormat("###.0");
		return Float.parseFloat(df.format(f));
	}

	public static int getNo(String string, int index, int length) {
		return Integer
				.parseInt(string.substring(index - 1, index + length - 1));
	}

	public static String getString(String string, int index, int length) {
		return string.substring(index - 1, index + length - 1);
	}

	/**
	 * 方法名称:getTypeById TODO(根据生物Id得到生物类型，�?过Id前缀确定)
	 * 
	 * @param id
	 * @return -1：异常，0：人�?：�?�?：武�?
	 */
	public static int getTypeById(String id) {
		if (id == null) {
			return -1;
		}
		if (id.startsWith("M")) {
			return 1;
		}
		if (id.startsWith("H")) {
			return 2;
		}
		if (id.startsWith("T")) {
			return 3;
		}
		return 0;
	}

	public static String replace(String strSource, String strFrom, String strTo) {
		if (strSource == null) {
			return null;
		}
		int i = 0;
		if ((i = strSource.indexOf(strFrom, i)) >= 0) {
			char[] cSrc = strSource.toCharArray();
			char[] cTo = strTo.toCharArray();
			int len = strFrom.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = strSource.indexOf(strFrom, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		}
		return strSource;
	}

	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	public static int isAmOrPm() {
		GregorianCalendar ca = new GregorianCalendar();
		return ca.get(GregorianCalendar.AM_PM);
	}

	public static boolean teshuzifu(String str) {
		// String regEx =
		// "[^`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]{1,}";
		String regEx = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.matches()) {
			return true;
		}
		return false;
		// String regEx2 = "[\u4e00-\u9fa5]{0,}";
		// Pattern p2 = Pattern.compile(regEx2);
		// Matcher m2 = p.matcher(str);
		// String regEx3 =
		// "[^`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]{1,}";
		// Pattern p3 = Pattern.compile(regEx3);
		// Matcher m3 = p.matcher(str);
		// if(m.matches() && ){
		//
		// }
		// return m.matches();
	}

	public static boolean teshuzifu1(String str) {
		// String regEx =
		// "[^`~!@#$%^&*()+=|{}':'\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]{1,}";
		String regEx = "[^`#<>]{1,}";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.matches()) {
			return true;
		}
		return false;
		// String regEx2 = "[\u4e00-\u9fa5]{0,}";
		// Pattern p2 = Pattern.compile(regEx2);
		// Matcher m2 = p.matcher(str);
		// String regEx3 =
		// "[^`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]{1,}";
		// Pattern p3 = Pattern.compile(regEx3);
		// Matcher m3 = p.matcher(str);
		// if(m.matches() && ){
		//
		// }
		// return m.matches();
	}

	/**
	 * 判断字段是否在长度范围内
	 * 
	 * @param minLen
	 * @param maxLen
	 * @return
	 */
	public static boolean checkStringLength(int minLen, int maxLen, String str) {
		int strLength = str.length();
		if (strLength >= minLen && strLength <= maxLen) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是字母和数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean matchNumAndLetter(String str) {
		String regEx = "^[a-zA-Z0-9]+$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean matchNum(String str) {
		String regEx = "^[0-9]+$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是字母和中文
	 * 
	 * @param str
	 * @return
	 */
	public static boolean matchChineseAndLetter(String str) {
		String regEx = "^[a-zA-Z\u4e00-\u9fa5]+$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否有空格
	 * 
	 * @param str
	 * @return
	 */
	public static boolean matchSpace(String str) {
		String regEx = "^[^\\s]+$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.matches()) {
			return false;
		}
		return true;
	}

	// []闭区间，()开区间
	public static boolean isInScope(String scope, String value) {
		String[] scopes = scope.split(",");
		Float valueF = Float.valueOf(value);
		Float min = Float.valueOf(scopes[0].substring(1));

		if ("(".equals(String.valueOf(scopes[0].charAt(0)))) {
			if (valueF <= min)
				return false;
		} else if ("[".equals(String.valueOf(scopes[0].charAt(0)))) {
			if (valueF < min)
				return false;
		}

		Float max = Float
				.valueOf(scopes[1].substring(0, scopes[1].length() - 1));
		if (")".equals(String.valueOf(scopes[1].charAt(scopes[1].length() - 1)))) {
			if (valueF >= max)
				return false;
		} else if ("]".equals(String.valueOf(scopes[1].charAt(scopes[1]
				.length() - 1)))) {
			if (valueF > max)
				return false;
		}

		return true;
	}

	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/** * 将字母表示的列转为用数字表示(如A=1，AB=28) */
	public static int convertLetterToNum(String letters) {
		letters = letters.replaceAll("\\d", "");// 只留字母
		letters = letters.toUpperCase();
		int v = 0;
		String regex = "[A-Z]+";
		if (!letters.matches(regex))
			return v;
		int radix = 1;
		for (int i = letters.length() - 1; i >= 0; i--) {
			char c = letters.charAt(i);
			v += (c - 'A' + 1) * radix;
			radix *= 26;
		}
		return v;
	}

	/** * 将数字表示的列转为字母表示(如1=A，28=AB,704=AAB) */
	public static String convertNumToLetter(int colNum) {
		String colLetter = "";
		do {
			colNum--;
			colLetter = ((char) (colNum % 26 + (int) 'A')) + colLetter;
			colNum = (int) ((colNum - colNum % 26) / 26);
		} while (colNum > 0);
		return colLetter;
	}

	public static void main(String[] args) {
		System.out.println(StringUtils.convertLetterToNum("T10"));
	}

	/**
	 * 功能：验证字符串长度是否符合要求，一个汉字等于两个字符
	 * 
	 * @param value
	 *            要验证的字符串
	 * @param limitLength
	 *            验证的长度
	 * @return 符合长度ture 超出范围false
	 */
	public static boolean validateStrByLength(String value, int limitLength) {
		try {
			int length = value.getBytes("GBK").length;
			if (length <= limitLength && length >= 1) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 方法名称:isOpenTimePast TODO(开服时间是否已经过几天)
	 * 
	 * @param openTime
	 * @return
	 */
	public static boolean isOpenTimePast(long openTime, int day) {
		return openTime + day * 24 * 60 * 60 * 1000 > System
				.currentTimeMillis() ? false : true;
	}

	// 空字符串。
	public static final String EMPTY_STRING = "";

	/**
	 * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)      = true
	 * StringUtil.isEmpty(&quot;&quot;)        = true
	 * StringUtil.isEmpty(&quot; &quot;)       = false
	 * StringUtil.isEmpty(&quot;bob&quot;)     = false
	 * StringUtil.isEmpty(&quot;  bob  &quot;) = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * @return 如果为空, 则返回<code>true</code>
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 除去字符串头尾部的空白，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.trim(null)          = null
	 * StringUtil.trim(&quot;&quot;)            = &quot;&quot;
	 * StringUtil.trim(&quot;     &quot;)       = &quot;&quot;
	 * StringUtil.trim(&quot;abc&quot;)         = &quot;abc&quot;
	 * StringUtil.trim(&quot;    abc    &quot;) = &quot;abc&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要处理的字符串
	 * @return 除去空白的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * 除去字符串头尾部的空白，如果结果字符串是空字符串<code>""</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.trimToNull(null)          = null
	 * StringUtil.trimToNull(&quot;&quot;)            = null
	 * StringUtil.trimToNull(&quot;     &quot;)       = null
	 * StringUtil.trimToNull(&quot;abc&quot;)         = &quot;abc&quot;
	 * StringUtil.trimToNull(&quot;    abc    &quot;) = &quot;abc&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要处理的字符串
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
	 *         <code>null</code>
	 */
	public static String trimToNull(String str) {
		if (str == null) {
			return null;
		}

		String result = str.trim();

		if (result == null || result.length() == 0) {
			return null;
		}

		return result;
	}

	/**
	 * 除去字符串头尾部的空白，如果字符串是<code>null</code>，则返回空字符串<code>""</code>。
	 * 
	 * <pre>
	 * StringUtil.trimToEmpty(null)          = &quot;&quot;
	 * StringUtil.trimToEmpty(&quot;&quot;)            = &quot;&quot;
	 * StringUtil.trimToEmpty(&quot;     &quot;)       = &quot;&quot;
	 * StringUtil.trimToEmpty(&quot;abc&quot;)         = &quot;abc&quot;
	 * StringUtil.trimToEmpty(&quot;    abc    &quot;) = &quot;abc&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要处理的字符串
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
	 *         <code>null</code>
	 */
	public static String trimToEmpty(String str) {
		if (str == null) {
			return EMPTY_STRING;
		}

		String result = str.trim();

		if (result == null) {
			return EMPTY_STRING;
		}

		return result;
	}

	/**
	 * 如果字符串是<code>null</code>或空字符串<code>""</code>，则返回指定默认字符串，否则返回字符串本身。
	 * 
	 * <pre>
	 * StringUtil.defaultIfEmpty(null, &quot;default&quot;)  = &quot;default&quot;
	 * StringUtil.defaultIfEmpty(&quot;&quot;, &quot;default&quot;)    = &quot;default&quot;
	 * StringUtil.defaultIfEmpty(&quot;  &quot;, &quot;default&quot;)  = &quot;  &quot;
	 * StringUtil.defaultIfEmpty(&quot;bat&quot;, &quot;default&quot;) = &quot;bat&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要转换的字符串
	 * @param defaultStr
	 *            默认字符串
	 * @return 字符串本身或指定的默认字符串
	 */
	public static String defaultIfEmpty(String str, String defaultStr) {
		return str == null || str.length() == 0 ? defaultStr : str;
	}

	/**
	 * 检测给定的对象是否<strong>为空</strong>。
	 * <p>
	 * <strong>object</strong> 类型列表：
	 * <ul>
	 * <li>java.util.Collection --&gt; 为null或者长度为 0 时，返回 {@code true}，否则
	 * {@code false}。</li>
	 * <li>java.util.Iterator --&gt; 为null或者无下一个元素时，返回 {@code true}，否则
	 * {@code false}。</li>
	 * <li>java.util.Map --&gt; 为null或者大小为 0 时，返回 {@code true}，否则{@code false}。</li>
	 * <li>java.lang.String --&gt; 为null或者为空字符串时，返回 {@code true}，否则{@code false}
	 * 。</li>
	 * <li>java.util.Enumeration --&gt; 为null或者无下一个元素时，返回 {@code true}，否则
	 * {@code false}。</li>
	 * <li>java.lang.Array --gt; 为null或者长度为 0 时，返回 {@code true}，否则{@code false}。
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @param object
	 *            要检测的对象。
	 * @return 若检测的对象为空(值)，返回 {@code true}，否则{@code false}。
	 */
	@SuppressWarnings({ "rawtypes" })
	public static boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}
		if (object instanceof CharSequence) {
			return isNull(object.toString());
		} else if (object instanceof Collection) {
			return ((Collection) object).size() == 0;
		} else if (object instanceof Map) {
			return ((Map) object).size() == 0;
		} else if (object instanceof Iterator) {
			return !((Iterator) object).hasNext();
		} else if (object instanceof Enumeration) {
			return !((Enumeration) object).hasMoreElements();
		} else if (object.getClass().isArray()) {
			try {
				return Array.getLength(object) == 0;
			} catch (IllegalArgumentException e) {
				return true;
			}
		}
		return isNull(object.toString());
	}

	/**
	 * 判断此字符串是否为空、空字符串（包括只含空格的字符串）
	 * 
	 * <pre>
	 * StringUtils.isNullStr(null)      = true
	 * StringUtils.isNullStr( )        = true
	 * StringUtils.isNullStr(   )       = true
	 * StringUtils.isNullStr( bob )     = false
	 * StringUtils.isNullStr(   bob   ) = false
	 * </pre>
	 * 
	 * @param str
	 *            待检查的字符串
	 * @return 如果为null或空字符串（包括只含空格的字符串）则返回true，否则返回false
	 */
	public static boolean isNull(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static String toString(Throwable e) {
		UnsafeStringWriter w = new UnsafeStringWriter();
		PrintWriter p = new PrintWriter(w);
		p.print(e.getClass().getName());
		if (e.getMessage() != null) {
			p.print(": " + e.getMessage());
		}
		p.println();
		try {
			e.printStackTrace(p);
			return w.toString();
		} finally {
			p.close();
		}
	}

	public static String toString(String msg, Throwable e) {
		UnsafeStringWriter w = new UnsafeStringWriter();
		w.write(msg + "\n");
		PrintWriter p = new PrintWriter(w);
		try {
			e.printStackTrace(p);
			return w.toString();
		} finally {
			p.close();
		}
	}
	
	public static int getOpneTimePast(long openTime) {
		return (int) ((System.currentTimeMillis() - openTime) / (24 * 60 * 60 * 1000));
	}
}
