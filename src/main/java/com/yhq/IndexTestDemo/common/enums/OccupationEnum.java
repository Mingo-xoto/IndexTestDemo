package com.yhq.IndexTestDemo.common.enums;

/**
 * @author HuaQi.Yang
 * @date 2017年8月16日
 */
public enum OccupationEnum {
	PROGRAMMER("程序员"), TEACHER("教师"), DOCTOR("医生"), WAITER("服务员"), LAWER("律师"), FARMER("农民");

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private OccupationEnum(String text) {
		this.text = text;
	}
}