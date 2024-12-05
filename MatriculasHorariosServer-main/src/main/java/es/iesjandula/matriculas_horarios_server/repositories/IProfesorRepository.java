package es.iesjandula.matriculas_horarios_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.matriculas_horarios_server.models.ProfesorEntity;

public interface IProfesorRepository extends JpaRepository<ProfesorEntity, String>
{

}
