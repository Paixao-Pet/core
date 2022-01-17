package br.com.paixaopet.core.entrypoint.restcontrollers.v1;

import br.com.paixaopet.core.entrypoint.restcontrollers.v1.requests.CreatePetRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping(PetsController.PATH)
public class PetsController {

    public static final String PATH = "/v1/pets";

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreatePetRequest request) {
        var id = "15";

        var location = URI.create(PATH.concat(format("/%s", id)));
        return created(location).build();
    }
}
