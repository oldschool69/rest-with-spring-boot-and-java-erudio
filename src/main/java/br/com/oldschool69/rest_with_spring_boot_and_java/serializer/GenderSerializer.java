package br.com.oldschool69.rest_with_spring_boot_and_java.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GenderSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String gender, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        String formatedGender = "male".equalsIgnoreCase(gender) ? "M" : "F";
        jsonGenerator.writeString(formatedGender);

    }
}
