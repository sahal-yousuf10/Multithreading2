package com.example.sahal.Springbootmultithreading2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import static com.example.sahal.Springbootmultithreading2.constant.Constant.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = ID_NOT_NULL_ERROR_MESSAGE)
    private long id;

    @NotBlank(message = FIRSTNAME_MANDATORY_ERROR_MESSAGE)
    @Pattern(regexp = "^[a-zA-Z ]*$", message = FIRSTNAME_PATTERN_ERROR_MESSAGE)
    @Size(min = 3, max = 100, message = FIRSTNAME_SIZE_ERROR_MESSAGE)
    private String firstName;

    @NotBlank(message = LASTNAME_MANDATORY_ERROR_MESSAGE)
    @Pattern(regexp = "^[a-zA-Z ]*$", message = LASTNAME_PATTERN_ERROR_MESSAGE)
    @Size(min = 3, max = 100, message = LASTNAME_SIZE_ERROR_MESSAGE)
    private String lastName;

    @Email(message = EMAIL_PATTERN_ERROR_MESSAGE)
    @NotBlank(message = EMAIL_MANDATORY_ERROR_MESSAGE)
    private String email;

    @NotBlank(message = GENDER_MANDATORY_ERROR_MESSAGE)
    @Pattern(regexp = "^[a-zA-Z ]*$", message = GENDER_PATTERN_ERROR_MESSAGE)
    private String gender;

    @NotBlank(message = COMPANY_NAME_MANDATORY_ERROR_MESSAGE)
    private String companyName;

    @NotBlank(message = JOB_TITLE_MANDATORY_ERROR_MESSAGE)
    private String jobTitle;
}
