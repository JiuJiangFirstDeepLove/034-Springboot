package com.disk.base.service.admin;
/**
 * 后台文件分享操作service
 */
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.disk.base.bean.PageBean;
import com.disk.base.entity.admin.Share;
import com.disk.base.entity.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.disk.base.dao.admin.ShareDao;

@Service
public class ShareService {
	
	@Autowired
	private ShareDao shareDao;
	
	/**
	 * 文件添加/编辑
	 * @param share
	 * @return
	 */
	public Share save(Share share){
		return shareDao.save(share);
	}
	
	
	
	/**
	 * 根据sn查询分享文件
	 * @param sn
	 * @return
	 */
	public Share find(String sn){
		return shareDao.find(sn);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Share find(Long id){
		return shareDao.find(id);
	}
	
	/**
	 * 根据id删除一条记录
	 * @param id
	 */
	public void delete(Long id){
		shareDao.deleteById(id);
	}
	
	/**
	 * 分页获取分享列表
	 * @param user
	 * @param pageBean
	 * @return
	 */
	public PageBean<Share> findPage(User user, PageBean<Share> pageBean){
		Specification<Share> specification = new Specification<Share>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Share> root,
					CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				Predicate predicate = criteriaBuilder.equal(root.get("user"), user.getId());
				predicate = criteriaBuilder.and(predicate);
				return predicate;
			}
		};
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<Share> findAll = shareDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * 获取分享数
	 * @param user
	 * @return
	 */
	public Long getShareCount(User user){
		return shareDao.getShareCount(user.getId());
	}
	
	/**
	 * 获取统计信息
	 * @param user
	 * @return
	 */
	public List<Object[]> shareStats(User user,int shareType){
		return shareDao.shareStats(user.getId(),shareType);
	}
	
	/**
	 * 获取待检查是否失效列表
	 * @return
	 */
	public List<Share> findCheckList(){
		return shareDao.findCheckList(0, Share.ADMIN_SHARE_STATUS_ENABLE);
	}
}
