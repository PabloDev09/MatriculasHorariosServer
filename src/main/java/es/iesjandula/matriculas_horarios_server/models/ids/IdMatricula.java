package es.iesjandula.matriculas_horarios_server.models.ids;

import java.io.Serializable;

import es.iesjandula.matriculas_horarios_server.models.Alumno;
import es.iesjandula.matriculas_horarios_server.models.Asignatura;
import es.iesjandula.matriculas_horarios_server.models.Profesor;
import es.iesjandula.matriculas_horarios_server.models.Reduccion;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class IdMatricula implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "asignatura_curso", referencedColumnName = "curso"),
        @JoinColumn(name = "asignatura_etapa", referencedColumnName = "etapa"),
        @JoinColumn(name = "asignatura_grupo", referencedColumnName = "grupo"),
        @JoinColumn(name = "asignatura_nombre", referencedColumnName = "nombre")
    })
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "alumno_id", referencedColumnName = "id")
    private Alumno alumno;

 
}
