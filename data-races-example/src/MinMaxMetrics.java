public class MinMaxMetrics {

    private volatile long min = Long.MAX_VALUE;
    private volatile long max = Long.MIN_VALUE;

    // Add all necessary member variables

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {

    }

    /**
     * Adds a new sample to our metrics.
     */
    public void addSample(long newSample) {
        // Add code here
        synchronized (this){
            if (newSample < min) {
                min = newSample;
            }
        }
        synchronized (this) {
            if (newSample > max) {
                max = newSample;
            }
        }
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        // Add code here
        return min;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        // Add code here
        return max;
    }
}
