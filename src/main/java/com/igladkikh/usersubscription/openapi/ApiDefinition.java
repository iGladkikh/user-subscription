package com.igladkikh.usersubscription.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(title = "${api.definition.title}",
                version = "${api.definition.version}",
                description = "${api.definition.description}",
                contact = @Contact(
                        name = "${api.definition.contact.name}",
                        email = "${api.definition.contact.email}"
                )))
public interface ApiDefinition {
}
