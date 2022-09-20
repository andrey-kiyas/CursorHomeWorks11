package com.CursorHomeWorks11.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ShopDTO {

    private String city;
    private String street;
    private String name;
    private boolean siteAvail;
}
