package org.notabarista.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(exclude = {"userProfile"})
@EqualsAndHashCode(callSuper = false, exclude = {"userProfile"})
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_profile_data")
public class UserProfileDataEntity extends AbstractAuditedEntity {

	@Column(name = "key")
	private String key;
	
	@Column(name = "data")
	private String data;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_profile_id", referencedColumnName = "id")
	private UserProfileEntity userProfile;
	
}
