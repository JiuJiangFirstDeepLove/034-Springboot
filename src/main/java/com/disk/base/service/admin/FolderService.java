package com.disk.base.service.admin;
/**
 * 后台文件夹操作service
 */
import java.util.List;

import com.disk.base.entity.admin.Folder;
import com.disk.base.entity.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disk.base.dao.admin.FolderDao;

@Service
public class FolderService {
	
	@Autowired
	private FolderDao folderDao;
	
	/**
	 * 文件夹添加/编辑
	 * @param folder
	 * @return
	 */
	public Folder save(Folder folder){
		return folderDao.save(folder);
	}
	
	/**
	 * 获取所有的文件夹列表
	 * @return
	 */
	public List<Folder> findAll(){
		return folderDao.findAll();
	}
	
	/**
	 * 根据id查询文件夹
	 * @param id
	 * @return
	 */
	public Folder find(Long id){
		return folderDao.find(id);
	}
	
	/**
	 * 根据id删除一条记录
	 * @param id
	 */
	public void delete(Long id){
		folderDao.deleteById(id);
	}
	
	/**
	 * 获取所有的根目录文件夹
	 * @return
	 */
	public List<Folder> findAllRootFolder(User user, Boolean isTrash){
		return folderDao.findByUserAndIsTrashAndParentIsNull(user,isTrash);
	}
	
	/**
	 * 获取指定文件夹下的文件夹列表
	 * @param user
	 * @param parent
	 * @return
	 */
	public List<Folder> findByFolder(User user,Folder parent,Boolean isTrash){
		return folderDao.findByUserAndParentAndIsTrash(user, parent,isTrash);
	}
	
	/**
	 * 获取指定文件夹的子文件夹
	 * @param parent
	 * @return
	 */
	public List<Folder> findChildFolder(Folder parent,Boolean isTrash){
		return folderDao.findByParentAndIsTrash(parent,isTrash);
	}
	
	/**
	 * 批量更新文件夹状态
	 * @param isTrash
	 * @param folderIds
	 */
	@Transactional
	public void batchUpdateFolderStatus(Boolean isTrash,List<Long> folderIds){
		folderDao.batchUpdateFolderStatus(isTrash, folderIds);
	}
	
	/**
	 * 根据用户和是否删除查询
	 * @param user
	 * @param parent
	 * @param isTrash
	 * @return
	 */
	public List<Folder> findByUserAndIsTrash(User user,Boolean isTrash){
		return folderDao.findByUserAndIsTrash(user, isTrash);
	}
	
	/**
	 * 获取文件夹数量
	 * @param user
	 * @return
	 */
	public Long getFolderCount(User user){
		return folderDao.getFolderCount(user.getId());
	}
}
