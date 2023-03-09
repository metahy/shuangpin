package cn.hyleon;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("====== Start =====");

        Random random = new Random();
        List<Pair<String, String>> data = Lists.newArrayList(
                Pair.of("双", "ul"),
                Pair.of("拼", "pb")
        );
        int size = data.size();
        Scanner scanner = new Scanner(System.in);
        boolean start = true;
        int total = 0, wrong = 0;
        long timeStart = System.currentTimeMillis();
        while (start) {
            Pair<String, String> pair = data.get(random.nextInt(size));
            System.out.println("Target: " + pair.getKey());
            total++;
            while (scanner.hasNext()) {
                String guess = scanner.nextLine();

                if (guess.equals("E")) {
                    start = false;
                    break;
                }

                if (guess.equals(pair.getValue())) {
                    break;
                }
                System.err.println("x");
                wrong++;
            }
        }
        System.out.println("=> Result");
        System.out.println("(" + (total - wrong) + "/" + total + ") - Spent: " + (System.currentTimeMillis() - timeStart) / 1000 + "s \n");

        System.out.println("===== END =====");
    }
}
