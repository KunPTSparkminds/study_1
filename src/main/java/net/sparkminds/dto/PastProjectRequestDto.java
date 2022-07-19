package net.sparkminds.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;
import net.sparkminds.entity.Application;
import net.sparkminds.entity.enumeration.Capacity;
import net.sparkminds.entity.enumeration.Employment;


@Data
public class PastProjectRequestDto {
    
    @NotBlank(message = "PastProjectName is required")
    private String pastProjectName;
    
    @NotBlank(message = "Employment is required")
    private Employment employment;
    
    @NotBlank(message = "Capacity is required")
    private Capacity capacity;
    
    @NotBlank(message = "Duration is required")
    private String duration;
    
    @NotBlank(message = "StartYear is required")
    private String startYear;
    
    @NotBlank(message = "Role is required")
    private String role;
    
    @NotEmpty(message = "TeamSize is required")
    private Long teamSize;
    
    private String linkToRepository;
    
    private String linkToLiveUrl;
    
    private Application application;
    
}
