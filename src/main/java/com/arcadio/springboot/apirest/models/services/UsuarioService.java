package com.arcadio.springboot.apirest.models.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcadio.springboot.apirest.models.dao.IUsuarioDao;
import com.arcadio.springboot.apirest.models.entity.Usuario;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("No existe el usuario");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles().
											stream().
											map(role->{
												return new SimpleGrantedAuthority(role.getNombre());
											}).collect(Collectors.toList());
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(),true,true,true,authorities);
	} // interfaz de spring security


	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {

		return usuarioDao.findByUsername(username);
	}

}
