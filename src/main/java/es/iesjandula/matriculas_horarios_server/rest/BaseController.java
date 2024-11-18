package es.iesjandula.matriculas_horarios_server.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.iesjandula.matriculas_horarios_server.models.CursoEtapaEntity;
import es.iesjandula.matriculas_horarios_server.repositories.ICursoEtapaRepository;
import es.iesjandula.matriculas_horarios_server.repositories.IDatosBrutoAlumnoMatriculaRepository;
import es.iesjandula.matriculas_horarios_server.utils.MatriculasHorariosServerException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/base")
public class BaseController 
{
	@Autowired
	IDatosBrutoAlumnoMatriculaRepository iDatosBrutoAlumnoMatriculaRepository;
	
	@Autowired
	ICursoEtapaRepository iCursoEtapaRepository;
	
	@RequestMapping(method = RequestMethod.POST, value = "/base/cargarMatriculas")
	public ResponseEntity<?> cargarMatriculas 
	(
			@RequestHeader CursoEtapaEntity cursoEtapaEntity,
			@RequestHeader MultipartFile file
	)throws MatriculasHorariosServerException
	{
		log.info("Se ha enviado todo correctamente");
		return ResponseEntity.status(200).body("INFO - Se ha insertado todos los registros correctamente");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/base/cargarCursoEtapa")

	public ResponseEntity<?> cargarCursoEtapa
	()throws MatriculasHorariosServerException
	{
		return ResponseEntity.status(200).body(null);
	}
	public ResponseEntity<?> cargarCursoEtapa (@ModelAttribute CursoEtapaEntity CursoEtapaEntity)
	throws MatriculasHorariosServerException
	{
		try {
			List<CursoEtapaEntity>ListaCursoEtapa= new ArrayList<>();
			
			ListaCursoEtapa = iCursoEtapaRepository.findAll();
			
			return ResponseEntity.ok(ListaCursoEtapa);
		}catch(Exception exception) {
			MatriculasHorariosServerException CursoEtapaException= new MatriculasHorariosServerException(1,"Error al cargar la lista ", exception);
			log.error("Error al cargar la lista", CursoEtapaException);
			return ResponseEntity.status(500).body(CursoEtapaException.getBodyExceptionMessage());
		}
		

	}
}
