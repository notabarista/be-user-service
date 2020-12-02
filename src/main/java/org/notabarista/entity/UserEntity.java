package org.notabarista.entity;

import javax.persistence.Column;

//@Getter
//@Setter
//@ToString(exclude = {  })
//@EqualsAndHashCode(callSuper = false, exclude = { })
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "user")
public class UserEntity extends AbstractEntity {

	@Column(name = "username")
	private String username;
	
	@Column(name = "username")
	private String email;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
//	TODO add mapping for user profile, user-role and user-type
}
