package net.sparkminds.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApplicationRequestDto {
    
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotNull(message = "Github is required")
    private String github;
    
    @NotEmpty (message = "PastProject is required")
    private List<PastProjectRequestDto> pastProject;
    
}
