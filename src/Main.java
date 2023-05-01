import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите пример из двух операндов от 1 до 10 включительно:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        calc(input);
    }

    public static void calc (String input) {
        int a, b;
        String[] parts = input.split(" ");

        try {
            if (parts.length != 3) {
                throw new IllegalArgumentException("Должно быть два операнда и один оператор");
            }

            a = Integer.parseInt(parts[0]);
            b = Integer.parseInt(parts[2]);
            if (a < 1 || a > 10 || b < 1 || b > 10) {
                throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
            }
        } catch (NumberFormatException e) {
            a = romanToArabic(parts[0]);
            b = romanToArabic(parts[2]);
        }

        String operation = parts[1];
        int result = 0;

        switch (operation) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Деление на ноль невозможно");
                }
                result = a / b;
                break;
            default:
                System.out.println("Недопустимая операция: " + operation);
        }

        if (result < 1 && Character.isLetter(input.charAt(0))) {
            throw new ArithmeticException("Результат не может быть меньше единицы для римских цифр");
        } else {
            if (Character.isLetter(input.charAt(0))) {
                System.out.println("Результат: " + arabicToRoman(String.valueOf(result)));
            } else {
                System.out.println("Результат: " + result);
            }
        }
    }

    public static int romanToArabic(String romanNumeral) {
        String[] arabic = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] roman = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        for (int i = 0; i < 10; i++) {
            if (romanNumeral.equals(roman[i])) {
                return Integer.parseInt(arabic[i]);
            }
        }
        throw new NumberFormatException("Числа должны быть от 1 до 10 включительно");
    }

    public static String arabicToRoman(String arabicNumeral) {
        int arabic = Integer.parseInt(arabicNumeral);

        String[] roman = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return roman[arabic - 1];
    }
}
