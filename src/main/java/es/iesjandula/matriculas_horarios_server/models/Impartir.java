package es.iesjandula.matriculas_horarios_server.models;

import es.iesjandula.matriculas_horarios_server.models.ids.IdImpartir;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad - Impartir
 * -----------------------------------------------------------------------------------------------------------------
 * Esta clase representa la relación entre un {@link Asignatura} y un {@link Profesor} en el contexto de la asignación
 * de profesores a asignaturas. Utiliza un identificador compuesto {@link IdImpartir} como clave primaria para esta relación.
 * -----------------------------------------------------------------------------------------------------------------
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Impartir")
public class Impartir 
{
    /**
     * Identificador compuesto para la relación entre {@link Asignatura} y {@link Profesor}.
     * El identificador se compone de la {@link Asignatura} y el {@link Profesor} que imparten la asignatura.
     */
    @EmbeddedId
    private IdImpartir idImpartir;
    
    /**
     * Asignatura que está siendo impartida por el profesor.
     * Relación de muchos a uno con la entidad {@link Asignatura}.
     */
    @MapsId(value = "asignatura")
    @ManyToOne
    private Asignatura asignatura;
    
    /**
     * Profesor que está asignado para impartir la asignatura.
     * Relación de muchos a uno con la entidad {@link Profesor}.
     */
    @MapsId(value = "profesor")
    @ManyToOne
    private Profesor profesor;
}
