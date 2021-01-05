package com.everis.persons.app.controller;

import com.everis.persons.app.model.document.Person;
import com.everis.persons.app.model.service.IPersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
public class PersonController {
    /**
     * inject IPersonService.
     */
    private final IPersonService service;

    /**
     * @return getAllPerson.
     */
    @GetMapping(value = "/core/getall")
    public Flux<Person> getAllPerson() {
        return service.findAllPerson()
                .doOnNext(person -> log.info("DOCUMENT: " + person.getDocument()));
    }

    /**
     * @param document .
     * @return getPersonByDocument.
     */
    @GetMapping(value = "/core/persons")
    public Mono<ResponseEntity<Person>> getPersonByDocument(
            @RequestParam(value = "documentNumber") final String document) {
        return service.findPersonByDocument(document)
                .doOnNext(person -> log.info("DOCUMENT: " + person.getDocument()))
                .map(person -> new ResponseEntity<>(person, HttpStatus.FOUND))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * @param id .
     * @return getPersonById.
     */
    @GetMapping(value = "/core/persons/{id}")
    public Mono<ResponseEntity<Person>> getPersonById(
            @PathVariable(value = "id") final String id) {
        return service.findAllPerson()
                .filter(person -> id.equals(person.getId()))
                .next()
                .doOnNext(person -> log.info("DOCUMENT: " + person.getDocument()))
                .map(person -> new ResponseEntity<>(person, HttpStatus.FOUND))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * @param personMono .
     * @return saveProduct.
     */
    @PostMapping(value = "/core/persons")
    public Mono<ResponseEntity<Map<String, Object>>> saveProduct(
            @Valid @RequestBody final Mono<Person> personMono) {
        Map<String, Object> objectMap = new HashMap<>();
        return personMono
                .flatMap(person -> {
                    return service.savePerson(person)
                            .doOnNext(p -> log.info("created person: " + person.getDocument()))
                            .map(savedPerson -> {
                                objectMap.put("person", person);
                                objectMap.put("success", true);
                                return new ResponseEntity<>(objectMap, HttpStatus.CREATED);
                            })
                            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
                })
                .onErrorResume(throwable -> {
                    return Mono.just(throwable).cast(WebExchangeBindException.class)
                            .flatMap(e -> Mono.just(e.getFieldErrors()))
                            .flatMapMany(Flux::fromIterable)
                            .map(FieldError::getDefaultMessage)
                            .collectList()
                            .flatMap(listErrors -> {
                                objectMap.put("err", listErrors);
                                objectMap.put("dateTime", new Date());
                                return Mono.just(new ResponseEntity<>(objectMap, HttpStatus.BAD_REQUEST));
                            });
                });
    }
}
