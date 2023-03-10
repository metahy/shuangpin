package cn.hyleon;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    public void test() {
        System.out.print("Progress:");
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + "%");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (int j = 0; j <= String.valueOf(i).length(); j++) {
                System.out.print("\b");
            }
        }
        System.out.println();
    }
}