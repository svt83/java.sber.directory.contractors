package ru.turkov.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Класс описывающий контрагента (contractors) в таблице БД приложения
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contractors")
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "Поле Наименование не может быть пустым")
    private String name;

    @Column(nullable = false, length = 12)
    @NotBlank(message = "Поле ИНН не может быть пустым")
    @Pattern(regexp = "^[0-9]{10}|^[0-9]{12}", message = "Шаблон ИНН: 10 или 12 цифр")
    private String inn;

    @Column(nullable = false, length = 9)
    @NotBlank(message = "Поле КПП не может быть пустым")
    @Pattern(regexp = "^[0-9]{9}", message = "Шаблон КПП: 9 цифр")
    private String kpp;

    @Column(nullable = false, length = 9)
    @NotBlank(message = "Поле БИК не может быть пустым")
    @Pattern(regexp = "^[0-9]{9}", message = "Шаблон КПП: 9 цифр")
    private String bik;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "Поле Счет не может быть пустым")
    @Pattern(regexp = "^[0-9]{20}", message = "Шаблон счета: 20 цифр")
    private String account;
}

