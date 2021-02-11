package org.notabarista.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@ToString(exclude = {"users"})
@EqualsAndHashCode(callSuper = false, exclude = {"users", "userActions"})
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_role")
public class UserRoleEntity extends AbstractAuditedEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToMany(mappedBy = "userRoles", targetEntity = UserEntity.class)
	@JsonIgnoreProperties
	private List<UserEntity> users;
	
	@ManyToMany(targetEntity = UserActionEntity.class, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role_user_action", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "action_id", referencedColumnName = "id") })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<UserActionEntity> userActions;

}
