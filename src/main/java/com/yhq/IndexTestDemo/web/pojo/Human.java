package com.yhq.IndexTestDemo.web.pojo;

import java.util.Date;

/**
 * @author HuaQi.Yang
 * @date 2017年8月17日
 */
/**
 * @author HuaQi.Yang
 * @date 2017年8月17日
 */
public class Human {

	private int id;
	private String name;// ` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '姓名',
	private int age;// ` int(11) NOT NULL DEFAULT '0' COMMENT '年龄',
	private boolean sex;// ` tinyint(4) NOT NULL DEFAULT '0' COMMENT '性别',
	private int occupation;// ` int(11) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '职业',
	private int education;// ` int(11) NOT NULL DEFAULT '0' COMMENT '学历',
	private Date birthday;// ` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生日',
	private String city;// ` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '所在城市',
	private String adress;// ` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '详细地址',
	private String town;// ` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '镇',
	private String village;// ` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '村',
	private String province;// ` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '省份',
	private String district;// ` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '区',

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public int getOccupation() {
		return occupation;
	}

	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}

	public int getEducation() {
		return education;
	}

	public void setEducation(int education) {
		this.education = education;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Human(String name, int age, boolean sex, int occupation, int education, Date birthday, String city, String adress, String town, String village, String province,
			String district) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.occupation = occupation;
		this.education = education;
		this.birthday = birthday;
		this.city = city;
		this.adress = adress;
		this.town = town;
		this.village = village;
		this.province = province;
		this.district = district;
	}

}
