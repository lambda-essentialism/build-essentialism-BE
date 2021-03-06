package com.lambda.essentialism.repository;

import com.lambda.essentialism.model.User;
import com.lambda.essentialism.model.Value;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ValueRepository
  extends CrudRepository<Value, Long> {

    @Modifying
    @Query(value = "INSERT INTO UserValues(valueid, userid) values (:valueid, :userid)", nativeQuery = true)
    @Transactional
    void insertUserValue(long valueid, long userid);

    @Modifying
    @Query(value = "DELETE FROM uservalues WHERE valueid=:valueid AND userid=:userid", nativeQuery = true)
    @Transactional
    void deleteUserValue(long valueid, long userid);

}

