package net.sparkminds.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;
import net.sparkminds.entity.PastProject;

@Builder
@Data
public class ApplicationRequestDto {
    
    @NotBlank
    private String email;
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String github;
    
    @Valid
    @NotBlank 
    private List<PastProjectRequestDto> pastProject;
    
}
