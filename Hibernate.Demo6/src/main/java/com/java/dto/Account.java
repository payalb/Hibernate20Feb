package com.java.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Audited/*(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)*/
public  class Account {
	@Column(name="ano")
	@Id
	int accountNumber;
	float balance;
	@Version
	int version;
	@ManyToMany(cascade=CascadeType.PERSIST)
	List<User> users;
}
