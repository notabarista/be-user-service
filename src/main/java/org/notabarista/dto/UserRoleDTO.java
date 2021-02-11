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
@ToString(callSuper = true, exclude = {"userActions"})
@EqualsAndHashCode(callSuper = false, exclude = {"userActions"})
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ApiModel
public class UserRoleDTO extends AbstractAuditedDTO {

	private String name;
	private String description;

	private List<UserActionDTO> userActions;
	
}
