package es.iesjandula.matriculas_horarios_server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.CursoEtapaGrupo;
import es.iesjandula.matriculas_horarios_server.models.DatosBrutoAlumnoMatriculaGrupo;

@Repository
public interface IDatosBrutoAlumnoMatriculaGrupoRepository extends JpaRepository<DatosBrutoAlumnoMatriculaGrupo, Integer>
{

	public List<DatosBrutoAlumnoMatriculaGrupo> findAllByCursoEtapaGrupo(CursoEtapaGrupo cursoEtapaGrupo);

	public List<Optional<DatosBrutoAlumnoMatriculaGrupo>> findAllByNombreAndApellidos(String nombre, String apellidos);
}
