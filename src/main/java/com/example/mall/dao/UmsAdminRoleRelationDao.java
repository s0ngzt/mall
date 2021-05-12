package com.example.mall.dao;

import com.example.mall.mbg.model.UmsPermission;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 后台用户与角色管理自定义 Dao
 */
@Mapper
public interface UmsAdminRoleRelationDao {

  /**
   * 获取用户所有权限(包括+-权限)
   */
  List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
