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
import java.util.stream.Stream;

public class ClientsServlet extends HttpServlet {

    private static final String CLIENTS_PAGE_TEMPLATE = "clients.html";
    private static final String TEMPLATE_ATTR_CLIENT_DTO = "clientDtoList";
    public static final String REQUEST_PARAMETER_CLIENT_NAME = "clientNameTextBox";
    public static final String REQUEST_PARAMETER_ADDRESS = "clientStreetNameTextBox";
    public static final String REQUEST_PARAMETER_PHONES = "clientPhoneTextBox";

    private final DBServiceClient serviceClient;
    private final TemplateProcessor templateProcessor;

    public ClientsServlet(TemplateProcessor templateProcessor, DBServiceClient serviceClient) {
        this.templateProcessor = templateProcessor;
        this.serviceClient = serviceClient;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> allClients = serviceClient.findAll();

        var toConvert = new ToClientDtoServiceImpl();
        List<ClientDto> clientDtoList = toConvert.convert(allClients);

        Map<String, Object> paramsMap = Map.of(TEMPLATE_ATTR_CLIENT_DTO, clientDtoList);

        resp.setContentType("text/html");
        resp.getWriter().println(templateProcessor.getPage(CLIENTS_PAGE_TEMPLATE, paramsMap));
    }

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

        return new Client(clientName, address, phones);
    }

    private String getClientName(HttpServletRequest request) {
        return request.getParameter(REQUEST_PARAMETER_CLIENT_NAME);
    }

    private Address getAddress(HttpServletRequest request) {
        return new Address(request.getParameter(REQUEST_PARAMETER_ADDRESS));
    }

    private List<Phone> getPhones(HttpServletRequest request) {
//        Stream.of(request.getParameter(REQUEST_PARAMETER_PHONES).split("; ")).map(Phone::new).collect(Collectors.toList());

        return Arrays.stream(request.getParameterMap().get(REQUEST_PARAMETER_PHONES))
                .filter(StringUtils::isNotBlank)
                .map(Phone::new)
                .collect(Collectors.toList());
    }

}
