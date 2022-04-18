package ru.otus.dto;

public class ClientDto {
    private final Long id;
    private final String name;
    private final String addressStreet;
    private final String phoneNumber;

    public ClientDto(Long id, String name, String addressStreet, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.addressStreet = addressStreet;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
