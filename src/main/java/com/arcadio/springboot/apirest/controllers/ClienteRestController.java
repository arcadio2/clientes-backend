package com.arcadio.springboot.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.arcadio.springboot.apirest.models.entity.Cliente;
import com.arcadio.springboot.apirest.models.entity.Region;
import com.arcadio.springboot.apirest.models.services.IClienteService;
import com.arcadio.springboot.apirest.services.IUploadFileService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class ClienteRestController {

	@Autowired 
	private IClienteService clienteService;
	@Autowired
	private IUploadFileService fileService; 
	
	/*PUBLICOS*/
	@GetMapping("/clientes")
	public List<Cliente> index(){
		
		return clienteService.findAll(); 
	}
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page,4); 
		
		return clienteService.findAll(pageable);
	}
	
	
	@GetMapping("/clientes/{id}")
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@ResponseStatus(HttpStatus.OK) //202  
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente= null; 
		Map<String, Object> response = new HashMap<>();
		try {
			 cliente = clienteService.findOne(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la BD");
			response.put("error", e.getMessage()+" \n" + e.getMostSpecificCause());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND); 
		}
		
		
		if(cliente == null) {
			response.put("mensaje", "No existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
		
	}
	
	
	@PostMapping("/clientes")
	@Secured({"ROLE_ADMIN"})
	@ResponseStatus(HttpStatus.CREATED)//201
	public  ResponseEntity<?> create(@RequestBody @Valid Cliente cliente, BindingResult result) {
		Cliente guardado = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			/*List<String> errors = new ArrayList<>();
			
			for(FieldError err: result.getFieldErrors()) {
				errors.add("El campo "+ err.getField()+"' "+ err.getDefaultMessage());
			}*/
			 
			/*List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> {
						//retornamos un string
						return "El campo "+ err.getField()+"' "+ err.getDefaultMessage();
					})
					.collect(Collectors.toList());*/
			

			Map<String,String> errors = result.getFieldErrors()
					.stream()
					.collect(Collectors.toMap(
							e ->{
								return e.getField();
							}
							, 
							e->{
								return e.getDefaultMessage();
							}
							));
					  
			
			response.put("mensaje","Ocurrió un error al guardar, datos invalidos");
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		if(clienteService.existeEmail(cliente.getEmail())) {
			response.put("mensaje", "El email ya existe");
			response.put("error","Email inválido");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		try {
			guardado = clienteService.save(cliente);
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Ocurrió un error al guardar ");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		response.put("mensaje", "El cliente ha sido creado con éxito");
		response.put("cliente",guardado);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/clientes/{id}") //actualizar
	public ResponseEntity<?> update(@RequestBody @Valid Cliente cliente,BindingResult result, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Cliente clienteActual =  clienteService.findOne(id); 
		Cliente clienteUpdated = null;
		if(clienteActual == null) {
			response.put("mensaje", "Error: No se pudo editar, No existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND); 
		}
		if(result.hasErrors()) {
			
			Map<String,String> errors = result.getFieldErrors()
					.stream()
					.collect(Collectors.toMap(
							e ->{
								return e.getField();
							}
							, 
							e->{
								return e.getDefaultMessage();
							}
							));
					  
			
			response.put("mensaje","Ocurrió un error al guardar, datos invalidos");
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		
		try {
			clienteActual.setNombre(cliente.getNombre()); 
			clienteActual.setApellido(cliente.getApellido()); 
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setRegion(cliente.getRegion());
			 
			clienteUpdated = clienteService.save(clienteActual);  
		}catch(DataAccessException e) {
			if(clienteService.existeEmail(clienteActual.getEmail()) ) {
				response.put("mensaje", "El email ya existe");
				response.put("error","Email inválido");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensaje", "Error: No se pudo editar, No existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND); 
		}
		response.put("mensaje", "El cliente ha sido actualizado con éxito");
		response.put("cliente", clienteUpdated); 
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clientes/{id}")
	@Secured({"ROLE_ADMIN"})
	@ResponseStatus(HttpStatus.NO_CONTENT)//204
	public  ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			
			fileService.deleteImage(id);	
			clienteService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		response.put("mensaje", "Cliente eliminado con éxito");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK); 
	}
	
	@PostMapping("/clientes/upload")
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	public ResponseEntity<?> upload(@RequestParam(name = "file") MultipartFile archivo, @RequestParam("id") Long id){
		System.out.println("ARCHIVO "+archivo.getOriginalFilename());
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = clienteService.findOne(id); 
		if(!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = fileService.uploadImage(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR); 
			
			}
			fileService.deleteImage(id);
				
			cliente.setFoto(nombreArchivo); 
			clienteService.save(cliente);
			response.put("cliente", cliente);
			response.put("mensaje", "Se ha subidio correctamente la imagen "+nombreArchivo);
			
		}else {
			response.put("error", "Debe seleccionar una imagen");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST); 
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK); 
	}
	
	
	/*DESCARGAR LA IMAGEN*//*PUBLICO*/
	@GetMapping("/clientes/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		Resource recurso = null; 
		try {
			recurso = fileService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 

		/*Para que se pueda descargar el recurso*/
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\" "+recurso.getFilename()+"\" ");
		
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}

	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
		return clienteService.findAllRegiones();
	}
	
}
