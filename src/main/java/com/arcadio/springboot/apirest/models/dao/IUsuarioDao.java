package com.arcadio.springboot.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.arcadio.springboot.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username); // ya hce la consulta
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario finByUsername2(String username);
	
}
