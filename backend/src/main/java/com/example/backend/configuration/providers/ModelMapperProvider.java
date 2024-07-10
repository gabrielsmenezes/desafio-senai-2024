package com.example.backend.configuration.providers;

import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ModelMapperProvider {
    @Bean
    public ModelMapper getObjectMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.createTypeMap(String.class, LocalDate.class);
        modelMapper.addConverter(new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                return LocalDate.parse(source, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
        });
        modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(request -> LocalDate.now());

        modelMapper.createTypeMap(LocalDate.class, String.class);
        modelMapper.addConverter(new AbstractConverter<LocalDate, String>() {
            @Override
            protected String convert(LocalDate source) {
                return source.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
        });
        return modelMapper;
    }
}
