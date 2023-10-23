package com.disk.base.dao.admin;
/**
 * 后台文件夹数据库操作层
 */
import java.util.List;

import com.disk.base.entity.admin.Folder;
import com.disk.base.entity.admin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderDao extends JpaRepository<Folder, Long> {
	@Query("select f from Folder f where f.id = :id")
	Folder find(@Param("id")Long id);
	
	@Query("select count(f.id) from Folder f where f.user.id = :userId")
	Long getFolderCount(@Param("userId")Long userId);
	
	List<Folder> findByUserAndIsTrashAndParentIsNull(User user, Boolean isTrash);
	
	List<Folder> findByUserAndIsTrash(User user,Boolean isTrash);
	
	List<Folder> findByUserAndParentAndIsTrash(User user,Folder parent,Boolean isTrash);
	
	List<Folder> findByParentAndIsTrash(Folder folder,Boolean isTrash);
	
	@Query("update Folder f set f.isTrash = :isTrash where f.id in :folderIds")
	@Modifying
	void batchUpdateFolderStatus(@Param("isTrash")Boolean isTrash,@Param("folderIds") List<Long> folderIds);
}
