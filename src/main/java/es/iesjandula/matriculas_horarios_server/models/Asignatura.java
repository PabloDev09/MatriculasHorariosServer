package es.iesjandula.matriculas_horarios_server.models;

import java.util.List;

import es.iesjandula.matriculas_horarios_server.models.ids.IdAsignatura;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Asignatura")
public class Asignatura 
{
	@EmbeddedId
	private IdAsignatura id;
	
	@ManyToOne
	@JoinColumn(name="departamento_propietario", referencedColumnName = "nombre")
	private Departamento departamentoPropietario;
	
	@ManyToOne
	@JoinColumn(name="departamento_receptor", referencedColumnName = "nombre")
	private Departamento departamentoReceptor;
	
	@ManyToOne
	@JoinColumn(name="bloque_id", referencedColumnName="id")
	private Bloque bloqueId;
	
	@OneToMany(mappedBy = "asignatura")
	private List<Matricula> matriculas;
}	
