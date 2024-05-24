package es.studium.GestionAbogados;

import java.io.IOException;

public class Ayuda
{
	public Ayuda()
	{
		try
		{
			Runtime.getRuntime().exec("hh.exe ayudaCrud.chm");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
