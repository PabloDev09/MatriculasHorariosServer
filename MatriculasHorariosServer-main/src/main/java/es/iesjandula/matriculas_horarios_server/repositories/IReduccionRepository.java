package es.iesjandula.matriculas_horarios_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.matriculas_horarios_server.models.ReduccionEntity;

public interface IReduccionRepository extends JpaRepository<ReduccionEntity, String>
{

}
