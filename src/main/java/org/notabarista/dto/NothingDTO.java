package org.notabarista.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {  })
@EqualsAndHashCode(callSuper = false, exclude = { })
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel
public class NothingDTO extends AbstractAuditedDTO {

	private String name;
	private String description;
	private Integer someNumber;
	private Boolean isActive;
	
	
}
