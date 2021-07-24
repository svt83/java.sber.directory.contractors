package ru.turkov.service.validation;

/**
 * Сервис проверки корректности ИНН контрагента
 */
public class InnValidService {

    private final static int[] Ratio10 = {2, 4, 10, 3, 5, 9, 4, 6, 8, 0};
    private final static int[] Ratio11 = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8, 0};
    private final static int[] Ratio12 = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8, 0};

    /**
     * Внешний метод, который вызывается дял проверки ИНН. Определяет принадлежность ИНН организации или
     * физическому лицу (ИП).
     *
     * @param inn - ИНН
     * @return - true, если ИНН указан правильно
     */
    public static boolean checkInn(String inn) {
        if (inn.length() == 10) {
            return checkControlNumber(inn, Ratio10);
        }
        if (inn.length() == 12) {
            return checkControlNumber(inn, Ratio11) && checkControlNumber(inn, Ratio12);
        }
        return false;
    }

    /**
     * Процедура, которая вычисляет и проверяет корректно ли контрольное число для ИНН
     *
     * @param inn     - ИНН
     * @param wtRatio - весовые коэффициенты разрядов ИНН
     * @return - true, если контрольное число соответствует ключу в ИНН
     */
    private static boolean checkControlNumber(String inn, int[] wtRatio) {
        int controlNumber = 0;
        for (int i = 0; i < wtRatio.length; i++) {
            controlNumber += wtRatio[i] * Integer.parseInt(inn.substring(i, i + 1));
        }
        controlNumber %= 11;
        if (controlNumber > 9) {
            controlNumber %= 10;
        }
        return Integer.parseInt(inn.substring(wtRatio.length - 1, wtRatio.length)) == controlNumber;
    }
}