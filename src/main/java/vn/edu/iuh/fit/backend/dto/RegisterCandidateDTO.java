/*
 * @ (#) RegisterCanđiateDTO.java   1.0     01/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.dto;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import vn.edu.iuh.fit.backend.models.Address;

import java.time.LocalDate;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 01/12/2024
 * @version: 1.0
 */
@Data
public class RegisterCandidateDTO {
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDate dob;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Họ và tên không được để trống")
    @Size(min = 2, max = 100, message = "Họ và tên phải từ 2 đến 100 ký tự")
    private String fullName;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Số điện thoại phải gồm 10-11 chữ số")
    private String phone;

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
