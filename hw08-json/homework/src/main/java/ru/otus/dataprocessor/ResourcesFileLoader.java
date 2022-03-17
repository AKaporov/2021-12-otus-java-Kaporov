package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.model.Measurement;

import javax.json.Json;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;

public class ResourcesFileLoader implements Loader {
    private final String fileName;
    private final ObjectMapper mapper = new ObjectMapper();

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        try (var jsonReader = Json.createReader(ResourcesFileLoader.class.getClassLoader().getResourceAsStream(fileName))) {
            return createMeasurementListFromJson(jsonReader.read());
        }
    }

    private List<Measurement> createMeasurementListFromJson(JsonStructure jsonStructure) {
        ArrayList<Measurement> result = new ArrayList<>(jsonStructure.asJsonArray().size());

        for (JsonValue jv : jsonStructure.asJsonArray()) {
            String name = readNameString(jv);
            double value = readValueDouble(jv);

            result.add(new Measurement(name, value));
        }

        return result;
    }

    private double readValueDouble(JsonValue jv) {
        return jv.asJsonObject().getJsonNumber("value").doubleValue();
    }

    private String readNameString(JsonValue jv) {
        return jv.asJsonObject().getString("name");
    }
}
