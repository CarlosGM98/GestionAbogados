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
	MenuItem mniAbogadoBaja = new MenuItem("Baja");
	MenuItem mniAbogadoEditar = new MenuItem("Editar");
	
	
	Menu mnuCasos = new Menu("Casos");
	MenuItem mniCasoAlta = new MenuItem("Alta");
	MenuItem mniCasoConsulta = new MenuItem("Consulta");
	MenuItem mniCasoBaja = new MenuItem("Baja");
	MenuItem mniCasoEditar = new MenuItem("Editar");
	
	
	Menu mnuClientes = new Menu("Clientes");
	MenuItem mniClienteAlta = new MenuItem("Alta");
	MenuItem mniClienteConsulta = new MenuItem("Consulta");
	MenuItem mniClienteBaja = new MenuItem("Baja");
	MenuItem mniClienteEditar = new MenuItem("Editar");
	
	
	Menu mnuAsignar = new Menu("Asignar");
	MenuItem mniAsignarAlta = new MenuItem("Alta");
	MenuItem mniAsignarConsulta = new MenuItem("Consulta");
	MenuItem mniAsignarBaja = new MenuItem("Baja");
	MenuItem mniAsignarEditar = new MenuItem("Editar");
	
	
	Menu mnuAyuda = new Menu("Ayuda");
	MenuItem mniAyuda = new MenuItem("Ayuda");
	
	
	Utilidades utilidades = new Utilidades(); //LOG
	

	char tipoUsuario;

	
	public MenuPrincipal(char t)
	{
		tipoUsuario = t;
		
		ventana.setLayout(new FlowLayout());
		
		
		ventana.addWindowListener(this);
		mniAbogadoConsulta.addActionListener(this);
		mniAbogadoAlta.addActionListener(this);
		mniAbogadoBaja.addActionListener(this);
		mniAbogadoEditar.addActionListener(this);
		
		mniCasoAlta.addActionListener(this);
		mniCasoConsulta.addActionListener(this);
		mniCasoBaja.addActionListener(this);
		mniCasoEditar.addActionListener(this);
		
		mniClienteAlta.addActionListener(this);
		mniClienteConsulta.addActionListener(this);
		mniClienteBaja.addActionListener(this);
		mniClienteEditar.addActionListener(this);
		
		mniAsignarAlta.addActionListener(this);
		mniAsignarConsulta.addActionListener(this);
		mniAsignarBaja.addActionListener(this);
		mniAsignarEditar.addActionListener(this);
		
		
		mniAyuda.addActionListener(this);  //Para el menú de ayuda
		

		
		//Menú para usuarios tipo A y tipo B
				

		
		mnuAbogados.add(mniAbogadoAlta);
		if (tipoUsuario=='A')
		{
			mnuAbogados.add(mniAbogadoConsulta);
			mnuAbogados.add(mniAbogadoBaja);
			mnuAbogados.add(mniAbogadoEditar);
		}
		barraMenu.add(mnuAbogados);
		
		
		mnuCasos.add(mniCasoAlta);
		if (tipoUsuario=='A')
		{
			mnuCasos.add(mniCasoConsulta);
			mnuCasos.add(mniCasoBaja);
			mnuCasos.add(mniCasoEditar);
		}
		barraMenu.add(mnuCasos);
		
		
		mnuClientes.add(mniClienteAlta);
		if (tipoUsuario=='A')
		{
			mnuClientes.add(mniClienteConsulta);
			mnuClientes.add(mniClienteBaja);
			mnuClientes.add(mniClienteEditar);
		}
		barraMenu.add(mnuClientes); 
		
		
		// La tabla con 2 FK
		mnuAsignar.add(mniAsignarAlta);
		if (tipoUsuario=='A')
		{
			mnuAsignar.add(mniAsignarConsulta);
			mnuAsignar.add(mniAsignarBaja);
			mnuAsignar.add(mniAsignarEditar);
		}
		barraMenu.add(mnuAsignar);
		
		
		mnuAyuda.add(mniAyuda);
		barraMenu.add(mnuAyuda);
		
		
		
		ventana.setMenuBar(barraMenu);
		
		ventana.setSize(300, 200);
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
		else if(e.getSource().equals(mniAbogadoBaja))
		{
			new BajaAbogado();
		}
		else if(e.getSource().equals(mniAbogadoEditar))
		{
			new EditarAbogado();
		}
		else if(e.getSource().equals(mniCasoAlta))
		{
			new AltaCasos();
		}
		else if(e.getSource().equals(mniCasoConsulta))
		{
			new ConsultaCasos();
		}
		else if(e.getSource().equals(mniCasoBaja))
		{
			new BajaCasos();
		}
		else if(e.getSource().equals(mniCasoEditar))
		{
			new EditarCasos();
		}
		else if(e.getSource().equals(mniClienteAlta))
		{
			new AltaClientes();
		}
		else if(e.getSource().equals(mniClienteConsulta))
		{
			new ConsultaClientes();
		}
		else if(e.getSource().equals(mniClienteBaja))
		{
			new BajaClientes();
		}
		else if(e.getSource().equals(mniClienteEditar))
		{
			new EditarClientes();
		}
		else if(e.getSource().equals(mniAsignarAlta))
		{
			new AltaAsignar();
		}
		else if(e.getSource().equals(mniAsignarConsulta))
		{
			new ConsultaAsignar();
		}
		else if(e.getSource().equals(mniAsignarBaja))
		{
			new BajaAsignar();
		}
		else if(e.getSource().equals(mniAsignarEditar))
		{
			new EditarAsignar();
		}
		else if(e.getSource().equals(mniAyuda))
		{
			new Ayuda();
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
		String usuario = null;
		utilidades.guardarLog("Ha salido");
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
