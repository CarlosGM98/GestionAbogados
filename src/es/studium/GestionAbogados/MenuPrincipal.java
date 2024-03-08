package es.studium.GestionAbogados;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuPrincipal implements WindowListener, ActionListener
{
	Frame ventana = new Frame ("Principal");
	MenuBar barraMenu = new MenuBar();
	
	Menu mnuAbogados = new Menu("Abogados");
	MenuItem mniAbogadoAlta = new MenuItem("Alta");
	MenuItem mniAbogadoConsulta = new MenuItem("Consulta");

	char tipoUsuario;

	
	public MenuPrincipal(char t)
	{
		tipoUsuario = t;
		
		ventana.setLayout(new FlowLayout());
		
		
		ventana.addWindowListener(this);
		mniAbogadoConsulta.addActionListener(this);
		mniAbogadoAlta.addActionListener(this);

		

		barraMenu.add(mnuAbogados);
		
		//Menú para usuarios tipo A y tipo B
				
		mnuAbogados.add(mniAbogadoAlta);
		if (tipoUsuario=='A')
		{
			mnuAbogados.add(mniAbogadoConsulta);		
		}
		
		barraMenu.add(mnuAbogados); 
		
		
		ventana.setMenuBar(barraMenu);
		
		ventana.setSize(250, 200);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);//Centrar la ventana en la pantalla
		ventana.setVisible(true);
		

	}
	
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(mniAbogadoAlta))
		{
			new AltaAbogado();
		}
		else if(e.getSource().equals(mniAbogadoConsulta))
		{
			new ConsultaAbogado();
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
		System.exit(0);
		
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
