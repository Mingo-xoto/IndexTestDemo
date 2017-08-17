package com.yhq.IndexTestDemo.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author HuaQi.Yang
 * @date 2017年8月16日
 */
public interface ConstantBuilder {

	default void buildProvinceArray(String[] values, String fileName, String fix, int skipLine) {
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
			ArrayList<String> tmpList = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				String[] infos = line.split(fix);
				for (String info : infos) {
					tmpList.add(info);
				}
			}
			values = tmpList.toArray(values);
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

	public void build();
}
