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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Impartir")
public class Impartir 
{
	@EmbeddedId
	private IdImpartir idImpartir;
	
	@MapsId(value = "asignatura")
	@ManyToOne
	private Asignatura asignatura;
	
	@MapsId(value = "profesor")
	@ManyToOne
	private Profesor profesor;
	
}
