/**
 * package controller person.
 */
package com.everis.persons.app.controller;

import com.everis.persons.app.model.document.Person;
import com.everis.persons.app.model.service.IPersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@RestController
public class PersonController {
    /**
     * inject IPersonService.
     */
    private final IPersonService service;

    /**
     * @param document
     * @return getPersonByDocument.
     */
    @GetMapping(value = "/core/persons")
    public Mono<ResponseEntity<Person>> getPersonByDocument(
            @RequestParam(value = "documentNumber") final String document) {
        return service.findPersonByDocument(document)
                .doOnNext(person -> log.info("DOCUMENT: " + person.getDocument()))
                .map(person -> new ResponseEntity<>(person, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
