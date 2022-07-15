package net.sparkminds.dto;

import javax.validation.constraints.NotBlank;

public class ReviewerLoginRequestDto {

    @NotBlank
    private String email;
    
    @NotBlank
    private String password;
}
