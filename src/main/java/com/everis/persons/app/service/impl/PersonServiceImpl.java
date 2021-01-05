package com.everis.persons.app.service.impl;

import com.everis.persons.app.model.document.Person;
import com.everis.persons.app.repository.IPersonRepository;
import com.everis.persons.app.service.IPersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements IPersonService {
    /**
     * inject IPersonRepository.
     */
    @Autowired
    private IPersonRepository repository;

    /**
     * @return findAllPerson.
     */
    @Override
    public Flux<Person> findAllPerson() {
        return repository.findAll();
    }

    /**
     * @param document .
     * @return findPersonByDocument.
     */
    @Override
    public Mono<Person> findPersonByDocument(final String document) {
        return repository.findByDocument(document);
    }

    /**
     * @param person .
     * @return savePerson.
     */
    @Override
    public Mono<Person> savePerson(final Person person) {
        return repository.save(person);
    }
}
