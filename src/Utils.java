import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
/**  Функция проверки входящего выражения
     Возвращает true если выражение корректно в арабских цифрах,
    false если в римских и рейзит ошибку если строка не корректна
*/
    public static boolean validate(String str) throws MyExceptions {
        Pattern arab = Pattern.compile("^\\d+ [+-/*//] \\d+$");
        Pattern rome = Pattern.compile("^[IVX]+ [+-/*//] [IVX]+$");
        Matcher mArab = arab.matcher(str);
        if (mArab.find()){return true;}
        Matcher mRome = rome.matcher(str);
        if (mRome.find()) {return false;}

        Pattern shorty = Pattern.compile("^\\d$");
        Matcher mshorty = shorty.matcher(str);
        if (mshorty.find()){throw new MyExceptions("Cтрока не является математической операцией");}

        Pattern longy = Pattern.compile("^\\d+ [+-/*//] \\d+ [+-/*//].+");
        Matcher mlongy = longy.matcher(str);
        if (mlongy.find()){throw new MyExceptions("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");}

        Pattern arLatin = Pattern.compile("(^\\d+ [+-/*//] [IVX]+$)|(^[IVX]+ [+-/*//] \\d+$)");
        Matcher marLatin = arLatin.matcher(str);
        if (marLatin.find()){throw new MyExceptions("Используются одновременно разные системы счисления");}

        else {throw new MyExceptions("Некорректная строка ввода");}
    }

    /**
     * Функция конвертирует римские цифры в арабские
     */
    public static int romanToInt (String s)throws MyExceptions{
        HashMap<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("II", 2);
        map.put("III", 3);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("VI", 6);
        map.put("VII", 7);
        map.put("VIII", 8);
        map.put("IX", 9);
        map.put("X", 10);
        try {
            return map.get(s);
        } catch (Exception e) {
            throw new MyExceptions("Символ вне диапазона (I - X или 1 - 10)");
        }
    }

    /**
     * Функция калькулятор
     */

    public static int calculator ( int value1, int value2, String sign){
        return switch (sign) {
            case "+" -> value1 + value2;
            case "-" -> value1 - value2;
            case "*" -> value1 * value2;
            default -> value1 / value2;
        };
    }

    /**
     * Функция конвертирует арабские цифры в римские
     */

    public static String romanize (int number) throws MyExceptions {
        if (number < 1) {throw new MyExceptions("В римской системе нет отрицательных чисел");}
        else {
            String result = "";
            int i = 0;
            int tmp = 0;
            String[] roman = new String[] {
                    "","I","II","III","IV","V","VI","VII","VIII","IX",
                    "","X","XX","XXX","XL","L","LX","LXX","LXXX","XC",
                    "","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"
            };
            while (true) {
                 if (number >= 10) {tmp = number % 10; number = number / 10; result = roman[tmp + i] + result; i += 10;}
                 else {result = (roman[number + i] + result); break;}
            }return result;
        }
    }
}
