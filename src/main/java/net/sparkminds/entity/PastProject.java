package net.sparkminds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sparkminds.entity.enumeration.Capacity;
import net.sparkminds.entity.enumeration.Employment;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="t_past_project")
public class PastProject {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="past_project_name", nullable = false)
	private String pastProjectName;
	
	
	@Column(name="employment_mode", nullable = false)
	@Enumerated(EnumType.STRING)
	private Employment employment;
	
	
	@Column(name="capacity", nullable = false)
	@Enumerated(EnumType.STRING)
	private Capacity capacity;
	
	
	@Column(name="duration", nullable = false)
	private String duration;
	
	
	@Column(name="start_year", nullable = false)
	private String startYear;
	
	
	@Column(name="role", nullable = false)
	private String role;
	
	
	@Column(name="team_size", nullable = false)
	private Long teamSize;
	
	
	@Column(name="link_to_repository")
	private String linkToRepository;
	
	
	@Column(name="link_to_live_url")
	private String linkToLiveUrl;
	
	@Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;
	
	@ManyToOne
	@JoinColumn(name = "application_id", referencedColumnName="id")
	private Application application;
}
