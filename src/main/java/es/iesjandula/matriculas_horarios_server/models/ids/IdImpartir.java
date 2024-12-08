package es.iesjandula.matriculas_horarios_server.models.ids;

import es.iesjandula.matriculas_horarios_server.models.Asignatura;
import es.iesjandula.matriculas_horarios_server.models.Profesor;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class IdImpartir 
{
	@ManyToOne
	private Asignatura asignatura;

	@ManyToOne
	private Profesor profesor;
}
