package es.iesjandula.matriculas_horarios_server.rest;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.iesjandula.matriculas_horarios_server.models.CursoEtapaEntity;
import es.iesjandula.matriculas_horarios_server.repositories.ICursoEtapaRepository;
import es.iesjandula.matriculas_horarios_server.services.IParseoDatosBrutos;
import es.iesjandula.matriculas_horarios_server.utils.MatriculasHorariosServerException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/base")
public class BaseController 
{	
	@Autowired
	ICursoEtapaRepository iCursoEtapaRepository;
	
	@Autowired
	IParseoDatosBrutos iParseoDatosBrutos;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/cargarDatosBrutos")
	public ResponseEntity<?> cargarMatriculas 
	(
			@RequestHeader CursoEtapaEntity cursoEtapaEntity,
			@RequestHeader MultipartFile file
	)throws MatriculasHorariosServerException, IOException
	{
		try 
		{
			// Si el archivo esta vacio
			if(file.isEmpty())
			{
				throw new MatriculasHorariosServerException(400, "ERROR - El archivo importado está vacio");
			}
			
	        // Convertir MultipartFile a Readable 
	        ByteArrayInputStream fileReadable = new ByteArrayInputStream(file.getBytes());
			
	        
	        // Declarar Scanner para realizar lectura del fichero
			Scanner scanner = new Scanner(fileReadable);
			
			// Llamar al Service IParseoDatosBrutos para realizar parseo
			this.iParseoDatosBrutos.parseoDatosBrutos(scanner, cursoEtapaEntity);
			
			log.info("INFO - Se ha enviado todo correctamente");
			
			// Devolver OK informando que se ha insertado los registros
			return ResponseEntity.status(200).body("INFO - Se ha insertado todos los registros correctamente");
		}
		catch(MatriculasHorariosServerException e)
		{
			// Obtener traza
			e.getStackTrace();
			
			log.error("ERROR - El fichero no se ha podido importar");
			
			// Devolver codigo, mensaje y traza de error
			return ResponseEntity.status(400).body(e.getBodyExceptionMessage());
		}
	}
		

	@RequestMapping(method = RequestMethod.GET, value = "/obtenerCursoEtapa")
	public ResponseEntity<?> cargarCursoEtapa()
	
	throws MatriculasHorariosServerException
	{
		try 
		{
			// Lista usada para guardar los registros de la Tabla CursoEtapa
			List<CursoEtapaEntity> listaCursoEtapa= new ArrayList<>();
			
			// Asignar los registros de la Tabla CursoEtapa
			listaCursoEtapa = this.iCursoEtapaRepository.findAll();
			
			// Si la lista esta vacia, lanzar excepcion
			if(listaCursoEtapa.isEmpty())
			{
				log.error("ERROR - Lista vacía");
				throw new MatriculasHorariosServerException(404, "ERROR - No se ha encontrado ningun curso");
			}
			
			// Devolver la lista
			return ResponseEntity.status(200).body(listaCursoEtapa);
		}
		catch(MatriculasHorariosServerException e) 
		{
			// Obtener traza
			e.getStackTrace();
			
			log.error("ERROR - La lista no ha podido ser cargada");
			
			// Devolver codigo, mensaje y traza de error
			return ResponseEntity.status(404).body(e.getBodyExceptionMessage());
		}
		

	}
}
