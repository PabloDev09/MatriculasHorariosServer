package es.iesjandula.matriculas_horarios_server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.DatosBrutoAlumnoMatricula;

/**
 * Interfaz que define los métodos para acceder y manipular los datos de la entidad {@link DatosBrutoAlumnoMatricula}.
 * ----------------------------------------------------------------------------------------------------------------------
 * Esta interfaz extiende {@link JpaRepository}, lo que facilita la ejecución de operaciones CRUD sobre la tabla correspondiente
 * a la entidad {@link DatosBrutoAlumnoMatricula}.
 * ----------------------------------------------------------------------------------------------------------------------
 */
@Repository
public interface IDatosBrutoAlumnoMatriculaRepository extends JpaRepository<DatosBrutoAlumnoMatricula, Integer>
{

    /**
     * Recupera los registros de {@link DatosBrutoAlumnoMatricula} filtrados por nombre y apellidos.
     * 
     * @param nombre	 								 - El nombre del alumno a buscar.
     * @param apellidos 								 - Los apellidos del alumno a buscar.
     * @return List<Optional<DatosBrutoAlumnoMatricula>> - Una lista de {@link Optional} de {@link DatosBrutoAlumnoMatricula} que coinciden con el nombre y apellidos.
     */
    public List<Optional<DatosBrutoAlumnoMatricula>> findByNombreAndApellidos(String nombre, String apellidos);
}
