package org.notabarista.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(exclude = {"userRoles"})
@EqualsAndHashCode(callSuper = false, exclude = {"userRoles"})
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_action")
public class UserActionEntity extends AbstractAuditedEntity {

	@Column(name = "name")
	private String name;
	
	@Column(name = "service_name")
	private String serviceName;
	
	@Column(name = "path")
	private String path;
	
	@Column(name = "action")
	private String action;
	
	@Column(name = "resource")
	private String resource;
	
	@Column(name = "description")
	private String description;
	
	@ManyToMany(mappedBy = "userActions", targetEntity = UserRoleEntity.class)
	@JsonIgnoreProperties
	private List<UserRoleEntity> userRoles;
	
}
