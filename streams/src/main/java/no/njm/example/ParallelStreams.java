package no.njm.example;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Sequential streams uses a single thread while parallel uses multiple threads.
 */
@Slf4j
public class ParallelStreams {

    private static final int MAX = 1000;

    public static void main(String[] args) {
        // sequentialSort - mode : 1
        sort(1);
        // parallelSort - mode : 1
        sort(2);
    }

    private static void sort(int mode) {
        List<String> list = getSampleStrings();

        long start = System.nanoTime();
        long count;
        if (mode == 1)
            count = list.stream().count();
        else
            count = list.parallelStream().count();
        long end = System.nanoTime();
        log.info("Count : " + count);
        log.debug(String.format("Sequential sort took: %d ms", TimeUnit.NANOSECONDS.toMillis(end - start)));
    }

    private static List<String> getSampleStrings() {
        List<String> list = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            list.add(uuid.toString());
        }
        return list;
    }
}
