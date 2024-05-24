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

public class BajaCasos implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Baja Casos");
	Label lblBaja = new Label("Elegir Caso a Borrar:");
	Choice choCaso = new Choice();
	Button btnAceptar = new Button("Aceptar");

	Dialog dlgSeguro = new Dialog(ventana, "¿Segur@", true);
	Label lblMensaje = new Label("¿Seguro de borrar?");
	Button btnSi = new Button("Si");
	Button btnNo = new Button("No");

	Datos datos = new Datos();
	
	public BajaCasos()
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblBaja);
		// Rellenar el Choice
		datos.conectar();
		String[] elementos = datos.rellenarChoiceCasos();
		for (String elemento : elementos)
		{
			choCaso.add(elemento);
		}
		datos.desconectar();
		ventana.add(choCaso);
		ventana.add(btnAceptar);
		btnAceptar.addActionListener(this);
		btnSi.addActionListener(this);		
		btnNo.addActionListener(this);
		ventana.setSize(350, 200);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(btnAceptar))
		{
			// Comprobarquetodo OK
			if (choCaso.getSelectedIndex() != 0)
			{
				// Sitodo OK, mostrardiálogoSeguroSí/No
				dlgSeguro.setLayout(new FlowLayout());
				dlgSeguro.addWindowListener(this);
				dlgSeguro.setSize(250, 70);
				dlgSeguro.setResizable(false);
				dlgSeguro.setLocationRelativeTo(null);
				dlgSeguro.add(lblMensaje);
				dlgSeguro.add(btnSi);
				dlgSeguro.add(btnNo);
				dlgSeguro.setVisible(true);
			}
		} else if (e.getSource().equals(btnSi))
		{
			datos.conectar();
			String elementoSeleccionado = choCaso.getSelectedItem().split("-")[0];
			datos.bajaCasos(elementoSeleccionado);
			datos.desconectar();
			dlgSeguro.setVisible(false);
			ventana.setVisible(false);
		} else if (e.getSource().equals(btnNo))
		{
			dlgSeguro.setVisible(false);
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
		if (dlgSeguro.isActive())
		{
			dlgSeguro.setVisible(false);
		} else
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
