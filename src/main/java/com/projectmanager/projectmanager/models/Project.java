package com.projectmanager.projectmanager.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Title is required")
    @Size(min=3, message="Title must be at least 3 characters")
    private String title;
    
    @NotEmpty(message="Description is required")
    @Size(min=3, message="Description must be at least 3 characters")
    private String description;
    
    @Future(message="Due date must be in the future")
    private Date dueDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lead_id")
    private User lead;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "projects_users", 
        joinColumns = @JoinColumn(name = "project_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> teamMembers;
    
    public Project() {
		// TODO Auto-generated constructor stub
	}

	public Project(
			@NotEmpty(message = "Title is required") @Size(min = 3, message = "Title must be at least 3 characters") String title,
			@NotEmpty(message = "Description is required") @Size(min = 3, message = "Description must be at least 3 characters") String description,
			@Future(message = "Due date must be in the future") Date dueDate, User lead, List<User> teamMembers) {
		super();
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.lead = lead;
		this.teamMembers = teamMembers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public User getLead() {
		return lead;
	}

	public void setLead(User lead) {
		this.lead = lead;
	}

	public List<User> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<User> teamMembers) {
		this.teamMembers = teamMembers;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", description=" + description + ", dueDate=" + dueDate
				+ ", lead=" + lead + ", teamMembers=" + teamMembers + "]";
	}
    
    
}
