package com.yhq.common.enums;

/**
 * @author HuaQi.Yang
 * @date 2017年8月16日
 */
public enum EducationEnum {
	KINDERGARTEN("幼儿园"), PRIMARY_SCHOOL("小学"), JUNIOR_SCHOOL("初中"), HIGH_SCHOOL("高中"), UNDERGRADUATE("本科"), MASTER("硕士"), DOCTOR("博士");

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private EducationEnum(String text) {
		this.text = text;
	}
}