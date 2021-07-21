package ru.turkov.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contractors")
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = false, length = 20)
    @NotBlank(message = "Поле Наименование не может быть пустым")
    private String name;

    @Column(nullable = false, length = 12)
    @NotBlank(message = "Поле ИНН не может быть пустым")
    private String inn;

    @Column(nullable = false, length = 9)
    @NotBlank(message = "Поле КПП не может быть пустым")
    private String kpp;

    @Column(nullable = false, length = 9)
    @NotBlank(message = "Поле БИК не может быть пустым")
    private String bik;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "Поле Счет не может быть пустым")
    private String account;

    public Contractor(String name, String inn, String kpp, String bik, String account) {
        this.name = name;
        this.inn = inn;
        this.kpp = kpp;
        this.bik = bik;
        this.account = account;
    }

//    public static Contractor from(ContractorInputForm form) {
//        return Contractor.builder()
//                .name(form.getName())
//                .inn(form.getInn())
//                .kpp(form.getInn())
//                .bik(form.getBik())
//                .account(form.getAccount()).build();
//    }
}

