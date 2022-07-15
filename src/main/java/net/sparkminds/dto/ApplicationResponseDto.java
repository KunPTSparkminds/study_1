package net.sparkminds.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ApplicationResponseDto {
    
    
	private String email;
	
	private String name;
	
	private String github;
	
//	private Boolean deleted;
	
	private List<PastProjectResponseDto> pastProjects;
	

	
}
