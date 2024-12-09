package es.iesjandula.matriculas_horarios_server.utils;

import java.io.File;

/**
 * Clase de utilidades - Constants
 * -----------------------------------------------------------------------------------------------------------------
 * Esta clase contiene constantes que se utilizan en diferentes partes de la aplicación para estandarizar valores 
 * comunes, como delimitadores de archivos CSV, rutas, y otras configuraciones generales.
 * -----------------------------------------------------------------------------------------------------------------
 */
public class Constants 
{
    /** 
     * Delimitador para separar valores en archivos CSV.
     * <p>Por defecto, se utiliza la coma (",").</p>
     */
    public static final String CSV_DELIMITER = ",";

    /** 
     * Ruta de acceso a la carpeta de recursos dentro de src/main/resources/.
     * <p>Se utiliza el separador de archivos del sistema operativo para garantizar la compatibilidad.</p>
     */
    public static final String CSV_ROUTES = "src" + File.separator + "main" + File.separator + "resources" + File.separator;

    /** 
     * Letra por defecto para el grupo .
     * <p>Indica el grupo base asignado, comenzando con 'A'.</p>
     */
    public static final char GROUP = 'A';
}
