package com.zhaoxiao.util;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
 
public class AppendStrToEachLineEnd {
	public static void main(String[] args) {
//		addStr("E:\\暂时\\毕设\\参考\\单词\\json\\CET4luan_1.json", ",","E:\\暂时\\毕设\\参考\\单词\\json\\CET4luan_1_update.json");
//		addStr("E:\\暂时\\毕设\\参考\\单词\\json\\CET4luan_2.json", ",","E:\\暂时\\毕设\\参考\\单词\\json\\CET4luan_2_update.json");
//		addStr("E:\\暂时\\毕设\\参考\\单词\\json\\CET6_2.json", ",","E:\\暂时\\毕设\\参考\\单词\\json\\CET6_2_update.json");
//		addStr("E:\\暂时\\毕设\\参考\\单词\\json\\CET6luan_1.json", ",","E:\\暂时\\毕设\\参考\\单词\\json\\CET6luan_1_update.json");
//		addStr("E:\\暂时\\毕设\\参考\\单词\\json\\KaoYan_2.json", ",","E:\\暂时\\毕设\\参考\\单词\\json\\KaoYan_2_update.json");
//		addStr("E:\\暂时\\毕设\\参考\\单词\\json\\KaoYanluan_1.json", ",","E:\\暂时\\毕设\\参考\\单词\\json\\KaoYanluan_1_update.json");
//		addStr("E:\\暂时\\毕设\\参考\\单词\\json\\Level4luan_1.json", ",","E:\\暂时\\毕设\\参考\\单词\\json\\Level4luan_1_update.json");
//		addStr("E:\\暂时\\毕设\\参考\\单词\\json\\Level4luan_2.json", ",","E:\\暂时\\毕设\\参考\\单词\\json\\Level4luan_2_update.json");
//		addStr("E:\\暂时\\毕设\\参考\\单词\\json\\Level8_1.json", ",","E:\\暂时\\毕设\\参考\\单词\\json\\Level8_1_update.json");
//		addStr("E:\\暂时\\毕设\\参考\\单词\\json\\Level8luan_2.json", ",","E:\\暂时\\毕设\\参考\\单词\\json\\Level8luan_2_update.json");
	}
 
	/**
	 * 为指定文本文件的的每一行的结尾添加指定字符串并写入新文件中
	 * 
	 * @param sourceFilePath
	 *            文件位置
	 * @param appendStr
	 *            添加内容
	 * @param destFilePath
	 *            修改后保存位置
	 */
	public static void addStr(String sourceFilePath, String appendStr,String destFilePath) {
		File fileReaded = new File(sourceFilePath);
		try {
			//定义读取文件
			FileReader fr = new FileReader(fileReaded);
			BufferedReader br = new BufferedReader(fr);
			String content = br.readLine();
			
			//定义写出文件
			FileWriter fw = new FileWriter(destFilePath);
			BufferedWriter bw = new BufferedWriter(fw);
			
			while (content != null) {
				System.out.println("读入内容:\t" + content);
				System.out.println("写出内容：\t"+content+appendStr);
//				if(content.indexOf(appendStr)>0) {
//					bw.write(content+"\n");
//				} else {
//					bw.write(content+appendStr+"\n");
//				}
				bw.write(content+appendStr+"\n");
				content = br.readLine();
			}
			
			fw.flush();
			bw.flush();
			fw.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
}