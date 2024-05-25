import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TaskBuilder {
    private static final int HUNDRED_YEARS = 100;
    private static final int TASK_2_LOWER_BORDER = -9;
    private static final int TASK_2_UPPER_BORDER = 19;
    private static final int TASK_2_BORDER = 0;
    private static final int TASK_2_ARRAY_SIZE = 10;
    private static final int TASK_3_AND_4_UPPER_BORDER = 10000;

    private static final double USD_TO_BYN_EXCHANGE_RATE = 3.2;

    private static final String BYN = "BYN";
    private static final String USD = "USD";
    private static final String TASK_3_AND_4_FORMAT = "%.2f ";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_KHAKI = "\u001B[38;5;107m";


    //Пользователь вводит в консоль дату своего рождения. Программа должна вернуть дату,
    //когда пользователю исполнится 100 лет. Использовать Date/Time API.

    public static void build1() {
        Scanner scanner = new Scanner(System.in);
        String birthDateString;
        LocalDate birthDate;

        while (true) {
            System.out.print("Введите дату вашего рождения (в формате гггг-мм-дд): ");
            birthDateString = scanner.nextLine();
            try {
                birthDate = LocalDate.parse(birthDateString, DateTimeFormatter.ISO_LOCAL_DATE);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты. Пожалуйста, попробуйте еще раз.");
            }
        }
        LocalDate hundredthBirthday = birthDate.plusYears(HUNDRED_YEARS);

        System.out.println("Вам исполнится 100 лет: " + hundredthBirthday.format(DateTimeFormatter.ISO_LOCAL_DATE) + "\n");
    }

    //Используя Predicate среди массива чисел вывести только те, которые являются положительными.

    public static void build2() {
        ArrayList<Integer> arrayList = getRandomIntegerArrayList(TASK_2_ARRAY_SIZE);
        ArrayList<Integer> resultList = new ArrayList<>();

        Predicate<Integer> isPositive = x -> x > TASK_2_BORDER;

        for (Integer integer : arrayList) {
            if (isPositive.test(integer)) {
                resultList.add(integer);
            }
        }

        System.out.println("Массив: " + arrayList);
        System.out.println("Результат: " + resultList);
        System.out.println();
    }

    //Используя Function реализовать лямбду, которая будет принимать в себя строку в
    //формате “*сумма* BYN”(через пробел, вместо *сумма* вставить любое значение), а
    //возвращать сумму, переведенную сразу в доллары.

    public static void build3() {
        Function<String, String> currencyConverter = line -> {
            double amount = Double.parseDouble(line.substring(0, line.indexOf(" ")));
            amount = amount / USD_TO_BYN_EXCHANGE_RATE;
            return String.format(TASK_3_AND_4_FORMAT, amount).replace(",", ".") + USD;
        };

        String amount = getRandomAmountInBYN();
        System.out.println(amount + " = " + currencyConverter.apply(amount));
        System.out.println();
    }

    //Используя Consumer реализовать лямбду, которая будет принимать в себя строку в
    //формате “*сумма* BYN”(через пробел, вместо *сумма* вставить любое значение), а
    //выводить сумму, переведенную сразу в доллары.

    public static void build4() {
        Consumer<String> currencyConverter = line -> {
            double amount = Double.parseDouble(line.substring(0, line.indexOf(" ")));
            amount = amount / USD_TO_BYN_EXCHANGE_RATE;
            String convertAmount = String.format(TASK_3_AND_4_FORMAT, amount).replace(",", ".") + USD;
            System.out.println(line + " = " + convertAmount);
        };

        currencyConverter.accept(getRandomAmountInBYN());
        System.out.println();
    }

    //Используя Supplier написать метод, который будет возвращать введенную с консоли строку задом наперед.

    public static void build5() {
        Supplier<String> reverseStringSupplier = () -> {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите строку: ");
            String input = scanner.nextLine();
            StringBuilder sb = new StringBuilder(input);
            return sb.reverse().toString();
        };

        System.out.println("Строка в обратном порядке: " + ANSI_KHAKI + reverseStringSupplier.get() + ANSI_RESET);
        System.out.println();
    }


    private static ArrayList<Integer> getRandomIntegerArrayList(int size) {
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arrayList.add(random.nextInt(TASK_2_UPPER_BORDER) + TASK_2_LOWER_BORDER);
        }
        return arrayList;
    }

    private static String getRandomAmountInBYN() {
        Random random = new Random();
        return random.nextInt(TASK_3_AND_4_UPPER_BORDER) + " " + BYN;
    }

}
