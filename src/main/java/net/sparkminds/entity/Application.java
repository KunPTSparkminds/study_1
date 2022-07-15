package net.sparkminds.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "application")
public class Application {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "email")
	private String email;

	@NotBlank
	@Column(name = "name")
	private String name;

	@NotBlank
	@Column(name = "github")
	private String github;

	@NotBlank
	@Column()
	@OneToMany(mappedBy="application", cascade = CascadeType.ALL)
	private List<PastProject> pastProject;
	
	@Column()
	@NotNull
	private boolean deleted = false;

//	@Override
//	public String toString() {
//		return "Application [id=" + id + ", email=" + email + ", name=" + name + ", github=" + github
//				+ ", applicationPastProject=" + applicationPastProject + "]";
//	}

}
