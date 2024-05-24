package es.studium.GestionAbogados;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EditarClientes implements WindowListener, ActionListener, ItemListener
{
	Frame ventana = new Frame("Editar Clientes");
	Label lblEditar = new Label("Elegir Cliente a Editar:");
	Choice choCliente = new Choice();

	Dialog dlgConfirmacion = new Dialog(ventana, "Modificacion", true);
	Label lblDialog = new Label("Modificación realizada con exito");

	Datos datos = new Datos();
	
	
	Label lblNombre = new Label ("Nombre");
	TextField txtNombre = new TextField(20);
	Label lblApellido = new Label ("Apellido");
	TextField txtApellido = new TextField(20);
	Label lblCorreo = new Label ("Correo");
	TextField txtCorreo = new TextField(20);
	
	Button btnEditar = new Button("Editar");

	// Clase padre del resto de componentes de la libreria AWT, Array de componentes
	Component[] componentes =
	{ 
			lblNombre, txtNombre, lblApellido, txtApellido, lblCorreo, txtCorreo, btnEditar 
	};
	
	public EditarClientes() 
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblEditar);
		ventana.add(choCliente);
		
		rellenarChoice(); //Metodo que rellena el choice
		
		// Añado los componentes a la ventana
		for (int i = 0; i < componentes.length; i++)
		{
			ventana.add(componentes[i]);
		}

		setEditarVisible(false); //Metodo que oculta los componentes del array

		choCliente.addItemListener(this);

		btnEditar.addActionListener(this); 

		ventana.setSize(300, 250);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

		dlgConfirmacion.setLayout(new FlowLayout());
		dlgConfirmacion.setSize(200, 100);
		dlgConfirmacion.setResizable(false);
		dlgConfirmacion.setLocationRelativeTo(null);
		dlgConfirmacion.add(lblDialog);
		dlgConfirmacion.setVisible(false);
		dlgConfirmacion.addWindowListener(this);
	}
	
	
	public void rellenarChoice() // Rellena el Choice
	{
		choCliente.removeAll(); //Borra todo
		
		datos.conectar();

		String[] elementos = datos.rellenarChoiceClientes();
		
		datos.desconectar();
		
		for (int i=0; i<elementos.length; i++) 
		{
			String elemento=elementos[i]; //Cojo elemento del array y lo llamo elemento
			choCliente.add(elemento);     //Meto el elemento en el choice
		}
		choCliente.select(0); //Se muestre la primera opcion en el choice por defecto
		setEditarVisible(false); //Se muestre la primera opcion en el choice por defecto
	}
	
	
	//Oculta o muestra los componentes del array en función de la variable booleana v
	public void setEditarVisible(boolean v)
		{
			for (int i = 0; i < componentes.length; i++)
			{
				componentes[i].setVisible(v);
			}
			ventana.setVisible(true); //Fuerza el refresco de la ventana para que se vean los cambios
		}
		
	
	//Coge el cliente seleccionado del choice y lo introduce en los textFields para que se puedan editar
	public void rellenarTxt()
		{
			//Es un array de string con los datos de los clientes
			String[] datosCliente = this.choCliente.getSelectedItem().split("-"); //Coge el elemento seleccionado y lo divide en base a los guiones
			String id = datosCliente[0];
			String nombre = datosCliente[1];
			String apellidos = datosCliente[2];
			String correo = datosCliente[3];

			txtNombre.setText(nombre);
			txtApellido.setText(apellidos);
			txtCorreo.setText(correo);
		}
		
		
		
		

	@Override
	public void itemStateChanged(ItemEvent e)
	{
		if (this.choCliente.getSelectedIndex() == 0) // La primera opción no sale nada
		{
			this.setEditarVisible(false);
		} else
		{
			this.setEditarVisible(true);
			this.rellenarTxt(); //Llena los campos de texto con los datos del caso seleccionado
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//Acción que realiza el botón de editar
		String[] datosCliente = this.choCliente.getSelectedItem().split("-");
		String id = datosCliente[0]; //Lo unico que no varia es el id, el resto de datos se obtienen de los textFields
		String nombre = txtNombre.getText();
		String apellidos = txtApellido.getText();
		String correo = txtCorreo.getText();
		datos.conectar();
		this.datos.editarCliente(id, nombre, apellidos, correo); //Mando a actualizar el cliente
		datos.desconectar();
		dlgConfirmacion.setVisible(true); //Muestro el dialogo
		rellenarChoice(); //Refresco el choice para mostrar los cambios
		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		if (dlgConfirmacion.isActive())
		{
			dlgConfirmacion.setVisible(false);
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
