package es.iesjandula.matriculas_horarios_server.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Departamento")
public class Departamento
{
	@Id
	@Column(length = 100)
	private String nombre;
	
    @OneToMany(mappedBy = "departamentoPropietario")
    private List<Asignatura> asignaturasPropietarias;
    
    @OneToMany(mappedBy = "departamentoReceptor")
    private List<Asignatura> asignaturasReceptores;
    
    @OneToMany(mappedBy = "departamento")
    private List<Profesor> profesores;
}
