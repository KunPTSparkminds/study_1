package net.sparkminds.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import net.sparkminds.dto.PastProjectRequestDto;

@Entity
@Data
@Table(name = "application")
public class Application {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "email", nullable = false)
	private String email;

	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;

	@NotBlank
	@Column(name = "github", nullable = false)
	private String github;

	@NotBlank
	@Column()
	@OneToMany(mappedBy="application", cascade = CascadeType.ALL)
	@Valid
	private List<PastProject> pastProjects;
	
	@Column(name = "is_deleted", nullable = false)
	@NotNull
	private boolean deleted = false;

//	@Override
//	public String toString() {
//		return "Application [id=" + id + ", email=" + email + ", name=" + name + ", github=" + github
//				+ ", applicationPastProject=" + applicationPastProject + "]";
//	}

	public void addProject(PastProject project) {
	    if (pastProjects == null) pastProjects = new ArrayList<>();
	    pastProjects.add(project);
	    project.setApplication(this);
	}
	
	public void addProjects(List<PastProject> projects) {
	    projects.forEach(this::addProject);
	}
}
