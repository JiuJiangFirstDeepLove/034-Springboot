package com.disk.base.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 文件类型工具类
 * @author llq
 *
 */
public class FileTypeUtil {

	
	public static final List<String> DOC = Arrays.asList("txt",
			"xlsx",
			"xlt",
			"doc",
			"docx",
			"pdf",
			"xls");
	public static final List<String> IMAGE = Arrays.asList("jpg",
			"png",
			"gif",
			"jpeg");
	public static final List<String> ZIP = Arrays.asList("zip",
			"rar",
			"7-Zip",
			"7z",
			"tar",
			"gz",
			"bz2",
			"xz",
			"lzh");
	public static final List<String> VIDEO = Arrays.asList("mp4",
			"avi",
			"rmvb",
			"mkv",
			"flv",
			"3gp",
			"wmv",
			"WMV");
	public static final List<String> MUSIC = Arrays.asList("mp3",
			"WMA",
			"MOD",
			"aiff");
	
	/**
	 * 获取文件类型后缀
	 * @param type
	 * @return
	 */
	public static List<String> getFileSuffix(String type){
		switch (type) {
			case "doc":{
				return DOC;
			}
			case "image":{
				return IMAGE;
			}
			case "music":{
				return MUSIC;
			}
			case "video":{
				return VIDEO;
			}
			case "zip":{
				return ZIP;
			}
		}
		List<String> ret = new ArrayList<String>();
		ret.addAll(DOC);
		ret.addAll(MUSIC);
		ret.addAll(IMAGE);
		ret.addAll(VIDEO);
		ret.addAll(ZIP);
		return null;
	}
}
