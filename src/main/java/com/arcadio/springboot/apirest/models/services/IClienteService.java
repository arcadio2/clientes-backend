package com.arcadio.springboot.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arcadio.springboot.apirest.models.entity.Cliente;




public interface IClienteService {
	public List<Cliente> findAll();
	public Cliente findOne(Long id); 
	public Cliente save(Cliente cliente); 
	public void delete(Long id); 
	public Page<Cliente> findAll(Pageable pageable);
	/*
	public List<Producto> findByNombre(String term); 
	public List<Producto> findByNombre2(String term);
	public void saveFactura(Factura factura);
	
	public Producto findProductoById(Long id);
	
	public void saveItemFactura(ItemFactura item);
	
	public Factura findFacturaById(Long id);
	
	public boolean deleteFactura(Long id); 
	
	public Factura fetchFacturaById(Long id); 
	
	public Cliente fetchClienteById(Long id); */
}
