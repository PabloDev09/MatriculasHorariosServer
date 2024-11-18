package es.iesjandula.matriculas_horarios_server.models.ids;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class IdCursoEtapa implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private int curso;
	
	@Column(length = 50)
	private String etapa;
}
