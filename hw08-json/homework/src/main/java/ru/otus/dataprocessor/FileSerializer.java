package ru.otus.dataprocessor;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FileSerializer implements Serializer {
    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл

        String json = createJsonFromMap(data);

        saveJsonToFile(json);
    }

    private void saveJsonToFile(String json) {
        try {
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
    }

    private String createJsonFromMap(Map<String, Double> data) {
        var gson = new Gson();
        return gson.toJson(data);
    }
}
