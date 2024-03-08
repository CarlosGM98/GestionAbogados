package es.studium.GestionAbogados;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AltaAbogado implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Listado abogados");
	
	Label lblNombre = new Label ("Nombre");
	TextField txtNombre = new TextField(20);
	Label lblApellido = new Label ("Apellido");
	TextField txtApellido = new TextField(20);
	Label lblTarifa = new Label ("Tarifa");
	TextField txtTarifa = new TextField(20);
	Label lblCorreo = new Label ("Correo");
	TextField txtCorreo = new TextField(20);
	
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	Datos datos = new Datos(); //Instanciar objeto "Datos" para conectar con la clase "Datos"
	Dialog mensaje = new Dialog(ventana, "Mensaje", true);
	Label lblMensaje = new Label("Abogado creado correctamente");

	AltaAbogado()
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblApellido);
		ventana.add(txtApellido);
		ventana.add(lblTarifa);
		ventana.add(txtTarifa);
		ventana.add(lblCorreo);
		ventana.add(txtCorreo);
		
		btnAceptar.addActionListener(this);
		ventana.add(btnAceptar);
		btnLimpiar.addActionListener(this);
		ventana.add(btnLimpiar);
		
		ventana.setSize(300, 200);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

	}
	public void windowActivated(WindowEvent windowEvent)
	{
	}

	public void windowClosed(WindowEvent windowEvent)
	{
	}

	public void windowClosing(WindowEvent windowEvent)
	{
		if (mensaje.isActive())
		{
			mensaje.setVisible(false);
			txtNombre.setText("");
			txtApellido.setText("");
			txtTarifa.setText("");
			txtCorreo.setText("");
			txtNombre.requestFocus();
		}
		else
		{
			ventana.setVisible(false);
		}
	}
	public void windowDeactivated(WindowEvent windowEvent)
	{
	}
	public void windowDeiconified(WindowEvent windowEvent)
	{
	}
	public void windowIconified(WindowEvent windowEvent)
	{
	}
	public void windowOpened(WindowEvent windowEvent)
	{
	}
	public void actionPerformed(ActionEvent actionEvent)
	{
		if (actionEvent.getSource().equals(btnAceptar))
		{
			datos.conectar();
			boolean altaCorrecta = datos.altaAbogado(txtNombre.getText(), txtApellido.getText(), txtTarifa.getText(), txtCorreo.getText());
			mensaje.setLayout(new FlowLayout());
			mensaje.addWindowListener(this);
			mensaje.setSize(250, 70);
			mensaje.setResizable(false);
			mensaje.setLocationRelativeTo(null);
			if (altaCorrecta == false)
			{
				lblMensaje.setText("Se ha producido un error");
			}
			else
			{
				lblMensaje.setText("Abogado creado correctamente");
			}
			mensaje.add(lblMensaje);
			mensaje.setVisible(true);
			datos.desconectar();
		}
		else if (actionEvent.getSource().equals(btnLimpiar))
		{
			txtNombre.setText("");
			txtApellido.setText("");
			txtTarifa.setText("");
			txtCorreo.setText("");
			txtNombre.requestFocus();
		}
	}
}