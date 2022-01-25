package homework;


import java.util.*;
import java.util.stream.Collectors;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private HashMap<Customer, String> map = new HashMap<>();


    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk

        // отсортировать коллекцию по полю getScores().
        List<Customer> sortedListByScores = getSortedListByScores();

        // Из отсортированного списка взять первый элемент. По этому ключу найти значение в map. Вернуть полученный результат
        return getCustomerStringSimpleEntry(sortedListByScores.get(0));
    }

    private AbstractMap.SimpleEntry<Customer, String> getCustomerStringSimpleEntry(Customer customer) {
        String value = map.get(customer);
        return new AbstractMap.SimpleEntry<>(new Customer(customer.getId(), customer.getName(), customer.getScores()), value);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        // 1. Отсортировать коллекцию по полю getScores().
        List<Customer> sortedListByScores = getSortedListByScores();

        // 2. Вернуть следующено customer-а после переданного
        Optional<Customer> result = sortedListByScores.stream()
                .filter(c -> customer.getScores() < c.getScores())
                .findFirst();
        if (result.isPresent()) {
            return getCustomerStringSimpleEntry(result.get());
        }

        // 3. Вернуть customer-а с max(customerID), если в коллекции существует Customer с переданным ИД.
        Optional<Customer> currentId = map.keySet().stream()
                .filter(c -> customer.getId() == c.getId())
                .findFirst();
        if (currentId.isPresent()) {
            Optional<Customer> maxId = sortedListByScores.stream()
                    .max(Comparator.comparing(Customer::getId));
            if (maxId.isPresent()) {
                return getCustomerStringSimpleEntry(maxId.get());
            }
        }

        // 4. Вернуть null, если переданного Customer-а нет в коллекции
        return null;
    }

    private List<Customer> getSortedListByScores() {
        return map.keySet().stream()
                .sorted(Comparator.comparing(Customer::getScores))
                .collect(Collectors.toList());
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
