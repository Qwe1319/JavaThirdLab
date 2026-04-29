/**
 * Точка входа для бенчмарка.
 */
public class Main {
    /**
     * args[0] — количество операций (по умолчанию 10000)
     * args[1] — повторений для усреднения (по умолчанию 5)
     * args[2] — прогревочных прогонов (по умолчанию 3)
     *
     * @param args аргументы командной строки (см. описание выше)
     */
    public static void main(String[] args) {
        int count = 10000;
        int repeats = 5;
        int warmups = 3;
        if (args.length > 0) try { count = Integer.parseInt(args[0]); } catch (Exception ignored) {}
        if (args.length > 1) try { repeats = Integer.parseInt(args[1]); } catch (Exception ignored) {}
        if (args.length > 2) try { warmups = Integer.parseInt(args[2]); } catch (Exception ignored) {}

        Benchmarks.runAll(count, repeats, warmups);
    }
}
