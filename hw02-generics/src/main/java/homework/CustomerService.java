package homework;


import java.util.*;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    private final NavigableMap<Customer, String> customers = new TreeMap<>(
            Comparator.comparingLong(Customer::getScores)
    );

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk

        return getCustomerStringSimpleEntry(customers.firstKey());
    }

    private AbstractMap.SimpleEntry<Customer, String> getCustomerStringSimpleEntry(Customer customer) {
        String value = customers.get(customer);
        return new AbstractMap.SimpleEntry<>(new Customer(customer.getId(), customer.getName(), customer.getScores()), value);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {

        if (customers.containsKey(customer)) {
            return getCustomerStringSimpleEntry(customers.lastKey());
        }

        SortedMap<Customer, String> tailMap = customers.tailMap(customer);
        if (tailMap.isEmpty()) {
            return null;
        }

        return getCustomerStringSimpleEntry(tailMap.firstKey());
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
