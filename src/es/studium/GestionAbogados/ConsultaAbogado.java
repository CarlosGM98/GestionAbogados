package es.studium.GestionAbogados;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ConsultaAbogado implements WindowListener, ActionListener
{
	Frame ventana = new Frame ("Listado abogados");
	
	Label lblNombre = new Label ("Nombre      ");
	Label lblApellido = new Label ("     Apellido     ");
	Label lblTarifa = new Label ("     Tarifa     ");
	Label lblCorreo = new Label ("     Correo");
	TextArea listado = new TextArea(8,40);
	Button btnVolver = new Button("Volver");
	Button btnExportar = new Button("Exportar a PDF");
	Datos datos = new Datos();
	
	ConsultaAbogado()
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		btnVolver.addActionListener(this);
		
		//Conectar BD
		datos.conectar();
		listado.append(datos.dameAbogados());
		//listado.setEnabled(false); //Para bloquear la escritura en el textArea
		datos.desconectar();
		
		ventana.add(lblNombre);
		ventana.add(lblApellido);
		ventana.add(lblTarifa);
		ventana.add(lblCorreo);
		
		ventana.add(listado);
		ventana.add(btnVolver);
		ventana.add(btnExportar);
		
		ventana.setSize(400, 250);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		ventana.setVisible(false);
		
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

	@Override
	public void actionPerformed(ActionEvent e)
	{
		ventana.setVisible(false);
		
	}
}
