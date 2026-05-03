/**
 *
 * <p>Содержит среднее значение `mean` и стандартное отклонение `stddev`.
 * Метод {@link #from(double[])} вычисляет эти показатели по массиву значений.</p>
 */
public class Stats {
    /** Среднее значение измерений (мс). */
    public final double mean;
    /** Стандартное отклонение измерений (мс). */
    public final double stddev;

    private Stats(double mean, double stddev) {
        this.mean = mean;
        this.stddev = stddev;
    }

    /**
     * Вычисляет `mean` и `stddev` по массиву значений.
     *
     * @param values массив значений в миллисекундах
     * @return объект {@code Stats} с рассчитанными показателями
     */
    public static Stats from(double[] values) {
        double sum = 0.0;
        for (double v : values) sum += v;
        double mean = sum / values.length;
        double s = 0.0;
        for (double v : values) {
            double d = v - mean;
            s += d * d;
        }
        double stddev = Math.sqrt(s / values.length);
        return new Stats(mean, stddev);
    }
}
