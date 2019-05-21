package com.lambda.essentialism.model;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static javax.persistence.TemporalType.TIMESTAMP;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
abstract class Auditable {
  @CreatedBy
  protected String createdBy;

  @CreatedDate
  @Temporal(TIMESTAMP)
  protected Date createdDate;

  @LastModifiedBy
  protected String lastModifiedBy;

  @LastModifiedDate
  @Temporal(TIMESTAMP)
  protected Date lastModifiedDate;
}

