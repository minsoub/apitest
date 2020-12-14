package com.framework.test.domain.posts;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass   // JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifedDate)도 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime modifedDate;		// 조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.

}
