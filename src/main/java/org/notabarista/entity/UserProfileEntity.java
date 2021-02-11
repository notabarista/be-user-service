package org.notabarista.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@ToString(exclude = {"user", "userProfileData"})
@EqualsAndHashCode(callSuper = false, exclude = {"user", "userProfileData"})
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_profile")
public class UserProfileEntity extends AbstractAuditedEntity {

	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "avatar")
	private String avatar;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserEntity user;
	
	@OneToMany(targetEntity = UserProfileDataEntity.class, mappedBy = "userProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties("userProfile")
	private List<UserProfileDataEntity> userProfileData;

}
