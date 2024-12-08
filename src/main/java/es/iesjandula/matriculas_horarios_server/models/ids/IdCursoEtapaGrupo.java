package es.iesjandula.matriculas_horarios_server.models.ids;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class IdCursoEtapaGrupo implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(length = 1)
	private int curso;
	
	@Column(length = 20)
	private String etapa;
	
	@Column(length = 1)
	private char grupo;
	
}
