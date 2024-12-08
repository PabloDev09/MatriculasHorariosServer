package es.iesjandula.matriculas_horarios_server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.DatosBrutoAlumnoMatricula;

@Repository
public interface IDatosBrutoAlumnoMatriculaRepository extends JpaRepository<DatosBrutoAlumnoMatricula, Integer>
{

	public List<Optional<DatosBrutoAlumnoMatricula>> findByNombreAndApellidos(String nombre, String apellidos);
	
}
