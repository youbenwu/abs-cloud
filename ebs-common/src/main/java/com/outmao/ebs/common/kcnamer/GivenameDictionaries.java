package com.outmao.ebs.common.kcnamer;

/**
 * 
 * <h2>青阳龙野中文姓名随机生成工具-名称字典</h2>
 * <p>该接口定义了获取随机中文名生成工具（KCNamer）需要使用的名称字典的格式，
 * 随机中文名工具将通过这个字典获取能够使用的名称集合。如果您需要自定义名称生成范围，
 * 请创建自定义名称字典类并按照功能说明实现这个接口。
 * </p>
 * @author 青阳龙野(kohgylw)
 * @version 1.0
 */
public interface GivenameDictionaries {
	
	/**
	 * 
	 * <h2>从名称字典中随机获取一个男性单字名</h2>
	 * <p>
	 * 实现：该方法每次调用均重新随机。如果男性单字名字典为空，则返回一个空字符串。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return String 随机的男性单字名
	 */
	String getRandomSingleMaleGivename();
	
	/**
	 * 
	 * <h2>从名称字典中随机获取一个男性双字名</h2>
	 * <p>
	 * 实现：该方法每次调用均重新随机。如果男性双字名字典为空，则返回一个空字符串。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return String 随机的男性双字名
	 */
	String getRandomDoubleMaleGivenname();
	
	/**
	 * 
	 * <h2>从名称字典中随机获取一个女性单字名</h2>
	 * <p>
	 * 实现：该方法每次调用均重新随机。如果女性单字名字典为空，则返回一个空字符串。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return String 随机的女性单字名
	 */
	String getRandomSingleFemaleGivename();
	
	/**
	 * 
	 * <h2>从名称字典中随机获取一个女性双字名</h2>
	 * <p>
	 * 实现：该方法每次调用均重新随机。如果女性双字名字典为空，则返回一个空字符串。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return String 随机的女性双字名
	 */
	String getRandomDoubleFemaleGivename();
	
	/**
	 * 
	 * <h2>检查当前名称字典中是否有某一名称</h2>
	 * <p>
	 * 实现：该方法检查传入的字符串是否存在于名称字典中。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param testgivename
	 *            String 需要检查的名称字符串
	 * @return boolean 表示是否存在
	 */
	boolean haveGivenameOf(String testgivename);
	
}
