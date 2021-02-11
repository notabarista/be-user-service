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
@ToString(callSuper = true, exclude = {"userProfileData"})
@EqualsAndHashCode(callSuper = false, exclude = {"userProfileData"})
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ApiModel
public class UserProfileDTO extends AbstractAuditedDTO {

	private String phoneNumber;
	private String avatar;
	
	private List<UserProfileDataDTO> userProfileData;
	
}
