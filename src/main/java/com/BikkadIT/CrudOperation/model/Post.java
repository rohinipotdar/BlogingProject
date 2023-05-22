package com.BikkadIT.CrudOperation.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="POST")
@Setter
@Getter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	
	@Column(name="POST_TITLE" , length=100 , nullable = false)
	private String title;
	
	@Column(name="POST_CONTENT" , length=100 , nullable = false)
	private String content;
	
	@Column(name="IMAGE" , length=100 , nullable = false)
	private String imageName;
	
	@Column(name="ADDED_DATE" , length=100 , nullable = false)
	private Date addedDate;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();
	
	
	
	
}
