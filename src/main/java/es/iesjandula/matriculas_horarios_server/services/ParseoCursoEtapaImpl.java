package es.iesjandula.matriculas_horarios_server.services;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.matriculas_horarios_server.repositories.ICursoEtapaRepository;
import es.iesjandula.matriculas_horarios_server.utils.MatriculasHorariosServerException;

@Service
public class ParseoCursoEtapaImpl implements IParseoCursoEtapa
{
	@Autowired
	ICursoEtapaRepository iCursoEtapaRepository;
	
	@Override
	public void parseaCursoEtapa(Scanner scanner) throws MatriculasHorariosServerException 
	{	
		
	}

}
