package com.community.netflickers.entity.auditing;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)//엔티티 db 적용 전,후로 콜백 적용
@MappedSuperclass
@Getter
public abstract class BaseEntity extends BaseTimeEntity{

	@CreatedBy
	@Column(updatable = false)
	private String writer;
	
}
