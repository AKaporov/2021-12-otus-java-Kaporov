package homework;


import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    Deque<Customer> customers = new ArrayDeque<>();

    public void add(Customer customer) {
        customers.addLast(customer);
    }

    public Customer take() {
        return customers.pollLast();
    }
}
