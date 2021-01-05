package com.everis.persons.app.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
    @NotEmpty(message = "El campo 'document' no debe ser vacío")
    @Size(min = 8, max = 8, message = "El campo 'document' debe tener 8 caracteres")
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
