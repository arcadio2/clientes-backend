package com.arcadio.springboot.apirest.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.arcadio.springboot.apirest.models.entity.Cliente;



public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{

}
