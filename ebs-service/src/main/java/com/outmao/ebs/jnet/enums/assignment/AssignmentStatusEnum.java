package com.outmao.ebs.jnet.enums.assignment;

/**
 * 外发状态枚举: 0生效中 1已到期 2已满员（上限50人） 3已结束 4已取消 5待完成(包含12状态)
 * 
 * @author yeyi
 * @date 2019年9月5日
 */
public enum AssignmentStatusEnum {
	ACTIVE ((byte)0, "生效中"),
	EXPIRED((byte)1, "已到期"),
	FULLED ((byte)2, "已满员"),
	END    ((byte)3, "已结束"),
	CANCEL ((byte)4, "已取消"),
	TO_COMPLETED ((byte)5, "待完成"), // 这个就是已到期与已满员的集合
	;
	
	/**
	 * 状态值
	 */
	private byte value;
	/**
	 * 状态说明
	 */
	private String msg;
	private AssignmentStatusEnum(byte value, String msg) {
		this.value = value;
		this.msg = msg;
	}
	
	public byte getValue() {
		return value;
	}

	public String getMsg() {
		return msg;
	}
	
}
