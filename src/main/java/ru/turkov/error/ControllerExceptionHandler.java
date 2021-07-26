package ru.turkov.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;

/**
 * Обработчик исключений в приложении
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Исключение, которое будет выброшено, если контрагент с введенными реквизитами уже существует в БД
     *
     * @param exception - исключение EntityExistsException
     * @return  - ошибка и http-статус
     */
    @ExceptionHandler(value = EntityExistsException.class)
    public ResponseEntity<Object> contractorExistException(EntityExistsException exception) {
        return new ResponseEntity<>("Ошибка. " + exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Исключение, которое будет выброшено, если введены неверные реквизиты контрагента
     *
     * @param exception - исключение IllegalArgumentException
     * @return - ошибка и http-статус
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> contractorBadArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>("Ошибка. " + exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}