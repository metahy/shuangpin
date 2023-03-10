package cn.hyleon;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Random random = new Random();
    public static final List<Pair<String, String>> DIC = Lists.newArrayList(
            Pair.of("小", "xn"),
            Pair.of("鹤", "he"),
            Pair.of("双", "ul"),
            Pair.of("拼", "pb")
    );

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("====== Shuangpin Practice Program =====");
        int dicSize = DIC.size();
        System.out.println("-> Loading dictionary...  Total:[" + dicSize + "]");

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
            Pair<String, String> pair = DIC.get(random.nextInt(dicSize));
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

                if (guess.equals(pair.getValue())) {
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
                "Spent: " + (timeStart != null ? (System.currentTimeMillis() - timeStart) / 1000 : 0) + "s \n");

        System.out.println("===== END =====");
    }
}
