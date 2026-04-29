/** Простая структура для статистики. */
public class Stats {
    public final double mean;
    public final double stddev;

    private Stats(double mean, double stddev) {
        this.mean = mean;
        this.stddev = stddev;
    }

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
