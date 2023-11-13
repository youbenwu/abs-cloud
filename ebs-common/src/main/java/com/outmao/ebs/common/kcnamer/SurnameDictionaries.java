package com.outmao.ebs.common.kcnamer;

/**
 * 
 * <h2>青阳龙野中文姓名随机生成工具-姓氏字典</h2>
 * <p>该接口定义了获取随机中文名生成工具（KCNamer）需要使用的姓氏字典的格式，
 * 随机中文名工具将通过这个字典获取能够使用的姓氏集合。如果您需要自定义姓氏生成范围，
 * 请创建自定义姓氏字典类并按照功能说明实现这个接口。
 * </p>
 * @author 青阳龙野(kohgylw)
 * @version 1.0
 */
public interface SurnameDictionaries {

	/**
	 * 
	 * <h2>检查当前姓氏字典中是否有某一姓氏</h2>
	 * <p>
	 * 实现：该方法检查传入的字符串是否存在于姓氏字典中。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param testsurname
	 *            String 需要检查的姓氏字符串
	 * @return boolean 表示是否存在
	 */
	boolean haveSurnameOf(String testsurname);

	/**
	 * 
	 * <h2>获取当前姓氏集合规模</h2>
	 * <p>
	 * 实现：以int形式返回当前数据集合的规模
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return int 集合规模
	 */
	int getSurnameSetSize();

	/**
	 * 
	 * <h2>从姓氏字典中随机获取一个单姓</h2>
	 * <p>
	 * 实现：该方法每次调用均重新随机。如果姓氏集合为空，则返回一个空字符串。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return String 随机的单字姓氏
	 */
	String getRandomSingleSurname();

	/**
	 * 
	 * <h2>从姓氏字典中随机获取一个复姓</h2>
	 * <p>
	 * 实现：该方法每次调用均重新随机。如果双字姓氏字典为空，则返回一个空字符串。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return String 随机的双字姓氏
	 */
	String getRandomDoubleSurname();
	
	/**
	 * 
	 * <h2>得到单姓与复姓的比率</h2>
	 * <p>实现：该方法返回复姓集合规模占姓氏总规模的比值，该值会决定姓名随机生成时使用单复姓的占比。其值应小于1。</p>
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return double 比值
	 */
	double getSingleDoubleRate();

}