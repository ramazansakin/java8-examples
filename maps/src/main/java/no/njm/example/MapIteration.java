package no.njm.example;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class MapIteration {

    private static final String DELIMITER = ": ";

    public static void main(String[] args) {
        singleValuedMap();
        multiValuedMap();
    }

    private static void singleValuedMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key_1", "value_1");
        map.put("key_2", "value_2");

        // Java 8 forEach
        map.forEach((key, val) -> log.debug(key + DELIMITER + val));

        // Iterators
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            log.debug(entry.getKey() + DELIMITER + entry.getValue());
        }

        // Enhanced for-loop
        for (Map.Entry<String, String> entry : map.entrySet()) {
            log.debug(entry.getKey() + DELIMITER + entry.getValue());
        }
    }

    private static void multiValuedMap() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("key_3", Arrays.asList("value_3_1", "value_3_2"));
        map.put("key_4", Arrays.asList("value_4_1", "value_4_1"));

        // Java 8 forEach
        map.forEach((key, list) -> list.forEach(value -> log.debug(key + DELIMITER + value)));

        // Iterators
        Iterator<Map.Entry<String, List<String>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> entry = iterator.next();
            String key = entry.getKey();
            List<String> values = entry.getValue();
            for (String value : values) {
                log.debug(key + DELIMITER + value);
            }
        }

        // Enhanced for-loop
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for (String value : entry.getValue()) {
                log.debug(entry.getKey() + DELIMITER + value);
            }
        }
    }
}
