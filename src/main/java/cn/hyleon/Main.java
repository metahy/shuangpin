package cn.hyleon;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final Random random = new Random();
    public static final List<Pair<String, List<String>>> DIC = Lists.newArrayList(
//            Pair.of("小", List.of("xn")),
//            Pair.of("鹤", List.of("he")),
//            Pair.of("双", List.of("ul")),
//            Pair.of("拼", List.of("pb"))
    );

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        System.out.println("====== Shuangpin Practice Program =====");

        BufferedReader dicReader = new BufferedReader(new FileReader("/home/hyleon/IdeaProjects/shuangpin/src/main/resources/shuangpin.dic"));
        System.out.print("-> Loading dictionary...  0");
        List<String> lines = dicReader.lines().toList();
        int count = 0;
        for (String line : lines) {
            String[] data;
            if (StringUtils.isEmpty(line) || (data = line.split(",")).length < 2) {
                continue;
            }

            DIC.add(Pair.of(data[0], new ArrayList<>(Arrays.asList(data).subList(1, data.length))));
            count++;
            System.out.print("\r-> Loading dictionary...  " + count);
            TimeUnit.MILLISECONDS.sleep(5);
        }

        int dicSize = DIC.size();
        System.out.print("\r-> Loading dictionary...  Total:[" + dicSize + "]");
        System.out.println();

        System.out.println("[[Notice: Enter -> start, [`] -> end, [;] -> skip word]]");

        boolean start = false;
        Long timeStart = null;
        System.out.println("-> Press Enter to Start...");
        while (!start) {
            String s = scanner.nextLine();
            if ("".equals(s)) {
                start = true;
                timeStart = System.currentTimeMillis();
            } else if ("`".equals(s)) {
                break;
            } else {
                System.out.println("-> Press Enter to Start...");
            }
        }

        int total = 0, wrong = 0;
        while (start) {
            Pair<String, List<String>> pair = DIC.get(random.nextInt(dicSize));
            System.out.println("Target: " + pair.getKey());

            while (scanner.hasNext()) {
                String guess = scanner.nextLine();
                while ("".equals(guess)) {
                    guess = scanner.nextLine();
                }

                if (";".equals(guess)) {
                    System.out.println("Jump...");
                    break;
                } else if ("`".equals(guess)) {
                    start = false;
                    break;
                }

                total++;

                List<String> shuangpin = pair.getValue();

                String finalGuess = guess;
                if (shuangpin.stream().anyMatch(s -> s.equals(finalGuess))) {
                    System.out.println("√");
                    break;
                } else {
                    wrong++;
                    System.err.println("x");
                    System.out.println("Target: " + pair.getKey());
                }
            }
        }

        System.out.println("=> Result: (" + (total - wrong) + "/" + total + ") - " +
                "Spent: " + (timeStart != null ? (System.currentTimeMillis() - timeStart) / 1000.0 : 0) + "s \n");

        System.out.println("===== END =====");
    }
}
