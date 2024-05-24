package es.studium.GestionAbogados;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AltaAsignar implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Alta Asignar");
	
	Label lblCaso = new Label ("Dime un caso");
	Choice choCaso = new Choice();
	Label lblCliente = new Label ("Dime un cliente");
	Choice choCliente = new Choice();
	
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	
	Datos datos = new Datos(); //Instanciar objeto "Datos" para conectar con la clase "Datos"
	Dialog mensaje = new Dialog(ventana, "Mensaje", true);
	Label lblMensaje = new Label("Caso creado correctamente");

	
	public AltaAsignar()
	{
		datos.conectar();
		String[] casos = datos.rellenarChoiceCasos();
		String[] cliente = datos.rellenarChoiceClientes();
		datos.desconectar();
		
		for(int i=0; i<casos.length; i++)
		{
			choCaso.add(casos[i]);
		}
		
		for(int i=0; i<cliente.length; i++)
		{
			choCliente.add(cliente[i]);
		}
		
		
		
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		
		
		ventana.add(lblCaso);
		ventana.add(choCaso);
		ventana.add(lblCliente);
		ventana.add(choCliente);
		
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		ventana.add(btnAceptar);
		ventana.add(btnLimpiar);
		
		ventana.setSize(320, 200);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		if (actionEvent.getSource().equals(btnAceptar))
		{
			// Saco los ID de los elementos seleccionados en los choices
			String idCaso=choCaso.getSelectedItem().split("-")[0];  
			String idCliente=choCliente.getSelectedItem().split("-")[0];
			datos.conectar(); 
			// Realizo la asignación pasandole los ID sacados como parámetros
			boolean altaCorrecta = datos.altaAsignar(idCaso, idCliente);
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
				lblMensaje.setText("Asignación creada correctamente");
			}
			mensaje.add(lblMensaje);
			mensaje.setVisible(true);
			datos.desconectar();
		}
		else if (actionEvent.getSource().equals(btnLimpiar))
		{
			choCaso.select(0);
			choCliente.select(0);
		}
		
	}


	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent e)
	{
		if (mensaje.isActive())
		{
			mensaje.setVisible(false);
			choCaso.select(0);
			choCliente.select(0);
		}
		else
		{
			ventana.setVisible(false);
		}
		
	}


	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
