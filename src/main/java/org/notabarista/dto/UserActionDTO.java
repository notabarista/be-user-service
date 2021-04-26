package org.notabarista.dto;

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
@ToString(callSuper = true, exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ApiModel
public class UserActionDTO extends AbstractAuditedDTO {

	private String name;
	private String serviceName;
	private String path;
	private String action;
	private String resource;
	private String description;
	
}
