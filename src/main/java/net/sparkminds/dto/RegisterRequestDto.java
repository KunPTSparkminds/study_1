package net.sparkminds.dto;



import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterRequestDto {
    
    private String name;
    
    private String email;
    
    private String password;
    
    private String picture;
}
