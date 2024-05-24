package es.studium.GestionAbogados;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilidades
{
	static String nombreUsuario = ""; //Variable que no va a cambiar durante la ejecución del programa
	static char tipo = ' ';
	
	public void guardarLog(String mensaje)
	{
		String tipoUsuario;
		if (tipo == 'A')
		{
			tipoUsuario = "Administrador";
		} else
		{
			tipoUsuario = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			// Abrir fichero.log en modo añadir
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			// Meter una nueva línea
			salida.println("[" + simpleDateFormat.format(fecha) + "][" + nombreUsuario + "][" + tipoUsuario + "][" + mensaje + "]");

			// Cerrar el fichero
			salida.close();
			bw.close();
			fw.close();
		} catch (IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	

	/*public String fechaAmericana(String fecha)
	{
		// 21/02/2024 --> 2024/02/21
		String[] fechaCambiada = fecha.split("/");

		return fechaCambiada[2] + "-" + fechaCambiada[1] + "-" + fechaCambiada[0];
	}

	public String fechaEuropea(String fecha)
	{
		// 2024/02/21 --> 21/02/2024
		String[] fechaCambiada = fecha.split("-");

		return fechaCambiada[2] + "-" + fechaCambiada[1] + "-" + fechaCambiada[0];
	}*/

}