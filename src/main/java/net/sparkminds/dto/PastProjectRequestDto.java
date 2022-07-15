package net.sparkminds.dto;

import lombok.Builder;
import lombok.Data;
import net.sparkminds.entity.enumeration.Capacity;
import net.sparkminds.entity.enumeration.Employment;


@Builder
@Data
public class PastProjectRequestDto {
    
    private String pastProjectName;
    
    private Employment employment;
    
    private Capacity capacity;
    
    private String duration;
    
    private String startYear;
    
    private String role;
    
    private Long teamSize;
    
    private String linkToRepository;
    
    private String linkToLiveUrl;
    
}
