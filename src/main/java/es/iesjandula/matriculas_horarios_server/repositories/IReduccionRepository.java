package es.iesjandula.matriculas_horarios_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.ReduccionEntity;

@Repository
public interface IReduccionRepository extends JpaRepository<ReduccionEntity, String>
{

}
