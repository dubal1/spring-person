package com.everis.persons.app.service;

import com.everis.persons.app.model.document.Person;
import reactor.core.publisher.Mono;

public interface IPersonService {

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
