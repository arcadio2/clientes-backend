package com.arcadio.springboot.apirest.models.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity 
@Table(name = "clientes")
public class Cliente {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;

	@NotEmpty(message = "Debes escribir un nombre")
	@Size(min = 4, message = "El nombre debe tener al menos 4 caracteres")
	@Column(nullable = false)
	private String nombre;
	
	@NotEmpty(message = "Debes escribir un apellido")
	@Size(min = 4, message = "El apellido debe tener al menos 4 caracteres")
	private String apellido;
	
	@NotEmpty(message = "Debes escribir un email")
	@Email (message = "El formato de email es inválido")
	@Column(nullable = false, unique = true)
	private String email; 

	//@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //para respuyesto json
	private Date createAt;

	
	private String foto;

	@NotNull(message = "la región no puede ser vacía")
	@ManyToOne(fetch = FetchType.LAZY)//carga perezosa	
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})//solo dejamos id y nombre
	private Region region; 
	  
	private static final long serialVersionUID = 1L;

	@PrePersist //para que se ejecute antes de crear un registro
	public void prePersist() {
		createAt = new Date();  
	} 

	 
	public Long getId() { 
		return id;
	} 

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	

	public Region getRegion() {
		return region;
	}


	public void setRegion(Region region) {
		this.region = region;
	}


	@Override
	public String toString() {
		return  nombre + " " + apellido ;
	}
}
