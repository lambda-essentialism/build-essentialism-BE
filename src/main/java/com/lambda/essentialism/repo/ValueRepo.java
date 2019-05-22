package com.lambda.essentialism.repo;

import com.lambda.essentialism.model.Value;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ValueRepo
  extends CrudRepository<Value, Long> {
    @Modifying
    @Query(value = "INSERT INTO UserValues(userid, valueid) values (:userid, :valueid)", nativeQuery = true)
    @Transactional
    void insertUserValues(long userid, long valueid);
}

