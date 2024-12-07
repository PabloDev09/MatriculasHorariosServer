package es.iesjandula.matriculas_horarios_server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.DatosBrutoAlumnoMatriculaEntity;

@Repository
public interface IDatosBrutoAlumnoMatriculaRepository extends JpaRepository<DatosBrutoAlumnoMatriculaEntity, Integer>
{

	public Optional<DatosBrutoAlumnoMatriculaEntity> findByNombreAndApellidos(String nombre, String apellidos);
	
}
