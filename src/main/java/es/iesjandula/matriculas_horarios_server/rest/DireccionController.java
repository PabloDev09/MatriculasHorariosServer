package es.iesjandula.matriculas_horarios_server.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.iesjandula.matriculas_horarios_server.dtos.AlumnoDto;
import es.iesjandula.matriculas_horarios_server.models.CursoEtapa;
import es.iesjandula.matriculas_horarios_server.models.CursoEtapaGrupo;
import es.iesjandula.matriculas_horarios_server.models.DatosBrutoAlumnoMatricula;
import es.iesjandula.matriculas_horarios_server.models.DatosBrutoAlumnoMatriculaGrupo;
import es.iesjandula.matriculas_horarios_server.models.ids.IdCursoEtapa;
import es.iesjandula.matriculas_horarios_server.models.ids.IdCursoEtapaGrupo;
import es.iesjandula.matriculas_horarios_server.parsers.IParseoDatosBrutos;
import es.iesjandula.matriculas_horarios_server.repositories.ICursoEtapaGrupoRepository;
import es.iesjandula.matriculas_horarios_server.repositories.ICursoEtapaRepository;
import es.iesjandula.matriculas_horarios_server.repositories.IDatosBrutoAlumnoMatriculaGrupoRepository;
import es.iesjandula.matriculas_horarios_server.repositories.IDatosBrutoAlumnoMatriculaRepository;
import es.iesjandula.matriculas_horarios_server.services.AlumnoService;
import es.iesjandula.matriculas_horarios_server.utils.Constants;
import es.iesjandula.matriculas_horarios_server.utils.MatriculasHorariosServerException;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase controlador - DireccionController 
 * --------------------------------------------------------------------------- 
 * REST para gestionar la dirección de cursos, grupos y asignación de alumnos.
 * ---------------------------------------------------------------------------
 */

@Slf4j
@RestController
@RequestMapping(value = "/direccion")
public class DireccionController 
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
	
    @RequestMapping(method = RequestMethod.POST, value = "/matriculas", consumes = "multipart/form-data")
    public ResponseEntity<?> cargarMatriculas 
    (
            @RequestParam(value = "csv", required = true) MultipartFile archivoCsv,
            @RequestHeader(value = "curso", required = true) Integer curso,
            @RequestHeader(value = "etapa", required = true) String etapa
    )
    {
        try 
        {
            // Si el archivo esta vacio
            if(archivoCsv.isEmpty())
            {
            	// Lanazar excepcion
            	String msgError = "ERROR - El archivo importado está vacío";
            	log.error(msgError);
                throw new MatriculasHorariosServerException(1, msgError);
            }
            
            // Convertir MultipartFile a String
            String archivoCsvReadable = new String(archivoCsv.getBytes());
            
            // Declarar Scanner para realizar lectura del fichero
            Scanner scanner = new Scanner(archivoCsvReadable);
            
            // Registro cursoEtapa
            CursoEtapa cursoEtapa = new CursoEtapa();
            IdCursoEtapa idCursoEtapa = new IdCursoEtapa();
            
            // Asignar los campos al id de cursoEtapa
            idCursoEtapa.setCurso(curso);
            idCursoEtapa.setEtapa(etapa);
            
            // Asignar id al registro cursoEtapa
            cursoEtapa.setIdCursoEtapa(idCursoEtapa);
            
            // Llamar al Service IParseoDatosBrutos para realizar parseo
            this.iParseoDatosBrutos.parseoDatosBrutos(scanner, cursoEtapa);
            
            log.info("INFO - Se ha enviado todo correctamente");
            
            // Devolver OK informando que se ha insertado los registros
            return ResponseEntity.status(200).body("INFO - Se ha insertado todos los registros correctamente");
        }
        catch(MatriculasHorariosServerException e)
        {
            // En caso de MatriculasHorariosServerException, devolver un nuevo error con código 1 y la excepción
            log.error("ERROR - El fichero no se ha podido importar: " + e.getMessage());
            
            // Devolver el nuevo ResponseEntity con la excepción
            MatriculasHorariosServerException exceptionResponse = new MatriculasHorariosServerException(1, "ERROR - El fichero no se ha podido importar", e);
            return ResponseEntity.status(400).body(exceptionResponse);
        } 
        catch (IOException e) 
        {
            // En caso de IOException, devolver un nuevo error con código 1 y la excepción
            String msgError = "ERROR - El fichero no se ha podido leer";
            log.error(msgError, e);
            
            // Devolver el nuevo ResponseEntity con la excepción
            MatriculasHorariosServerException exceptionResponse = new MatriculasHorariosServerException(1, msgError, e);
            return ResponseEntity.status(500).body(exceptionResponse);
        }
    }

		

	@RequestMapping(method = RequestMethod.GET, value = "/cursoEtapa")
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
			// Manejo excepcion personalizada
			String msgError = "ERROR - La lista no puede estar vacía";
			log.error(msgError, e);
			
			// Devolver codigo, mensaje y traza de error
			return ResponseEntity.status(404).body(e);
		}
	    catch (Exception e) 
	    {
	        // Manejo de excepciones generales
	        String msgError = "ERROR - No se pudo cargar la lista";
	        log.error(msgError, e);
	        
	        MatriculasHorariosServerException exception = new MatriculasHorariosServerException(1, msgError, e);
	        return ResponseEntity.status(500).body(exception);
	    }
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/grupos")
	public ResponseEntity<?> crearGrupo
	(
	        @RequestHeader(value = "curso", required = true) Integer curso, 
	        @RequestHeader(value = "etapa", required = true) String etapa
	) 	
	{
	    try 
	    {     
	        // Numero de veces repetido el Curso Etapa en la BD
	        int contador = this.iCursoEtapaGrupoRepository.findCountByCursoAndEtapa(curso, etapa);
	        
	        // Asignar la letra A
	        char grupo = Constants.GROUP;
	        
	        // Asignar la letra según el numero de veces que este repetido en BD
	        for (int i = 0; i < contador; i++) 
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

	        // Log de información antes de la respuesta
	        log.info("INFO - Grupo creado correctamente para el curso: {} y etapa: {}", curso, etapa);

	        // Devolver la respuesta indicando que el grupo ha sido creado correctamente
	        return ResponseEntity.status(200).body("INFO - Grupo creado correctamente");
	    } 
	    catch (Exception e) 
	    {
	    	// Manejo de excepciones generales
	    	String msgError = "ERROR - No se ha podido crear el grupo";
	        log.error(msgError, e);
	        
	        MatriculasHorariosServerException exception = new MatriculasHorariosServerException(1, msgError, e);
	        return ResponseEntity.status(500).body(exception);
	    }
	}

	

	@RequestMapping(method = RequestMethod.GET, value = "/grupos")
	public ResponseEntity<?> obtenerGrupo
	(
	        @RequestHeader(value = "curso", required = true) Integer curso, 
	        @RequestHeader(value = "etapa", required = true) String etapa
	) 
	{
	    try 
	    {
	        // Crear el objeto CursoEtapa con los parámetros recibidos
	        CursoEtapa cursoEtapa = new CursoEtapa();
	        IdCursoEtapa idCursoEtapa = new IdCursoEtapa(curso, etapa);
	        cursoEtapa.setIdCursoEtapa(idCursoEtapa);

	        // Obtener la lista de grupos según curso y etapa
	        List<String> grupos = this.iCursoEtapaGrupoRepository.findGrupoByCursoAndEtapa(curso, etapa);

	        // Si la lista está vacía, lanzar una excepción
	        if (grupos.isEmpty()) 
	        {
	        	// Lanzar excepcion con mensaje de Error
	        	String msgError = "ERROR - No se encontraron grupos para el curso {} y etapa {}";
	            log.error(msgError, curso, etapa);
	            throw new MatriculasHorariosServerException(1, "ERROR - No se encontraron grupos para el curso y etapa especificados");
	        }

	        // Log de información antes de la respuesta
	        log.info("INFO - Se han encontrado los siguientes grupos para el curso: {} y etapa: {}", curso, etapa);

	        // Devolver la lista de grupos encontrados
	        return ResponseEntity.status(200).body(grupos);
	    } 
	    catch(MatriculasHorariosServerException e)
	    {
	        // Manejo de excepciones personalizadas
	        String msgError = "ERROR - No se encontraron grupos para el curso y etapa especificados";
	        log.error(msgError, e);
	        	        
	        return ResponseEntity.status(404).body(e);
	    }
	    catch (Exception e) 
	    {
	        // Manejo de excepciones generales
	        String msgError = "ERROR - No se pudo obtener la información de los grupos";
	        log.error(msgError, e);
	        
	        MatriculasHorariosServerException exception = new MatriculasHorariosServerException(1, msgError, e);
	        return ResponseEntity.status(500).body(exception);
	    }
	}


	@RequestMapping(method = RequestMethod.DELETE, value = "/grupos")
	public ResponseEntity<?> eliminarGrupo
	(
	        @RequestHeader(value = "curso", required = true) Integer curso, 
	        @RequestHeader(value = "etapa", required = true) String etapa, 
	        @RequestHeader(value = "grupo", required = true) Character grupo
	) 
	{
	    try 
	    {
	        // Crear el objeto CursoEtapaGrupo con los parámetros recibidos
	        CursoEtapaGrupo cursoEtapaGrupo = new CursoEtapaGrupo();
	        IdCursoEtapaGrupo idCursoEtapaGrupo = new IdCursoEtapaGrupo(curso, etapa, grupo);
	        cursoEtapaGrupo.setIdCursoEtapaGrupo(idCursoEtapaGrupo);

	        // Obtener los Alumnos asignados al grupo a eliminar
	        List<DatosBrutoAlumnoMatriculaGrupo> alumnosAsignadosAlGrupo = 
	                iDatosBrutoAlumnoMatriculaGrupoRepository.findAllByCursoEtapaGrupo(cursoEtapaGrupo);
	        
	        // Registro de DatosBrutoAlumnoMatriculaEntity
	        DatosBrutoAlumnoMatricula datosBrutoAlumnoMatricula = new DatosBrutoAlumnoMatricula();

	        // Crear el objeto CursoEtapa del Alumno
	        CursoEtapa cursoEtapa = new CursoEtapa();
	        IdCursoEtapa idCursoEtapa = new IdCursoEtapa();
	        idCursoEtapa.setCurso(curso);
	        idCursoEtapa.setEtapa(etapa);
	        cursoEtapa.setIdCursoEtapa(idCursoEtapa);
	        
	        // Transferir los Alumnos a la tabla DatosBrutoAlumnoMatricula
	        for(DatosBrutoAlumnoMatriculaGrupo alumno : alumnosAsignadosAlGrupo)
	        {
	            // Asignar valores de Alumno a registro de DatosBrutoAlumnoMatriculaEntity
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
	        
	        // Log de información antes de la respuesta
	        log.info("INFO - Grupo eliminado correctamente para el curso: {} y etapa: {}", curso, etapa);
	        
	        // Devolver mensaje de OK
	        return ResponseEntity.status(200).body("INFO - Grupo eliminado correctamente");
	    } 
	    catch (Exception e) 
	    {
	        // Manejo de excepciones generales
	        String msgError = "ERROR - No se pudo eliminar el grupo";
	        log.error(msgError, e);

	        MatriculasHorariosServerException exception = new MatriculasHorariosServerException(1, msgError, e);
	        return ResponseEntity.status(500).body(exception);
	    }
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/grupos/alumnos")
	public ResponseEntity<?> asignarAlumnos
	(
	        @RequestBody List<AlumnoDto> alumnos,
	        @RequestParam(value = "curso", required = true) Integer curso,
	        @RequestParam(value = "etapa", required = true) String etapa,
	        @RequestParam(value = "grupo", required = true) Character grupo
	) 
	{
	    try 
	    {
	        // Crear el objeto CursoEtapaGrupo con los parámetros recibidos
	        CursoEtapaGrupo cursoEtapaGrupo = new CursoEtapaGrupo();
	        IdCursoEtapaGrupo idCursoEtapaGrupo = new IdCursoEtapaGrupo(curso, etapa, grupo);
	        cursoEtapaGrupo.setIdCursoEtapaGrupo(idCursoEtapaGrupo);

	        // Crear registro de la Tabla DatosBrutosAlumnoMatriculaGrupo
	        DatosBrutoAlumnoMatriculaGrupo datosBrutoAlumnoMatriculaGrupo = new DatosBrutoAlumnoMatriculaGrupo();

	        // Por cada alumno buscarlo en DatosBrutosAlumnoMatricula y añadirlos a DatosBrutosAlumnoMatriculaGrupo
	        for (AlumnoDto alumno : alumnos)
	        {
	            // Optional de DatosBrutoAlumnoMatriculaEntity
	            List<Optional<DatosBrutoAlumnoMatricula>> datosBrutoAlumnoMatriculaAsignaturasOpt;

	            // Buscar los registros del alumno en DatosBrutosAlumnoMatricula
	            datosBrutoAlumnoMatriculaAsignaturasOpt = iDatosBrutoAlumnoMatriculaRepository.findByNombreAndApellidos(alumno.getNombre(), alumno.getApellidos());

	            for (Optional<DatosBrutoAlumnoMatricula> datosBrutoAlumnoMatriculaAsignaturaOpt : datosBrutoAlumnoMatriculaAsignaturasOpt)
	            {
	                // Si no existe el AlumnoDto
	                if (!datosBrutoAlumnoMatriculaAsignaturaOpt.isPresent())
	                {
	                    // Mensaje de error
	                    String msgError = "ERROR - No se encontraron los datos de un alumno";

	                    // Mostrar el error a través de un log
	                    log.error(msgError);

	                    // Lanzar excepción con mensaje de error
	                    throw new MatriculasHorariosServerException(1, msgError);
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

	        // Log de información antes de la respuesta
	        log.info("INFO - Alumnos asignados correctamente al grupo {} para el curso {} y etapa {}", grupo, curso, etapa);

	        // Devolver mensaje de OK
	        return ResponseEntity.status(200).body("INFO - Alumnos asignados correctamente con el curso " 
	                + curso + " y etapa " + etapa + " y grupo " + grupo);
	    } 
	    catch (MatriculasHorariosServerException e) 
	    {
	        // Manejo de excepciones personalizadas
	        log.error("ERROR - Código: {}, Mensaje: {}, Excepción: {}", e.getMessage(), e.getBodyExceptionMessage(), e);

	        // Devolver la excepción personalizada con código 1 y el mensaje de error
	        return ResponseEntity.status(404).body(e);
	    } 
	    catch (Exception e) 
	    {
	        // Manejo de excepciones generales
	        String msgError = "ERROR - No se pudo asignar los alumnos al curso";
	        log.error(msgError, e);

	        // Devolver una excepción personalizada con código 1 y el mensaje de error
	        MatriculasHorariosServerException exception = new MatriculasHorariosServerException(1, msgError, e);
	        return ResponseEntity.status(500).body(exception);
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
	        for (Optional<DatosBrutoAlumnoMatriculaGrupo> datosBrutoAlumnoMatriculaGrupoAsignaturaOpt : datosBrutoAlumnoMatriculaGrupoAsignaturasOpt)
	        {
	            // Si no existe el registro
	            if (!datosBrutoAlumnoMatriculaGrupoAsignaturaOpt.isPresent())
	            {
	                // Mensaje de error
	                String msgError = "ERROR - No se encontraron los datos del alumno";

	                // Mostrar el error a través de un log
	                log.error(msgError);

	                // Lanzar una excepción con el mensaje de error
	                throw new MatriculasHorariosServerException(1, msgError);
	            }

	            // Crear registro DatosBrutoAlumnoMatricula
	            DatosBrutoAlumnoMatricula datosBrutoAlumnoMatricula = new DatosBrutoAlumnoMatricula();

	            // Crear CursoEtapa del Alumno
	            CursoEtapa cursoEtapa = new CursoEtapa();
	            IdCursoEtapa idCursoEtapa = new IdCursoEtapa();
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

	        // Log de información antes de la respuesta
	        log.info("INFO - Alumno desasignado correctamente");

	        // Devolver mensaje de OK
	        return ResponseEntity.status(200).body("INFO - Alumno desasignado correctamente");
	    } 
	    catch (MatriculasHorariosServerException e) 
	    {
	    	// Manejo de excepciones personalizadas
	    	log.error("ERROR - Código: {}, Mensaje: {}, Excepción: {}", e.getMessage(), e.getBodyExceptionMessage(), e);

	        // Devolver la excepción personalizada con código 1 y el mensaje de error
	        return ResponseEntity.status(404).body(e);
	    } 
	    catch (Exception e) 
	    {
	        // Manejo de excepciones generales
	        String msgError = "ERROR - No se pudo desasignar el alumno del grupo";
	        log.error(msgError, e);

	        // Devolver una excepción personalizada con código 1 y el mensaje de error
	        MatriculasHorariosServerException exception = new MatriculasHorariosServerException(1, msgError, e);
	        return ResponseEntity.status(500).body(exception);
	    }
	}

	
	    @RequestMapping(method = RequestMethod.GET, value = "/grupos/alumnos")
	    public List<AlumnoDto> obtenerAlumnos
	    (
	    		@RequestParam(value="curso", required = true) String curso, 
	            @RequestParam(value="etapa", required = true) String etapa, 
	            @RequestParam(value="grupo", required = true) String grupo
	                                     
	    ) 
	    {
	        return alumnoService.obtenerAlumnosPorCursoEtapaGrupo(curso, etapa, grupo);
	    }
	
}
