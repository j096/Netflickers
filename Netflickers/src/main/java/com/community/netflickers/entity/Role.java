package com.community.netflickers.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    private String memberId; // member id
    
    @Column
    private String roleName;
    
    @Builder
    public Role(Long id, String memberId, String roleName) {
    	this.id=id;
    	this.memberId=memberId;
    	this.roleName=roleName;
    }

}
