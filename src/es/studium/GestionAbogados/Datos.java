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
	

	Utilidades utilidades = new Utilidades();

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
	
	
	// Para las Consultas
	
	public String dameAbogados()
	{
		String contenido = "";
		String sentencia = "SELECT * FROM Abogados";                                           //ORDER BY 
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //Hace la consulta y no lo cambia
			rs = statement.executeQuery(sentencia); //Consultas y lo demas update  //Scroll desplazable y sensible a cambios
			while (rs.next())														// y result set refleja los cambios
			{
				contenido = contenido + rs.getString("nombreAbogado") + "--";
				contenido = contenido + rs.getString("apellidoAbogado") + "--";
				contenido = contenido + rs.getString("tarifaHoraAbogado") + "--";
				contenido = contenido + rs.getString("correoAbogado") + "\n";
			}
			utilidades.guardarLog("Consulta abogados");  //Log
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return contenido;
		
	}
	
	
	public String dameCasos()
	{
		String contenido = "";
		String sentencia = "SELECT idCaso, descripcionCaso, DATE_FORMAT(fechaInicioCaso, '%d/%m/%Y') AS 'fechaInicioCaso', estadoCaso, nombreAbogado, apellidoAbogado "
				+ "FROM casos "
				+ "JOIN abogados ON casos.idAbogadoFK = abogados.idAbogado;";                                           //ORDER BY 
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //Hace la consulta y no lo cambia
			rs = statement.executeQuery(sentencia); //Consultas y lo demas update  //Scroll desplazable y sensible a cambios
			while (rs.next())														// y result set refleja los cambios
			{
				contenido = contenido + rs.getString("descripcionCaso") + "--";
				contenido = contenido + rs.getString("fechaInicioCaso") + "--";
				contenido = contenido + rs.getString("estadoCaso") + "--";
				contenido = contenido + rs.getString("nombreAbogado") + "--";
				contenido = contenido + rs.getString("apellidoAbogado")+ "\n";
			}
			utilidades.guardarLog("Consulta casos");  //Log
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return contenido;
	}
	
	
	public String dameClientes()
	{
		String contenido = "";
		String sentencia = "SELECT * FROM clientes";                                           //ORDER BY 
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //Hace la consulta y no lo cambia
			rs = statement.executeQuery(sentencia); //Consultas y lo demas update  //Scroll desplazable y sensible a cambios
			while (rs.next())														// y result set refleja los cambios
			{
				contenido = contenido + rs.getString("nombreCliente") + "--";
				contenido = contenido + rs.getString("apellidoCliente") + "--";
				contenido = contenido + rs.getString("correoCliente") + "\n";
			}
			utilidades.guardarLog("Consulta clientes");  //Log
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return contenido;
	}
	
	

	public String dameAsignar()  //Para la tabla con 2 FK
	{
		String contenido = "";
		String sentencia = "SELECT idCasoFK, idCLienteFK, descripcionCaso, fechaInicioCaso, nombreCliente, apellidoCliente " 
				+ " FROM tener "
				+ " JOIN casos ON tener.idCasoFK = casos.idCaso "
				+ " JOIN clientes ON tener.idClienteFK = clientes.idCliente; ";
				                                           
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //Hace la consulta y no lo cambia
			rs = statement.executeQuery(sentencia); //Consultas y lo demas update  //Scroll desplazable y sensible a cambios
			while (rs.next())														// y result set refleja los cambios
			{
				contenido = contenido + rs.getString("descripcionCaso") + "--";
				contenido = contenido + rs.getString("fechaInicioCaso") + "--";
				contenido = contenido + rs.getString("nombreCliente") + "--";
				contenido = contenido + rs.getString("apellidoCliente")+ "\n";
			}
			utilidades.guardarLog("Consulta asignaciones");  //Log
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return contenido;
	}
	
	
	// Para las Altas

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
			utilidades.guardarLog("Alta abogado -- " + sentenciaSQL);  //Log
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		return altaCorrecta;
	}
	
	public boolean altaCasos(String descripcion, String fechaInicio, String estado, String abogado)
	{
		boolean altaCorrecta = true;
		String sentenciaSQL = "INSERT INTO casos VALUES (null,'" + descripcion + "', '" + fechaInicio + "', '" + estado + "' , '" + abogado + "');";
		System.out.println(sentenciaSQL);
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentenciaSQL);
			utilidades.guardarLog("Alta caso -- " + sentenciaSQL);  //Log
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		return altaCorrecta;
	}

	
	public boolean altaClientes(String nombre, String apellido, String correo)
	{
		boolean altaCorrecta = true;
		String sentenciaSQL = "INSERT INTO clientes VALUES (null,'" + nombre + "', '" + apellido + "', '" + correo + "');";
		System.out.println(sentenciaSQL);
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentenciaSQL);
			utilidades.guardarLog("Alta cliente -- " + sentenciaSQL);  //Log

		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		return altaCorrecta;
	}
	
	
	public boolean altaAsignar(String caso, String cliente)
	{
		boolean altaCorrecta = true;
		String sentenciaSQL = "INSERT INTO tener VALUES ('" + caso + "', '" + cliente + "');";
		System.out.println(sentenciaSQL);
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentenciaSQL);
			utilidades.guardarLog("Alta asignar -- " + sentenciaSQL);  //Log

		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		return altaCorrecta;
	}
	
	
	
	// Para las bajas 
	
	//Para la tabla abogados
	
	public String[] rellenarChoiceAbogado()
	{
		String elementosCadena = "Elegir un abogado...*";  //Primer elemento del Choice, predeterminado	
		String sentencia = "SELECT * FROM abogados";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idAbogado") + "-"
					+ rs.getString("nombreAbogado") + "-" + rs.getString("apellidoAbogado") + "-"
					+ rs.getString("tarifaHoraAbogado") + "-" + rs.getString("correoAbogado")+ "*";
			}
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena.split("\\*"); //Divido el array con \\
	}

	public void bajaAbogado(String elementoSeleccionado)
	{
		String sentencia = "DELETE FROM abogados WHERE idAbogado = " + elementoSeleccionado;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			utilidades.guardarLog("Baja abogado -- " + sentencia);  //Log
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		
	}

	
	//Para la tabla Casos
	
	
	public String[] rellenarChoiceCasos()
	{
		String elementosCadena = "Elegir un caso...*";
		String sentencia = "SELECT * FROM casos";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idCaso") + "-"
					+ rs.getString("descripcionCaso") + "-" + rs.getString("fechaInicioCaso") + "-"
					+ rs.getString("estadoCaso") + "-" + rs.getString("idAbogadoFK")+ "*";
			}
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena.split("\\*");
	}

	
	public void bajaCasos(String elementoSeleccionado)
	{
		String sentencia = "DELETE FROM casos WHERE idCaso = " + elementoSeleccionado;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			utilidades.guardarLog("Baja caso -- " + sentencia);  //Log
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		
	}

	
	// Para la tabla Clientes
	
	
	public String[] rellenarChoiceClientes()
	{
		String elementosCadena = "Elegir un cliente...*";
		String sentencia = "SELECT * FROM clientes";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idCliente") + "-"
					+ rs.getString("nombrecliente") + "-" + rs.getString("apellidoCliente") + "-"
					+ rs.getString("correoCliente") + "*";
			}
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena.split("\\*");
	}

	public void bajaClientes(String elementoSeleccionado)
	{
		String sentencia = "DELETE FROM clientes WHERE idCliente = " + elementoSeleccionado;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			utilidades.guardarLog("Baja cliente -- " + sentencia);  //Log
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		
	}
	
	
	// Para la tabla tener
	
	public String[] rellenarChoiceAsignar()
	{
		String elementosCadena = "Elegir una asignación...*";
		String sentencia = "SELECT idCasoFK, idCLienteFK, descripcionCaso, fechaInicioCaso, nombreCliente, apellidoCliente " 
				+ " FROM tener "
				+ " JOIN casos ON tener.idCasoFK = casos.idCaso "
				+ " JOIN clientes ON tener.idClienteFK = clientes.idCliente; ";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idCasoFK") + "-"
					+ rs.getString("idClienteFK") + "-" + rs.getString("descripcionCaso") + "-"
					+ rs.getString("fechaInicioCaso") + "-" + rs.getString("nombreCliente") + "-" 
					+ rs.getString("apellidoCliente") + "*";
			}
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena.split("\\*");
	}
	
	
	public void bajaAsignar(String elementoSeleccionado)
	{
		String sentencia = "DELETE FROM tener WHERE idCasoFk = " + elementoSeleccionado;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			utilidades.guardarLog("Baja asignar -- " + sentencia);  //Log
		} catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		
	}
	
	
	


	// Para las modificaciones
	
	public void editarAbogado(String id, String nombre, String apellidos, String tarifaAbogado, String correo)
	{
		String sentencia = "UPDATE abogados SET nombreAbogado='" + nombre + "', apellidoAbogado='" + apellidos + "', "
				+ "tarifaHoraAbogado='" + tarifaAbogado + "', correoAbogado='" + correo +  "' WHERE idAbogado='" + id + "'";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			utilidades.guardarLog("Editar abogado -- " + sentencia);  //Log
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void editarCasos(String idCaso, String descripcion, String fechaInicio, String estado, String abogado)
	{
		String sentencia = "UPDATE casos SET descripcionCaso='" + descripcion + "', fechaInicioCaso='" + fechaInicio + "', "
				+ "estadoCaso='" + estado + "', idAbogadoFK='" + abogado +  "' WHERE idCaso='" + idCaso + "'";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			utilidades.guardarLog("Editar caso -- " + sentencia);  //Log
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void editarCliente(String id, String nombre, String apellidos, String correo)
	{
		String sentencia = "UPDATE clientes SET nombreCliente='" + nombre + "', apellidoCliente='" + apellidos + "', correoCliente='" + correo +  "' WHERE idCliente='" + id + "'";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			utilidades.guardarLog("Editar cliente -- " + sentencia);  //Log
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void editarAsignar(String idCaso, String idCliente)
	{
		String sentencia = "UPDATE tener SET idCasoFK='" + idCaso + "', idClienteFK='" + idCliente 
				+ "' WHERE idCasoFK='" + idCaso + "' LIMIT 1"; //Limit hace que solo lo cambie 1 vez
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			utilidades.guardarLog("Editar asignar -- " + sentencia);  //Log
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
