package ru.otus.services;

import ru.otus.dto.ClientDto;
import ru.otus.model.Client;

import java.util.List;

public interface ToClientDtoService {
    public List<ClientDto> convert(List<Client> clients) ;
}
