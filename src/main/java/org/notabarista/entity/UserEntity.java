package org.notabarista.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
@ToString(exclude = {"userProfile", "userRoles"})
@EqualsAndHashCode(callSuper = false, exclude = {"userProfile", "userRoles"})
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user")
public class UserEntity extends AbstractAuditedEntity {

	@Column(name = "user_identifier")
	private String userIdentifier;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@OneToOne(targetEntity = UserProfileEntity.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties("user")
	private UserProfileEntity userProfile;

	@ManyToMany(targetEntity = UserRoleEntity.class, fetch = FetchType.LAZY)
	@JoinTable(name = "user_user_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private List<UserRoleEntity> userRoles;
	
}
