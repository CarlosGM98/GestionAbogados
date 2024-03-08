package es.studium.GestionAbogados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Datos
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/gestionAbogados";
	String login = "administrativo";
	String password = "Studium2023;";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	Datos()
	{

	}

	public boolean conectar()
	{
		boolean conexionCorrecta = true;
		//Cargar el Driver
		try
		{
			Class.forName(driver);
		} catch (ClassNotFoundException e)
		{
			System.out.println("Se ha producido un error al cargar el Driver");
			conexionCorrecta = false;
		}
		//Establecer la conexión con la base dedatos
		try
		{
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e)
		{
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			conexionCorrecta = false;
		}
		return conexionCorrecta;
	}

	public boolean comprobarCredenciales(String usuario, String clave)
	{
		boolean credencialesCorrectas = true;
		String sentencia = "SELECT * FROM usuarios " + "WHERE nombreUsuario='" + usuario + "' "
				+ "AND claveUsuario = ('" + clave + "');";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			if (!rs.next())
			{
				// Credenciales incorrectas
				credencialesCorrectas = false;
			}
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return credencialesCorrectas;
	}

	public void desconectar()
	{
		try
		{
			statement.close();
			connection.close();
		} catch (SQLException e)
		{
			System.out.println("Error al cerrar " + e.toString());
		}

	}

	public String dameAbogados()
	{
		String contenido = "";
		String sentencia = "SELECT * FROM Abogados";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				contenido = contenido + rs.getString("nombreAbogado") + "--";
				contenido = contenido + rs.getString("apellidoAbogado") + "--";
				contenido = contenido + rs.getString("tarifaHoraAbogado") + "--";
				contenido = contenido + rs.getString("correoAbogado") + "\n";
			}
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return contenido;
	}

	public boolean altaAbogado(String nombre, String apellido, String tarifa, String correo)
	{
		boolean altaCorrecta = true;
		String sentenciaSQL = "INSERT INTO abogados VALUES (null,'" + nombre + "', '" + apellido + "', " + tarifa
				+ " , '" + correo + "');";
		System.out.println(sentenciaSQL);
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentenciaSQL);

		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		return altaCorrecta;
	}

	public char dameTipo(String usuario)
	{
		String sentencia = "SELECT tipoUsuario FROM usuarios WHERE nombreUsuario = '"+usuario + "';";
		
		//lanzar sentencia
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			
			if (rs.next())
			{
				if (rs.getString("tipoUsuario").charAt(0)=='A')
				{
					return 'A';
				}

			}

		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia de comprobacion de USUARIO:" + e.toString());
		}
		
		
		return 'B';
	}

	
}
