package org.example.usermanagement.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidConverter implements Converter<String, UUID> {
    @Override
    public UUID convert(@NonNull String source) {
        return UUID.fromString(source);
    }
}
