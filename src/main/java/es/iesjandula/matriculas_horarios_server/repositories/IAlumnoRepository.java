package es.iesjandula.matriculas_horarios_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.AlumnoEntity;

@Repository
public interface IAlumnoRepository extends JpaRepository<AlumnoEntity, Integer>
{

}
