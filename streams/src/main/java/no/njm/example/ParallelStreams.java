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
        sortSequential();
        sortParallel();
    }

    private static void sortSequential() {
        List<String> list = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            list.add(uuid.toString());
        }

        long start = System.nanoTime();
        list.stream()
                .sorted()
                .count();
        long end = System.nanoTime();
        log.debug(String.format("Sequential sort took: %d ms", TimeUnit.NANOSECONDS.toMillis(end - start)));
    }

    private static void sortParallel() {
        List<String> list = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            list.add(uuid.toString());
        }

        long start = System.nanoTime();
        list.parallelStream()
                .sorted()
                .count();
        long end = System.nanoTime();
        log.debug(String.format("Parallel sort took: %d ms", TimeUnit.NANOSECONDS.toMillis(end - start)));
    }
}
