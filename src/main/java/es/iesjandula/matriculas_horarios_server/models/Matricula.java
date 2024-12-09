package es.iesjandula.matriculas_horarios_server.models;

import es.iesjandula.matriculas_horarios_server.models.ids.IdMatricula;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad - Matricula
 * -----------------------------------------------------------------------------------------------------------------
 * Esta clase representa la matrícula de un alumno en una asignatura específica. La relación entre las entidades se
 * maneja a través de un identificador compuesto {@link IdMatricula}, que está formado por varios atributos relacionados
 * con la asignatura y el alumno.
 * -----------------------------------------------------------------------------------------------------------------
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Matricula")
public class Matricula {

    /**
     * Identificador compuesto para la matrícula, que incluye la asignatura y el alumno.
     * La clave primaria se compone de diferentes atributos relacionados con la asignatura.
     */
    @EmbeddedId
    private IdMatricula id;

    /**
     * Asignatura en la que está matriculado el alumno. Relación de muchos a uno con la entidad {@link Asignatura}.
     * Se usa {@link JoinColumns} para mapear varias columnas que corresponden a los atributos de la asignatura.
     */
    @MapsId("asignatura")
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "asignatura_curso", referencedColumnName = "curso", insertable = false, updatable = false),
        @JoinColumn(name = "asignatura_etapa", referencedColumnName = "etapa", insertable = false, updatable = false),
        @JoinColumn(name = "asignatura_grupo", referencedColumnName = "grupo", insertable = false, updatable = false),
        @JoinColumn(name = "asignatura_nombre", referencedColumnName = "nombre", insertable = false, updatable = false)
    })
    private Asignatura asignatura;
}
