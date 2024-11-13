package es.iesjandula.matriculas_horarios_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.matriculas_horarios_server.models.ImpartirEntity;
import es.iesjandula.matriculas_horarios_server.models.ids.IdImpartir;

public interface IImpartirRepository extends JpaRepository<ImpartirEntity, IdImpartir>
{

}
