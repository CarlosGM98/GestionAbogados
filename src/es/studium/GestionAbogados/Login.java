package es.studium.GestionAbogados;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Login implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Login");
	Label lblUsuario = new Label("Usuario:");
	Label lblClave = new Label("Clave:");
	Label lblError = new Label("Credenciales Incorrectas");
	TextField txtUsuario = new TextField("",20); 
	TextField txtClave = new TextField("",20);
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	Dialog dlgError = new Dialog(ventana, "Error", true);

	Login()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(220, 190);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
		ventana.add(lblUsuario);
		ventana.add(txtUsuario);
		ventana.add(lblClave);
		txtClave.setEchoChar('*');
		ventana.add(txtClave);
		btnAceptar.addActionListener(this);
		ventana.add(btnAceptar);
		btnLimpiar.addActionListener(this);
		ventana.add(btnLimpiar);
		ventana.addWindowListener(this);	
	}

	public static void main(String[] args)
	{
		new Login();
	}

	public void windowActivated(WindowEvent windowEvent)
	{
	}
	public void windowClosed(WindowEvent windowEvent)
	{
	}
	public void windowClosing(WindowEvent windowEvent)
	{
		// Si se pulsa X del diálogo
		if (dlgError.isActive())
		{
			dlgError.setVisible(false);
		}
		else // Si se pulsa X del Frame
		{
			System.exit(0);
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
		if (actionEvent.getSource().equals(btnLimpiar))
		{
			txtUsuario.setText("");
			txtClave.setText("");
			txtUsuario.requestFocus();
		}
		else if (actionEvent.getSource().equals(btnAceptar))
		{
			// Conectar a la BD
			Datos datos = new Datos();
			
			if (datos.conectar() == true)
			{
				// Si OK, comprobar las credenciales
				String usuario = txtUsuario.getText();
				String clave = txtClave.getText();
				
				if (datos.comprobarCredenciales(usuario, clave) == true)
				{
					char tipoUsuario = datos.dameTipo(usuario); //Para login con tipo usuarios
					
					// Si OK, ir al Menú Principal
					//System.out.println(tipoUsuario);
					if (tipoUsuario == 'A')
					{
						new MenuPrincipal('A');
					}
					else if (tipoUsuario== 'B'){
						new MenuPrincipal('B');
					}
					
				}
				else
				{
					// Si no OK, mostrarDiálogo
					dlgError.setLayout(new FlowLayout());
					dlgError.setSize(220, 190);
					dlgError.setResizable(false);
					dlgError.setLocationRelativeTo(null);
					dlgError.add(lblError);
					dlgError.addWindowListener(this);
					dlgError.setVisible(true);
				}
			}
			else
			{
				System.out.println("Conexión rechazada");
			}
		}
	}
}