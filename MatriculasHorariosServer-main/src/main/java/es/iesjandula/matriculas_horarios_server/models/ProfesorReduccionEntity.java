package es.iesjandula.matriculas_horarios_server.models;

import es.iesjandula.matriculas_horarios_server.models.ids.IdProfesorReduccion;
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
@Table(name = "Profesor_Reduccion")
public class ProfesorReduccionEntity 
{
	@EmbeddedId
	private IdProfesorReduccion idProfesorReduccion;
	
	@MapsId(value = "profesor")
	@ManyToOne
	private ProfesorEntity profesor;
	
	@MapsId(value = "reduccion")
	@ManyToOne
	private ReduccionEntity reduccion;

}
