package es.iesjandula.matriculas_horarios_server.models.ids;

import es.iesjandula.matriculas_horarios_server.models.Profesor;
import es.iesjandula.matriculas_horarios_server.models.Reduccion;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class IdProfesorReduccion 
{
	@ManyToOne
	private Profesor profesor;
	
	@ManyToOne
	private Reduccion reduccion;
}
