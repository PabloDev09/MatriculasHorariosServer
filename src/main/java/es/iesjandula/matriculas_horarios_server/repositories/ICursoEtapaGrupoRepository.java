package es.iesjandula.matriculas_horarios_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.CursoEtapaGrupoEntity;
import es.iesjandula.matriculas_horarios_server.models.ids.IdCursoEtapaGrupo;

@Repository
public interface ICursoEtapaGrupoRepository extends JpaRepository<CursoEtapaGrupoEntity, IdCursoEtapaGrupo>
{
}
