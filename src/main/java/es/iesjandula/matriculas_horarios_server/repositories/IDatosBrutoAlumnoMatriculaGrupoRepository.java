package es.iesjandula.matriculas_horarios_server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.CursoEtapaGrupoEntity;
import es.iesjandula.matriculas_horarios_server.models.DatosBrutoAlumnoMatriculaGrupoEntity;

@Repository
public interface IDatosBrutoAlumnoMatriculaGrupoRepository extends JpaRepository<DatosBrutoAlumnoMatriculaGrupoEntity, Integer>
{

	public List<DatosBrutoAlumnoMatriculaGrupoEntity> findAllByCursoEtapaGrupo(CursoEtapaGrupoEntity cursoEtapaGrupo);

	public Optional<DatosBrutoAlumnoMatriculaGrupoEntity> findByNombreAndApellidos(String nombre, String apellidos);
}
