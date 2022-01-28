package homework;


import java.util.*;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    private SortedMap<Customer, String> map = new TreeMap<>(new Comparator<Customer>() {
        @Override
        public int compare(Customer o1, Customer o2) {
            return (int) (o1.getScores() - o2.getScores());
        }
    });


    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk

        return getCustomerStringSimpleEntry(map.firstKey());
    }

    private AbstractMap.SimpleEntry<Customer, String> getCustomerStringSimpleEntry(Customer customer) {
        String value = map.get(customer);
        return new AbstractMap.SimpleEntry<>(new Customer(customer.getId(), customer.getName(), customer.getScores()), value);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {

        if (map.containsKey(customer)) {
            return getCustomerStringSimpleEntry(map.lastKey());
        }

        SortedMap<Customer, String> tailMap = map.tailMap(customer);
        if (tailMap.isEmpty()) {
            return null;
        }

        return getCustomerStringSimpleEntry(tailMap.firstKey());
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
