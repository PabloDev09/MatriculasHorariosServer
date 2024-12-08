package es.iesjandula.matriculas_horarios_server.services;

import java.util.Scanner;

import es.iesjandula.matriculas_horarios_server.models.CursoEtapa;
import es.iesjandula.matriculas_horarios_server.utils.MatriculasHorariosServerException;

public interface IParseoDatosBrutos 
{
	public void parseoDatosBrutos(Scanner scanner, CursoEtapa cursoEtapa) throws MatriculasHorariosServerException;
}
