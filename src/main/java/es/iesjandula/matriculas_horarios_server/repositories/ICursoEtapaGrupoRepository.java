package es.iesjandula.matriculas_horarios_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.CursoEtapaGrupoEntity;
import es.iesjandula.matriculas_horarios_server.models.ids.IdCursoEtapaGrupo;

@Repository
public interface ICursoEtapaGrupoRepository extends JpaRepository<CursoEtapaGrupoEntity, IdCursoEtapaGrupo>
{

	public int findCountByCursoAndEtapa(int curso, String etapa);
	
	@Query("SELECT c.grupo FROM CursoEtapaGrupoEntity c WHERE c.curso = :curso AND c.etapa = :etapa")
	public List<String> findGrupoByCursoAndEtapa
	(
			@Param("curso") int curso,
			@Param("etapa") String etapa
	);
}
