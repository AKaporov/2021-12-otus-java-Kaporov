package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        //группирует выходящий список по name, при этом суммирует поля value

        // группировка сразу в TreeMap, что бы не перекладывать в другую мапу и не сортировать
        return data.stream()
                .collect(Collectors.groupingBy(Measurement::getName, TreeMap::new,
                        Collectors.summingDouble(Measurement::getValue)));

//        Map<String, Double> result = data.stream()
//                .collect(Collectors.groupingBy(Measurement::getName,
//                        Collectors.summingDouble(Measurement::getValue)));
//
//        return result.entrySet().stream()
//                .sorted(Map.Entry.comparingByKey())
//                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
    }
}
