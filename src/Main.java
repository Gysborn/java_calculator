import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

// Shut up and calculate! David Mermin,

public class Main {
    public static void main(String[] args) throws MyExceptions {
        Scanner in = new Scanner(System.in);
        System.out.println("Input: ");
        System.out.println("Output: \n" + calc(in.nextLine()));
        in.close();
    }
    
    public static String calc(String input) throws MyExceptions {
        // Если запрос в арабских цифрах
        if (Utils.validate(input)) {
            String[] row = input.split(" ");
            int opr1 = parseInt(row[0]);
            int opr2 = parseInt(row[2]);
            if (opr1 < 11 && opr1 > 0 && opr2 < 11 && opr2 > 0) {
                return String.valueOf(Utils.calculator(opr1, opr2, row[1]));
            }
        }
        // Если запрос в римских цифрах
        String[] row = input.split(" ");
        int opr1 = Utils.romanToInt(row[0]);
        int opr2 = Utils.romanToInt(row[2]);
        int result = Utils.calculator(opr1, opr2, row[1]);
        if (result < 1) {
            throw new NullPointerException("В римской системе нет отрицательных чисел");
        }return Utils.romanize(result);
    }
}