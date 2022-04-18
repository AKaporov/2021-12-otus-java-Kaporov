package ru.otus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.otus.dto.ClientDto;
import ru.otus.model.Client;
import ru.otus.services.DBServiceClient;
import ru.otus.services.TemplateProcessor;
import ru.otus.services.ToClientDtoService;
import ru.otus.services.ToClientDtoServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientsServlet extends HttpServlet {

    private static final String CLIENTS_PAGE_TEMPLATE = "clients.html";
    private static final String TEMPLATE_ATTR_CLIENT_DTO = "clientDtoList";

    private final DBServiceClient serviceClient;
    private final TemplateProcessor templateProcessor;

    public ClientsServlet(TemplateProcessor templateProcessor, DBServiceClient serviceClient) {
        this.templateProcessor = templateProcessor;
        this.serviceClient = serviceClient;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> paramsMap = new HashMap<>();

        List<Client> allClients = serviceClient.findAll();

        var toConvert = new ToClientDtoServiceImpl();
        List<ClientDto> clientDtoList = toConvert.convert(allClients);
//        clientDtoList.forEach(clientDto -> paramsMap.put(getKey(clientDto), clientDto));
        paramsMap.put(TEMPLATE_ATTR_CLIENT_DTO, clientDtoList.get(1));

        resp.setContentType("text/html");
        resp.getWriter().println(templateProcessor.getPage(CLIENTS_PAGE_TEMPLATE, paramsMap));
    }

    private String getKey(ClientDto clientDto) {
        return TEMPLATE_ATTR_CLIENT_DTO + ":" + clientDto.getId();
    }
}
