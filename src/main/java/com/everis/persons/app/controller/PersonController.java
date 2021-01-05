package com.everis.persons.app.controller;

import com.everis.persons.app.model.document.Person;
import com.everis.persons.app.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class PersonController {
    /**
     * inject IPersonService.
     */
    @Autowired
    private IPersonService service;

    /**
     * @param document .
     * @return getPersonByDocument.
     */
    @GetMapping(value = "/core/persons")
    public Mono<ResponseEntity<Person>> getPersonByDocument(
            @RequestParam(value = "documentNumber") final String document) {
        return service.findPersonByDocument(document)
                .flatMap(person -> {
                    if (person.getBlacklist()) {
                        return Mono.error(new Exception("Usted se encuentra en la Lista negra"));
                    } else {
                        return Mono.just(new ResponseEntity<>(person, HttpStatus.OK));
                    }
                })
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * @param person .
     * @return saveProduct.
     */
    @PostMapping(value = "/core/persons")
    public Mono<Person> saveProduct(@RequestBody final Person person) {
        return service.savePerson(person);
    }
}
