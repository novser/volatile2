import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    private final static int storeCount = 3;
    private final static int workTime = 20000;
    private final static int minBill = 1;
    private final static int maxBill = 1000;

    private static LongAdder income = new LongAdder();

    public static void main(String[] args) {

        List<Callable<String>> stores = new ArrayList<>();
        for (int i = 0; i < storeCount; i++) {
            stores.add(new Store(workTime, "Магазин " + (i + 1)));
        }

        ExecutorService pool = Executors.newFixedThreadPool(storeCount);
        try {
            List<Future<String>> futureTasks = pool.invokeAll(stores);
            for (Future<String> futureTask : futureTasks) {
                System.out.println(futureTask.get() + " закончил работу");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            pool.shutdown();
        }

        System.out.println("Итоговая прибыль: " + income.sum());
    }

    public static void addIncome(int bill) {
        income.add(bill);
    }

    public static int getRandomBill() {
        return (int) (Math.random() * ((maxBill - minBill) + 1)) + minBill;
    }
}
