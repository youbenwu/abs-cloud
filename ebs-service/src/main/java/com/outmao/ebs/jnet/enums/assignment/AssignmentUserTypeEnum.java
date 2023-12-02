package com.outmao.ebs.jnet.enums.assignment;

/**
 * 外发用户类型枚举: 0普通外发 13机器人发的外发
 * 
 * @author yeyi
 * @date 2019年11月3日
 */
public enum AssignmentUserTypeEnum {
	NORMAL (0, "普通外发"),
	ROBOT(13, "机器人发的外发"),
	;
	
	/**
	 * 状态值
	 */
	private int value;
	/**
	 * 状态说明
	 */
	private String msg;
	private AssignmentUserTypeEnum(int value, String msg) {
		this.value = value;
		this.msg = msg;
	}
	
	public int getValue() {
		return value;
	}

	public String getMsg() {
		return msg;
	}
	
}
