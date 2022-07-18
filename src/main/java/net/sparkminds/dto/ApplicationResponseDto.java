package net.sparkminds.dto;

import java.util.List;

import lombok.Data;



@Data

public class ApplicationResponseDto {
    
    
	private String email;
	
	private String name;
	
	private String github;
	
	private List<PastProjectResponseDto> pastProjects;
	

	
}
