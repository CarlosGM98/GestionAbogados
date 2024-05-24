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
import java.util.Arrays;

public class EditarCasos implements WindowListener, ActionListener, ItemListener
{
	Frame ventana = new Frame("Editar Casos");
	Label lblCaso = new Label("Caso:");
	Choice choCaso = new Choice();

	Label lblDescripcion = new Label("Descripción");
	TextField txtDescripcion = new TextField(20);
	Label lblFechaInicio = new Label("Fecha de inicio");
	TextField txtFechaInicio = new TextField(20);
	Label lblEstado = new Label("Estado");
	TextField txtEstado = new TextField(20);
	Label lblAbogado = new Label("Abogado");
	Choice choAbogado = new Choice();

	Button btnAceptar = new Button("Aceptar");

	Datos datos = new Datos(); // Instanciar objeto "Datos" para conectar con la clase "Datos"
	Dialog mensaje = new Dialog(ventana, "Mensaje", true);
	Label lblMensaje = new Label("Caso editado correctamente");

	Component[] componentes =
	{ lblDescripcion, txtDescripcion, lblFechaInicio, txtFechaInicio, lblEstado, txtEstado, lblAbogado, choAbogado,
			btnAceptar };

	
	public EditarCasos()
	{
		rellenarChoice(); // Llamo al método
		datos.conectar();
		String[] abogados = datos.rellenarChoiceAbogado();
		datos.desconectar();

		for (int i = 0; i < abogados.length; i++)
		{
			choAbogado.add(abogados[i]);
		}

		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblCaso);
		ventana.add(choCaso);

		ventana.add(lblDescripcion);
		ventana.add(txtDescripcion);
		ventana.add(lblFechaInicio);
		ventana.add(txtFechaInicio);
		ventana.add(lblEstado);
		ventana.add(txtEstado);
		ventana.add(lblAbogado);
		ventana.add(choAbogado);

		btnAceptar.addActionListener(this);
		choCaso.addItemListener(this);
		ventana.add(btnAceptar);

		ventana.setSize(320, 250);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

	}

	public void rellenarChoice() // Rellena el Choice de Casos
	{
		choCaso.removeAll(); // Borra todo

		datos.conectar();

		String[] elementos = datos.rellenarChoiceCasos();

		datos.desconectar();

		for (int i = 0; i < elementos.length; i++)
		{
			String elemento = elementos[i]; // Cojo elemento del array y lo llamo elemento
			choCaso.add(elemento); // Meto el elemento en el choice
		}
		choCaso.select(0); // Se muestre la primera opcion en el choice por defecto
		setEditarVisible(false); // Oculta los elementos

	}

	// Oculta o muestra los componentes del array en función de la variable booleana v
	public void setEditarVisible(boolean v)
	{
		for (int i = 0; i < componentes.length; i++)
		{
			componentes[i].setVisible(v);
		}
		ventana.setVisible(true); // Fuerza el refresco de la ventana para que se vean los cambios
	}
	
	
	public void rellenarTxt() //Lleno los campos de texto con los datos del caso separados por guiones
	{
		//Es un array de string con los datos de los casos
		String[] datosCaso = this.choCaso.getSelectedItem().split("-"); //Coge el elemento seleccionado y lo divide en base a los guiones
		String id = datosCaso[0];
		String descripcion = datosCaso[1];
		String fechaInicio = datosCaso[2] + "-" + datosCaso[3] + "-" + datosCaso[4]; //Año, mes, dia
		String estado = datosCaso[5];
		String idAbogadoFK = datosCaso[6];

		txtDescripcion.setText(descripcion);
		txtFechaInicio.setText(fechaInicio);
		txtEstado.setText(estado);
		choAbogado.select(getIndiceAbogado(idAbogadoFK)); //Seleciona al abogado correspondiente en el choice
	}
	
	
	public int getIndiceAbogado(String idAbogado)
	{ //Encuentra el índice de un abogado en el choAbogado basado en su ID
		int indice = 0;
		for (int i=1;i<choAbogado.getItemCount();i++) //Te dice el número de elementos en el choice
		{
			String datosAbogado=choAbogado.getItem(i); //Busco en que posición del choice está el abogado que pido
			String id = datosAbogado.split("-")[0];
			if(id.equals(idAbogado)) 
			{
				return i;
			}
		}
		return indice;
	}
	
	public void actionPerformed(ActionEvent actionEvent)
	{
		if (actionEvent.getSource().equals(btnAceptar))
		{
			String idCaso = choCaso.getSelectedItem().split("-")[0];
			String idAbogado = choAbogado.getSelectedItem().split("-")[0];
			datos.conectar();
			datos.editarCasos(idCaso, txtDescripcion.getText(), txtFechaInicio.getText(),
					txtEstado.getText(), idAbogado);
			datos.desconectar();
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
	public void itemStateChanged(ItemEvent e)
	{
		// this.setEditarVisible(this.choAbogado.getSelectedIndex()!=0);
		if (this.choCaso.getSelectedIndex() == 0) // La primera opción no sale nada
		{
			this.setEditarVisible(false);
		} else
		{
			this.setEditarVisible(true);
			this.rellenarTxt(); //Llena los campos de texto con los datos del caso seleccionado
		} 
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
		} else
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

	
	
	
}
