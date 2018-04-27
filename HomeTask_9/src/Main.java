import java.io.IOException;

/**
 * Задание на пятницу:
 1) Скопировать файл
 2) Разбить/склеить файл. Размер куска задается
 3) Посмотрите самостоятельно и попробуйте реализовать:
 a) Зашифровать/ дешифровать файл паролем (XOR)
 b) Зашифровать/ дешифровать файл другим файлом
 */

public class Main {
    public static void main(String[] args) {
        FileCopyTask fileCopyTask = new FileCopyTask();
        fileCopyTask.writeToFileTest(fileCopyTask.getOne(),"Test_string");
        try {
            fileCopyTask.fileCopy();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fileCopyTask.splitFile(null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fileCopyTask.joinFiles(null, null, "test_part*");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}