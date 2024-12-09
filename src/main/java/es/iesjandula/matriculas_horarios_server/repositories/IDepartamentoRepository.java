package es.iesjandula.matriculas_horarios_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.Departamento;

/**
 * Interfaz que define los métodos para acceder y manipular los datos de la entidad {@link Departamento}.
 * ----------------------------------------------------------------------------------------------------------------------
 * Esta interfaz extiende {@link JpaRepository}, lo que facilita la ejecución de operaciones CRUD sobre la tabla correspondiente
 * a la entidad {@link Departamento}.
 * ----------------------------------------------------------------------------------------------------------------------
 */
@Repository
public interface IDepartamentoRepository extends JpaRepository<Departamento, String>
{

}
