package es.studium.GestionAbogados;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AltaCasos implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Alta Casos");
	
	Label lblDescripcion = new Label ("Descripción");
	TextField txtDescripcion = new TextField(20);
	Label lblFechaInicio = new Label ("Fecha de inicio");
	TextField txtFechaInicio = new TextField(20);
	Label lblEstado = new Label ("Estado");
	TextField txtEstado = new TextField(20);
	Label lblAbogado = new Label ("Abogado");
	Choice choAbogado = new Choice();
	
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	Datos datos = new Datos(); //Instanciar objeto "Datos" para conectar con la clase "Datos"
	Dialog mensaje = new Dialog(ventana, "Mensaje", true);
	Label lblMensaje = new Label("Caso creado correctamente");

	AltaCasos()
	{
		datos.conectar();
		String[] abogados = datos.rellenarChoiceAbogado();
		datos.desconectar();
		
		for(int i=0; i<abogados.length; i++)
		{
			choAbogado.add(abogados[i]);
		}
		
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		
		ventana.add(lblDescripcion);
		ventana.add(txtDescripcion);
		ventana.add(lblFechaInicio);
		ventana.add(txtFechaInicio);
		ventana.add(lblEstado);
		ventana.add(txtEstado);
		ventana.add(lblAbogado);
		ventana.add(choAbogado);
		
		btnAceptar.addActionListener(this);
		ventana.add(btnAceptar);
		btnLimpiar.addActionListener(this);
		ventana.add(btnLimpiar);
		
		ventana.setSize(320, 200);
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
			txtDescripcion.setText("");
			txtFechaInicio.setText("");
			txtEstado.setText("");
			choAbogado.select(0);
			txtDescripcion.requestFocus();
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
			String idAbogado=choAbogado.getSelectedItem().split("-")[0];
			datos.conectar(); 
			boolean altaCorrecta = datos.altaCasos(txtDescripcion.getText(), txtFechaInicio.getText(), txtEstado.getText(), idAbogado);
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
				lblMensaje.setText("Caso creado correctamente");
			}
			mensaje.add(lblMensaje);
			mensaje.setVisible(true);
			datos.desconectar();
		}
		else if (actionEvent.getSource().equals(btnLimpiar))
		{
			txtDescripcion.setText("");
			txtFechaInicio.setText("");
			txtEstado.setText("");
			choAbogado.select(0);
			txtDescripcion.requestFocus();
		}
	}
}
