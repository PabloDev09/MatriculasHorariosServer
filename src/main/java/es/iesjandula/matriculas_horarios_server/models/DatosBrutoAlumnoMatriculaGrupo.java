package es.iesjandula.matriculas_horarios_server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Datos_Bruto_Alumno_Matricula_Grupo")
public class DatosBrutoAlumnoMatriculaGrupo 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 50)
    private String nombre;
    
    @Column(length = 100)
    private String apellidos;
    
    @Column(length = 100)
    private String asignatura;

    @ManyToOne
    private CursoEtapaGrupo cursoEtapaGrupo;
}