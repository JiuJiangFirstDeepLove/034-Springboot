package com.disk.base.schedule.admin;

import java.util.List;

import com.disk.base.entity.admin.Share;
import com.disk.base.service.admin.ShareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 检查分享文件是否过期定时器
 * @author Administrator
 *
 */
@Configuration
@EnableScheduling
public class ShareFileCheckSchedule {

	@Autowired
	private ShareService shareService;
	
	private Logger log = LoggerFactory.getLogger(ShareFileCheckSchedule.class);
	
	//@Scheduled(initialDelay=10000,fixedRate=5000)
	@Scheduled(cron="0 0 1 * * ?")//每天凌晨1点0分0秒执行检查任务
	public void shareCheck(){
		log.info("开始执行定时检查分享是否失效任务！");
		List<Share> findCheckList = shareService.findCheckList();
		if(findCheckList == null || findCheckList.size() ==0)return;
		for(Share share : findCheckList){
			if(System.currentTimeMillis() - share.getCreateTime().getTime() > share.getExpireTime()){
				share.setStatus(Share.ADMIN_SHARE_STATUS_EXPIRED);
				shareService.save(share);
				log.info("分享【" + share.getTitle() + "】已经失效！");
			}
		}
	}
	
	@Scheduled(cron="0 */5 * * * ?")//每隔五分钟检查一次
	public void updateCheck(){
	}
}
