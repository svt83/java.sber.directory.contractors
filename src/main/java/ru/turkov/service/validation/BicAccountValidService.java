package ru.turkov.service.validation;

/**
 * Сервис проверки корректности БИК и номера счета контрагента
 */
public class BicAccountValidService {

    private static final int[] Ratio = {7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1};

    /**
     * Внешний метод, который вызывается дял проверки соответствия БИК и номера счета.
     *
     * @param bik     - БИК
     * @param account - номер счета
     */
    public static boolean checkKeyBicAccount(String bik, String account) {
        return bik.length() != 9 || account.length() != 20 || !controlNumber(bik, account);
    }

    /**
     * Процедура определяющая принадлежность счета РКЦ или кредитной организации
     *
     * @param bik     - БИК
     * @param account - номер счета
     * @return true - счет указан верно
     */
    private static boolean controlNumber(String bik, String account) {
        if (bik.indexOf("00", 6) == 6) {
            return checkControlNumber("0" + bik.substring(4, 6) + account);
        }
        return checkControlNumber(bik.substring(6, 9) + account);
    }

    /**
     * Процедура, которая вычисляет и проверяет корректно ли контрольное число
     *
     * @param control - строка (набор цифр) для проверки
     * @return - true, если контрольное число корректно (== 0)
     */
    private static boolean checkControlNumber(String control) {
        int controlNumber = 0;
        for (int i = 0; i < Ratio.length; i++) {
            controlNumber += Ratio[i] * Integer.parseInt(control.substring(i, i + 1));
        }
        return controlNumber % 10 == 0;
    }
}
