import java.util.concurrent.Callable;

public class Store implements Callable<String> {

    private final int workTime;
    private final String name;

    public Store(int workTime, String name) {
        this.workTime = workTime;
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        long beginTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - beginTime < workTime) {
            int bill = Main.getRandomBill();
            Main.addIncome(bill);
            System.out.println(name + " пробил чек на сумму " + bill);
        }

        return name;
    }
}
