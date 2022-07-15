package net.sparkminds.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApplicationRequestDto {
    private String email;
    
    private String name;
    
    private String github;
    
}
