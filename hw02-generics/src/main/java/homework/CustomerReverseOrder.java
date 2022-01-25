package homework;


import java.util.NavigableSet;
import java.util.TreeSet;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    NavigableSet<Customer> customers = new TreeSet<>();

    public void add(Customer customer) {
        customers.add(customer);
    }

    public Customer take() {
        return customers.pollLast();
    }
}
