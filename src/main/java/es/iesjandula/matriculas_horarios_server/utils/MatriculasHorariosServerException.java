package es.iesjandula.matriculas_horarios_server.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class MatriculasHorariosServerException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int code;
	private String msg;
	private Exception exception;
	
	public MatriculasHorariosServerException(String msg)
	{
		this.msg = msg;
		
		System.out.println(msg);
	}
	
	public MatriculasHorariosServerException(int code, String msg) 
	{
		this.code = code;
		this.msg = msg;
	}

	public Map<String,String> getBodyExceptionMessage()
	{
		Map<String,String> messageMap = new HashMap<String,String>() ;
		
		messageMap.put("code", String.valueOf(this.code)) ;
		messageMap.put("message", this.msg) ;
		
		if (this.exception != null)
		{
			// Dependencia de commons3lang
			String stackTrace = ExceptionUtils.getStackTrace(this.exception) ;
			messageMap.put("exception", stackTrace) ;
		}
		
		return messageMap ;
	}	
}
