package es.studium.GestionAbogados;

import java.awt.Button;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class ConsultaAsignar implements WindowListener, ActionListener
{
	Frame ventana = new Frame ("Listado asignar");
	
	Label lblDescripcion = new Label ("Descripción    ");
	Label lblFechaInicio = new Label ("   Fecha Inicio    ");
	Label lblNombreCliente = new Label ("   Nombre Cliente   ");
	Label lblApellidoCliente = new Label ("   Apellido Cliente");
	
	TextArea listado = new TextArea(8,50);
	
	Button btnVolver = new Button("Volver");
	Button btnExportar = new Button("Exportar a PDF");
	
	Datos datos = new Datos();
	
	String exportar = "ConsultaCasos.pdf";
	
	
	public ConsultaAsignar()
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		btnVolver.addActionListener(this);
		btnExportar.addActionListener(this);
		
		
		datos.conectar();
		listado.append(datos.dameAsignar());
		listado.setEditable(false); //Para bloquear la escritura en el textArea
		datos.desconectar();
		
		ventana.add(lblDescripcion);
		ventana.add(lblFechaInicio);
		ventana.add(lblNombreCliente);
		ventana.add(lblApellidoCliente);
		
		ventana.add(listado);
		
		ventana.add(btnVolver);
		ventana.add(btnExportar);
		
		ventana.setSize(450, 250);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(btnExportar))
		{
			try
			{
				datos.conectar();
				//Initialize PDF writer
				PdfWriter writer = new PdfWriter(exportar);
				//Initialize PDF document
				PdfDocument pdf = new PdfDocument(writer);
				// Initialize document
				Document document = new Document(pdf);
				// Create a pdfFOnt
				PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
				//Add paragraph to the document
				document.add(new Paragraph(datos.dameAsignar()).setFont(font));
				//Close document
				document.close();
				// Open the new PDF document just created
				Desktop.getDesktop().open(new File(exportar));
				datos.desconectar();
			} catch (IOException ioe)
			{
			}
		}
		else if(e.getSource().equals(btnVolver))
		{
			ventana.setVisible(false);
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
}
