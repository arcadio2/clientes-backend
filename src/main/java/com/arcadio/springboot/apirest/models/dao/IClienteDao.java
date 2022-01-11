package com.arcadio.springboot.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.arcadio.springboot.apirest.models.entity.Cliente;
import com.arcadio.springboot.apirest.models.entity.Region;





public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{

	@Query("from Region")
	public List<Region> findAllRegions();
	
	@Query("select c from Cliente c where c.email=?1")
	public Cliente findClienteByEmail(String email);
}
