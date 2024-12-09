package es.iesjandula.matriculas_horarios_server.models;

import java.util.List;

import es.iesjandula.matriculas_horarios_server.models.ids.IdCursoEtapaGrupo;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad - CursoEtapaGrupo
 * -----------------------------------------------------------------------------------------------------------------
 * Esta clase representa la entidad "CursoEtapaGrupo" en la base de datos, que mapea la tabla "Curso_Etapa_Grupo".
 * <p>Utiliza la anotación {@link EmbeddedId} para definir una clave primaria compuesta a través de la clase {@link IdCursoEtapaGrupo}.</p>
 * -----------------------------------------------------------------------------------------------------------------
 * <ul>
 * <li>La clase tiene un identificador compuesto, representado por el objeto {@link IdCursoEtapaGrupo}, que es la clave primaria.</li>
 * <li>La clase tiene una relación de uno a muchos con la entidad {@link DatosBrutoAlumnoMatriculaGrupo}, representando los registros
 * de alumnos matriculados en el curso, etapa y grupo correspondientes.</li>
 * </ul>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Curso_Etapa_Grupo")
public class CursoEtapaGrupo 
{
    /**
     * Identificador compuesto que representa el curso, etapa y grupo.
     * <p>Este campo se mapea como la clave primaria compuesta de la entidad {@link CursoEtapaGrupo} mediante la clase {@link IdCursoEtapaGrupo}.</p>
     */
    @EmbeddedId
    private IdCursoEtapaGrupo idCursoEtapaGrupo;

    /**
     * Lista de registros de alumnos matriculados en el curso, etapa y grupo correspondientes.
     * <p>La clase tiene una relación de uno a muchos con la entidad {@link DatosBrutoAlumnoMatriculaGrupo}. Esta relación se mapea
     * a través de la propiedad "cursoEtapaGrupo" en la clase {@link DatosBrutoAlumnoMatriculaGrupo}.</p>
     */
    @OneToMany(mappedBy = "cursoEtapaGrupo")
    private List<DatosBrutoAlumnoMatriculaGrupo> datosBrutosAlumnosMatriculadosGrupo;
}
