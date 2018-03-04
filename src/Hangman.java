import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // word заданное слово
        // errorsLeft кол-во ошибок, которое можно допустить
        // placeholders строка с подчёркиваниями вместо букв
        // usedLetters лист использованных букв
        String word = "word";
        int errorsLeft = 5;
        String placeholders = "";
        List<String> usedLetters = new ArrayList<>();
        System.out.println("Я загадал слово, твоя задача отгадать его. Ты можешь допустить 5 ошибок.");

        // создает строку, заменяя буквы на подчёркивания
        for (int i = 0; i < word.length(); i++) {
            placeholders += "_";
        }
        System.out.println(getWordWithSpaces(placeholders));

        //цикл игры
        while (errorsLeft > 0) {
            //считывание буквы или слова
            String userInput = scanner.next();
            char inputLetter = userInput.charAt(0);

            //проверка использовалась ли буква
            if (usedLetters.indexOf(userInput.substring(0, 1)) >= 0) {
                System.out.println("Буква " + inputLetter + " была использована.");
                System.out.println(getWordWithSpaces(placeholders) +
                        "   Использованные буквы: " + getUsedLettersWithSpaces(usedLetters));
                System.out.println("Кол-во ошибок которое можно допустить: " + errorsLeft);
                continue;
            }
            //добавление буквы в использованные
            usedLetters.add(userInput.substring(0, 1));

            for (int i = 0; i < word.length(); i++) {
                //замена подчёркивания на буквы, если она была угадана
                if (word.charAt(i) == inputLetter) {
                    placeholders = placeholders.substring(0, i) + inputLetter +
                            placeholders.substring(i + 1);
                }
            }
            //если буква не угадана,  кол-во попыток уменьшается
            if (word.indexOf(inputLetter) < 0) {
                errorsLeft--;
            }

            // вывод изменений после после проверок
            System.out.println(getWordWithSpaces(placeholders) +
                    "   Использованные буквы: " + getUsedLettersWithSpaces(usedLetters));
            System.out.println("Кол-во ошибок которое можно допустить: " + errorsLeft);

            //если слово угнадано заканчивает игру
            if (word.equals(placeholders)) {
                System.out.println("Поздравляю, ты выиграл.");
                break;
            }

        }
        //если ошибок больше чем разрешено заканчивает игру
        if (errorsLeft == 0) {
            System.out.println("Ты проиграл.Загаданным словом было слово: " + word + ".");
        }
    }

    //функция добавляющая пробелы между подчёркиваниями
    private static String getWordWithSpaces(String word) {
        String wordWithSpaces = "";
        for (int i = 0; i < word.length(); i++) {
            wordWithSpaces += word.charAt(i) + " ";
        }
        return wordWithSpaces;
    }

    //функция выозвращающая массив букву в строке
    private static String getUsedLettersWithSpaces(List<String> letters) {
        String lettersWithSpaces = "";
        Collections.sort(letters);
        for (String letter : letters) {
            lettersWithSpaces += letter + " ";
        }
        return lettersWithSpaces;
    }
}
