package com.outmao.ebs.common.kcnamer;

/**
 * <h2>青阳龙野中文姓名随机生成工具-随机中文名生成工具</h2>
 * <p>
 * 该类用于随机生成中文姓名，长度为2-4个字，使用utf-8编码格式。该过程依赖于姓氏字典
 * （GivenameDictionaries）定义的姓氏集合和名称字典（SurnameDictionaries）定义的名称集合。
 * 默认的随机姓名生成风格为百家姓+现代常用中文名风格（提示：虽然默认字典在多数情况下能给出基本满足
 * 常见命名规则的结果，但在有些时候，某系特定的[姓氏-名称]组合不一定好听，甚至不合常理。这也是默认 字典的不足所在，您可以通过自定义字典来弥补这一缺陷）。
 * </p>
 * <em> 注：如果您使用RandomChineseNameUtile()构造器来实例化该类的对象，则该对象会使用默认的姓氏字典和名称字典。
 * 默认的姓氏字典（DefultSurnameDictionaries）为《百家姓》的全部姓氏，包括单姓和复姓；
 * 默认的名称字典则包含了大多数适用于男性或女性的、可用于取名的、常见的单字汉字和双字词组。当然，
 * 您也可以自定义姓氏字典或名称字典，之后通过RandomChineseNameUtile(SurnameDictionaries
 * sd,GivenameDictionaries gd)构造器来实例化该类的对象，这样可以得到更加个性化的姓名风格
 * （例如使用古风风格特色的名称字典或洋气风格特色的名称字典）。 </em>
 * 
 * @see GivenameDictionaries
 *      名称字典（kohgylw.kcnamer.core.GivenameDictionaries）
 * @see SurnameDictionaries
 *      姓氏字典（kohgylw.kcnamer.core.SurnameDictionaries）
 * @author kohgylw
 * @version 1.0
 */
public class KCNamer {

	private SurnameDictionaries sd;// 使用的姓氏字典
	private GivenameDictionaries gd;// 使用的名称字典
	private double rate;

	/**
	 * 
	 * <h2>以默认字典实例化本工具的对象</h2>
	 * <p>
	 * 对象会以默认的姓名风格为基准随机生成中文姓名。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 */
	public KCNamer() {
		// 获取默认姓氏字典
		sd = DefultSurnameDictionaries.instance();
		gd = DefultGivenNameDictionaries.instance();
		rate = sd.getSingleDoubleRate();
	}

	/**
	 * 
	 * <h2>以指定字典实例化本工具的对象</h2>
	 * <p>
	 * 对象会以传入的字典风格为基准随机生成中文姓名。
	 * </p>
	 * <em>请确保传入的两个参数不为null，否则会抛出NullPointerException</em>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param sd
	 *            SurnameDictionaries 自定义的姓氏字典
	 * @param gd
	 *            GivenameDictionaries 自定义的名称字典
	 * @see SurnameDictionaries 姓氏字典
	 * @see GivenameDictionaries 名称字典
	 */
	public KCNamer(SurnameDictionaries sd, GivenameDictionaries gd) {
		// 获取数据字典
		if (sd != null && gd != null) {
			this.sd = sd;
			this.gd = gd;
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * 
	 * <h2>以默认规则获取一个随机生成的中文名称</h2>
	 * <p>
	 * 该方法会生成一个不限男女性特征，不限长度（2-4），随机姓氏的中文名。以String形式输出。
	 * </p>
	 * <em>注：考虑到日常命名习惯，生成姓名的长度将分别以40%，50%，10%的概率对应2，3，4个字。</em>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return String 随机生成的中文名
	 */
	public String getRandomName() {
		int sex = Math.random() < 0.5 ? 0 : 1;// 以50%的概率确定性别
		if (sex == 0) {
			return getRandomFemaleName();// 执行获取指定长度女性风格名称的方法
		} else {
			return getRandomMaleName();// 执行获取指定长度男性风格名称的方法
		}
	}

	/**
	 * 
	 * <h2>根据指定长度获取一个随机生成的男性风格的中文名</h2>
	 * <p>
	 * 该方法会生成使用NameLength枚举类指定长度的男性风格中文名。
	 * </p>
	 * <em> 注：如果长度限制为TWO，则使用【单姓】+【单字名】的生成方式。
	 * 如果长度限制为TREE，则可能性使用【单姓】+【双字名】或【复姓】+【单字名】的生成方式， 这取决于姓氏字典中单姓与复姓的数量比率。
	 * 如果长度限制为FOUR，则使用【复姓】+【双字名】的生成方式。 </em>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param nl
	 *            NameLength 指定生成长度
	 * @return String 生成的中文名
	 */
	public String getRandomMaleName(NameLength nl) {
		if (nl == null) {
			throw new NullPointerException();// 如果输入为null，则抛出空指针异常。
		} else {
			String sur;// 存储姓氏
			String giv;// 存储名称
			/*
			 * 生成规则：如果是2个字的姓名，只可能是单姓+单字名；如果是4个字的姓名，只可能是复姓+双字名；
			 * 如果是3个字的姓名，有两种可能：复姓+单字名、单姓+双字名。两种方式的使用占比与单姓数目/复姓数目一致。
			 */
			switch (nl) {
			case TWO:
				sur = sd.getRandomSingleSurname();// 从数据字典中获取一个单姓，下同
				giv = gd.getRandomSingleMaleGivename();// 从数据字典中获取一个单字男性名称，下同
				return sur + giv;
			case FOUR:
				sur = sd.getRandomDoubleSurname();
				giv = gd.getRandomDoubleMaleGivenname();
				return sur + giv;
			case THREE:
				int dice = Math.random() > rate ? 0 : 1;// 按照比值比率选取单复姓
				if (dice == 0) {// 使用单姓+双字名的方式生成姓名
					sur = sd.getRandomSingleSurname();
					giv = gd.getRandomDoubleMaleGivenname();
				} else {// 使用复姓+单字名的方式生成姓名
					sur = sd.getRandomDoubleSurname();
					giv = gd.getRandomSingleMaleGivename();
				}
				return sur + giv;

			default:
				return "";// 预留处理。
			}

		}
	}

	/**
	 * 
	 * <h2>根据指定长度获取一个随机生成的女性风格的中文名</h2>
	 * <p>
	 * 该方法会生成使用NameLength枚举类指定长度的女性风格中文名。
	 * </p>
	 * <em> 注：如果长度限制为TWO，则使用【单姓】+【单字名】的生成方式。
	 * 如果长度限制为TREE，则可能性使用【单姓】+【双字名】或【复姓】+【单字名】的生成方式， 这取决于姓氏字典中单姓与复姓的数量比率。
	 * 如果长度限制为FOUR，则使用【复姓】+【双字名】的生成方式。 </em>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param nl
	 *            NameLength 指定生成长度
	 * @return String 生成的中文名
	 */
	public String getRandomFemaleName(NameLength nl) {
		if (nl == null) {
			throw new NullPointerException();// 如果输入为null，则抛出空指针异常。
		} else {
			String sur;// 存储姓氏
			String giv;// 存储名称
			/*
			 * 生成规则：如果是2个字的姓名，只可能是单姓+单字名；如果是4个字的姓名，只可能是复姓+双字名；
			 * 如果是3个字的姓名，有两种可能：复姓+单字名、单姓+双字名。两种方式的使用占比与单姓数目/复姓数目一致。
			 */
			switch (nl) {
			case TWO:
				sur = sd.getRandomSingleSurname();// 从数据字典中获取一个单姓，下同
				giv = gd.getRandomSingleFemaleGivename();// 从数据字典中获取一个单字女性名称，下同
				return sur + giv;
			case FOUR:
				sur = sd.getRandomDoubleSurname();
				giv = gd.getRandomDoubleFemaleGivename();
				return sur + giv;
			case THREE:
				int dice = Math.random() > rate ? 0 : 1;// 按照比值比率选取单复姓
				if (dice == 0) {// 使用单姓+双字名的方式生成姓名
					sur = sd.getRandomSingleSurname();
					giv = gd.getRandomDoubleFemaleGivename();
				} else {// 使用复姓+单字名的方式生成姓名
					sur = sd.getRandomDoubleSurname();
					giv = gd.getRandomSingleFemaleGivename();
				}
				return sur + giv;

			default:
				return "";// 预留处理。
			}

		}
	}

	/**
	 * 
	 * <h2>使用随机长度获取一个随机生成的女性风格的中文名</h2>
	 * <p>
	 * 该方法会以30%，60%，10%的概率生成2，3，4个字的女性风格姓名。
	 * </p>
	 * <em> 注：详见getRandomFemaleName(NameLength nl)</em>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param nl
	 *            NameLength 指定生成长度
	 * @return String 生成的中文名
	 */
	public String getRandomFemaleName() {
		NameLength nl;
		double k = Math.random();
		// 以40%，40%，20%的概率确定姓名长度。
		if (k <= 0.3) {
			nl = NameLength.TWO;
		} else if (k > 0.3 && k <= 0.9) {
			nl = NameLength.THREE;
		} else {
			nl = NameLength.FOUR;
		}
		return getRandomFemaleName(nl);
	}

	/**
	 * 
	 * <h2>使用随机长度获取一个随机生成的男性风格的中文名</h2>
	 * <p>
	 * 该方法会以30%，60%，10%的概率生成2，3，4个字的男性风格姓名。
	 * </p>
	 * <em> 注：详见getRandomMaleName(NameLength nl)</em>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param nl
	 *            NameLength 指定生成长度
	 * @return String 生成的中文名
	 */
	public String getRandomMaleName() {
		NameLength nl;
		double k = Math.random();
		// 以40%，40%，20%的概率确定姓名长度。
		if (k <= 0.3) {
			nl = NameLength.TWO;
		} else if (k > 0.3 && k <= 0.9) {
			nl = NameLength.THREE;
		} else {
			nl = NameLength.FOUR;
		}
		return getRandomMaleName(nl);
	}

	/**
	 * 
	 * <h2>获取一个指定姓氏的随机男性风格名称</h2>
	 * <p>
	 * 返回的姓名有50%的可能性是单字，50%的可能性是双字。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param surname
	 *            String 指定的姓氏，不可为null，否则抛出NullPointerException。
	 * @return String 生成的姓名
	 */
	public String getMaleNameOfSurname(String surname) {
		if (surname == null) {
			throw new NullPointerException();
		}
		int i = Math.random() < 0.5 ? 0 : 1;
		if (i == 0) {
			return surname + gd.getRandomSingleMaleGivename();
		} else {
			return surname + gd.getRandomDoubleMaleGivenname();
		}
	}

	/**
	 * 
	 * <h2>获取一个指定姓氏的随机女性风格名称</h2>
	 * <p>
	 * 返回的姓名有50%的可能性是单字，50%的可能性是双字。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param surname
	 *            String 指定的姓氏，不可为null，否则抛出NullPointerException。
	 * @return String 生成的姓名
	 */
	public String getFemaleNameOfSurname(String surname) {
		if (surname == null) {
			throw new NullPointerException();
		}
		int i = Math.random() < 0.5 ? 0 : 1;
		if (i == 0) {
			return surname + gd.getRandomSingleFemaleGivename();
		} else {
			return surname + gd.getRandomDoubleFemaleGivename();
		}
	}

	/**
	 * 
	 * <h2>获取一个完全随机的、不分男女风格的、以姓氏字典中定义的姓氏为基础的名称</h2>
	 * <p>
	 * 该方法将返回一个2-4位长度的名称，其中姓氏不分来源于姓氏字典，名称部分使用utf-8编码格式下全部汉字范围进行随机，
	 * 包含生僻字和部首，因此本方法生成的名称将完全不可控，很多时候风格怪异，但丰富程度也比使用名称字典更高一些， 请根据具体需求谨慎选择本方法。
	 * </p>
	 * <em>注：本方法有70%的概率使用单字名，30%的概率使用双字名。这是考虑到随机组合两个汉字成为一个词组的概率较小， 因此尽量生成一个单字名。</em>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return String 生成的姓名
	 */
	public String getComRandomName() {
		double k = Math.random();
		String sur;
		String giv;
		if (k <= 0.7) {
			giv = getRandomChineseChar();//70%的概率使用单字
		} else {
			giv = getRandomChineseChar() + getRandomChineseChar();//30%的概率使用
		}
		int dice = Math.random() > rate ? 0 : 1;// 按照比值比率选取单复姓
		if (dice == 0) {
			sur=sd.getRandomSingleSurname();
		} else {// 使用复姓+单字名的方式生成姓名
			sur=sd.getRandomDoubleSurname();
		}
		return sur+giv;
	}

	// 随机生成一个汉字，范围为utf-8编码下的（u4e00-u51d0）,不区分简繁体，也不区分生僻字，每次调用均重新随机。
	private String getRandomChineseChar() {
		int s = (int) (19968 + Math.random() * 20944);
		char c = (char) (s);
		return String.valueOf(c);
	}
}
