package com.everis.persons.app.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "persons")
public class Person {
    /**
     * field id.
     */
    @Id
    private String id;
    /**
     * field document.
     */
    private String document;
    /**
     * field fingerprint.
     */
    private Boolean fingerprint;
    /**
     * field blacklist.
     */
    private Boolean blacklist;
}
