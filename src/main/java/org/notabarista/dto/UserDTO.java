package org.notabarista.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true, exclude = { "userProfile", "userRoles" })
@EqualsAndHashCode(callSuper = false, exclude = { "userProfile", "userRoles" })
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ApiModel
public class UserDTO extends AbstractAuditedDTO {

	private String userIdentifier;
	private String username;
	private String email;
	private String firstName;
	private String lastName;

	private UserProfileDTO userProfile;
	private List<UserRoleDTO> userRoles;

}
