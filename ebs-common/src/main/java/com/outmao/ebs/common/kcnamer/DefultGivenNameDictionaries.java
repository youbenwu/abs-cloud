package com.outmao.ebs.common.kcnamer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * <h2>青阳龙野中文姓名随机生成工具-默认名称字典</h2>
 * <p>
 * 该类用于存储、操作名称字典，使用单例模式进行设计，请使用instance()方法获取其唯一实例
 * </p>
 * 
 * @author 青阳龙野(kohgylw)
 * @version 1.0
 */
public class DefultGivenNameDictionaries implements GivenameDictionaries {

	private static GivenameDictionaries gnd = new DefultGivenNameDictionaries();
	private HashSet<String> maleName_sSet;// 男性单字名集合
	private HashSet<String> maleName_dSet;// 男性双字名集合
	private HashSet<String> femaleName_sSet;// 女性单字名集合
	private HashSet<String> femaleName_dSet;// 女性双字名集合

	private DefultGivenNameDictionaries() {
		// 配置默认名称字典，该字典分为男性名称列表和女性名称列表，偏中性的名称也被划分到男性名称列表之中。
		// 如需修改请在此添加或删除名称
		// 默认的单字男姓名列表
		String[] maleName_s = { "楚", "淳", "慈", "聪", "存", "迪", "典", "柏", "宝", "彬", "斌", "炳", "波", "博", "才", "财", "灿",
				"畅", "超", "朝", "琛", "宸", "晨", "诚", "承", "城", "驰", "栋", "恩", "法", "凡", "繁", "飞", "丰", "枫", "峰", "锋", "福",
				"甫", "富", "功", "海", "寒", "翰", "瀚", "航", "豪", "昊", "浩", "皓", "河", "衡", "弘", "泓", "鸿", "化", "焕", "煌", "辉",
				"季", "嘉", "坚", "俭", "健", "杰", "捷", "锦", "劲", "晋", "京", "靖", "镜", "炯", "举", "均", "钧", "俊", "峻", "骏", "凯",
				"恺", "楷", "珂", "魁", "昆", "琨", "朗", "乐", "磊", "理", "利", "联", "亮", "烈", "霖", "凌", "令", "禄", "茂", "旻", "鸣",
				"铭", "默", "念", "宁", "培", "沛", "鹏", "品", "璞", "奇", "淇", "琪", "琦", "棋", "祺", "麒", "谦", "庆", "权", "泉", "锐",
				"瑞", "睿", "森", "山", "善", "深", "升", "声", "胜", "晟", "盛", "实", "书", "树", "帅", "硕", "松", "泰", "涛", "韬", "腾",
				"通", "同", "桐", "旺", "望", "威", "唯", "伟", "玮", "炜", "稳", "夕", "曦", "玺", "喜", "宪", "献", "翔", "骁", "霄", "啸",
				"昕", "新", "旭", "绪", "宣", "选", "学", "言", "源", "研", "砚", "彦", "焱", "扬", "阳", "洋", "耀", "业", "晔", "烨", "宜",
				"亦", "轶", "奕", "意", "毅", "翼", "胤", "泳", "勇", "渝", "宇", "育", "昱", "裕", "煜", "誉", "渊", "原", "远", "岳", "跃",
				"越", "云", "昀", "赞", "增", "展", "钊", "招", "照", "哲", "祯", "臻", "振", "征", "知", "志", "智", "洲", "卓", "梓", "佐",
				"野", "羽" };
		maleName_sSet = new HashSet<>();
		maleName_sSet.addAll(Arrays.asList(maleName_s));
		// 默认的双字男姓名列表
		String[] maleName_d = { "展鹏", "哲瀚", "哲茂", "哲圣", "哲彦", "振海", "振国", "正诚", "正初", "正德", "正浩", "正豪", "正平", "正奇",
				"正青", "正卿", "正文", "正祥", "正信", "正雅", "正阳", "正业", "正谊", "正真", "正志", "志诚", "志新", "志勇", "志明", "志国", "志强",
				"志尚", "志专", "志文", "志行", "志学", "志业", "志义", "志用", "志泽", "致远", "智明", "智鑫", "智勇", "智敏", "智志", "智渊", "子安",
				"子晋", "子民", "子明", "子默", "子墨", "子平", "子琪", "子石", "子实", "子真", "子濯", "子昂", "子轩", "子瑜", "自明", "自强", "作人",
				"自怡", "自珍", "曾琪", "泽宇", "泽语", "雅昶", "雅畅", "雅达", "雅惠", "雅健", "雅珺", "雅逸", "雅懿", "雅志", "炎彬", "阳飙", "阳飇",
				"阳冰", "阳波", "阳伯", "阳成", "阳德", "阳华", "阳晖", "阳辉", "阳嘉", "阳平", "阳秋", "阳荣", "阳舒", "阳朔", "阳文", "阳曦", "阳夏",
				"阳旭", "阳煦", "阳炎", "阳焱", "阳曜", "阳羽", "阳云", "阳泽", "阳州", "烨赫", "烨华", "烨磊", "烨霖", "烨然", "烨烁", "烨伟", "龙野",
				"烨熠", "烨煜", "毅然", "逸仙", "逸明", "叶秋", "宜春", "宜民", "宜年", "宜然", "宜人", "宜修", "意远", "意蕴", "意致", "意智", "熠彤",
				"懿轩", "英飙", "英博", "英才", "英达", "英发", "英范", "英光", "英豪", "英华", "英杰", "英朗", "英锐", "英睿", "英睿", "英韶", "英卫",
				"英武", "英悟", "英勋", "英彦", "英耀", "英奕", "英逸", "英毅", "英哲", "英喆", "英卓", "英资", "英纵", "永怡", "永春", "永安", "永昌",
				"永长", "永丰", "永福", "永嘉", "永康", "永年", "永宁", "永寿", "永思", "永望", "永新", "向晨", "向笛", "向文", "向明", "向荣", "向阳",
				"翔宇", "翔飞", "项禹", "项明", "晓博", "心水", "心思", "心远", "欣德", "欣嘉", "欣可", "欣然", "欣荣", "欣怡", "欣怿", "欣悦", "新翰",
				"新霁", "新觉", "新立", "新荣", "新知", "信鸿", "信厚", "信鸥", "信然", "信瑞", "兴安", "兴邦", "兴昌", "兴朝", "兴德", "兴发", "兴国",
				"兴怀", "兴平", "兴庆", "兴生", "兴思", "兴腾", "兴旺", "兴为", "兴文", "兴贤", "兴修", "兴学", "兴言", "兴业", "兴运", "星波", "星辰",
				"星驰", "星光", "星海", "星汉", "星河", "星华", "星晖", "星火", "星剑", "星津", "星阑", "星纬", "星文", "星宇", "星雨", "星渊", "星洲",
				"修诚", "修德", "修杰", "修洁", "修谨", "修筠", "修明", "修能", "修平", "修齐", "修然", "修为", "修伟", "修文", "修雅", "修永", "修远",
				"修真", "修竹", "修贤", "旭尧", "炫明", "学博", "学海", "学林", "学民", "学名", "学文", "学义", "学真", "雪松", "雪峰", "雪风", "安邦",
				"安福", "安歌", "安国", "安和", "安康", "安澜", "安民", "安宁", "安平", "安然", "安顺", "安翔", "安晏", "安宜", "安怡", "安易", "安志",
				"昂然", "昂雄", "宾白", "宾鸿", "宾实", "彬彬", "彬炳", "彬郁", "斌斌", "斌蔚", "滨海", "波光", "波鸿", "波峻", "波涛", "博瀚", "博超",
				"博达", "博厚", "博简", "博明", "博容", "博赡", "博涉", "博实", "博涛", "博文", "博学", "博雅", "博延", "博艺", "博易", "博裕", "博远",
				"才捷", "才良", "才艺", "才英", "才哲", "才俊", "成和", "成弘", "成化", "成济", "成礼", "成龙", "成仁", "成双", "成天", "成文", "成业",
				"成益", "成荫", "成周", "承安", "承弼", "承德", "承恩", "承福", "承基", "承教", "承平", "承嗣", "承天", "承望", "承宣", "承颜", "承业",
				"承悦", "承允", "承运", "承载", "承泽", "承志", "德本", "德海", "德厚", "德华", "德辉", "德惠", "德容", "德润", "德寿", "德水", "德馨",
				"德曜", "德业", "德义", "德庸", "德佑", "德宇", "德元", "德运", "德泽", "德明", "飞昂", "飞白", "飞飙", "飞掣", "飞尘", "飞沉", "飞驰",
				"飞光", "飞翰", "飞航", "飞翮", "飞鸿", "飞虎", "飞捷", "飞龙", "飞鸾", "飞鸣", "飞鹏", "飞扬", "飞文", "飞翔", "飞星", "飞翼", "飞英",
				"飞宇", "飞羽", "飞雨", "飞语", "飞跃", "飞章", "飞舟", "风华", "丰茂", "丰羽", "刚豪", "刚洁", "刚捷", "刚毅", "高昂", "高岑", "高畅",
				"高超", "高驰", "高澹", "高飞", "颖逸", "高峯", "高峰", "高歌", "高格", "高寒", "高翰", "高杰", "高洁", "高峻", "高朗", "高丽", "高邈",
				"高旻", "高明", "高爽", "高轩", "高雅", "高扬", "高阳", "高义", "高谊", "高逸", "高懿", "高远", "高韵", "高卓", "光赫", "光华", "光辉",
				"光济", "光霁", "光亮", "光临", "光明", "光启", "光熙", "光耀", "光誉", "光远", "国安", "国兴", "国源", "冠宇", "冠玉", "晗昱", "晗日",
				"涵畅", "涵涤", "涵亮", "涵忍", "涵容", "涵润", "涵煦", "涵蓄", "涵衍", "涵意", "涵映", "涵育", "翰采", "翰池", "翰飞", "翰海", "翰翮",
				"翰林", "翰墨", "翰学", "翰音", "瀚玥", "翰藻", "瀚海", "瀚漠", "昊苍", "昊昊", "昊空", "昊乾", "昊穹", "昊然", "昊然", "昊天", "昊焱",
				"昊英", "浩波", "浩博", "浩初", "浩大", "浩宕", "浩荡", "浩歌", "浩广", "浩涆", "浩瀚", "浩慨", "浩旷", "浩阔", "浩漫", "浩淼", "浩渺",
				"浩邈", "浩气", "浩然", "浩穰", "浩壤", "浩思", "浩言", "皓轩", "和蔼", "和安", "和璧", "和昶", "和畅", "和风", "和歌", "和光", "和平",
				"和洽", "和惬", "和顺", "和硕", "和颂", "和泰", "和悌", "和通", "无尘", "和煦", "和雅", "和宜", "和怡", "和玉", "和裕", "和豫", "和悦",
				"和韵", "和泽", "和正", "和志", "鹤轩", "弘博", "弘方", "弘光", "弘和", "弘厚", "弘化", "弘济", "弘阔", "弘亮", "弘量", "弘深", "弘盛",
				"弘图", "弘伟", "弘文", "弘新", "弘雅", "弘扬", "弘业", "弘义", "弘益", "弘毅", "弘懿", "弘致", "弘壮", "宏伯", "宏博", "宏才", "宏畅",
				"宏达", "宏大", "宏放", "宏富", "宏峻", "宏浚", "宏恺", "宏旷", "宏阔", "宏朗", "宏茂", "宏邈", "宏儒", "宏深", "宏胜", "宏盛", "宏爽",
				"宏硕", "宏伟", "宏扬", "宏义", "宏逸", "宏毅", "宏远", "宏壮", "鸿宝", "鸿波", "鸿博", "鸿才", "鸿彩", "鸿畅", "鸿畴", "鸿达", "鸿德",
				"鸿飞", "鸿风", "鸿福", "鸿光", "鸿晖", "鸿朗", "鸿文", "鸿熙", "鸿羲", "鸿禧", "鸿信", "鸿轩", "鸿煊", "鸿煊", "鸿雪", "鸿羽", "鸿远",
				"鸿云", "鸿运", "鸿哲", "鸿祯", "鸿振", "鸿志", "鸿卓", "华奥", "华采", "华彩", "华灿", "华藏", "华池", "华翰", "华皓", "华晖", "华辉",
				"华茂", "华美", "华清", "华荣", "华容", "嘉赐", "嘉德", "嘉福", "嘉良", "嘉茂", "嘉木", "嘉慕", "嘉纳", "嘉年", "嘉平", "嘉庆", "嘉荣",
				"嘉容", "嘉瑞", "嘉胜", "嘉石", "嘉实", "嘉树", "嘉澍", "嘉熙", "嘉禧", "嘉祥", "嘉歆", "嘉许", "嘉勋", "嘉言", "嘉谊", "嘉懿", "嘉颖",
				"嘉佑", "嘉玉", "嘉誉", "嘉悦", "嘉运", "嘉泽", "嘉珍", "嘉祯", "嘉志", "嘉致", "坚白", "坚壁", "坚秉", "坚成", "坚诚", "建安", "建白",
				"建柏", "建本", "建弼", "建德", "建华", "建明", "建茗", "建木", "建树", "建同", "建修", "建业", "建义", "建元", "建章", "建中", "健柏",
				"金鑫", "锦程", "瑾瑜", "晋鹏", "经赋", "经亘", "经国", "经略", "经纶", "经纬", "经武", "经业", "经义", "经艺", "景澄", "景福", "景焕",
				"景辉", "景辉", "景龙", "景明", "景山", "景胜", "景铄", "景天", "景同", "景曜", "靖琪", "君昊", "君浩", "俊艾", "俊拔", "俊弼", "俊才",
				"俊材", "俊驰", "俊楚", "俊达", "俊德", "俊发", "俊风", "俊豪", "俊健", "俊杰", "俊捷", "俊郎", "俊力", "俊良", "俊迈", "俊茂", "俊美",
				"俊民", "俊名", "俊明", "俊楠", "俊能", "俊人", "俊爽", "俊悟", "俊晤", "俊侠", "俊贤", "俊雄", "俊雅", "俊彦", "俊逸", "俊英", "俊友",
				"俊语", "俊誉", "俊远", "俊哲", "俊喆", "俊智", "峻熙", "季萌", "季同", "长卿", "墨竹", "曜瑞", "修谨", "飞扬", "智渊", "宏胜", "运浩",
				"飞昂", "浩皛", "青青", "彭亮", "乐语", "奇文", "博艺", "西岭", "光赫", "鹏云", "伟彦", "秋月", "寂离", "苗宣", "兴思", "自强", "鸿煊",
				"子轩", "鸿畅", "子石", "商震", "远航", "襦宗", "学智", "鸿宝", "寂弦", "思源", "英发", "玉堂", "宇定", "思淼", "夜洛", "玉石", "良才",
				"玉书", "志用", "振国", "兴业", "彭祖", "承允", "景福", "兴腾", "鹏海", "明德", "天宇", "振濂", "高明", "昊东", "正诚", "翰海", "兴学",
				"博裕", "英纵", "安国", "承教", "浩慨", "明贤", "文博", "明辉", "浩大", "明煦", "飞星", "礼骞", "阳州", "承福", "文柏", "钱青", "乐逸",
				"耘豪", "斯伯", "德曜", "鸿博", "方伟", "自怡", "鹏飞", "秦迟", "辰沛", "鸿风", "星辰", "鸿信", "溪叠", "文光", "起运", "小云", "昂然",
				"博瀚", "保赫", "英耀", "鹏鹍", "瑾瑜", "鸿卓", "吕恭", "银龙", "秦斩", "昂熙", "运恒", "吉星", "英华", "俊逸", "建木", "宏伯", "志义",
				"谭三", "天佑", "烨华", "冥夜", "星腾", "和玉", "衡虑", "乐湛", "乐心", "姚石", "思博", "俊民", "星鹏", "子辰", "英叡", "曜文", "志诚",
				"令秋", "叶五", "鹤龄", "辰龙", "鸿羲", "经赋", "伟志", "明知", "熙云", "钟展", "铭晨", "安民", "君墨", "飞虎", "洛华", "浩初", "鸿远",
				"炫明", "沈浪", "承恩", "开畅", "曦哲", "智明", "华晖", "泰平", "海超", "长恨", "单弓", "弘伟", "英勋", "嘉木", "阳飇", "嘉石", "安阳",
				"光济", "向明", "元章", "俊良", "修洁", "曜灿", "俊彦", "良骏", "辰阳", "元白", "涵畅", "正奇", "曾笑", "俊智", "雨石", "出野", "咏思",
				"运诚", "乐生", "兴德", "俊悟", "杜吟", "星雨", "修明", "英睿", "奇略", "逸春", "冠玉", "高韵", "莫希", "高刚", "峻熙", "骏奇", "宇航",
				"德华", "经业", "光临", "承载", "丁雨", "凯风", "文林", "骞仕", "嘉澍", "德业", "嘉良", "晨朗", "昊英", "丁雷", "何平", "赤岩", "元龙",
				"俊晤", "隐水", "修然", "宏邈", "宏朗", "时铭", "元明", "普松", "光华", "穆冉", "安顺", "正阳", "永贞", "奇玮", "胜涝", "思聪", "俊才",
				"嘉荣", "金鹏", "轩昂", "浩波", "玉宸", "嘉祯", "毅庵", "昊焱", "凯康", "鸿禧", "才哲", "疏珂", "元徽", "晟睿", "积厚", "子默", "华奥",
				"昊天", "祖鹤", "开济", "飞鸾", "英资", "阳平", "蕴藉", "金林", "昊阳", "淮晨", "骏俊", "绍晖", "砚文", "涵亮", "海阳", "英奕", "涵育",
				"阳煦", "睿范", "茂学", "友樵", "志专", "阳曜", "宏富", "昊焜", "永康", "骞尧", "震博", "烨磊", "信瑞", "高懿", "鹭洋", "凯泽", "枫涟",
				"霍英", "嘉纳", "元青", "安宁", "胤运", "昆琦", "光熙", "正青", "明朗", "易安", "昆杰", "雨华", "文栋", "兴庆", "飞飙", "翊歌", "康裕",
				"高邈", "星汉", "星火", "和风", "博涛", "宾鸿", "仲渊", "仰岳", "弘光", "浩荡", "厉刚", "鸿骞", "建弼", "浩宕", "昌勋", "宏畅", "承天",
				"翔飞", "可人", "龙光", "正德", "敏学", "冷勋", "滨海", "昂雄", "华美", "乐邦", "胜泫", "沈义", "君博", "风畔", "温书", "宏浚", "嘉佑",
				"瀚昂", "和泽", "齐智", "苏燕", "高畅", "德厚", "伟兆", "勇军", "高丽", "光明", "文斌", "燕七", "同化", "海荣", "良弼", "高飞", "茂实",
				"英韶", "弘新", "慎之", "小林", "德宇", "博涉", "凯歌", "洛城", "嘉福", "阿苏", "向文", "池暝", "兴生", "鸿振", "永逸", "骏喆", "俊晖",
				"康平", "开宇", "志文", "玉韵", "旭东", "勇锐", "伯寅", "雨泽", "嘉懿", "祺然", "奇希", "风史", "和安", "彦君", "小白", "乐意", "浩言",
				"越泽", "云瀚", "刚豪", "修远", "卓君", "缪文", "和裕", "安福", "辰钊", "阳旭", "阳荣", "浩涆", "君之", "麻雀", "清夷", "阎宝", "黎明",
				"保臣", "建柏", "流觞", "弘扬", "康胜", "锐进", "墨一", "章横", "英卓", "良平", "彭薄", "苑博", "明亮", "俊迈", "晗昱", "侯林", "立果",
				"钱明", "雅昶", "芷阳", "昊明", "翰池", "奇思", "丰茂", "凯捷", "巴英", "正真", "永春", "志尚", "天逸", "志强", "涵衍", "泰宁", "凌龙",
				"高旻", "雪松", "博耘", "英光", "学潞", "烨霖", "飞尘", "睿才", "子濯", "边浩", "德明", "鹏程", "元忠", "灼光", "兴运", "君浩", "思远",
				"明诚", "修平", "睿识", "星洲", "星海", "波娃", "安晏", "英锐", "阳晖", "和光", "嘉运", "和畅", "俊弼", "奇逸", "文彬", "凯定", "良策",
				"萧迟", "卜霸", "俊达", "乐池", "凯旋", "元凯", "鸿朗", "冯浩", "鑫鹏", "玄天", "心远", "运珧", "涵煦", "仲卿", "飞羽", "雅志", "文华",
				"逸仙", "楚青", "查猛", "昌翰", "昌胤", "文康", "高昂", "晗日", "崇凛", "马鲁", "宏义", "温文", "绍辉", "阳焱", "元魁", "飞翰", "建明",
				"嘉胜", "康安", "朝斑", "阳泽", "文乐", "才艺", "弘壮", "乐家", "皓君", "俊侠", "智刚", "和同", "飞章", "心思", "星渊", "意智", "向笛",
				"煜祺", "绯辞", "玉泽", "朝明", "弘深", "光启", "狐若", "英才", "茂才", "开诚", "哲彦", "敏达", "丰羽", "伟泽", "文景", "正浩", "文轩",
				"曦之", "骏祥", "兴修", "曾琪", "单鹗", "志业", "德水", "乐童", "浩思", "华藏", "飞航", "文昌", "澄邈", "胡非", "星晖", "宗清", "俊英",
				"绍元", "卜鹰", "锐藻", "黎昕", "越彬", "璞瑜", "运锋", "鸿志", "烨赫", "令雪", "允晨", "烨烨", "君植", "修伟", "胡媚", "英逸", "鸿轩",
				"茂勋", "玄裳", "博远", "歌者", "建德", "佑运", "花蜂", "和豫", "承嗣", "茂材", "运良", "运乾", "良哲", "逸清", "子航", "国发", "超英",
				"灵均", "清野" };
		maleName_dSet = new HashSet<>();
		maleName_dSet.addAll(Arrays.asList(maleName_d));
		// 默认的单字女性名列表
		String[] femaleName_s = { "梓", "晨", "丽", "丹", "佩", "惠", "月", "玉", "婉", "晓", "倩", "瑞", "静", "颖", "韶", "爱", "姿",
				"惠", "娇", "媛", "妩", "萱", "娈", "瑷", "悠", "源", "赫", "晗", "贻", "楚", "梦", "琪", "忆", "柳", "桃", "慕", "兰", "岚",
				"香", "沛", "菡", "珊", "曼", "菱", "寒", "柔", "蓉", "安", "蓝", "春", "语", "彤", "晴", "语", "菱", "霜", "紫", "莲", "翠",
				"烟", "南", "寻", "慕", "蕊", "雪", "海", "沛", "宛", "晓", "菡", "巧", "蕙", "卉", "靖", "枫", "漪", "漫", "澜", "灵", "玥",
				"玫", "环", "玲", "珂", "珊", "珍", "珠", "婉", "婕", "清", "琦", "琪", "琬", "琰", "琳", "琴", "琼", "瑗", "瑛", "瑜", "瑶",
				"瑾", "璇", "璐", "璟", "白", "盈", "盼", "碧", "秀", "筠", "红", "绮", "芃", "芊", "芝", "芬", "婧", "婵", "婷", "芮", "芯",
				"芳", "芷", "芸", "苑", "若", "苹", "茗", "茜", "茵", "茹", "荔", "荭", "荷", "莉", "莎", "莲", "莹", "菁", "菡", "菲", "萍",
				"萱", "蓉", "蓓", "蔓", "蕊", "蕾", "薇", "诗", "语", "贞", "采", "钰", "银", "雅", "雨", "雪", "雯", "霏", "霖", "霜", "霞",
				"露", "青", "靖", "静", "音", "韵", "颖", "颜", "馨", "黛", "娉", "娜", "娟", "娣", "娥", "娴", "薇", "忆", "旋", "芷", "蕾",
				"代", "芙", "盼", "蝶", "筠", "瑶", "珍", "谷", "荷", "画", "棋", "芹", "萍", "幻", "露", "灵", "含", "雅", "薇", "瑶", "丹",
				"丽", "云", "亿", "仪", "伊", "伶", "佳", "依", "俞", "俪", "倩", "偲", "兰", "冰", "凝", "凡", "凤", "叆", "呤", "嘉", "囡",
				"女", "如", "妃", "妍", "妙", "妮", "姗", "姝", "姞", "姬", "娅", "娆", "娇", "媚", "媛", "嫔", "嫣", "嫱", "安", "宛", "宜",
				"宝", "容", "巧", "希", "彤", "彩", "心", "忆", "念", "怀", "怜", "思", "怡", "情", "惠", "慧", "敏", "旭", "春", "晴", "曼",
				"月", "梅", "梦", "楠", "檀", "欢", "欣", "歆", "毓", "水", "洁", "涵", "淑", "清", "滟", "滢", "羽" };
		femaleName_sSet = new HashSet<>();
		femaleName_sSet.addAll(Arrays.asList(femaleName_s));
		// 默认的双字女性名列表
		String[] femaleName_d = { "梦琪", "之桃", "慕青", "尔岚", "初夏", "沛菡", "傲珊", "曼文", "乐菱", "惜文", "香寒", "新柔", "语蓉", "海安",
				"夜蓉", "涵柏", "水桃", "醉蓝", "语琴", "从彤", "傲晴", "语兰", "又菱", "碧彤", "元霜", "怜梦", "紫寒", "妙彤", "曼易", "南莲", "紫翠",
				"雨寒", "易烟", "如萱", "若南", "寻真", "晓亦", "向珊", "慕灵", "以蕊", "映易", "雪柳", "海云", "凝天", "沛珊", "寒云", "冰旋", "宛儿",
				"绿真", "晓霜", "碧凡", "夏菡", "曼香", "若烟", "半梦", "雅绿", "冰蓝", "灵槐", "平安", "书翠", "翠风", "代云", "梦曼", "幼翠", "听寒",
				"梦柏", "醉易", "访旋", "亦玉", "凌萱", "访卉", "怀亦", "笑蓝", "靖柏", "夜蕾", "冰夏", "梦松", "书雪", "乐枫", "念薇", "靖雁", "从寒",
				"觅波", "静曼", "凡旋", "汀兰", "念露", "芷蕾", "千兰", "新波", "代真", "新蕾", "雁玉", "冷卉", "紫山", "千琴", "傲芙", "盼山", "怀蝶",
				"冰兰", "山柏", "翠萱", "问旋", "白易", "问筠", "如霜", "半芹", "丹珍", "冰彤", "亦寒", "之瑶", "冰露", "尔珍", "谷雪", "乐萱", "涵菡",
				"海莲", "傲蕾", "青槐", "易梦", "惜雪", "宛海", "之柔", "夏青", "亦瑶", "妙菡", "紫蓝", "幻柏", "元风", "冰枫", "访蕊", "芷蕊", "凡蕾",
				"凡柔", "安蕾", "天荷", "含玉", "书兰", "雅琴", "书瑶", "从安", "夏槐", "念芹", "代曼", "幻珊", "谷丝", "秋翠", "白晴", "海露", "代荷",
				"含玉", "书蕾", "听白", "灵雁", "雪青", "乐瑶", "含烟", "涵双", "平蝶", "雅蕊", "傲之", "灵薇", "含蕾", "从梦", "从蓉", "初丹", "听兰",
				"听蓉", "语芙", "夏彤", "凌瑶", "忆翠", "云舒", "怜菡", "紫南", "依珊", "妙竹", "访烟", "怜蕾", "映寒", "友绿", "冰萍", "惜霜", "凌香",
				"芷蕾", "雁卉", "迎梦", "元柏", "代萱", "清月", "千青", "凌寒", "紫安", "寒安", "怀蕊", "秋荷", "涵雁", "以山", "凡梅", "盼曼", "翠彤",
				"谷冬", "冷安", "千萍", "冰烟", "雅阳", "友绿", "南松", "诗云", "飞风", "寄灵", "书芹", "幼蓉", "以蓝", "笑寒", "忆寒", "秋烟", "芷巧",
				"水香", "映之", "醉波", "幻莲", "夜山", "芷卉", "向彤", "小玉", "幼南", "凡梦", "尔曼", "念波", "迎松", "青寒", "笑天", "涵蕾", "碧菡",
				"映秋", "盼烟", "忆山", "以寒", "寒香", "幽若", "代亦", "梦露", "映波", "友蕊", "寄凡", "怜蕾", "雁枫", "水绿", "曼荷", "笑珊", "寒珊",
				"谷南", "慕儿", "夏岚", "友儿", "小萱", "紫青", "妙菱", "冬寒", "曼柔", "语蝶", "青筠", "夜安", "觅海", "问安", "晓槐", "雅山", "访云",
				"翠容", "寒凡", "晓绿", "以菱", "冬云", "含玉", "访枫", "含卉", "夜白", "冷安", "灵竹", "醉薇", "元珊", "幻波", "盼夏", "元瑶", "迎曼",
				"水云", "访琴", "谷波", "笑白", "妙海", "紫霜", "凌旋", "孤丝", "怜寒", "凡松", "青丝", "翠安", "如天", "凌雪", "绮菱", "代云", "香薇",
				"冬灵", "凌珍", "沛文", "紫槐", "幻柏", "采文", "雪旋", "盼海", "映梦", "安雁", "映容", "凝阳", "访风", "天亦", "觅风", "小霜", "雪萍",
				"半雪", "山柳", "谷雪", "靖易", "白薇", "梦菡", "飞绿", "如波", "又晴", "友易", "香菱", "冬亦", "问雁", "海冬", "秋灵", "凝芙", "念烟",
				"白山", "从灵", "尔芙", "迎蓉", "念寒", "翠绿", "翠芙", "靖儿", "妙柏", "千凝", "小珍", "妙旋", "雪枫", "夏菡", "绮琴", "雨双", "听枫",
				"觅荷", "凡之", "晓凡", "雅彤", "悦知", "从安", "绮彤", "之玉", "雨珍", "幻丝", "代梅", "青亦", "元菱", "海瑶", "飞槐", "听露", "梦岚",
				"幻竹", "谷云", "忆霜", "水瑶", "慕晴", "秋双", "雨真", "觅珍", "丹雪", "元枫", "思天", "如松", "妙晴", "谷秋", "妙松", "晓夏", "宛筠",
				"碧琴", "盼兰", "小夏", "安容", "青曼", "千儿", "寻双", "涵瑶", "冷梅", "秋柔", "思菱", "醉波", "醉柳", "以寒", "迎夏", "向雪", "以丹",
				"依凝", "如柏", "雁菱", "凝竹", "宛白", "初柔", "南蕾", "书萱", "梦槐", "南琴", "绿海", "沛儿", "晓瑶", "凝蝶", "紫雪", "念双", "念真",
				"曼寒", "凡霜", "飞雪", "雪兰", "雅霜", "从蓉", "冷雪", "靖巧", "翠丝", "觅翠", "凡白", "乐蓉", "迎波", "丹烟", "梦旋", "书双", "念桃",
				"夜天", "安筠", "觅柔", "初南", "秋蝶", "千易", "安露", "诗蕊", "山雁", "友菱", "香露", "晓兰", "白卉", "语山", "冷珍", "秋翠", "如之",
				"忆南", "书易", "翠桃", "寄瑶", "如曼", "问柳", "幻桃", "又菡", "醉蝶", "亦绿", "诗珊", "听芹", "新之", "易巧", "念云", "晓灵", "静枫",
				"夏蓉", "如南", "幼丝", "秋白", "冰安", "秋白", "南风", "醉山", "初彤", "凝海", "紫文", "凌晴", "雅琴", "傲安", "傲之", "初蝶", "代芹",
				"诗霜", "碧灵", "诗柳", "夏柳", "采白", "慕梅", "乐安", "冬菱", "紫安", "宛凝", "雨雪", "易真", "安荷", "静竹", "代柔", "丹秋", "绮梅",
				"依白", "凝荷", "幼珊", "忆彤", "凌青", "疏雨", "芷荷", "听荷", "代玉", "念珍", "梦菲", "夜春", "千秋", "白秋", "谷菱", "飞松", "初瑶",
				"惜灵", "梦易", "新瑶", "曼梅", "碧曼", "友瑶", "雨兰", "夜柳", "芷珍", "含芙", "夜云", "依萱", "凝雁", "以莲", "安南", "幼晴", "尔琴",
				"飞阳", "白凡", "沛萍", "雪瑶", "向卉", "采文", "乐珍", "寒荷", "觅双", "白桃", "安卉", "迎曼", "盼雁", "乐松", "涵山", "问枫", "以柳",
				"含海", "翠曼", "忆梅", "涵柳", "海蓝", "晓曼", "代珊", "忆丹", "静芙", "绮兰", "梦安", "紫丝", "千雁", "凝珍", "香萱", "梦容", "冷雁",
				"飞柏", "天真", "翠琴", "寄真", "秋荷", "代珊", "初雪", "雅柏", "怜容", "如风", "南露", "紫易", "冰凡", "海雪", "语蓉", "碧玉", "语风",
				"凝梦", "从雪", "白枫", "傲云", "白梅", "念露", "慕凝", "雅柔", "盼柳", "半青", "从霜", "怀柔", "怜晴", "夜蓉", "代双", "以南", "若菱",
				"芷文", "南晴", "梦寒", "初翠", "灵波", "问夏", "琬琰", "亦旋", "沛芹", "幼萱", "白凝", "初露", "迎海", "绮玉", "凌香", "寻芹", "秋柳",
				"尔白", "映真", "含雁", "寒松", "寻雪", "青烟", "问蕊", "灵阳", "雪巧", "丹萱", "凡双", "杜若", "紫菱", "寻凝", "傲柏", "傲儿", "友容",
				"灵枫", "尔丝", "曼凝", "若蕊", "问丝", "思枫", "水卉", "问梅", "念寒", "诗双", "翠霜", "夜香", "寒蕾", "凡阳", "冷玉", "平彤", "语薇",
				"幻珊", "紫夏", "凌波", "芷蝶", "丹南", "之双", "凡波", "思雁", "白莲", "从菡", "如容", "采柳", "沛岚", "惜儿", "夜玉", "水儿", "半凡",
				"语海", "听莲", "幻枫", "念柏", "冰珍", "思山", "凝蕊", "天玉", "思萱", "向梦", "笑南", "夏旋", "之槐", "元灵", "以彤", "采萱", "巧曼",
				"绿兰", "平蓝", "问萍", "绿蓉", "靖柏", "迎蕾", "碧曼", "思卉", "白柏", "妙菡", "怜阳", "雨柏", "雁菡", "梦之", "又莲", "乐荷", "寒天",
				"凝琴", "书南", "映天", "白梦", "初瑶", "平露", "含巧", "慕蕊", "半莲", "醉卉", "天菱", "青雪", "雅旋", "巧荷", "飞丹", "若灵", "尔云",
				"幻天", "诗兰", "青梦", "海菡", "灵槐", "忆秋", "寒凝", "凝芙", "绮山", "静白", "尔蓉", "尔冬", "映萱", "白筠", "冰双", "访彤", "绿柏",
				"夏云", "笑翠", "晓灵", "含双", "盼波", "以云", "怜翠", "雁风", "之卉", "平松", "问儿", "绿柳", "如蓉", "曼容", "天晴", "丹琴", "惜天",
				"寻琴", "依瑶", "涵易", "忆灵", "从波", "依柔", "问兰", "山晴", "怜珊", "清川", "飞双", "烟岚", "沛春", "雨南", "梦之", "笑阳", "代容",
				"友琴", "雁梅", "友桃", "从露", "语柔", "傲玉", "觅夏", "晓蓝", "新晴", "雨莲", "凝旋", "绿旋", "幻香", "觅双", "冷亦", "忆雪", "友卉",
				"幻翠", "靖柔", "寻菱", "丹翠", "晏晏", "雅寒", "惜筠", "尔安", "雁易", "飞瑶", "夏兰", "沛蓝", "静丹", "山芙", "笑晴", "新烟", "笑旋",
				"雁兰", "凌翠", "秋莲", "书桃", "傲松", "语儿", "映菡", "初曼", "听云", "初夏", "雅香", "语雪", "初珍", "白安", "冰薇", "诗槐", "冷玉",
				"冰巧", "之槐", "夏寒", "诗筠", "新梅", "白曼", "梦西", "从阳", "含桃", "曼卉", "笑萍", "晓露", "寻菡", "沛白", "平灵", "水彤", "安彤",
				"涵易", "乐巧", "依风", "紫南", "亦丝", "澹雅", "紫萍", "惜萱", "诗蕾", "寻绿", "诗双", "寻云", "孤丹", "谷蓝", "山灵", "幻丝", "友梅",
				"从云", "雁丝", "盼旋", "幼旋", "尔蓝", "沛山", "代丝", "觅松", "冰香", "依玉", "冰之", "妙梦", "以冬", "曼青", "冷菱", "雪曼", "安白",
				"千亦", "凌蝶", "又夏", "南烟", "靖易", "沛凝", "翠梅", "书文", "雪卉", "乐儿", "傲丝", "安青", "初蝶", "寄灵", "惜寒", "雨竹", "冬莲",
				"绮南", "翠柏", "平凡", "亦玉", "孤兰", "秋珊", "新筠", "半芹", "夏瑶", "念文", "晓丝", "雁凡", "谷兰", "灵凡", "凝云", "曼云", "丹彤",
				"南霜", "夜梦", "从筠", "雁芙", "语蝶", "依波", "晓旋", "念之", "盼芙", "曼安", "采珊", "初柳", "迎天", "曼安", "南珍", "妙芙", "语柳",
				"含莲", "晓筠", "夏山", "尔容", "念梦", "傲南", "问薇", "雨灵", "凝安", "冰海", "初珍", "宛菡", "冬卉", "盼晴", "冷荷", "寄翠", "幻梅",
				"如凡", "语梦", "易梦", "千柔", "向露", "梦玉", "傲霜", "依霜", "灵松", "诗桃", "书蝶", "冰蝶", "山槐", "以晴", "友易", "梦桃", "香菱",
				"孤云", "水蓉", "雅容", "飞烟", "雁荷", "静影", "醉易", "夏烟", "依秋", "依波", "紫萱", "涵易", "忆之", "幻巧", "水风", "安寒", "白亦",
				"怜雪", "听南", "念蕾", "梦竹", "千凡", "寄琴", "采波", "元冬", "思菱", "平卉", "笑柳", "雪卉", "谷梦", "绿蝶", "飞荷", "平安", "孤晴",
				"芷荷", "曼冬", "尔槐", "以旋", "绿蕊", "初夏", "依丝", "怜南", "千山", "雨安", "广思", "寄柔", "幼枫", "凡桃", "新儿", "夏波", "雨琴",
				"静槐", "元槐", "映阳", "飞薇", "小凝", "映寒", "傲菡", "谷蕊", "笑槐", "飞兰", "笑卉", "迎荷", "新月", "书竹", "半烟", "绮波", "小之",
				"觅露", "夜雪", "寒梦", "尔风", "白梅", "雨旋", "芷珊", "山彤", "尔柳", "沛柔", "灵萱", "沛凝", "白容", "乐蓉", "映安", "依云", "映冬",
				"凡雁", "梦秋", "醉柳", "梦凡", "若云", "元容", "怀蕾", "灵寒", "天薇", "白风", "访波", "亦凝", "易绿", "夜南", "曼凡", "亦巧", "青易",
				"冰真", "白萱", "友安", "诗翠", "雪珍", "海之", "小蕊", "又琴", "香彤", "语梦", "惜蕊", "迎彤", "沛白", "雁山", "易蓉", "雪晴", "诗珊",
				"冰绿", "半梅", "笑容", "沛凝", "念瑶", "如冬", "向真", "从蓉", "亦云", "向雁", "尔蝶", "冬易", "丹亦", "夏山", "醉香", "盼夏", "孤菱",
				"安莲", "问凝", "冬萱", "晓山", "雁蓉", "梦蕊", "山菡", "南莲", "飞双", "凝丝", "思萱", "怀梦", "雨梅", "冷霜", "向松", "迎丝", "迎梅",
				"听双", "山蝶", "夜梅", "醉冬", "雨筠", "平文", "青文", "半蕾", "幼菱", "寻梅", "含之", "香之", "含蕊", "亦玉", "靖荷", "碧萱", "寒云",
				"向南", "书雁", "怀薇", "思菱", "忆文", "映叶", "向秋", "凡白", "绮烟", "从蕾", "天曼", "又亦", "依琴", "曼彤", "沛槐", "新眉", "元绿",
				"安珊", "夏之", "易槐", "宛亦", "白翠", "丹云", "问寒", "易文", "傲易", "青旋", "思真", "妙之", "半双", "若翠", "初兰", "怀曼", "惜萍",
				"初之", "宛丝", "幻儿", "千风", "天蓉", "雅青", "寄文", "代天", "惜珊", "向薇", "冬灵", "惜芹", "凌青", "谷芹", "雁桃", "映雁", "书兰",
				"寄风", "访烟", "绮晴", "傲柔", "寄容", "以珊", "紫雪", "芷容", "书琴", "寻桃", "涵阳", "怀寒", "易云", "采蓝", "代秋", "惜梦", "尔烟",
				"谷槐", "怀莲", "涵菱", "水蓝", "访冬", "半兰", "又柔", "冬卉", "安双", "冰岚", "香薇", "语芹", "静珊", "幻露", "访天", "静柏", "凌丝",
				"小翠", "雁卉", "访文", "凌文", "芷云", "思柔", "巧凡", "慕山", "依云", "千柳", "从凝", "安梦", "香旋", "映天", "安柏", "平萱", "以筠",
				"忆曼", "新竹", "绮露", "觅儿", "碧蓉", "白竹", "飞兰", "曼雁", "雁露", "凝冬", "含灵", "初阳", "海秋", "冰双", "绿兰", "盼易", "思松",
				"梦山", "友灵", "绿竹", "灵安", "凌柏", "秋柔", "又蓝", "尔竹", "天蓝", "青枫", "问芙", "语海", "灵珊", "凝丹", "小蕾", "迎夏", "水之",
				"飞珍", "冰夏", "亦竹", "飞莲", "容与", "元蝶", "芷天", "怀绿", "尔容", "元芹", "若云", "寒烟", "听筠", "采梦", "凝莲", "沅芷", "舒窈",
				"代桃", "冷之", "盼秋", "秋寒", "慕蕊", "海亦", "初晴", "巧蕊", "听安", "芷雪", "以松", "梦槐", "寒梅", "香岚", "寄柔", "映冬", "孤容",
				"晓蕾", "安萱", "听枫", "夜绿", "雪莲", "从丹", "碧蓉", "绮琴", "雨文", "幼荷", "青柏", "初蓝", "忆安", "盼晴", "寻冬", "雪珊", "梦寒",
				"迎南", "如彤", "采枫", "若雁", "翠阳", "沛容", "幻翠", "山兰", "芷波", "雪瑶", "寄云", "慕卉", "冷松", "涵梅", "书白", "乐天", "雁卉",
				"宛秋", "傲旋", "新之", "凡儿", "夏真", "静枫", "乐双", "白玉", "问玉", "寄松", "丹蝶", "元瑶", "冰蝶", "访曼", "代灵", "芷烟", "白易",
				"尔阳", "怜烟", "平卉", "丹寒", "访梦", "绿凝", "冰菱", "语蕊", "思烟", "忆枫", "映菱", "凌兰", "曼岚", "若枫", "傲薇", "凡灵", "乐蕊",
				"秋灵", "谷槐", "觅云", "秀云", "心月", "玉玲", "秀之", "弦思"};
		femaleName_dSet = new HashSet<>();
		femaleName_dSet.addAll(Arrays.asList(femaleName_d));
	}

	/**
	 * <h2>获取名称字典唯一实例</h2>
	 * <p>
	 * 请通过该方法获取唯一实例。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return GivenNameDictionaries 实例对象
	 */
	public static GivenameDictionaries instance() {
		return gnd;
	}

	/**
	 * 
	 * <h2>从名称字典中随机获取一个男性单字名</h2>
	 * <p>
	 * 该方法每次调用均会重新随机。如果男性单字名字典为空，则返回一个空字符串。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return String 随机的男性单字名
	 */
	public String getRandomSingleMaleGivename() {
		double r = Math.random();
		int size = maleName_sSet.size();
		if (size == 0) {
			return "";
		} else {
			int index = (int) (r * size);
			List<String> ls = new ArrayList<>(maleName_sSet);
			return ls.get(index);
		}
	}

	/**
	 * 
	 * <h2>从名称字典中随机获取一个男性双字名</h2>
	 * <p>
	 * 该方法每次调用均会重新随机。如果男性双字名字典为空，则返回一个空字符串。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return String 随机的男性双字名
	 */
	public String getRandomDoubleMaleGivenname() {
		double r = Math.random();
		int size = maleName_dSet.size();
		if (size == 0) {
			return "";
		} else {
			int index = (int) (r * size);
			List<String> ls = new ArrayList<>(maleName_dSet);
			return ls.get(index);
		}
	}

	/**
	 * 
	 * <h2>从名称字典中随机获取一个女性单字名</h2>
	 * <p>
	 * 该方法每次调用均会重新随机。如果女性单字名字典为空，则返回一个空字符串。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return String 随机的女性单字名
	 */
	public String getRandomSingleFemaleGivename() {
		double r = Math.random();
		int size = femaleName_sSet.size();
		if (size == 0) {
			return "";
		} else {
			int index = (int) (r * size);
			List<String> ls = new ArrayList<>(femaleName_sSet);
			return ls.get(index);
		}
	}

	/**
	 * 
	 * <h2>从名称字典中随机获取一个女性双字名</h2>
	 * <p>
	 * 该方法每次调用均会重新随机。如果女性双字名字典为空，则返回一个空字符串。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param 无
	 * @return String 随机的女性双字名
	 */
	public String getRandomDoubleFemaleGivename() {
		double r = Math.random();
		int size = femaleName_dSet.size();
		if (size == 0) {
			return "";
		} else {
			int index = (int) (r * size);
			List<String> ls = new ArrayList<>(femaleName_dSet);
			return ls.get(index);
		}
	}

	/**
	 * 
	 * <h2>检查当前名称字典中是否有某一名称</h2>
	 * <p>
	 * 该方法检查传入的字符串是否存在于名称字典中。
	 * </p>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param testgivename
	 *            String 需要检查的名称字符串
	 * @return boolean 表示是否存在
	 */
	public boolean haveGivenameOf(String testgivename) {
		return maleName_sSet.contains(testgivename) || maleName_dSet.contains(testgivename)
				|| femaleName_sSet.contains(testgivename) || femaleName_dSet.contains(testgivename);
	}
}
