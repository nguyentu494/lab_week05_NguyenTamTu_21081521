/*
 * @ (#) RegisterCompanyDTO.java   1.0     01/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.dto;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 01/12/2024
 * @version: 1.0
 */
@Data
@Builder
public class RegisterCompanyDTO {

    @NotBlank(message = "Mô tả không được để trống")
    @Size(max = 500, message = "Mô tả không được vượt quá 500 ký tự")
    private String about;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Tên công ty không được để trống")
    @Size(min = 3, max = 100, message = "Tên công ty phải từ 3 đến 100 ký tự")
    private String compName;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Số điện thoại phải gồm 10-11 chữ số")
    private String phone;

    @NotBlank(message = "URL không được để trống")
    @Size(max = 200, message = "URL không được vượt quá 200 ký tự")
    private String webUrl;

    @NotBlank(message = "Street không được để trống")
    @Size(max = 150, message = "Street không được vượt quá 150 ký tự")
    private String street;

    @NotBlank(message = "City không được để trống")
    @Size(max = 50, message = "City không được vượt quá 50 ký tự")
    private String city;

    private CountryCode country;

    @Pattern(regexp = "\\d+", message = "Number chỉ được chứa số")
    @Size(max = 20, message = "Number không được vượt quá 20 ký tự")
    private String number;

    @NotBlank(message = "Zipcode không được để trống")
    @Pattern(regexp = "\\d{5,7}", message = "Zipcode phải từ 5 đến 7 chữ số")
    private String zipcode;
}
