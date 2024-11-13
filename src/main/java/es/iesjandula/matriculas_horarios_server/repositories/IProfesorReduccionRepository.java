package es.iesjandula.matriculas_horarios_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.matriculas_horarios_server.models.ProfesorReduccionEntity;
import es.iesjandula.matriculas_horarios_server.models.ids.IdProfesorReduccion;

public interface IProfesorReduccionRepository extends JpaRepository<ProfesorReduccionEntity, IdProfesorReduccion>
{

}
