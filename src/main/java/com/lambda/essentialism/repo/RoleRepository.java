package com.lambda.essentialism.repo;

import com.lambda.essentialism.model.Role;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepository
  extends CrudRepository<Role, Long> {

  @Modifying
  @Query(value = "DELETE from UserRoles where userid = :userid")
  @Transactional
  void deleteUserRolesByUserId(long userid);

  @Modifying
  @Query(value = "DELETE from UserRoles where roleid = :roleid")
  @Transactional
  void deleteUserRolesByRoleId(long roleid);

  @Modifying
  @Query(
    value = "INSERT INTO UserRoles(userid, roleid) values (:userid, :roleid)",
    nativeQuery = true
  )
  @Transactional
  void insertUserRoles(long userid, long roleid);

  @Modifying
  @Query(
    value = "DELETE from UserRoles where userid = :userid AND roleid = :roleid",
    nativeQuery = true
  )
  @Transactional
  void deleteUserRoles(long userid, long roleid);

}

