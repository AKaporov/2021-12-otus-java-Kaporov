package ru.otus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import ru.otus.dto.ClientDto;
import ru.otus.model.Address;
import ru.otus.model.Client;
import ru.otus.model.Phone;
import ru.otus.services.DBServiceClient;
import ru.otus.services.TemplateProcessor;
import ru.otus.services.ToClientDtoServiceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientsServlet extends HttpServlet {

    private static final String CLIENTS_PAGE_TEMPLATE = "clients.html";
    private static final String TEMPLATE_ATTR_CLIENT_DTO = "clientDtoList";
    public static final String REQUEST_PARAMETR_CLIENT_NAME = "name";
    public static final String REQUEST_PARAMETR_ADDRESS = "address";
    public static final String REQUEST_PARAMETR_PHONES = "phones";

    private final DBServiceClient serviceClient;
    private final TemplateProcessor templateProcessor;

    public ClientsServlet(TemplateProcessor templateProcessor, DBServiceClient serviceClient) {
        this.templateProcessor = templateProcessor;
        this.serviceClient = serviceClient;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Map<String, Object> paramsMap = new HashMap<>();

        List<Client> allClients = serviceClient.findAll();
//
        var toConvert = new ToClientDtoServiceImpl();
        List<ClientDto> clientDtoList = toConvert.convert(allClients);
//        clientDtoList.forEach(clientDto -> paramsMap.put(getKey(clientDto), clientDto));
//        paramsMap.put(TEMPLATE_ATTR_CLIENT_DTO, clientDtoList.get(1));

        Map<String, Object> paramsMap = Map.of(TEMPLATE_ATTR_CLIENT_DTO, clientDtoList);

        resp.setContentType("text/html");
        resp.getWriter().println(templateProcessor.getPage(CLIENTS_PAGE_TEMPLATE, paramsMap));
    }

//    private String getKey(ClientDto clientDto) {
//        return TEMPLATE_ATTR_CLIENT_DTO + ":" + clientDto.getId();
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        var client = Client.builder()
//                .name(request.getParameter("name"))
//                .address(
//                        Address.builder()
//                                .street(request.getParameter("address"))
//                                .build()
//                )
//                .phones(
//                        Arrays.stream(request.getParameterMap().get("phones"))
//                                .filter(StringUtils::isNotBlank)
//                                .map(n -> Phone.builder().number(n).build())
//                                .collect(Collectors.toList())
//                )
//                .build();


        Client client = getClient(request);
        serviceClient.saveClient(client);

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.sendRedirect("/clients");
    }

    private Client getClient(HttpServletRequest request) {
        String clientName = getClientName(request);
        Address address = getAddress(request);

        List<Phone> phones = getPhones(request);

        var client = new Client(clientName, address, phones);
        return client;
    }

    private String getClientName(HttpServletRequest request) {
        return request.getParameter(REQUEST_PARAMETR_CLIENT_NAME);
    }

    private Address getAddress(HttpServletRequest request) {
        return new Address(request.getParameter(REQUEST_PARAMETR_ADDRESS));
    }

    private List<Phone> getPhones(HttpServletRequest request) {
        return Arrays.stream(request.getParameterMap().get(REQUEST_PARAMETR_PHONES))
                .filter(StringUtils::isNotBlank)
                .map(n -> new Phone(n))
                .collect(Collectors.toList());
    }

}
