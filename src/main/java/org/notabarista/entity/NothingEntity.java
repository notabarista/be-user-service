package org.notabarista.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@ToString(callSuper = true, exclude = {  })
@EqualsAndHashCode(callSuper = false, exclude = { })
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "nothing")
public class NothingEntity extends AbstractAuditedEntity {

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "some_number")
	private Integer someNumber;
	
	@Column(name = "is_active")
	private Boolean isActive;

}
