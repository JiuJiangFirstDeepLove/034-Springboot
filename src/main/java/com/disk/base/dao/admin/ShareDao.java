package com.disk.base.dao.admin;
/**
 * 后台文件分享数据库操作层
 */
import java.util.List;

import com.disk.base.entity.admin.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareDao extends JpaRepository<Share, Long>,JpaSpecificationExecutor<Share> {
	@Query("select s from Share s where s.sn = :sn")
	Share find(@Param("sn")String sn);
	@Query("select s from Share s where s.id = :id")
	Share find(@Param("id")Long id);
	@Query("select count(s.id) from Share s where s.user.id = :userId")
	Long getShareCount(@Param("userId")Long userId);
	@Query(value="select count(s.id) as num,DATE_FORMAT(create_time,'%Y-%m-%d') as shareDate from ylrc_share s WHERE s.user_id = :userId and s.share_type = :shareType GROUP BY create_time order by DATE_FORMAT(create_time,'%Y%m%d') asc",nativeQuery=true)
	List<Object[]> shareStats(@Param("userId")Long userId,@Param("shareType")int shareType);
	
	@Query("select s from Share s where s.expireTime <> :expireStatus and s.status = :status")
	List<Share> findCheckList(@Param("expireStatus")long expireStatus,@Param("status")int status);
}
