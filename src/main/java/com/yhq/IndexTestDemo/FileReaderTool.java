package com.yhq.IndexTestDemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.yhq.IndexTestDemo.Constants.FamilyName;

/**
 * @author HuaQi.Yang
 * @date 2017年8月16日
 */
public class FileReaderTool {

	public static void read(List<String[]> values, String fileName, String fix, int skipLine) {
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
			while ((line = br.readLine()) != null) {
				String[] info = line.split(fix);
				values.add(info);
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

	public static void map(Map<Integer, Object[]> map, Map<Integer, int[]> pMap, List<Integer> codes, List<Integer> pCodes, String fileName, String fix, int skipLine) {
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
			while ((line = br.readLine()) != null) {
				String[] info = line.split(fix);
				try {

					int key = Integer.parseInt(info[0]);
					int pvalue = Integer.parseInt(info[2]);
					int type = Integer.parseInt(info[3]);
					if (type == 5) {
						codes.add(key);
					}
					pCodes.add(pvalue);
					map.put(key, new Object[] { info[1], key, type });
					pMap.put(key, new int[] { pvalue });
				} catch (Exception e) {
					System.out.println();
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

}
