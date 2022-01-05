package com.arcadio.springboot.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arcadio.springboot.apirest.models.entity.Cliente;
import com.arcadio.springboot.apirest.models.services.IClienteService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.OK) //202
	public Cliente show(@PathVariable Long id) {
		return clienteService.findOne(id); 
	}
	
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)//201
	public Cliente create(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping("/clientes/{id}") //actualizar
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente clienteActual =  clienteService.findOne(id); 
		clienteActual.setNombre(cliente.getNombre()); 
		clienteActual.setApellido(cliente.getApellido()); 
		clienteActual.setEmail(cliente.getEmail());
		return clienteService.save(clienteActual);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)//204
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}
	
}