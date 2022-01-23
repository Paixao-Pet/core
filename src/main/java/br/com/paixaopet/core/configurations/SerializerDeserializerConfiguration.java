package br.com.paixaopet.core.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE;
import static com.fasterxml.jackson.databind.util.StdDateFormat.DATE_FORMAT_STR_ISO8601;

@Configuration
public class SerializerDeserializerConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        var builder = new Jackson2ObjectMapperBuilder();
        builder.propertyNamingStrategy(SNAKE_CASE);
        builder.simpleDateFormat(DATE_FORMAT_STR_ISO8601);
        return builder;
    }
}
