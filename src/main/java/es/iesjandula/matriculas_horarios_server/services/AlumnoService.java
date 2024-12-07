package es.iesjandula.matriculas_horarios_server.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.matriculas_horarios_server.dtos.AlumnoDto;
import es.iesjandula.matriculas_horarios_server.repositories.IAlumnoRepository;
import es.iesjandula.matriculas_horarios_server.repositories.IDatosBrutoAlumnoMatriculaRepository;
import es.iesjandula.matriculas_horarios_server.repositories.IMatriculaRepository;

@Service
public class AlumnoService {

    @Autowired
    private IMatriculaRepository matriculaRepository;

    @Autowired
    private IDatosBrutoAlumnoMatriculaRepository datosBrutoRepository;

    /**
     * Obtiene los IDs de los alumnos asignados a un curso, etapa y grupo específicos.
     */
    public List<String> obtenerIdsAlumnos(String curso, String etapa, String grupo) {
        return matriculaRepository.findAll().stream()
            .filter(m -> String.valueOf(m.getAsignatura().getId().getCurso()).equals(curso) && 
                         m.getAsignatura().getId().getEtapa().equals(etapa) &&
                         String.valueOf(m.getAsignatura().getId().getGrupo()).equals(grupo)) 
            .map(m -> String.valueOf(m.getId().getAlumno().getId())) 
            .collect(Collectors.toList());
    }
 
    
    /**
     * Obtiene los alumnos asignados y pendientes según el curso, etapa y grupo.
     */
    public List<AlumnoDto> obtenerAlumnosPorCursoEtapaGrupo(String curso, String etapa, String grupo) {
        // Obtener los alumnos asignados
        List<String> asignados = obtenerIdsAlumnos(curso, etapa, grupo);

        // Obtener todos los alumnos de DatosBrutoAlumnoMatricula
        List<String> todosLosAlumnos = datosBrutoRepository.findAll().stream()
                .map(d -> d.getNombre() + " " + d.getApellidos())
                .collect(Collectors.toList());

        // Calcular los alumnos pendientes (todos - asignados)
        List<String> pendientes = todosLosAlumnos.stream()
                .filter(alumno -> !asignados.contains(alumno))
                .collect(Collectors.toList());

        // Mete aqui cada AlumnoDto
        List<AlumnoDto> alumnosAsignadosYPendientes = List.of();
        
        // Crear y retornar el DTO -> Esto tiene que devolver -> List<AlumnoDto> con los alumnos asignados y pendientes de asignar
        return alumnosAsignadosYPendientes;
    }
}
