package com.disk.base.bean;

import org.springframework.stereotype.Component;

/**
 * 分享统计实体类
 * @author llq
 *
 */
@Component
public class ShareStats {

	private int num;
	
	private String shareDate;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getShareDate() {
		return shareDate;
	}

	public void setShareDate(String shareDate) {
		this.shareDate = shareDate;
	}
	
	
}
