package es.iesjandula.matriculas_horarios_server.services;

import java.util.Scanner;

import es.iesjandula.matriculas_horarios_server.utils.MatriculasHorariosServerException;

public interface IParseoCursoEtapa 
{
	public void parseaCursoEtapa(Scanner scanner) throws MatriculasHorariosServerException;
}
