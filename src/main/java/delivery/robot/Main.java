package delivery.robot;

import java.util.ArrayList;
import java.util.List;

import static delivery.robot.RouteGenerator.generateRoute;

public class Main {
    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads.add(new Thread(() -> RouteCounterUtils.calculate(generateRoute("RLRFR", 100))));
        }

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(RouteCounterUtils.sizeToFreqToString());
    }
}