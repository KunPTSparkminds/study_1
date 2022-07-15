package net.sparkminds.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table(name="t_reviewer")
public class Reviewer {
    
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;
    
    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;
    
    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;
    
    @NotNull
    @Column(name = "picture", nullable = false)
    private String picture;
}
