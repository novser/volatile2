import java.util.concurrent.Callable;

public class Store implements Callable<String> {

    private final int workTime;
    private String name;

    public Store(int workTime, String name) {
        this.workTime = workTime;
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        long beginTime = System.currentTimeMillis();
        try {
            while (System.currentTimeMillis() - beginTime < workTime) {
            // TODO
            }
        } finally {
            return name;
        }
    }
}
