package com.arcadio.springboot.apirest.models.services;

import com.arcadio.springboot.apirest.models.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username); // ya hce la consulta
}
