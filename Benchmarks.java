import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/** Выполняет измерения для коллекций и печатает таблицу. */
public class Benchmarks {

    public static void runAll(int count, int repeats, int warmups) {
        System.out.println("Benchmark collections — operations: " + count + ", repeats: " + repeats + ", warmups: " + warmups);
        System.out.println();
        runFor("ArrayList", count, repeats, warmups);
        System.out.println();
        runFor("LinkedList", count, repeats, warmups);
    }

    private static void runFor(String name, int count, int repeats, int warmups) {
        for (int i = 0; i < warmups; i++) {
            benchmarkAdd(create(name), count);
            benchmarkGet(create(name), count);
            benchmarkDelete(create(name), count);
        }

        double[] addTimes = new double[repeats];
        double[] getTimes = new double[repeats];
        double[] delTimes = new double[repeats];

        for (int i = 0; i < repeats; i++) addTimes[i] = benchmarkAdd(create(name), count) / 1_000_000.0;
        for (int i = 0; i < repeats; i++) getTimes[i] = benchmarkGet(create(name), count) / 1_000_000.0;
        for (int i = 0; i < repeats; i++) delTimes[i] = benchmarkDelete(create(name), count) / 1_000_000.0;

        Stats add = Stats.from(addTimes);
        Stats get = Stats.from(getTimes);
        Stats del = Stats.from(delTimes);

        System.out.println("Collection: " + name);
        System.out.printf("%-10s | %-8s | %-12s | %-12s\n", "Method", "Count", "Mean(ms)", "StdDev(ms)");
        System.out.println("-------------------------------------------------------");
        System.out.printf("%-10s | %-8d | %-12.3f | %-12.3f\n", "add", count, add.mean, add.stddev);
        System.out.printf("%-10s | %-8d | %-12.3f | %-12.3f\n", "get", count, get.mean, get.stddev);
        System.out.printf("%-10s | %-8d | %-12.3f | %-12.3f\n", "delete", count, del.mean, del.stddev);
    }

    private static List<Integer> create(String name) {
        if ("ArrayList".equals(name)) return new ArrayList<Integer>();
        return new LinkedList<Integer>();
    }

    private static long benchmarkAdd(List<Integer> list, int count) {
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) list.add(i);
        // против оптимизаций
        if (list.size() == Integer.MIN_VALUE) System.out.print("");
        return System.nanoTime() - start;
    }

    private static long benchmarkGet(List<Integer> list, int count) {
        for (int i = 0; i < count; i++) list.add(i);
        Random rnd = new Random(0);
        int bound = list.size();
        long start = System.nanoTime();
        long sum = 0;
        for (int i = 0; i < count; i++) sum += list.get(rnd.nextInt(bound));
        if (sum == Long.MIN_VALUE) System.out.print("");
        return System.nanoTime() - start;
    }

    private static long benchmarkDelete(List<Integer> list, int count) {
        for (int i = 0; i < count; i++) list.add(i);
        long start = System.nanoTime();
        while (!list.isEmpty()) list.remove(list.size() / 2);
        return System.nanoTime() - start;
    }
}
