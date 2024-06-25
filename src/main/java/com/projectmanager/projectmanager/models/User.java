package com.projectmanager.projectmanager.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="First Name is required!")
    private String firstName;
    
    @NotEmpty(message="Last Name is required!")
    private String lastName;
    
    @NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotEmpty(message="Password is required!")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password is required!")
    private String confirm;
    
    @OneToMany(mappedBy="lead", fetch = FetchType.LAZY)
    private List<Project> projectsLed;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "projects_users", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;
    
    public User() {
		// TODO Auto-generated constructor stub
	}

	public User(@NotEmpty(message = "First Name is required!") String firstName,
			@NotEmpty(message = "Last Name is required!") String lastName,
			@NotEmpty(message = "Email is required!") @Email(message = "Please enter a valid email!") String email,
			@NotEmpty(message = "Password is required!") String password,
			@NotEmpty(message = "Confirm Password is required!") String confirm, List<Project> projectsLed,
			List<Project> projects) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
		this.projectsLed = projectsLed;
		this.projects = projects;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Project> getProjectsLed() {
		return projectsLed;
	}

	public void setProjectsLed(List<Project> projectsLed) {
		this.projectsLed = projectsLed;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", confirm=" + confirm + ", projectsLed=" + projectsLed + ", projects="
				+ projects + "]";
	}
    
    
}
