package ru.otus;

public class Car {
    private String model;
    private int age;

    public Car(String model, int age) {
        this.model = model;
        this.age = age;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPersonalInformation() {
        System.out.println("Модель: " + this.model + ", возраст машины = " + this.age);
        return "Модель: " + this.model + ", возраст машины = " + this.age;
    }
}
