package es.iesjandula.matriculas_horarios_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.MatriculaEntity;
import es.iesjandula.matriculas_horarios_server.models.ids.IdMatricula;

@Repository
public interface IMatriculaRepository extends JpaRepository<MatriculaEntity, IdMatricula>
{

}
