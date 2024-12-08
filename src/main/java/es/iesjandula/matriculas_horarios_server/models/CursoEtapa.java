package es.iesjandula.matriculas_horarios_server.models;

import java.util.List;

import es.iesjandula.matriculas_horarios_server.models.ids.IdCursoEtapa;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Curso_Etapa")
public class CursoEtapa 
{
    @EmbeddedId
    private IdCursoEtapa idCursoEtapa;

    @OneToMany(mappedBy = "cursoEtapa")
    private List<DatosBrutoAlumnoMatricula> datosBrutosAlumnosMatriculados;
}
