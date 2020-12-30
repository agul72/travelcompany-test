package com.travelfactory.partners.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    Integer id = null;
    String name;
    String email;
    String firstName = null;
    String phone = null;
}
