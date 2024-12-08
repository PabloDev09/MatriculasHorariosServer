package es.iesjandula.matriculas_horarios_server.models;


import es.iesjandula.matriculas_horarios_server.models.ids.IdMatricula;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "Matricula")
public class Matricula {

    @EmbeddedId
    private IdMatricula id;

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
