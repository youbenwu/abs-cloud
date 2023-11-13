package com.outmao.ebs.common.kcnamer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * <h2>青阳龙野中文姓名随机生成工具-在终端运行本工具时的主类</h2>
 * <p>该类包含了供JVM在终端运行本工具时的所有处理方法，且仅供终端运行使用。具体说明请参见main(String[] args)方法。</p>
 * @author 青阳龙野(kohgylw)
 * @version 1.0
 */
public final class MC {

	private KCNamer rcnu = new KCNamer();//使用默认字典实例化工具对象
	private Map<String, String> om = new HashMap<>();// 选项提示信息表

	public MC() {
		// 各个选项代表的含义列表，在提示时输出。写在这里便于增加修改。
		om.put("无", "以默认规则随机生成一个不区分男女风格的、随机长度（2-4）的中文名称。");
		om.put("-m", "随机生成男性风格的、随机长度的中文名。该方法会根据默认的姓名字典，以30%，60%，10%的概率随机生成一个2，3，4个字的男性风格姓名。");
		om.put("-f", "随机生成女性风格的、随机长度的中文名。该方法会根据默认的姓名字典，以30%，60%，10%的概率随机生成一个2，3，4个字的女性风格姓名。");
		om.put("-sm '输入姓氏'", "随机生成男性风格的、指定姓氏的、随机长度的中文名。");
		om.put("-sf '输入姓氏'", "随机生成女性风格的、指定姓氏的、随机长度的中文名。");
		om.put("-ml -2|-3|-4", "随机生成男性风格的，指定长度的中文名，其中第二个参数表示长度。");
		om.put("-fl -2|-3|-4", "随机生成女性风格的，指定长度的中文名，其中第二个参数表示长度。");
		om.put("-c", "随机生成一个忽略名称字典、完全随机的中文名。");
	}

	/**
	 * 
	 * <h2>供终端运行使用的主方法</h2>
	 * <p>
	 * JVM入口，接收参数确定输出姓名的风格，并调用随机中文名生成工具生成一个符合要求的中文名，之后在终端打印。参数列表见下方：
	 * </p>
	 * <ul>
	 * <li>-m 随机生成男性风格的，随机长度的中文名</li>
	 * <li>-f 随机生成女性风格的，随机长度的中文名</li>
	 * <li>-sm sur 随机生成指定姓氏的，男性风格的，随机长度的中文名</li>
	 * <li>-sf sur 随机生成指定姓氏的，女性风格的，随机长度的中文名</li>
	 * <li>-ml -2/-3/-4 随机生成男性风格的，指定长度的中文名</li>
	 * <li>-fl -2/-3/-4 随机生成女性风格的，指定长度的中文名</li>
	 * <li>-c 生成一个无任何限制、完全随机的中文名</li>
	 * <li>无参数 以默认规则随机生成一个随机长度的、不区分男女风格的中文名。</li>
	 * </ul>
	 * 
	 * @author 青阳龙野(kohgylw)
	 * @param args
	 *            终端输入的参数列表
	 */
	public static void main(String[] args) {
		// 根据输入的选项参数进行输出。
		MC mc = new MC();
		if (args.length == 0) {
			mc.oD();
		} else {
			String fo = args[0];
			switch (fo) {
			case "-m":
				if(args.length==1) {
					mc.oM();
				}else {
					mc.printOpt();
				}
				break;
			case "-f":
				if(args.length==1) {
					mc.oF();
				}else {
					mc.printOpt();
				}
				break;
			case "-sm":
				if (args.length == 2) {
					mc.oSM(args[1]);
				} else {
					mc.printOpt();
				}
				break;
			case "-sf":
				if (args.length == 2) {
					mc.oSF(args[1]);
				} else {
					mc.printOpt();
				}
				break;
			case "-ml":
				if (args.length == 2) {
					mc.oML(args[1]);
				} else {
					mc.printOpt();
				}
				break;
			case "-fl":
				if (args.length == 2) {
					mc.oFL(args[1]);
				} else {
					mc.printOpt();
				}
				break;
			case "-c":
				if(args.length==1) {
					mc.oC();
				}else {
					mc.printOpt();
				}

			default:
				mc.printOpt();
				break;
			}
		}

	}
	
	//各种选项的处理方法
	// 无选项
	private void oD() {
		print(rcnu.getRandomName());
	}

	// -m
	private void oM() {
		print(rcnu.getRandomMaleName());
	}

	// -f
	private void oF() {
		print(rcnu.getRandomFemaleName());
	}

	// -sm
	private void oSM(String surname) {
		print(rcnu.getMaleNameOfSurname(surname));
	}

	// -sf
	private void oSF(String surname) {
		print(rcnu.getFemaleNameOfSurname(surname));
	}

	// -ml
	private void oML(String l) {
		switch (l) {
		case "-2":
			print(rcnu.getRandomMaleName(NameLength.TWO));
			break;
		case "-3":
			print(rcnu.getRandomMaleName(NameLength.THREE));
			break;
		case "-4":
			print(rcnu.getRandomMaleName(NameLength.FOUR));
			break;
		default:
			printOpt();
			break;
		}
	}

	// -ml
	private void oFL(String l) {
		switch (l) {
		case "-2":
			print(rcnu.getRandomFemaleName(NameLength.TWO));
			break;
		case "-3":
			print(rcnu.getRandomFemaleName(NameLength.THREE));
			break;
		case "-4":
			print(rcnu.getRandomFemaleName(NameLength.FOUR));
			break;
		default:
			printOpt();
			break;
		}
	}
	
	//-c
	private void oC() {
		print(rcnu.getComRandomName());
	}

	// 打印提示信息标准方法，便于修改
	private void printOpt() {
		System.err.println("============KCNamer============");
		System.err.println("《随机中文名生成工具@青阳龙野》");
		System.err.println("* 请输入 java -jar KCNamer.jar [选项] 运行本工具 *");
		System.err.println("[选项]列表：");
		Iterator<Entry<String, String>> itor = om.entrySet().iterator();
		while (itor.hasNext()) {
			Entry<String, String> e = itor.next();
			System.err.println(e.getKey() + " : " + e.getValue());
		}
		System.err.println("* 示例：随机生成男性风格的、随机长度的中文名：java -jar KCNamer.jar -m *");
		System.err.println("============KCNamer============");
	}

	// 输出名称结果标准方法，便于修改
	private void print(String txt) {
		System.out.println(txt);
	}

}
