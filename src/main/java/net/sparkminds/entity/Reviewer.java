package net.sparkminds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name="t_reviewer")
public class Reviewer {
    
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    
    @Column(name = "name", nullable = false)
    private String name;
    
   
    @Column(name = "email", nullable = false)
    private String email;
    
    
    @Column(name = "password", nullable = false)
    private String password;
    
    
    @Column(name = "picture", nullable = false)
    private String picture;
    
    @Column(name = "is_deleted", nullable = false)
	
	private boolean deleted = false;
}
