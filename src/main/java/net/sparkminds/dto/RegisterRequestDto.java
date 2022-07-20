package net.sparkminds.dto;



import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterRequestDto {
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String email;
    
    @NotBlank
    private String password;
    
    @NotBlank
    private String picture;
}
