package es.iesjandula.matriculas_horarios_server.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.iesjandula.matriculas_horarios_server.dtos.AlumnoDto;
import es.iesjandula.matriculas_horarios_server.models.CursoEtapa;
import es.iesjandula.matriculas_horarios_server.models.CursoEtapaGrupo;
import es.iesjandula.matriculas_horarios_server.models.DatosBrutoAlumnoMatricula;
import es.iesjandula.matriculas_horarios_server.models.DatosBrutoAlumnoMatriculaGrupo;
import es.iesjandula.matriculas_horarios_server.models.ids.IdCursoEtapa;
import es.iesjandula.matriculas_horarios_server.models.ids.IdCursoEtapaGrupo;
import es.iesjandula.matriculas_horarios_server.repositories.ICursoEtapaGrupoRepository;
import es.iesjandula.matriculas_horarios_server.repositories.ICursoEtapaRepository;
import es.iesjandula.matriculas_horarios_server.repositories.IDatosBrutoAlumnoMatriculaGrupoRepository;
import es.iesjandula.matriculas_horarios_server.repositories.IDatosBrutoAlumnoMatriculaRepository;
import es.iesjandula.matriculas_horarios_server.services.AlumnoService;
import es.iesjandula.matriculas_horarios_server.services.IParseoDatosBrutos;
import es.iesjandula.matriculas_horarios_server.utils.Constants;
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
	ICursoEtapaGrupoRepository iCursoEtapaGrupoRepository;
	
	@Autowired
	IDatosBrutoAlumnoMatriculaRepository iDatosBrutoAlumnoMatriculaRepository;
	
	@Autowired
	IDatosBrutoAlumnoMatriculaGrupoRepository iDatosBrutoAlumnoMatriculaGrupoRepository;
	
	@Autowired
	IParseoDatosBrutos iParseoDatosBrutos;
	
    @Autowired
    AlumnoService alumnoService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/cargarMatriculas", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> cargarMatriculas 
	(
			@RequestPart("cursoEtapa") CursoEtapa cursoEtapa,
			@RequestParam("archivo") MultipartFile archivo
	)
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
			this.iParseoDatosBrutos.parseoDatosBrutos(scanner, cursoEtapa);
			
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
		catch (IOException e) 
		{
			String msgError = "ERROR - El fichero no se ha podido leer";
			log.error(msgError);
			return ResponseEntity.status(500).body(msgError);
		}
	}
		

	@RequestMapping(method = RequestMethod.GET, value = "/obtenerCursoEtapa")
	public ResponseEntity<?> obtenerCursoEtapa()
	{
		try 
		{
			// Lista usada para guardar los registros de la Tabla CursoEtapa
			List<CursoEtapa> listaCursoEtapa= new ArrayList<>();
			
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
	
	@RequestMapping(method = RequestMethod.POST, value = "/grupos")
	public ResponseEntity<?> crearGrupo
	(
			@RequestBody CursoEtapa cursoEtapa
	)
	{
		// Curso donde crear el grupo
		int curso = cursoEtapa.getIdCursoEtapa().getCurso();
		
		// Etapa donde crear el grupo
		String etapa = cursoEtapa.getIdCursoEtapa().getEtapa();
		
		// Numero de veces repetido el Curso Etapa en la BD
		int contador = this.iCursoEtapaGrupoRepository.findCountByCursoAndEtapa(curso, etapa);
		
		// Asignar la letra A
		char grupo = Constants.GROUP;
		
		// Asignar la letra según el numero de veces que este repetido en BD
		for(int i = 0; i < contador; i++)
		{
			grupo++;
		}
		
		// Crear registro de Curso Etapa Grupo
		CursoEtapaGrupo cursoEtapaGrupo = new CursoEtapaGrupo();
		
		// Crear campo de id con todos los campos
		IdCursoEtapaGrupo idCursoEtapaGrupo = new IdCursoEtapaGrupo(curso, etapa, grupo);
		
		// Asignar el id al registro de Curso Etapa
		cursoEtapaGrupo.setIdCursoEtapaGrupo(idCursoEtapaGrupo);
		
		// Insertar en BD
		this.iCursoEtapaGrupoRepository.saveAndFlush(cursoEtapaGrupo);
		
		// Devolver la lista
		return ResponseEntity.status(200).body("INFO - Grupo creado correctamente");
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/grupos")
	public ResponseEntity<?> obtenerGrupo
	(
	        @RequestBody CursoEtapa cursoEtapa
	) 
	{
	    try 
	    {
	        // Obtener la lista de grupos según curso y etapa
	        List<String> grupos = this.iCursoEtapaGrupoRepository.findGrupoByCursoAndEtapa(cursoEtapa.getIdCursoEtapa().getCurso(), cursoEtapa.getIdCursoEtapa().getEtapa());

	        // Si la lista está vacía, lanzar una excepción
	        if (grupos.isEmpty()) 
	        {
	            log.error("ERROR - No se encontraron grupos para el curso {} y etapa {}", cursoEtapa.getIdCursoEtapa().getCurso(), cursoEtapa.getIdCursoEtapa().getEtapa());
	            throw new MatriculasHorariosServerException(404, "ERROR - No se encontraron grupos para el curso y etapa especificados");
	        }

	        // Devolver la lista de grupos encontrados
	        return ResponseEntity.status(200).body(grupos);
	    } 
	    catch (MatriculasHorariosServerException e) 
	    {
	        // Obtener traza del error
	        log.error("ERROR - {}", e.getBodyExceptionMessage());
	        return ResponseEntity.status(404).body(e.getBodyExceptionMessage());
	    } 
	    catch (Exception e) 
	    {
	        // Manejo de excepciones generales
	        String msgError = "ERROR - No se pudo obtener la información de los grupos";
	        log.error(msgError, e);
	        return ResponseEntity.status(500).body(msgError);
	    }
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/grupos")
	public ResponseEntity<?> eliminarGrupo
	(
	        @RequestBody CursoEtapaGrupo cursoEtapaGrupo
	) 
	{
	    try 
	    {
	    	// Obtener los alumnos asignados al grupo a eliminar
	    	List<DatosBrutoAlumnoMatriculaGrupo> alumnosAsignadosAlGrupo = iDatosBrutoAlumnoMatriculaGrupoRepository.findAllByCursoEtapaGrupo(cursoEtapaGrupo);
	    	
    		// Registro de DatosBrutoAlumnoMatriculaEntity
    		DatosBrutoAlumnoMatricula datosBrutoAlumnoMatricula = new DatosBrutoAlumnoMatricula();
    		
    		// Crear CursoEtapa del Alumno
    		CursoEtapa cursoEtapa = new CursoEtapa();
    		IdCursoEtapa idCursoEtapa =  new IdCursoEtapa();
    		idCursoEtapa.setCurso(cursoEtapaGrupo.getIdCursoEtapaGrupo().getCurso());
    		idCursoEtapa.setEtapa(cursoEtapaGrupo.getIdCursoEtapaGrupo().getEtapa());
    		cursoEtapa.setIdCursoEtapa(idCursoEtapa);
	    	
	    	for(DatosBrutoAlumnoMatriculaGrupo alumno : alumnosAsignadosAlGrupo)
	    	{
	    		// Asignar alumno a registro de DatosBrutoAlumnoMatriculaEntity
	    		datosBrutoAlumnoMatricula.setNombre(alumno.getNombre());
	    		datosBrutoAlumnoMatricula.setApellidos(alumno.getApellidos());
	    		datosBrutoAlumnoMatricula.setAsignatura(alumno.getAsignatura());
	    		datosBrutoAlumnoMatricula.setCursoEtapa(cursoEtapa);
	    		
	    		// Guardar el registro en BD
	    		iDatosBrutoAlumnoMatriculaRepository.saveAndFlush(datosBrutoAlumnoMatricula);
	    	}
	    	
	    	// Eliminar los alumnos asignados al grupo de la tabla DatosBrutoAlumnoMatriculaGrupo
	    	iDatosBrutoAlumnoMatriculaGrupoRepository.deleteAll(alumnosAsignadosAlGrupo);
	    	
	    	// Eliminar el grupo en la tabla CursoEtapaGrupo
	    	iCursoEtapaGrupoRepository.delete(cursoEtapaGrupo);
	    	
	    	// Devolver mensaje de OK
	    	return ResponseEntity.status(200).body("INFO - Grupo eliminado correctamente");
	    }
	    catch (Exception e) 
	    {
	        // Manejo de excepciones generales
	        String msgError = "ERROR - No se pudo eliminar el grupo";
	        log.error(msgError, e);
	        return ResponseEntity.status(500).body(msgError);
	    }
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/grupos/alumnos")
	public ResponseEntity<?> asignarAlumnos
	(
	        @RequestBody List<AlumnoDto> alumnos,
	        @RequestBody CursoEtapaGrupo cursoEtapaGrupo
	) 
	{
	    try 
	    {
	    	// Crear registro de la Tabla DatosBrutosAlumnoMatriculaGrupo
	    	DatosBrutoAlumnoMatriculaGrupo datosBrutoAlumnoMatriculaGrupo = new DatosBrutoAlumnoMatriculaGrupo();

	    	// Por cada alumno buscarlo en DatosBrutosAlumnoMatricula y añadirlos a DatosBrutosAlumnoMatriculaGrupo, eliminando los registros de la tabla Prikera
	    	for(AlumnoDto alumno : alumnos)
	    	{
	    		// Optional de DatosBrutoAlumnoMatriculaEntity
	    		List<Optional<DatosBrutoAlumnoMatricula>> datosBrutoAlumnoMatriculaAsignaturasOpt;
	    		
	    		// Buscar los registros del alumno en DatosBrutosAlumnoMatricula
	    		datosBrutoAlumnoMatriculaAsignaturasOpt = iDatosBrutoAlumnoMatriculaRepository.findByNombreAndApellidos(alumno.getNombre(), alumno.getApellidos());
	    		
	    		for(Optional<DatosBrutoAlumnoMatricula> datosBrutoAlumnoMatriculaAsignaturaOpt : datosBrutoAlumnoMatriculaAsignaturasOpt)
	    		{
		    		// Si no existe el AlumnoDto
		    		if(!datosBrutoAlumnoMatriculaAsignaturaOpt.isPresent())
		    		{
		    			// Mensaje de error
		    	        String msgError = "ERROR - No se encontro los datos de un alumno";
		    	        
		    	        // Mostrar el error a través de un log
		    	        log.error(msgError); 
		    	        
		    	        // Lanzar excepcion con mensaje de error
		    	        throw new MatriculasHorariosServerException(msgError);
		    		}
		    		
		    		// Asignar cada uno de los campos 
		    		datosBrutoAlumnoMatriculaGrupo.setNombre(datosBrutoAlumnoMatriculaAsignaturaOpt.get().getNombre());
		    		datosBrutoAlumnoMatriculaGrupo.setApellidos(datosBrutoAlumnoMatriculaAsignaturaOpt.get().getApellidos());
		    		datosBrutoAlumnoMatriculaGrupo.setAsignatura(datosBrutoAlumnoMatriculaAsignaturaOpt.get().getAsignatura());
		    		datosBrutoAlumnoMatriculaGrupo.setCursoEtapaGrupo(cursoEtapaGrupo);
		    		
		    		// Guardar el registro en la tabla DatosBrutoAlumnoMatriculaGrupo
		    		iDatosBrutoAlumnoMatriculaGrupoRepository.saveAndFlush(datosBrutoAlumnoMatriculaGrupo);
		    		
		    		// Eliminar el registro en la tabla DatosBrutoAlumnoMatricula
		    		iDatosBrutoAlumnoMatriculaRepository.delete(datosBrutoAlumnoMatriculaAsignaturaOpt.get());
	    		}

	    	}
	        // Devolver mensaje de OK
	        return ResponseEntity.status(200).body("INFO - Alumnos asignados correctamente con el curso" + cursoEtapaGrupo.getIdCursoEtapaGrupo().getCurso() + cursoEtapaGrupo.getIdCursoEtapaGrupo().getEtapa() + cursoEtapaGrupo.getIdCursoEtapaGrupo().getGrupo());
	    } 
	    catch (Exception e) 
	    {
	        // Manejo de excepciones generales
	        String msgError = "ERROR - No se pudo asginar los alumnos al curso";
	        log.error(msgError);
	        return ResponseEntity.status(500).body(msgError);
	    }
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/grupos/alumnos")
	public ResponseEntity<?> desasignarAlumno
	(
	        @RequestBody AlumnoDto alumno
	) 
	{
	    try 
	    {
	    	// Optional DatosBrutoAlumnoMatriculaGrupo
	    	List<Optional<DatosBrutoAlumnoMatriculaGrupo>> datosBrutoAlumnoMatriculaGrupoAsignaturasOpt = iDatosBrutoAlumnoMatriculaGrupoRepository.findAllByNombreAndApellidos(alumno.getNombre(), alumno.getApellidos());
	    	
	    	// Por cada asignatura del Alumno
	    	for(Optional<DatosBrutoAlumnoMatriculaGrupo> datosBrutoAlumnoMatriculaGrupoAsignaturaOpt: datosBrutoAlumnoMatriculaGrupoAsignaturasOpt)
	    	{
	    		// Si no existe
		    	if(!datosBrutoAlumnoMatriculaGrupoAsignaturaOpt.isPresent())
		    	{
	    			// Mensaje de error
	    	        String msgError = "ERROR - No se encontro los datos del alumno";
	    	        
	    	        // Mostrar el error a través de un log
	    	        log.error(msgError); 
	    	        
	    	        // Lanzar excepcion con mensaje de error
	    	        throw new MatriculasHorariosServerException(msgError);
		    	}
		    	
		    	// Crear registro DatosBrutoAlumnoMatricula 
		    	DatosBrutoAlumnoMatricula datosBrutoAlumnoMatricula = new DatosBrutoAlumnoMatricula();
		        
	    		// Crear CursoEtapa del Alumno
	    		CursoEtapa cursoEtapa = new CursoEtapa();
	    		IdCursoEtapa idCursoEtapa =  new IdCursoEtapa();
	    		idCursoEtapa.setCurso(datosBrutoAlumnoMatriculaGrupoAsignaturaOpt.get().getCursoEtapaGrupo().getIdCursoEtapaGrupo().getCurso());
	    		idCursoEtapa.setEtapa(datosBrutoAlumnoMatriculaGrupoAsignaturaOpt.get().getCursoEtapaGrupo().getIdCursoEtapaGrupo().getEtapa());
	    		cursoEtapa.setIdCursoEtapa(idCursoEtapa);
	    		
	    		// Asignar alumno a registro de DatosBrutoAlumnoMatriculaEntity
	    		datosBrutoAlumnoMatricula.setNombre(datosBrutoAlumnoMatriculaGrupoAsignaturaOpt.get().getNombre());
	    		datosBrutoAlumnoMatricula.setApellidos(datosBrutoAlumnoMatriculaGrupoAsignaturaOpt.get().getApellidos());
	    		datosBrutoAlumnoMatricula.setAsignatura(datosBrutoAlumnoMatriculaGrupoAsignaturaOpt.get().getAsignatura());
	    		datosBrutoAlumnoMatricula.setCursoEtapa(cursoEtapa);
	    		
	    		// Guardar el registro en la tabla DatosBrutoAlumnoMatricula
	    		iDatosBrutoAlumnoMatriculaRepository.saveAndFlush(datosBrutoAlumnoMatricula);
	    		
	    		// Eliminar el registro en la tabla DatosBrutoAlumnoMatriculaGrupo
	    		iDatosBrutoAlumnoMatriculaGrupoRepository.delete(datosBrutoAlumnoMatriculaGrupoAsignaturaOpt.get());
	    		
	    	}
	    	
	    	// Devolver mensaje de OK
	        return ResponseEntity.status(200).body("INFO - Alumno desasignado correctamente");
	    } 
	    catch (Exception e) 
	    {
	        // Manejo de excepciones generales
	        String msgError = "ERROR - No se pudo asginar los alumnos al curso";
	        log.error(msgError);
	        return ResponseEntity.status(500).body(msgError);
	    }
	}
	
	
	




	    @RequestMapping()
	    public List<AlumnoDto> obtenerAlumnos
	    (
	    		@RequestParam String curso, 
	            @RequestParam String etapa, 
	            @RequestParam String grupo
	                                     
	    		
	    		
	    ) 
	    {
	        return alumnoService.obtenerAlumnosPorCursoEtapaGrupo(curso, etapa, grupo);
	    }
	
}
