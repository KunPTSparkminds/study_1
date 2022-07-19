package net.sparkminds.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sparkminds.entity.enumeration.Capacity;
import net.sparkminds.entity.enumeration.Employment;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PastProjectResponseDto {
	private String pastProjectName;
	
	private Employment employment;
	
	private Capacity capacity;
	
	private String duration;
	
	private String startYear;
	
	private String role;
	
	private Long teamSize;
	
	private String linkToRepository;
	
	private String linkToLiveUrl;
	
//	private Long idApplication;
}
