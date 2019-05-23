package com.lambda.essentialism.repository;

import com.lambda.essentialism.model.Project;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProjectRepository extends CrudRepository<Project, Long> {

//    @Modifying
//    @Query(value = "SELECT * FROM UserValues(valueid, userid) values (:valueid, :userid)", nativeQuery = true)
//    @Transactional
//    void addUserProjects(long valueid, long userid);
//
//    @Modifying
//    @Query(value = "DELETE FROM uservalues WHERE valueid=:valueid AND userid=:userid", nativeQuery = true)
//    @Transactional
//    void deleteUserValue(long valueid, long userid);
}
