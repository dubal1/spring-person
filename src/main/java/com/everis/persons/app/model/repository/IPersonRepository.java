/**
 * package repository person.
 */
package com.everis.persons.app.model.repository;

import com.everis.persons.app.model.document.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IPersonRepository extends
        ReactiveMongoRepository<Person, String> {
    /**
     *
     * @param document
     * @return findByDocument.
     */
    Mono<Person> findByDocument(String document);
}
