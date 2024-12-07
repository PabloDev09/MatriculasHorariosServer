package es.iesjandula.matriculas_horarios_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.CursoEtapaEntity;
import es.iesjandula.matriculas_horarios_server.models.ids.IdCursoEtapa;

@Repository
public interface ICursoEtapaRepository extends JpaRepository<CursoEtapaEntity, IdCursoEtapa>
{	
	
}
