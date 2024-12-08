package es.iesjandula.matriculas_horarios_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.ProfesorReduccion;
import es.iesjandula.matriculas_horarios_server.models.ids.IdProfesorReduccion;

@Repository
public interface IProfesorReduccionRepository extends JpaRepository<ProfesorReduccion, IdProfesorReduccion>
{

}
