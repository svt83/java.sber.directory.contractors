package ru.turkov.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Table(name = "contractors")
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    //@Size(min = 1, max = 20)
    //@NotBlank
    private String name;

    @Column(nullable = false)
    //@Size(min = 10, max = 12)
    private int inn;

    @Column(nullable = false)
    //@Size(min = 9, max = 9)
    private int kpp;

    @Column(nullable = false)
    //@Size(min = 9, max = 9)
    private int bik;

    @Column(nullable = false)
    //@Size(min = 20, max = 20)
    private int account;

    public Contractor(String name, int inn, int kpp, int bik, int account) {
        this.name = name;
        this.inn = inn;
        this.kpp = kpp;
        this.bik = bik;
        this.account = account;
    }

    public static Contractor from(ContractorInputForm form) {
        return Contractor.builder()
                .name(form.getName())
                .inn(form.getInn())
                .kpp(form.getInn())
                .bik(form.getBik())
                .account(form.getAccount()).build();
    }
}

