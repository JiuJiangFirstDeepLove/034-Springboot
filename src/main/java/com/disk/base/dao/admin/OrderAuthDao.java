package com.disk.base.dao.admin;
/**
 * 后台操作日志类数据库操作层
 */
import com.disk.base.entity.admin.OrderAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderAuthDao extends JpaRepository<OrderAuth, Long> {
	
}
