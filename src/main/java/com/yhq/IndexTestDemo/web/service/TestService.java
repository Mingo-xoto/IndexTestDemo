package com.yhq.IndexTestDemo.web.service;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.yhq.IndexTestDemo.ChineseName;
import com.yhq.IndexTestDemo.FileReaderTool;
import com.yhq.IndexTestDemo.common.connection.DBHelper;
import com.yhq.IndexTestDemo.common.enums.AreaTypeEnum;
import com.yhq.IndexTestDemo.web.dao.TestMapper;
import com.yhq.IndexTestDemo.web.pojo.Human;

@Service
public class TestService implements ITestService {

	@Autowired
	private TestMapper testMapper;

	private AtomicInteger count = new AtomicInteger(0);
	private AtomicLong time = new AtomicLong(0);

	final static ThreadLocal<Object[][]> localObjects = new ThreadLocal<>();
	final static ThreadLocal<ArrayList<List<Object>>> localArrayObject = new ThreadLocal<>();

	final static ThreadLocal<List<Human>> localHumans = new ThreadLocal<>();

	@Transactional(value = "transactionManager", rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void batchInsert1(int len) {
		long begin = System.currentTimeMillis();
		List<Human> list = buildList(len);
		long end = System.currentTimeMillis();
		testMapper.batchInsertList(list);
		// count.addAndGet(list.size());
		end = System.currentTimeMillis();
		// time.addAndGet(end - begin);
		System.out.println("batchInsert1：" + Thread.currentThread().getName() + "插入" + list.size() + "条,耗时："
				+ (end - begin) + "豪秒");
		// System.out.println(
		// "batchInsert1：" + Thread.currentThread().getName() + "插入" +
		// count.intValue() + "条,耗时：" + time.longValue() + "豪秒");
	}

	@Transactional(value = "transactionManager", rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void batchInsert2(int len) {
		long begin = System.currentTimeMillis();
		List<List<Object>> array = buildArray(len);
		long end = System.currentTimeMillis();
		testMapper.batchInsertArray(array);
		count.addAndGet(array.size());
		end = System.currentTimeMillis();
		time.addAndGet(end - begin);
		System.out.println("batchInsert2：" + Thread.currentThread().getName() + "插入" + count.intValue() + "条,耗时："
				+ time.longValue() + "豪秒");
	}

	@Transactional(value = "transactionManager", rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void batchInsert3(int len) {
		long begin = System.currentTimeMillis();
		Object[][] array = buildArray2(len);
		long end = System.currentTimeMillis();
		testMapper.batchInsertArray2(array);
		count.addAndGet(array.length);
		end = System.currentTimeMillis();
		time.addAndGet(end - begin);
		System.out.println("batchInsert3：" + Thread.currentThread().getName() + "插入" + count.intValue() + "条,耗时："
				+ time.longValue() + "豪秒");
	}

	@Transactional(value = "transactionManager", rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void batchInsert4(int len) {
		Object[][] array = buildArray2(len);
		testMapper.batchInsertArray2(array);
	}

	static DBHelper db1 = null;
	static ResultSet ret = null;
	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static void main(String[] args) {
		// insertAreaData();
		insertPersonData();
		// selectData();
	}

	private static void selectData() {
		String sql = "select * from person";// SQL语句
		db1 = new DBHelper(sql);// 创建DBHelper对象

		try {
			ret = db1.pst.executeQuery();// 执行语句，得到结果集
			while (ret.next()) {
				String uid = ret.getString(1);
				String ufname = ret.getString(2);
				String ulname = ret.getString(3);
				String udate = ret.getString(4);
				System.out.println(uid + "\t" + ufname + "\t" + ulname + "\t" + udate);
			} // 显示数据
			ret.close();
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private List<Human> buildList(int len) {
		Map<Integer, Object[]> map = new HashMap<>();
		Map<Integer, int[]> pMap = new HashMap<>();
		List<Integer> codes = new ArrayList<>();
		List<Integer> pCodes = new ArrayList<>();
		try {
			FileReaderTool.map(map, pMap, codes, pCodes, ResourceUtils.getFile("classpath:static/area.txt"), "\t\t", 2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (localHumans.get() == null) {
			localHumans.set(new ArrayList<Human>(len));
		}
		List<Human> list = localHumans.get();
		for (int count = 0; count < len; ++count) {
			Human person = buildHuman(map, pMap, codes);
			list.add(person);
		}
		return list;
	}

	private Object[][] buildArray2(int len) {
		Map<Integer, Object[]> map = new HashMap<>();
		Map<Integer, int[]> pMap = new HashMap<>();
		List<Integer> codes = new ArrayList<>();
		List<Integer> pCodes = new ArrayList<>();
		try {
			FileReaderTool.map(map, pMap, codes, pCodes, ResourceUtils.getFile("classpath:static/area.txt"), "\t\t", 2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (localObjects.get() == null) {
			localObjects.set(new Object[len][15]);
		}
		Object[][] array = localObjects.get();
		System.out.println(Thread.currentThread().getName() + ":" + array.hashCode());
		for (int count = 0; count < len; ++count) {
			Human human = buildHuman(map, pMap, codes);
			// name,age,sex,occupation,education,birthday,city,adress,town,village,province,district,thread,create_time
			int i = 0;
			array[count][i++] = human.getName();
			array[count][i++] = human.getAge();
			array[count][i++] = human.isSex();
			array[count][i++] = human.getOccupation();
			array[count][i++] = human.getEducation();
			array[count][i++] = human.getBirthday();
			array[count][i++] = human.getCity();
			array[count][i++] = human.getAdress();
			array[count][i++] = human.getTown();
			array[count][i++] = human.getVillage();
			array[count][i++] = human.getProvince();
			array[count][i++] = human.getDistrict();
			array[count][i++] = Thread.currentThread().getName();
			array[count][i++] = new Date();
			array[count][i++] = array[count].hashCode();
		}
		return array;
	}

	private List<List<Object>> buildArray(int len) {
		Map<Integer, Object[]> map = new HashMap<>();
		Map<Integer, int[]> pMap = new HashMap<>();
		List<Integer> codes = new ArrayList<>();
		List<Integer> pCodes = new ArrayList<>();
		try {
			FileReaderTool.map(map, pMap, codes, pCodes, ResourceUtils.getFile("classpath:static/area.txt"), "\t\t", 2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (localArrayObject.get() == null) {
			localArrayObject.set(new ArrayList<List<Object>>(len));
		}
		List<List<Object>> array = localArrayObject.get();
		for (int count = 0; count < len; ++count) {
			Human human = buildHuman(map, pMap, codes);
			// name,age,sex,occupation,education,birthday,city,adress,town,village,province,district
			ArrayList<Object> array1 = new ArrayList<Object>();
			array1.add(human.getName());
			array1.add(human.getAge());
			array1.add(human.isSex());
			array1.add(human.getOccupation());
			array1.add(human.getEducation());
			array1.add(human.getBirthday());
			array1.add(human.getCity());
			array1.add(human.getAdress());
			array1.add(human.getTown());
			array1.add(human.getVillage());
			array1.add(human.getProvince());
			array1.add(human.getDistrict());
			array.add(array1);
		}
		return array;
	}

	private Human buildHuman(Map<Integer, Object[]> map, Map<Integer, int[]> pMap, List<Integer> codes) {
		int occupation_rand = new Random().nextInt(6);
		int education_rand = new Random().nextInt(7);
		int sex_rand = new Random().nextInt(2);
		int age_rand = new Random().nextInt(120);
		// 随机生日
		Calendar cal = Calendar.getInstance();
		int year_rand = new Random().nextInt(10);
		cal.add(Calendar.YEAR, -year_rand);
		int month_rand = new Random().nextInt(12);
		cal.add(Calendar.MONTH, -month_rand);
		int day_rand = new Random().nextInt(365);
		cal.add(Calendar.DAY_OF_MONTH, -day_rand);
		int hour_rand = new Random().nextInt(24);
		cal.add(Calendar.HOUR, -hour_rand);
		int minute_rand = new Random().nextInt(60);
		cal.add(Calendar.MINUTE, -minute_rand);
		int second_rand = new Random().nextInt(60);
		cal.add(Calendar.SECOND, -second_rand);
		int m_second_rand = new Random().nextInt(1000);
		cal.add(Calendar.MILLISECOND, -m_second_rand);
		int adress_rand = new Random().nextInt(codes.size());
		// 镇
		int key = codes.get(adress_rand);
		Object[] towns = map.get(key);
		int tkey;
		int ttype = (int) towns[2];
		String town;
		if (ttype == AreaTypeEnum.STREET.ordinal() + 1) {
			town = (String) towns[0];
			tkey = (int) towns[1];
		} else {
			town = "未规划镇级";
			tkey = key;
		}
		// 村
		String village = ChineseName.getChinese() + "村";
		// 区
		int dinfos[] = pMap.get(tkey);
		Object[] districts = map.get(dinfos[0]);
		int dtype = (int) districts[2];
		String district;
		int dkey;
		if (dtype == AreaTypeEnum.DISTRICT.ordinal() + 1) {
			district = (String) districts[0];
			dkey = (int) districts[1];
		} else {
			district = "未规划地区级";
			dkey = key;
		}
		// 市
		int cinfos[] = pMap.get(dkey);

		Object citys[] = map.get(cinfos[0]);
		int ctype = (int) citys[2];
		String city;
		int ckey;
		if (ctype == AreaTypeEnum.CITY.ordinal() + 1) {
			city = (String) citys[0];
			ckey = (int) citys[1];
		} else {
			city = "未规划市级";
			ckey = dkey;
		}
		// 省
		int pinfos[] = pMap.get(ckey);
		Object provinces[] = map.get(pinfos[0]);
		int ptype = (int) provinces[2];
		String province;
		int pkey;
		if (ptype == AreaTypeEnum.PROVINCE.ordinal() + 1) {
			province = (String) provinces[0];
			pkey = (int) provinces[1];
		} else {
			province = "未规划省级";
			pkey = ckey;
		}
		if (province.equals("中华人民共和国")) {
			System.out.println();
		}
		Human person = new Human(ChineseName.getRandomName(), age_rand, sex_rand == 0 ? false : true, occupation_rand,
				education_rand, cal.getTime(), city,
				province + city + district + town + village + new Random().nextInt(200) + "号", town, village, province,
				district);
		return person;
	}

	private static void insertPersonData() {
		try {
			Map<Integer, Object[]> map = new HashMap<>();
			Map<Integer, int[]> pMap = new HashMap<>();
			List<Integer> codes = new ArrayList<>();
			List<Integer> pCodes = new ArrayList<>();
			try {
				FileReaderTool.map(map, pMap, codes, pCodes, ResourceUtils.getFile("classpath:static/area.txt"), "\t\t",
						2);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Long begin = System.currentTimeMillis();
			Connection conn = new DBHelper().getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement(
					"insert into person(name,age,sex,occupation,education,birthday,city,adress,town,village,province,district) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			final int batchSize = 100000;
			int count = 0;
			for (; count < 100; ++count) {
				int occupation_rand = new Random().nextInt(6);
				int education_rand = new Random().nextInt(7);
				int sex_rand = new Random().nextInt(2);
				int age_rand = new Random().nextInt(120);
				// 随机生日
				Calendar cal = Calendar.getInstance();
				int year_rand = new Random().nextInt(10);
				cal.add(Calendar.YEAR, -year_rand);
				int month_rand = new Random().nextInt(12);
				cal.add(Calendar.MONTH, -month_rand);
				int day_rand = new Random().nextInt(365);
				cal.add(Calendar.DAY_OF_MONTH, -day_rand);
				int hour_rand = new Random().nextInt(24);
				cal.add(Calendar.HOUR, -hour_rand);
				int minute_rand = new Random().nextInt(60);
				cal.add(Calendar.MINUTE, -minute_rand);
				int second_rand = new Random().nextInt(60);
				cal.add(Calendar.SECOND, -second_rand);
				int m_second_rand = new Random().nextInt(1000);
				cal.add(Calendar.MILLISECOND, -m_second_rand);
				pst.setString(1, ChineseName.getRandomName());
				pst.setInt(2, age_rand);
				pst.setInt(3, sex_rand);
				pst.setInt(4, occupation_rand);
				pst.setInt(5, education_rand);
				pst.setString(6, df.format(cal.getTime()));
				int adress_rand = new Random().nextInt(codes.size());
				// 镇
				int key = codes.get(adress_rand);
				Object[] towns = map.get(key);
				int tkey;
				int ttype = (int) towns[2];
				String town;
				if (ttype == AreaTypeEnum.STREET.ordinal() + 1) {
					town = (String) towns[0];
					tkey = (int) towns[1];
				} else {
					town = "未规划镇级";
					tkey = key;
				}
				// 村
				String village = ChineseName.getChinese() + "村";
				// 区
				int dinfos[] = pMap.get(tkey);
				Object[] districts = map.get(dinfos[0]);
				int dtype = (int) districts[2];
				String district;
				int dkey;
				if (dtype == AreaTypeEnum.DISTRICT.ordinal() + 1) {
					district = (String) districts[0];
					dkey = (int) districts[1];
				} else {
					district = "未规划地区级";
					dkey = key;
				}
				// 市
				int cinfos[] = pMap.get(dkey);

				Object citys[] = map.get(cinfos[0]);
				int ctype = (int) citys[2];
				String city;
				int ckey;
				if (ctype == AreaTypeEnum.CITY.ordinal() + 1) {
					city = (String) citys[0];
					ckey = (int) citys[1];
				} else {
					city = "未规划市级";
					ckey = dkey;
				}
				// 省
				int pinfos[] = pMap.get(ckey);
				Object provinces[] = map.get(pinfos[0]);
				int ptype = (int) provinces[2];
				String province;
				int pkey;
				if (ptype == AreaTypeEnum.PROVINCE.ordinal() + 1) {
					province = (String) provinces[0];
					pkey = (int) provinces[1];
				} else {
					province = "未规划省级";
					pkey = ckey;
				}
				if (province.equals("中华人民共和国")) {
					System.out.println();
				}
				pst.setString(7, city);
				pst.setString(8, province + city + district + town + village + new Random().nextInt(200) + "号");
				pst.setString(9, town);
				pst.setString(10, village);
				pst.setString(11, province);
				pst.setString(12, district);
				pst.addBatch(); // 加入批量处理
				if (count % batchSize == 0) {
					pst.executeBatch();
					conn.commit();
				}
			}
			pst.executeBatch();
			conn.commit();
			pst.close();
			conn.close();
			Long end = System.currentTimeMillis();
			System.out.println("插入记录：" + count + "条");
			System.out.println("cast : " + (end - begin) / 1000 + " s");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static void insertAreaData() {
		try {

			Long begin = System.currentTimeMillis();
			Connection conn = new DBHelper().getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement(
					"insert into area(area_code,area_name,areap_parent_code,area_type) values(?,?,?,?)");
			final int batchSize = 1000;
			int count = 0;
			ArrayList<String[]> values = new ArrayList<>();
			FileReaderTool.read(values, "area.txt", "\t\t", 2);
			for (String[] value : values) {
				pst.setString(1, value[0]);
				pst.setString(2, value[1]);
				pst.setString(3, value[2]);
				pst.setString(4, value[3]);
				pst.addBatch(); // 加入批量处理
				if (count++ % batchSize == 0) {
					pst.executeBatch();
					conn.commit();
				}
			}
			pst.executeBatch();
			conn.commit();
			pst.close();
			conn.close();
			Long end = System.currentTimeMillis();
			System.out.println("插入记录：" + count + "条");
			System.out.println("cast : " + (end - begin) / 1000 + " s");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
