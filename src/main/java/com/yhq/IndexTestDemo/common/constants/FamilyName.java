package com.yhq.IndexTestDemo.common.constants;

/**
 * @author HuaQi.Yang
 * @date 2017年8月16日
 */
public class FamilyName implements ConstantBuilder {

	public static String[] values = new String[0];

	static {
		new FamilyName().build();
	}

	@Override
	public void build() {
		ConstantBuilder builder = new FamilyName();
		builder.buildProvinceArray(values, "family_name.txt", "\t\t",0);
	}

}
