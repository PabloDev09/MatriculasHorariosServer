package es.iesjandula.matriculas_horarios_server.models;

import java.util.List;

import es.iesjandula.matriculas_horarios_server.models.ids.IdCursoEtapaGrupo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Curso_Etapa_Grupo")
public class CursoEtapaGrupoEntity 
{
    @EmbeddedId
    private IdCursoEtapaGrupo idCursoEtapaGrupo;

    @OneToMany(mappedBy = "cursoEtapaGrupo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DatosBrutoAlumnoMatriculaEntity> datosBrutosAlumnosMatriculados;
}