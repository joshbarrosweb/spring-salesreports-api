package com.joshbarrosweb.springsalesapi.controller;

import com.joshbarrosweb.springsalesapi.entity.Client;
import com.joshbarrosweb.springsalesapi.formrequest.ClientFormRequest;
import com.joshbarrosweb.springsalesapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @GetMapping
    public Page<ClientFormRequest> index(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "cpf", required = false, defaultValue = "") String cpf,
            Pageable pageable
    ) {

        return repository
                .findByCpfAndName("%" + name + "%", "%" + cpf + "%", pageable)
                .map(ClientFormRequest::fromModel);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientFormRequest> getById(@PathVariable Long id) {

        return repository
                .findById(id)
                .map(ClientFormRequest::fromModel)
                .map(clientFR -> ResponseEntity.ok(clientFR))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClientFormRequest request) {

        Client client = request.toModel();
        repository.save(client);
        return ResponseEntity.ok(ClientFormRequest.fromModel(client));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ClientFormRequest request) {

        Optional<Client> clientExists = repository.findById(id);

        if (clientExists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Client client = request.toModel();
        client.setId(id);
        repository.save(client);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {

        return repository
                .findById(id)
                .map(client -> {
                    repository.delete(client);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
