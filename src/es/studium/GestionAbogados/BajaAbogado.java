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



public class BajaAbogado implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Baja Abogados");
	Label lblBaja = new Label("Elegir Abogado a Borrar:");
	Choice choAbogado = new Choice();
	Button btnAceptar = new Button("Aceptar");

	Dialog dlgSeguro = new Dialog(ventana, "¿Segur@", true);
	Label lblMensaje = new Label("¿Seguro de borrar?");
	Button btnSi = new Button("Si");
	Button btnNo = new Button("No");

	Datos datos = new Datos();
	
	public BajaAbogado()
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblBaja);
		// Rellenar el Choice
		datos.conectar();
		String[] elementos = datos.rellenarChoiceAbogado();
		for (String elemento : elementos)
		{
			choAbogado.add(elemento);
		}
		datos.desconectar();
		ventana.add(choAbogado);
		ventana.add(btnAceptar);
		btnSi.addActionListener(this);		
		btnNo.addActionListener(this);
		btnAceptar.addActionListener(this);
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
			// Comprobar que el abogado seleccionado no es el mensaje "Selecciona un abogado"
			if (choAbogado.getSelectedIndex() != 0)
			{
				// Si todo OK, mostrardiálogoSeguroSí/No
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
		} else if (e.getSource().equals(btnSi))  //Si se selecciona un abogado y en el diálogo se pulsa si
		{
			datos.conectar();
			String elementoSeleccionado = choAbogado.getSelectedItem().split("-")[0];
			datos.bajaAbogado(elementoSeleccionado);
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
