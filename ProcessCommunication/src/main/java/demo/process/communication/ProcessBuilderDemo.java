package demo.process.communication;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProcessBuilderDemo {
    public static void main(String[] args) throws IOException {
        String javaHome = System.getProperty("java.home");
        String java = javaHome + File.separator + "bin" + File.separator + "java";
        String sysCp = System.getProperty("java.class.path");
        String currPath = ClassLoader.getSystemResource("").getPath();
        String cp = "\"" + sysCp + File.pathSeparator + currPath + "\"";

        // 父进程和子进程如果编码不一致，会出现中文乱码。可以包装流，使得双方编码一致
        // 或者父进程启动子进程的时候，设置 " -Dfile.encoding=" + Charset.defaultCharset().name();
        String encoding = " -Dfile.encoding=" + Charset.defaultCharset().name();
        String cmd = java + encoding + " -cp " + cp + ChildProcess.class;
        // Process p = Runtime.getRuntime().exec(cmd);

        // 失败，需要用 StringTokenizer 解析命令行为数组，可能单个字符串是太长了
        // Process p = new ProcessBuilder(java, "-classpath", cp, ChildProcess.class.toString()).start();
        // 可以通过 ProcessBuilder 重定向子进程的流到文件，此时父进程将无法通过 p.getInputStream() 获取子进程输出
        ProcessBuilder processBuilder = new ProcessBuilder(resolveCommand(cmd));
        processBuilder.redirectOutput(new File("ProcessCommunication/src/main/java/demo/process/communication/output.txt"));
        processBuilder.redirectError(new File("ProcessCommunication/src/main/java/demo/process/communication/error.txt"));
        Process p = processBuilder.start();

        System.out.println("FatherProcess's default charset is: " + Charset.defaultCharset().name());
        // 父进程通过 IO 流将信息写入子进程的输入流
        System.out.println("【send two data to child】：" + 2);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
            // 子进程用的时 readLine，因此需要换行符
            writer.write("hello child process\n");
            writer.flush();
            Thread.sleep(1000);
            writer.write("I am your father\n");
            writer.flush();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String[] resolveCommand(String command) {
        if (command.length() == 0) {
            throw new IllegalArgumentException("Empty command");
        }

        StringTokenizer st = new StringTokenizer(command);
        String[] cmdArray = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++) {
            cmdArray[i] = st.nextToken();
        }
        return cmdArray;
    }
}

class ChildProcess {
    public static void main(String[] args) throws IOException {
        System.out.println("ChildProcess's default charset is: " + Charset.defaultCharset().name());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            List<String> all = new ArrayList<>();
            // readLine 以换行符作为一行结束
            while ((line = reader.readLine()) != null) {
                all.add(line);
            }

            FileOutputStream fileOutputStream = new FileOutputStream(new File("ProcessCommunication/src/main/java/demo/process/communication/childOutput.txt"));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write("【get message from father】: " + all.size() + "\n");
            for (String message :
                    all) {
                bufferedWriter.write("Message" + message + "\n");
            }
            // println 带有换行符
            System.out.println("【get message from father】：" + all.size());
            System.out.println(all);
            bufferedWriter.flush();
            fileOutputStream.close();
            bufferedWriter.close();
        }
    }
}
