package delivery.robot;

import java.util.HashMap;
import java.util.Map;

public final class RouteCounterUtils {

    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void calculate(String text) {
        var array = text.toCharArray();
        var count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 'R') {
                count++;
            }
        }

        synchronized (sizeToFreq) {
            sizeToFreq.put(count, sizeToFreq.getOrDefault(count, 0) + 1);
        }
    }

    public static String sizeToFreqToString() {
        var max = sizeToFreq.entrySet().stream().max(Map.Entry.comparingByValue()).get();

        var stringBuilder = new StringBuilder(
                "Самое частое количество повторений " + max.getKey() + " (встретилось " + max.getValue() + " раз)\n " +
                        "Другие размеры:\n"
        );

        sizeToFreq.entrySet()
                .stream()
                .filter(entry -> !entry.equals(max))
                .forEach(entry -> stringBuilder
                        .append("- ")
                        .append(entry.getKey())
                        .append(" ( ")
                        .append(entry.getValue())
                        .append(" раз)\n")
                );

        return stringBuilder.toString();
    }
}
