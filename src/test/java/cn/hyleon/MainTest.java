package cn.hyleon;

import org.junit.jupiter.api.Test;

import java.io.*;

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

    @Test
    public void prepareDic() throws IOException {
        File file = new File("/home/hyleon/IdeaProjects/shuangpin/src/main/resources/shuangpin.dic");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s = bufferedReader.readLine();
        fileReader.close();
        System.out.println(s);

        FileWriter fileWriter = new FileWriter(file);
        char[] chars = s.toCharArray();
        for (char c : chars) {
            fileWriter.write(c);
            fileWriter.write('\n');
        }
        fileWriter.close();
    }
}