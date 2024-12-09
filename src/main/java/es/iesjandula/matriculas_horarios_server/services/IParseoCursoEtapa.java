package es.iesjandula.matriculas_horarios_server.services;

import java.util.Scanner;

import es.iesjandula.matriculas_horarios_server.utils.MatriculasHorariosServerException;

/**
 * Interfaz - IParseoCursoEtapa 
 * ----------------------------------------------------------------------------------------------------------------- 
 * Que define el contrato para el parseo de datos de CursoEtapa.
 * Esta interfaz especifica un método para procesar datos en bruto (generalmente desde un archivo o flujo de entrada)
 * y extraer objetos significativos de CursoEtapa para su uso posterior.
 * -----------------------------------------------------------------------------------------------------------------
 */
public interface IParseoCursoEtapa 
{
    /**
     * Parsea los datos proporcionados para extraer información relacionada con "CursoEtapa".
     * 
     * @param scanner 							 - Una instancia de {@link Scanner} que contiene los datos de entrada en bruto a procesar.
     * @throws MatriculasHorariosServerException - Si ocurre algún error durante el proceso de parseo.
     */
    public void parseoCursoEtapa(Scanner scanner) throws MatriculasHorariosServerException;
}
