package es.iesjandula.matriculas_horarios_server.services;

import java.util.Scanner;

import es.iesjandula.matriculas_horarios_server.models.CursoEtapaEntity;
import es.iesjandula.matriculas_horarios_server.utils.MatriculasHorariosServerException;

public interface IParseoDatosBrutos 
{
	public void parseoDatosBrutos(Scanner scanner, CursoEtapaEntity cursoEtapaEntity) throws MatriculasHorariosServerException;
}
