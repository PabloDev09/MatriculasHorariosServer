package es.iesjandula.matriculas_horarios_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iesjandula.matriculas_horarios_server.models.CursoEtapaGrupo;
import es.iesjandula.matriculas_horarios_server.models.ids.IdCursoEtapaGrupo;

@Repository
public interface ICursoEtapaGrupoRepository extends JpaRepository<CursoEtapaGrupo, IdCursoEtapaGrupo>
{
    // Contar elementos por curso y etapa
    @Query("SELECT COUNT(c) FROM CursoEtapaGrupo c WHERE c.idCursoEtapaGrupo.curso = :curso AND c.idCursoEtapaGrupo.etapa = :etapa")
    public int findCountByCursoAndEtapa
    (
        @Param("curso") int curso, 
        @Param("etapa") String etapa
    );
    
    // Obtener lista de grupos por curso y etapa
    @Query("SELECT c.idCursoEtapaGrupo.grupo FROM CursoEtapaGrupo c WHERE c.idCursoEtapaGrupo.curso = :curso AND c.idCursoEtapaGrupo.etapa = :etapa")
    public List<String> findGrupoByCursoAndEtapa
    (
        @Param("curso") int curso,
        @Param("etapa") String etapa
    );
}
