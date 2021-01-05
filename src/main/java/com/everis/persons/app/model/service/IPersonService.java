package com.everis.persons.app.model.service;

import com.everis.persons.app.model.document.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonService {
    /**
     * @return findAllPerson.
     */
    Flux<Person> findAllPerson();

    /**
     * @param document .
     * @return findPersonByDocument.
     */
    Mono<Person> findPersonByDocument(String document);

    /**
     * @param person .
     * @return savePerson.
     */
    Mono<Person> savePerson(Person person);
}
