package es.iesjandula.matriculas_horarios_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.CursoEtapaEntity;
import es.iesjandula.matriculas_horarios_server.models.ids.IdCursoEtapa;

@Repository
public interface ICursoEtapaRepository extends JpaRepository<CursoEtapaEntity, IdCursoEtapa>
{
	public int findCountByCursoAndEtapa(int curso, String etapa);
	
	public List<String> findGrupoByCursoAndEtapa(int curso, String etapa);
}
