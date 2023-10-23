package com.disk.base.service.admin;
/**
 * 后台文件操作service
 */
import java.util.List;

import com.disk.base.dao.admin.FileDao;
import com.disk.base.entity.admin.File;
import com.disk.base.entity.admin.Folder;
import com.disk.base.entity.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileService {
	
	@Autowired
	private FileDao fileDao;
	
	/**
	 * 文件添加/编辑
	 * @param file
	 * @return
	 */
	public File save(File file){
		return fileDao.save(file);
	}
	
	/**
	 * 获取所有的文件列表
	 * @return
	 */
	public List<File> findAll(){
		return fileDao.findAll();
	}
	
	/**
	 * 根据id查询文件
	 * @param id
	 * @return
	 */
	public File find(Long id){
		return fileDao.find(id);
	}
	
	/**
	 * 根据id删除一条记录
	 * @param id
	 */
	public void delete(Long id){
		fileDao.deleteById(id);
	}
	
	/**
	 * 获取所有的根目录文件
	 * @return
	 */
	public List<File> findAllRootFile(User user, Boolean isTrash){
		return fileDao.findByUserAndIsTrashAndFolderIsNull(user,isTrash);
	}
	
	/**
	 * 获取指定目录下的所有文件列表
	 * @param user
	 * @param folder
	 * @return
	 */
	public List<File> findByFolder(User user, Folder folder, Boolean isTrash){
		return fileDao.findByUserAndFolderAndIsTrash(user, folder,isTrash);
	}
	
	/**
	 * 根据用户和是否删除查询
	 * @param user
	 * @param isTrash
	 * @return
	 */
	public List<File> findByUserAndIsTrash(User user,Boolean isTrash){
		return fileDao.findByUserAndIsTrash(user, isTrash);
	}
	
	/**
	 * 根据文件md5值查找
	 * @param md5
	 * @return
	 */
	public List<File> findByMd5(String md5){
		return fileDao.findByMd5(md5);
	}
	
	/**
	 * 根据文件类型查找
	 * @param type
	 * @return
	 */
	public List<File> findByFileType(User user,List<String> suffix,boolean isIn){
		if(isIn){
			return fileDao.findByUserAndSuffixInAndIsTrash(user,suffix,false);
		}
		return fileDao.findByUserAndSuffixNotIn(user, suffix);
	}
	
	/**
	 * 根据md5查询文件是否存在
	 * @param md5
	 * @return
	 */
	public boolean isExist(String md5){
		List<File> findByMd5 = fileDao.findByMd5(md5);
		if(findByMd5 == null) return false;
		if(findByMd5.size() == 0) return false;
		return true;
		
	}
	
	/**
	 * 批量更新文件状态
	 * @param isTrash
	 * @param folderIds
	 */
	@Transactional
	public void batchUpdateFileStatus(Boolean isTrash,List<Long> folderIds){
		fileDao.batchUpdateFileStatus(isTrash, folderIds);
	}
	
	/**
	 * 获取文件数量
	 * @param user
	 * @return
	 */
	public Long getFileCount(User user){
		return fileDao.getFileCount(user.getId());
	}
	
	/**
	 * 获取文件占用空间
	 * @param user
	 * @return
	 */
	public Long getStorageSize(User user){
		return fileDao.getStorageSize(user.getId());
	}
	
	/**
	 * 按照下载次数排行前十
	 * @param user
	 * @return
	 */
	public List<File> findFirst10ByUserOrderBydownloadTimes(User user){
		return fileDao.findFirst10ByUserOrderByDownloadTimesDesc(user);
	}
}
