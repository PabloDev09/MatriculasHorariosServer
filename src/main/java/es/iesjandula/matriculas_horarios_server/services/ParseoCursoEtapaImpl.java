package es.iesjandula.matriculas_horarios_server.services;


import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iesjandula.matriculas_horarios_server.models.CursoEtapaEntity;
import es.iesjandula.matriculas_horarios_server.models.ids.IdCursoEtapa;
import es.iesjandula.matriculas_horarios_server.repositories.ICursoEtapaRepository;
import es.iesjandula.matriculas_horarios_server.repositories.IDatosBrutoAlumnoMatriculaRepository;
import es.iesjandula.matriculas_horarios_server.utils.Constants;
import es.iesjandula.matriculas_horarios_server.utils.MatriculasHorariosServerException;


@Service
public class ParseoCursoEtapaImpl implements IParseoCursoEtapa
{
	@Autowired
	ICursoEtapaRepository iCursoEtapaRepository;
	
	@Autowired
	IDatosBrutoAlumnoMatriculaRepository iDatosBrutoAlumnoMatriculaRepository;
	

	
	@Override
	public void parseoCursoEtapa(Scanner scanner) throws MatriculasHorariosServerException 
	{	
		
		scanner.nextLine();
		while(scanner.hasNextLine()) {
			
			String linea = (String) scanner.nextLine();
			
			String[] valores = linea.split(Constants.CSV_DELIMITER);
			
			CursoEtapaEntity  cursoEtapaEntity = new CursoEtapaEntity();
			
			int curso = Integer.parseInt(valores[0]);
			
			String etapa = valores[1];	
			
			
			IdCursoEtapa idCursoEtapa = new IdCursoEtapa(curso, etapa);
			
			cursoEtapaEntity.setIdCursoEtapa(idCursoEtapa);
			
			
			this.iCursoEtapaRepository.saveAndFlush(cursoEtapaEntity);
		}
		
	}

}
