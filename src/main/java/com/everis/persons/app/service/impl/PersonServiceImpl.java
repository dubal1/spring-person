package com.everis.persons.app.service.impl;

import com.everis.persons.app.model.document.Person;
import com.everis.persons.app.repository.IPersonRepository;
import com.everis.persons.app.service.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PersonServiceImpl implements IPersonService {
    /**
     * inject IPersonRepository.
     */
    @Autowired
    private IPersonRepository repository;

    /**
     * @param document .
     * @return findPersonByDocument.
     */
    @Override
    public Mono<Person> findPersonByDocument(final String document) {
        return repository.findByDocument(document)
                .doOnNext(per -> log.info("DOCUMENT: " + per.getDocument()));
    }

    /**
     * @param person .
     * @return savePerson.
     */
    @Override
    public Mono<Person> savePerson(final Person person) {
        return repository.findByDocument(person.getDocument())
                .doOnNext(per -> log.info("DOCUMENT: " + per.getDocument()));
    }
}
