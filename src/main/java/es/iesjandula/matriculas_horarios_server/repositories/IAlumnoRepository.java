package es.iesjandula.matriculas_horarios_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.Alumno;

/**
 * Interfaz que define los métodos para acceder y manipular los datos de la entidad {@link Alumno}.
 * -----------------------------------------------------------------------------------------------------------------
 * Esta interfaz extiende {@link JpaRepository}, lo que permite realizar operaciones CRUD sobre la tabla que representa
 * la entidad {@link Alumno} sin necesidad de implementar manualmente los métodos.
 * -----------------------------------------------------------------------------------------------------------------
 * El uso de esta interfaz permite realizar operaciones de persistencia de forma sencilla, como guardar, eliminar, 
 * actualizar y buscar objetos de tipo {@link Alumno} en la base de datos.
 * -----------------------------------------------------------------------------------------------------------------
 */
@Repository
public interface IAlumnoRepository extends JpaRepository<Alumno, Integer>
{
	
}
