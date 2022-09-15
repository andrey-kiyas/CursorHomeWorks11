package com.CursorHomeWorks11.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String name;
    private int staff;
    private boolean siteAvail;

    public Shop(String city, String street, String name, int staff, boolean siteAvail) {
        this.city = city;
        this.street = street;
        this.name = name;
        this.staff = staff;
        this.siteAvail = siteAvail;
    }
}
