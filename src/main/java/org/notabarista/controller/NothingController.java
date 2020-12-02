package org.notabarista.controller;

import org.notabarista.controller.abstr.AbstractDeleteController;
import org.notabarista.dto.NothingDTO;
import org.notabarista.entity.NothingEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nothing")
public class NothingController extends AbstractDeleteController<NothingEntity, NothingDTO> {

}
