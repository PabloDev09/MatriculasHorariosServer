package es.iesjandula.matriculas_horarios_server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.CursoEtapaGrupo;
import es.iesjandula.matriculas_horarios_server.models.DatosBrutoAlumnoMatriculaGrupo;

/**
 * Interfaz que define los métodos para acceder y manipular los datos de la entidad {@link DatosBrutoAlumnoMatriculaGrupo}.
 * ----------------------------------------------------------------------------------------------------------------------
 * Esta interfaz extiende {@link JpaRepository}, lo que facilita la ejecución de operaciones CRUD sobre la tabla correspondiente
 * a la entidad {@link DatosBrutoAlumnoMatriculaGrupo}.
 * ----------------------------------------------------------------------------------------------------------------------
 */
@Repository
public interface IDatosBrutoAlumnoMatriculaGrupoRepository extends JpaRepository<DatosBrutoAlumnoMatriculaGrupo, Integer>
{

    /**
     * Recupera todos los registros de {@link DatosBrutoAlumnoMatriculaGrupo} para un {@link CursoEtapaGrupo} específico.
     * 
     * @param cursoEtapaGrupo 						- El {@link CursoEtapaGrupo} utilizado para filtrar los datos.
     * @return List<DatosBrutoAlumnoMatriculaGrupo> - Una lista de {@link DatosBrutoAlumnoMatriculaGrupo} correspondientes a dicho {@link CursoEtapaGrupo}.
     */
    public List<DatosBrutoAlumnoMatriculaGrupo> findAllByCursoEtapaGrupo(CursoEtapaGrupo cursoEtapaGrupo);

    /**
     * Recupera los registros de {@link DatosBrutoAlumnoMatriculaGrupo} filtrados por nombre y apellidos.
     * 
     * @param nombre 										  - El nombre del alumno a buscar.
     * @param apellidos 									  - Los apellidos del alumno a buscar.
     * @return List<Optional<DatosBrutoAlumnoMatriculaGrupo>> - Una lista de {@link Optional} de {@link DatosBrutoAlumnoMatriculaGrupo} que coinciden con el nombre y apellidos.
     */
    public List<Optional<DatosBrutoAlumnoMatriculaGrupo>> findAllByNombreAndApellidos(String nombre, String apellidos);
}
