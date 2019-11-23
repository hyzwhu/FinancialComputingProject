package com.group.financialcomputing;

import java.io.*;
import java.util.*;

public class Reader {
	public HashMap<String, List<OneDayStock>> readFiles(String path) throws IOException {

		HashMap<String, List<OneDayStock>> result = new HashMap<>();
		ArrayList<String> files = new ArrayList<>();
		File file = new File(path);
		File[] tempList = file.listFiles();

		for(int i = 0; i < tempList.length; i++) {
			List<OneDayStock> oneStock = new ArrayList<>();

			if (tempList[i].isFile()) {
				FileInputStream inputStream = new FileInputStream(tempList[i]);
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

				String line = null;
				String[] token = null;

				while ((line = br.readLine()) !=null) {
					token = line.split(",");

					if (token[0].equals("Ticker")){
						continue;
					}
					else {
						OneDayStock oneDayStock = new OneDayStock();
						oneDayStock.setTicker(token[0]);
						oneDayStock.setDate(token[1]);
						oneDayStock.setTime(Integer.parseInt(token[2]));
						oneDayStock.setOpen(Double.parseDouble(token[3]));
						oneDayStock.setHigh(Double.parseDouble(token[4]));
						oneDayStock.setLow(Double.parseDouble(token[5]));
						oneDayStock.setClose(Double.parseDouble(token[6]));
						oneDayStock.setVolume(Integer.parseInt(token[7]));
						oneStock.add(oneDayStock);
					}
				}
				br.close();
			}
			result.put(oneStock.get(0).getTicker(),oneStock);
		}
		return result;
	}
}
