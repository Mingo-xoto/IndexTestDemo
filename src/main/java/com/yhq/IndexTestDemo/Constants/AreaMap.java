package com.yhq.IndexTestDemo.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author HuaQi.Yang
 * @date 2017年8月16日
 */
public class AreaMap {

	public static HashMap<Integer, HashMap<String, List<String>>> map = new HashMap<>();

	static {
		map(map, "area.txt", "\t\t", 2);
	}

	public static void map(HashMap<Integer, HashMap<String, List<String>>> map, String fileName, String fix, int skipLine) {
		File file = new File(FamilyName.class.getResource("").getPath() + "../files/" + fileName);
		FileInputStream fis = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
			for (int i = 0; i < skipLine; ++i) {
				br.readLine();
			}
			String line = null;
			String cKey = "";
			while ((line = br.readLine()) != null) {
				String[] info = line.split(fix);
				HashMap<String, List<String>> cMap;
				Integer key = Integer.parseInt(info[3]);
				if (map.containsKey(key)) {
					cMap = map.get(key);
					if (cMap.containsKey(cKey)) {
						cMap.get(cKey).add(info[1]);
					} else {
						cMap = new HashMap<>();
						List<String> list = new ArrayList<String>();
						list.add(info[1]);
						cMap.put(cKey, list);
					}
				} else {
					cKey = info[1];
					cMap = new HashMap<>();
					map.put(key, cMap);
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		AreaMap.map(map, "area.txt", "\t\t", 2);
		for (Map.Entry<Integer, HashMap<String, List<String>>> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "\t:\t" + entry.getValue());
			for (Map.Entry<String, List<String>> entry1 : entry.getValue().entrySet()) {
				System.out.println(entry.getKey() + "\t:\t");
				List<String> list = entry1.getValue();
				for (String string : list) {
					System.out.println(string);
				}
			}
		}
	}
}
