package ru.gb.python;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PythonFromJava {

    public static void main(String[] args) {
        // STDIN
        // STDOUT
        // STDERR
        // /usr/bin/python3 python-scripts/hello-world.py Igor
//        ProcessBuilder command = new ProcessBuilder("/usr/bin/python3", "python-scripts/hello-world.py");

        ProcessBuilder command = new ProcessBuilder("/usr/bin/git" , "log");
        try {
            Process start = command.start();
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(start.getOutputStream());
//            bufferedOutputStream.write("my text".getBytes(StandardCharsets.UTF_8));
//            bufferedOutputStream.flush();
//            bufferedOutputStream.close();

            InputStream result = start.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(result);
            byte[] resultBytes = bufferedInputStream.readAllBytes();
            String resultString = new String(resultBytes);
            System.out.println("Результат вызова скрипта hello-world.py: " + resultString);
        } catch (IOException e) {
            System.err.println("Произошла ошибка при запуске команды python hello-world.py: " + e.getMessage());
        }

        //

    }

}
