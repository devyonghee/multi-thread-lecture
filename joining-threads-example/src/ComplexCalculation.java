import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        List<PowerCalculatingThread> threads = Arrays.asList(new PowerCalculatingThread(base1, power1), new PowerCalculatingThread(base2, power2));
        return threads.stream()
                .peek(Thread::start)
                .peek(thread -> {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).map(PowerCalculatingThread::getResult)
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            this.result = base.pow(power.intValue());
        }

        public BigInteger getResult() {
            return result;
        }
    }
}