package com.disk.base.dao.admin;
/**
 * 后台文件数据库操作层
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.disk.base.entity.admin.File;
import com.disk.base.entity.admin.Folder;
import com.disk.base.entity.admin.User;

@Repository
public interface FileDao extends JpaRepository<File, Long> {
	@Query("select f from File f where f.id = :id")
	File find(@Param("id")Long id);
	
	@Query("select count(f.id) from File f where f.user.id = :userId")
	Long getFileCount(@Param("userId")Long userId);
	
	@Query("select sum(f.size) from File f where f.user.id = :userId")
	Long getStorageSize(@Param("userId")Long userId);
	
	List<File> findByUserAndIsTrashAndFolderIsNull(User user,Boolean isTrash);
	
	List<File> findByUserAndIsTrash(User user,Boolean isTrash);
	
	List<File> findFirst10ByUserOrderByDownloadTimesDesc(User user);
	
	List<File> findByUserAndFolderAndIsTrash(User user,Folder folder,Boolean isTrash);
	
	List<File> findByMd5(String md5);
	
	List<File> findByUserAndSuffixInAndIsTrash(User user,List<String> suffix,Boolean isTrash);
	
	List<File> findByUserAndSuffixNotIn(User user,List<String> suffix);
	
	@Query("update File f set f.isTrash = :isTrash where f.folder.id in :folderIds")
	@Modifying
	void batchUpdateFileStatus(@Param("isTrash")Boolean isTrash,@Param("folderIds") List<Long> folderIds);
}
