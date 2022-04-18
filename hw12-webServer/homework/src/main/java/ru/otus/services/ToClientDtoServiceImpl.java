package ru.otus.services;

import ru.otus.dto.ClientDto;
import ru.otus.model.Client;
import ru.otus.model.Phone;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToClientDtoServiceImpl implements ToClientDtoService {
    @Override
    public List<ClientDto> convert(List<Client> clients) {
        List<ClientDto> resultList = new ArrayList<>(clients.size());

        clients.forEach(client -> {

            String phones = client.getPhones().stream()
                    .map(Phone::getNumber)
                    .collect(Collectors.joining("; "));

            ClientDto dto = new ClientDto(client.getId(), client.getName(), client.getAddress().getStreet(), phones);
            resultList.add(dto);
        });

        return resultList;
    }
}
