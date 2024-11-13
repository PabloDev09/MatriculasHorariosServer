package es.iesjandula.matriculas_horarios_server.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "Profesor")
public class ProfesorEntity 
{
	@Id
	@Column(length = 100)
	private String email;
	
	@Column(length = 50, nullable = false)
	private String nombre;
	
	@Column(length = 100, nullable = false)
	private String apellidos;
	
	@ManyToOne
	@JoinColumn(name = "departamento_nombre")
	private DepartamentoEntity departamento;
	
	@OneToMany(mappedBy = "profesor")
	private List<ImpartirEntity> impartires;
	
	@OneToMany(mappedBy = "profesor")
	private List<ProfesorReduccionEntity> profesorReducciones;
}
