package es.iesjandula.matriculas_horarios_server;

import java.io.File;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import es.iesjandula.matriculas_horarios_server.services.IParseoCursoEtapa;
import es.iesjandula.matriculas_horarios_server.services.IParseoDatosBrutos;
import es.iesjandula.matriculas_horarios_server.utils.Constants;

@SpringBootApplication
public class MatriculasHorariosServerApplication implements CommandLineRunner
{
	@Autowired
	IParseoCursoEtapa iParseoCursoEtapa;

	@Autowired
	IParseoDatosBrutos iParseoDatosBrutos;

	
	public static void main(String[] args) 
	{
		SpringApplication.run(MatriculasHorariosServerApplication.class, args);
	}

	@Transactional(readOnly = false)
	public void run(String... args) throws Exception 
	{
		// Parsear CSV CursoEtapa
		File file = new File(Constants.CSV_ROUTES + "cursos_etapas.csv");
		Scanner scanner = new Scanner(file);
		iParseoCursoEtapa.parseoCursoEtapa(scanner);
		
	}
}
