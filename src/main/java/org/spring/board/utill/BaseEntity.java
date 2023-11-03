package org.spring.board.utill;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreationTimestamp // insert쿼리 발생 시 저장해주는 어노테이션
    @Column(updatable = false)
    private LocalDateTime CreateTime;

    @UpdateTimestamp // update쿼리 발생 시 저장해주는 어노테이션
    @Column(insertable = false)
    private LocalDateTime UpdateTime;
}
