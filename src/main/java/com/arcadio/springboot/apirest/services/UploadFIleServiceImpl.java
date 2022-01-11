package com.arcadio.springboot.apirest.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.arcadio.springboot.apirest.models.entity.Cliente;
import com.arcadio.springboot.apirest.models.services.IClienteService;

@Service
public class UploadFIleServiceImpl  implements IUploadFileService{
	
	private final static String DIR_UPLOAD="uploads"; 
	@Autowired
	private IClienteService clienteService;
	


	@Override
	public String nombreUnico(String nombre) {
		return UUID.randomUUID().toString() + "_"+ nombre.replace(" ", "");
	}

	@Override
	public Path getPath(String nombre) {
		return Paths.get(DIR_UPLOAD).resolve(nombre).toAbsolutePath();
	}

	@Override
	public boolean deleteImage(Long id) {
		Cliente cliente = clienteService.findOne(id); 
		String nombreFotoAnterior = cliente.getFoto();
		if(nombreFotoAnterior !=null && nombreFotoAnterior.length()>0)  {
			Path rutaAnterior = this.getPath(nombreFotoAnterior);
			File archivoAnterior = rutaAnterior.toFile();
			if(archivoAnterior.exists() && archivoAnterior.canRead()) {
				archivoAnterior.delete();
				return true;
			}
		}
		return false; 
	}



	@Override
	public String uploadImage(MultipartFile archivo) throws IOException {
		String nombreArchivo = this.nombreUnico(archivo.getOriginalFilename());
		Path rutaArchivo = this.getPath(nombreArchivo);
		Files.copy(archivo.getInputStream(), rutaArchivo);
		return nombreArchivo; 

	}



	@Override
	public Resource cargar(String nombre) throws MalformedURLException {
		Path rutaArchivo = Paths.get("uploads").resolve(nombre).toAbsolutePath();
		Resource recurso = null; 
		recurso = new UrlResource(rutaArchivo.toUri());
		if(!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/source/static/images").resolve("no_user.png").toAbsolutePath();
			recurso = new UrlResource(rutaArchivo.toUri());			
			System.out.println("Error, no se pudo cambiar la imagen "+nombre);
		}
		return recurso; 
	}







	



	

}
