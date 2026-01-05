package com.the_self_actualization_project.usermanagement.adapter.out.persistence.auditing;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

public abstract class EntityAudit {

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedBy
  private LocalDateTime updatedAt;
}
