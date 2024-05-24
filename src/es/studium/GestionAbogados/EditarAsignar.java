package es.studium.GestionAbogados;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EditarAsignar implements WindowListener, ActionListener, ItemListener
{
	Frame ventana = new Frame("Editar Asignar");
	Label lblCaso = new Label("Caso:");
	Choice choCaso = new Choice();
	
	Label lblCliente = new Label("Cliente");
	Choice choCliente = new Choice();
	
	Button btnAceptar = new Button("Aceptar");
	
	Datos datos = new Datos();
	
	Dialog mensaje = new Dialog(ventana, "Modificacion", true);
	Label lblMensaje = new Label("Modificación realizada con exito");
	
	Component[] componentes =
		{ lblCliente, choCliente, btnAceptar};
	
	
	
	
	public EditarAsignar()
	{
		rellenarChoice(); // Llamo a los métodos para rellenar los choices de Clientes y Casos respectivamente
		rellenarChoCaso();
		

		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblCaso);
		ventana.add(choCaso);

		ventana.add(lblCliente);
		ventana.add(choCliente);

		btnAceptar.addActionListener(this);
		choCaso.addItemListener(this);
		choCliente.addItemListener(this);
		ventana.add(btnAceptar);

		ventana.setSize(320, 250);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
	}


	private void rellenarChoCaso()  //Rellenar el primer Choice que es el de Caso
	{
		choCaso.removeAll();
		datos.conectar();
		String[] casos = datos.rellenarChoiceAsignar();
		
		datos.desconectar();

		for (int i = 0; i < casos.length; i++)
		{
			choCaso.add(casos[i]);
		}
	}
	
	
	public void rellenarChoice() // Rellena el Choice de Cliente
	{
		choCliente.removeAll(); // Borra todo

		datos.conectar();

		String[] elementos = datos.rellenarChoiceClientes();

		datos.desconectar();

		for (int i = 0; i < elementos.length; i++)
		{
			String elemento = elementos[i]; // Cojo elemento del array y lo llamo elemento
			choCliente.add(elemento); // Meto el elemento en el choice
		}
		choCliente.select(0); // Se muestre la primera opcion en el choice por defecto
		setEditarVisible(false); // Oculta los elementos
	}
	
	//Al seleccionar un caso selecciona automaticamente al cliente relacionado con el caso
	public void rellenarTxt()
	{
		// Es un array de string con los datos del caso seleccionado
		String[] datosAsignar = this.choCaso.getSelectedItem().split("-"); //Coge el elemento seleccionado y lo divide en base a los guiones
		
		//String idCasoFK = datosAsignar[0];
		String idClienteFK = datosAsignar[1];

		//Selecciona al cliente del caso seleccionado
		choCliente.select(getIndiceCliente(idClienteFK));
	}
	
	
	//Oculta o muestra los componentes del array en función de la variable booleana v
	public void setEditarVisible(boolean v)
	{
		for (int i = 0; i < componentes.length; i++)
		{
			componentes[i].setVisible(v);
		}
		ventana.setVisible(true); // Fuerza el refresco de la ventana para que se vean los cambios
	}
	
	
	
	//Encuentra el índice de un cliente en el choCliente basado en su ID
	public int getIndiceCliente(String idCliente)  
	{
		int indice = 0;
		for (int i=1;i<choCliente.getItemCount();i++) //Te dice el número de elementos en el choice
		{
			String datosAsignar=choCliente.getItem(i); //Buscamos en que posición del choice está el cliente
			String id = datosAsignar.split("-")[0];
			if(id.equals(idCliente)) 
			{
				return i;
			}
		}
		return indice;
	}
	
	
	
	//Maneja los cambios en la selección del caso en choCaso, muestra los componentes y llena los casos
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		if (e.getSource().equals(choCaso)) //Si me mandas la señal desde aqui, hazme esto
		{
			if (this.choCaso.getSelectedIndex() == 0) // La primera opción no sale nada
			{
				this.setEditarVisible(false);
			} else
			{
				this.setEditarVisible(true);
				this.rellenarTxt(); //Llena los campos de texto con los datos del caso seleccionado
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{    //Actualiza la signación del caso al cliente en la BD
		if (actionEvent.getSource().equals(btnAceptar))
		{
			String idCaso = choCaso.getSelectedItem().split("-")[0];
			String idCliente = choCliente.getSelectedItem().split("-")[0];
			System.out.println("idCaso = " + idCaso + " idCliente= " + idCliente); //Syso
			datos.conectar();
			datos.editarAsignar(idCaso, idCliente);
			datos.desconectar();
			
			rellenarChoCaso();
			rellenarChoice();
			this.setEditarVisible(false);
			
			mensaje.setLayout(new FlowLayout());
			mensaje.addWindowListener(this);
			mensaje.setSize(250, 70);
			mensaje.setResizable(false);
			mensaje.setLocationRelativeTo(null);
			lblMensaje.setText("Caso editado correctamente");
			mensaje.add(lblMensaje);
			mensaje.setVisible(true);
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
