package br.com.paixaopet.core.entrypoint.restcontrollers.v1;

import br.com.paixaopet.core.business.PetService;
import br.com.paixaopet.core.entrypoint.restcontrollers.v1.requests.CreatePetRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping(PetsController.PATH)
public class PetsController {

    public static final String PATH = "/v1/pets";

    private final PetService service;

    public PetsController(PetService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Void> create(@Valid @RequestBody CreatePetRequest request) {
        var pet = service.save(request);

        var identifier = pet.identifier();

        var location = URI.create(PATH.concat(format("/%s", identifier)));
        return created(location).build();
    }
}
