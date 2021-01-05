/**
 * package service implement person.
 */
package com.everis.persons.app.model.service.impl;

import com.everis.persons.app.model.document.Person;
import com.everis.persons.app.model.repository.IPersonRepository;
import com.everis.persons.app.model.service.IPersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements IPersonService {
    /**
     * inject IPersonRepository.
     */
    private final IPersonRepository repository;
    /**
     *
     * @return findAllPerson.
     */
    @Override
    public Flux<Person> findAllPerson() {
        return repository.findAll();
    }

    /**
     *
     * @param document
     * @return findPersonByDocument.
     */
    @Override
    public Mono<Person> findPersonByDocument(final String document) {
        return repository.findByDocument(document);
    }
}
