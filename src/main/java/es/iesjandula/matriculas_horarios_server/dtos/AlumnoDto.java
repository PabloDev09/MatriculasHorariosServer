package es.iesjandula.matriculas_horarios_server.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDto 
{
	private String nombre;
	private String apellidos;
}
