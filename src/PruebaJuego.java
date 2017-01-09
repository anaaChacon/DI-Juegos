import java.awt.Color; 
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


public class PruebaJuego extends JFrame implements  ActionListener, ItemListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menu;
	private JMenu menuInterior;
	private JMenuItem menuContenedor1, menuContenedor2;
	private JTabbedPane tabPane;
	private int numRandon, numAleatorio;
	private int posicion, posicionNumero;
	private JTextField letra1, campoNumerico;
	private JButton letraNueva, letraNueva2, botonVocales, botonConsonante;

	JButton botonVerificar;

	private JButton botonReordenar;  

	private JButton miboton;

	private JButton miboton2;

	private JButton nuevoBoton;

	private JButton botonRevobinar;
	private JButton numero, botonIgual, suma, resta, div, multi, borrarTodo, borrarNumero, miboton3;
	private String [] vocales;
	private String [] consonantes;
	private JPanel letras, panelCifras4;
	private JComboBox<String> comboLista; 
	private String anyadir;
	private ArrayList<String> arrayList;
	@SuppressWarnings("unused")
	private ArrayList<String> arrayList1;
	private JLabel resultado;
	private int resultadoFinal, operacion, num1, num2;
	private Reloj tiempo, time;
	
	private int numeroClicado, palabraExiste;
	private ArrayList<JButton>arrayBotones;
	private File cargafichero;
	JScrollPane scroll2;
	JPanel botonesLetras;
	
	public JPanel getBotonesLetras() {
		return botonesLetras;
	}

	public void setBotonesLetras(JPanel botonesLetras) {
		this.botonesLetras = botonesLetras;
	}

	public JScrollPane getScroll2() {
		return scroll2;
	}

	public void setScroll2(JScrollPane scroll2) {
		this.scroll2 = scroll2;
	}

	public static void ComprobarPalabra(String anyadir, JTextField letra1, int numeroClicado, JComboBox<String>comboLista){
		
		try {
			File fichero = new File("diccionario.txt");
			FileReader lecturaFichero = new FileReader(fichero);
			BufferedReader lectura = new BufferedReader(lecturaFichero);
			String linea;
			anyadir = letra1.getText().toString();
			
			//comprobar = true;
			numeroClicado = 2;
			//recorrer el archivo
			while((linea = lectura.readLine())!=null){
				
				if(linea.toUpperCase().trim().equals(anyadir.toUpperCase().trim())){
					
					numeroClicado = 1;
				}
				
			}
			//Hacemos un switch case para comprobar hacer cosas distintas en caso de que exista o no la palabra
			switch(numeroClicado){
			
			case 1:
				JOptionPane.showMessageDialog(null, "Se ha añadido a la lista: " + anyadir, "Añadir a lista", JOptionPane.INFORMATION_MESSAGE);
				comboLista.addItem(anyadir);
				
			break;
			
			default:
				JOptionPane.showMessageDialog(null, "La palabra (" + anyadir + ") no existe.", "Añadir a lista", JOptionPane.ERROR_MESSAGE);
			break;
			}
			
			
			lectura.close();
			lecturaFichero.close();
		
		} 
		
		catch(IOException ex){ 
			ex.printStackTrace();
			//JOptionPane.showMessageDialog(this, "No se puede encontrar el fichero", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public PruebaJuego(){
		
		super("CIFRAS Y LETRAS");
		iniciarGUI();
		this.setExtendedState(MAXIMIZED_BOTH);
	}

	public void iniciarGUI(){  
	    
		arrayList = new ArrayList<>();
		arrayBotones = new ArrayList<>();
		arrayList1 = new ArrayList<>();
		cargafichero = new File("palabrasAnyadidas.txt");
		
		menu = new JMenuBar();
		menuInterior = new JMenu("Inicio");
		menuContenedor1 = new JMenuItem("Deshacer",KeyEvent.VK_Z);
		menuContenedor2 = new JMenuItem("Salir");
		
		menuContenedor1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		menuContenedor1.setMnemonic(KeyEvent.VK_Z);
		menuContenedor1.addActionListener(this);
		
		menuInterior.add(menuContenedor1);
	
		menuInterior.add(menuContenedor2);
		menuContenedor2.addActionListener(this);
		
		menu.add(menuInterior);
		
		setJMenuBar(menu);
		
		tabPane = new JTabbedPane();
		/*************************************************************************************/
		JPanel panelJuego = new JPanel();
		
		panelJuego.setLayout(new GridLayout(4,1));

		//Poner imagen de cabecera
		ImageIcon cabecera = new ImageIcon("./src/imagenes/letras.png");
		Image imgEn = cabecera.getImage();
		Image otraImagen = imgEn.getScaledInstance(120, 120, Image.SCALE_AREA_AVERAGING);
		ImageIcon iconCabecera = new ImageIcon(otraImagen);
		JLabel etiqueta = new JLabel(iconCabecera);
		
		panelJuego.setBackground(Color.decode("#33FFFF"));
		
		//Panel2
		/*--------------------------------------------------------------*/
		JPanel panelbotones = new JPanel();
		//panelbotones.setBorder(BorderFactory.createTitledBorder(null, "Opciones", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Calibri", 0, 14), new Color(0, 0, 102)));
		panelbotones.setBackground(Color.decode("#404040"));//#404040
		panelbotones.setLayout(new GridLayout(1, 3));
		  
		botonesLetras = new JPanel();
		botonesLetras.setBackground(Color.decode("#404040"));
		botonVocales = new JButton("Vocal");
		botonVocales.addActionListener(this);
		botonVocales.setBackground(Color.decode("#E67E22"));
		botonVocales.setForeground(Color.WHITE);
		botonVocales.setFont(new Font("Calibri", 1, 18));
		
		botonVocales.setFocusable(false);
		botonVocales.setRolloverEnabled(true);
		botonVocales.setPreferredSize(new Dimension(140,40));
		
		botonConsonante = new JButton("Consonante");
		botonConsonante.addActionListener(this);
		botonConsonante.setForeground(Color.WHITE);		
		botonConsonante.setBackground(Color.decode("#E67E22"));
		botonConsonante.setFont(new Font("Calibri", 1, 18));
		
		botonConsonante.setFocusable(false);
		botonConsonante.setRolloverEnabled(true);
		botonConsonante.setPreferredSize(new Dimension(140,40));
		
		botonesLetras.add(botonVocales);
		botonesLetras.add(botonConsonante);
		
		botonReordenar = new JButton();
		botonRevobinar = new JButton();
		
		JPanel resetear = new JPanel();
		resetear.setBackground(Color.decode("#404040"));
		resetear.setLayout(new FlowLayout());
		ImageIcon iconoRefresca = new ImageIcon("./src/imagenes/play.png");
		Image imgRefresca = iconoRefresca.getImage();
		Image otraRefresca = imgRefresca.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon iconRefresca = new ImageIcon(otraRefresca);
		botonReordenar.setIcon(iconRefresca);
		
		botonReordenar.setBorderPainted(false);
		botonReordenar.setContentAreaFilled(false);
		botonReordenar.setFocusable(false);
		botonReordenar.setRolloverEnabled(true);
		
		botonReordenar.addActionListener(this);
		botonReordenar.setBackground(Color.gray);
		
		botonReordenar.setFocusable(false);
		botonReordenar.setRolloverEnabled(true);
		//botonReordenar.setPreferredSize(new Dimension(50,50));
		
		ImageIcon iconoRev = new ImageIcon("./src/imagenes/revobinar.png");
		Image imgRevo = iconoRev.getImage();
		Image otraRevobi = imgRevo.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon iconRevobinar = new ImageIcon(otraRevobi);
		botonRevobinar.setIcon(iconRevobinar);
		
		
		botonRevobinar.setBorderPainted(false);
		botonRevobinar.setContentAreaFilled(false);
		botonRevobinar.setFocusable(false);
		botonRevobinar.setRolloverEnabled(true);
		
		botonRevobinar.addActionListener(this);
		botonRevobinar.setBackground(Color.gray);
		
		botonRevobinar.setFocusable(false);
		botonRevobinar.setRolloverEnabled(true);
		
		resetear.add(botonReordenar);
		resetear.add(botonRevobinar);
		
		JPanel lista = new JPanel();
		lista.setBackground(Color.decode("#404040"));
		comboLista = new JComboBox<String>();
		comboLista.addItem("Seleccione la palabra... ");
		comboLista.setPreferredSize(new Dimension(175,30));
		//comboLista.addActionListener(this);
		comboLista.addItemListener(this);
		
		lista.add(comboLista);
	
		panelbotones.add(resetear);
		panelbotones.add(botonesLetras);
		panelbotones.add(lista);
		
		
		/*PANEL3*/
		JPanel palabra = new JPanel();
		
		palabra.setBackground(Color.decode("#404040"));
		letra1 = new JTextField();
		letra1.setPreferredSize(new Dimension(700,100));
		letra1.setFont(new Font("Calibri", 1, 54));
		letra1.setHorizontalAlignment (JTextField.CENTER);
		letra1.setEditable(false);
				
		botonVerificar = new JButton(); 
		
		ImageIcon icono = new ImageIcon("./src/imagenes/button2.png");
		Image img = icono.getImage();
		Image otraimg = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(otraimg);
		botonVerificar.setIcon(icon);
		botonVerificar.addActionListener(this);
		
		botonVerificar.setBorderPainted(false);
		botonVerificar.setContentAreaFilled(false);
		botonVerificar.setFocusable(false);
		botonVerificar.setRolloverEnabled(true);
		//Reloj
		tiempo = new Reloj();
		tiempo.setPreferredSize(new Dimension(300,100));
		//tiempo.setVisible(true);
		
		tiempo.getTimer().start();
		palabra.add(tiempo);
		palabra.add(letra1);
		palabra.add(botonVerificar); 

		/*PANEL4*/
		letras = new JPanel();
		letras.setBackground(Color.decode("#404040"));
		letras.setLayout(new FlowLayout());
		vocales = new String [] {"A", "E", "I", "O", "U"};
		
		consonantes = new String []  {"B", "C", "D", "F", "G", "H", "J", 
				"K", "L", "M","N", "Ñ", "P","Q","R","S","T","V","W", "X","Y","Z"};
		
		scroll2 = new JScrollPane(letras);

		panelJuego.add(etiqueta);	
		panelJuego.add(panelbotones);
		
		//panelJuego.add(tiempo);
		panelJuego.add(palabra);
		panelJuego.add(scroll2); 
		
		
	/*****************************************SEGUNDA PESTAÑA CIFRAS*******************************/
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(4,1));
		

		//Poner imagen de cabecera
		ImageIcon cabecera2 = new ImageIcon("./src/imagenes/cifras.png");
		Image imgEnc = cabecera2.getImage();
		Image otraImagen2 = imgEnc.getScaledInstance(120, 120, Image.SCALE_AREA_AVERAGING);
		ImageIcon iconCabecera2 = new ImageIcon(otraImagen2);
		JLabel etiqueta2 = new JLabel(iconCabecera2);
		
		panel2.setBackground(Color.decode("#F5A9A9"));
		
		JPanel panelCifras2 = new JPanel();
		panelCifras2.setBackground(Color.decode("#404040"));
		//panelCifras2.setLayout(new GridLayout(1,2));
		numAleatorio = (int)Math.round(Math.random()* (999 - 101 +1) + 101);
	
		
		JLabel etiquetaNumero = new JLabel("Objetivo: " +numAleatorio);//Este numero será aleatorio
		
		etiquetaNumero.setFont(new Font("Calibri", 1, 54));
		etiquetaNumero.setForeground(Color.decode("#FDFEFE"));
		etiquetaNumero.setHorizontalAlignment(JLabel.CENTER);
		
		 
		panelCifras2.add(etiquetaNumero);
		
		JPanel panelCifras3 = new JPanel();
		panelCifras3.setBackground(Color.decode("#404040"));
		
		campoNumerico = new JTextField();
		campoNumerico.setPreferredSize(new Dimension(400,100));
		campoNumerico.setFont(new Font("Calibri", 1, 54));
		campoNumerico.setHorizontalAlignment (JTextField.CENTER);
		campoNumerico.setEditable(false);
		
		botonIgual = new JButton(); 
		
		ImageIcon iconoIg = new ImageIcon("./src/imagenes/igual.png");
		Image imgIgual = iconoIg.getImage();
		Image otraimgIg = imgIgual.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon iconIgual = new ImageIcon(otraimgIg);
		botonIgual.setIcon(iconIgual);
		botonIgual.addActionListener(this);
		
		botonIgual.setBorderPainted(false);
		botonIgual.setContentAreaFilled(false);
		botonIgual.setFocusable(false);
		botonIgual.setRolloverEnabled(true);
		
		resultado = new JLabel();
		resultado.setFont(new Font("Calibri", 1, 50));
		resultado.setForeground(Color.decode("#FDFEFE"));
		resultado.setHorizontalAlignment(JLabel.CENTER);
		//Reloj
		time = new Reloj();   
		//time.setPreferredSize(new Dimension(300,100));
		//tiempo.setVisible(false);
		
		time.getTimer().stop();
		panelCifras3.add(time);
		panelCifras3.add(campoNumerico);
		panelCifras3.add(botonIgual);
		panelCifras3.add(resultado);
		
		panelCifras4 = new JPanel();
		panelCifras4.setBackground(Color.decode("#404040"));
		panelCifras4.setLayout(new GridLayout(1,2));
		/********************************PANEL NUMEROS***************************************/
		//numerosJuego = new JPanel();
		//numerosJuego.setLayout(new GridLayout(1,6));
		//numerosJuego.setBackground(Color.decode("#404040"));
		//Creamos un array de enteros
		String cifras [] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "25", "50", "75" , "100"};
		
			for(int i = 0; i < 6; i++){
				numRandon = (int) Math.round(Math.random() * cifras.length -1);
				
				if(numRandon >=0){
				numero = new JButton(cifras[numRandon]);
				numero.setBackground(Color.decode("#084B8A"));
				numero.setForeground(Color.WHITE);	
				numero.setFont(new Font("Calibri", 1, 54));
				
				numero.addActionListener(this);
				numero.setActionCommand(posicionNumero++ +"Numero");
				panelCifras4.add(numero);
				}
				else{
					
					do{
					
					numRandon = (int) Math.round(Math.random() * cifras.length -1);
					if(numRandon >=0){
					
					numero = new JButton(cifras[numRandon]);
					numero.setBackground(Color.decode("#084B8A"));
					numero.setForeground(Color.WHITE);	
					numero.setFont(new Font("Calibri", 1, 54));
					numero.addActionListener(this);
					numero.setActionCommand(posicionNumero++ + "Numero");
					panelCifras4.add(numero);
					}
				}while(numRandon <=0);
			}
				
		}
		
		JPanel panelOperaciones = new JPanel();
	
		panelOperaciones.setLayout(new GridLayout(3,2));
		panelOperaciones.setBackground(Color.decode("#404040"));
		
		suma = new JButton("+");
		suma.setBackground(Color.decode("#000000"));
		suma.setForeground(Color.WHITE);	
		suma.setFont(new Font("Calibri", 1, 42));
		suma.addActionListener(this);
		suma.setActionCommand(posicionNumero++ +"");
	
		
		resta = new JButton("-");
		resta.setBackground(Color.decode("#000000"));
		resta.setForeground(Color.WHITE);	
		resta.setFont(new Font("Calibri", 1, 42));
		resta.addActionListener(this);
		resta.setActionCommand(posicionNumero++ +"");
		
		multi = new JButton("x");
		multi.setBackground(Color.decode("#000000"));
		multi.setForeground(Color.WHITE);	
		multi.setFont(new Font("Calibri", 1, 42));
		multi.addActionListener(this);
		multi.setActionCommand(posicionNumero++ +"");
		
		div = new JButton("/");
		div.setBackground(Color.decode("#000000"));
		div.setForeground(Color.WHITE);	
		div.setFont(new Font("Calibri", 1, 42));
		div.addActionListener(this);
		div.setActionCommand(posicionNumero++ +"");
		
		/*parentesisAbierto = new JButton("(");
		parentesisAbierto.setBackground(Color.decode("#000000"));
		parentesisAbierto.setForeground(Color.WHITE);	
		parentesisAbierto.setFont(new Font("Calibri", 1, 42));
		parentesisAbierto.addActionListener(this);
		//parentesisAbierto.setActionCommand(posicionNumero++ +"");
		
		parentesisCerrado = new JButton(")");
		parentesisCerrado.setBackground(Color.decode("#000000"));
		parentesisCerrado.setForeground(Color.WHITE);	
		parentesisCerrado.setFont(new Font("Calibri", 1, 42));
		parentesisCerrado.addActionListener(this);
		//parentesisCerrado.setActionCommand(posicionNumero++ +"");
		*/
		borrarTodo = new JButton("C");
		borrarTodo.setBackground(Color.decode("#000000"));
		borrarTodo.setForeground(Color.WHITE);	
		borrarTodo.setFont(new Font("Calibri", 1, 40));
		borrarTodo.addActionListener(this);
		
		
		//Función de deshacer
		borrarNumero = new JButton();
		borrarNumero.setBackground(Color.decode("#000000"));
		//borrarNumero.setForeground(Color.WHITE);	
		//borrarNumero.setFont(new Font("Calibri", 1, 42));
		borrarNumero.addActionListener(this);
		//borrarNumero.setActionCommand(posicionBorrar++ +"");
		
		ImageIcon iconoBor = new ImageIcon("./src/imagenes/borrarNumero.png");
		Image imgBorrar = iconoBor.getImage();
		Image otraimgBor = imgBorrar.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon iconBorrar = new ImageIcon(otraimgBor);
		borrarNumero.setIcon(iconBorrar);
		
		panelOperaciones.add(borrarTodo);
		panelOperaciones.add(borrarNumero);
		panelOperaciones.add(suma);
		panelOperaciones.add(resta);
		panelOperaciones.add(multi);
		panelOperaciones.add(div);
		//panelOperaciones.add(parentesisAbierto);
		//panelOperaciones.add(parentesisCerrado);
		
		//panelCifras4.add(numerosJuego);
		panelCifras4.add(panelOperaciones);
		
		panel2.add(etiqueta2);
		panel2.add(panelCifras2);
		
		panel2.add(panelCifras3);
		panel2.add(panelCifras4);
				
		tabPane.add("Letras", panelJuego);
		tabPane.add("Cifras", panel2);
	
		//Image logo = new ImageIcon(getClass().getResource("src/imagenes/logo.png")).getImage();
		ImageIcon logoV = new ImageIcon("./src/imagenes/logo.png");
		Image logo = logoV.getImage();
		
		
		add(tabPane);
		setIconImage(logo);
		pack();
		setVisible(true);
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cargafichero.deleteOnExit();
	}
	
	public JButton getBotonVerificar() {
		return botonVerificar;
	}

	public void setBotonVerificar(JButton botonVerificar) {
		this.botonVerificar = botonVerificar;
	}

	public static void main(String[]args){
		new PruebaJuego();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
				
		
		if(e.getSource() == botonRevobinar){
			
			if(letra1.getText().toString().isEmpty()){
				
			}
			else{
			//Este método sirve para repintar el JFrame
			repaint();
			//Cuando volvemos a atrás recuperamos el ultimo botón y eliminamos la ultima letra (ambos correspondientes)
			nuevoBoton = new JButton();
			int ultimaPosicion = arrayList.size() -1;
			nuevoBoton.setText(arrayList.get(ultimaPosicion));
			
			
			nuevoBoton.setBackground(Color.decode("#F5B041"));
			nuevoBoton.addActionListener(this);
			nuevoBoton.setActionCommand(posicion++ +"Letrav");
			
			nuevoBoton.setPreferredSize(new Dimension(60,60));
			nuevoBoton.setFont(new Font("Calibri", 1, 22));
			
			letras.add(nuevoBoton);
			
			letra1.setText(letra1.getText().substring(0, letra1.getText().length()-1));
			arrayList.remove(ultimaPosicion);
			}
			
			 
		}
		//Hacer uso del arrayList ahora
		if(e.getSource() == borrarNumero){
			
			if(campoNumerico.getText().toString().isEmpty()){
				
			}
			else{
			//Este método sirve para repintar el JFrame
			repaint();
			
			campoNumerico.setText(campoNumerico.getText().substring(0, campoNumerico.getText().length()-1));
			//borrarNumero.setActionCommand("Borrar");
			miboton3.setEnabled(true);
			
			}
		}
			
		if(e.getSource() == menuContenedor2){
			cargafichero.deleteOnExit();
			System.exit(0);
			
		}
		
		//Comparamos los botones según la posición para poder hacer clic en cualquiera de uno de ellos t mostrarlos en el JTextField
		for(int i=0;i<posicion;i++) 
		{
			//Boton para vocales
			if(e.getActionCommand().equals(i+"Letrav"))
			{
				    miboton = new JButton();
			        miboton = (JButton)e.getSource();
			       
			        String cadena = letra1.getText();
					cadena = cadena + miboton.getText().toString();
					letra1.setText(cadena);
					
				
					miboton.setVisible(false);
					//Obtenemos la letra del boton y la añadimos a la lista
					
					arrayList.add(miboton.getText().toString());          
					
					
			}
			
			else if(e.getActionCommand().equals(i+"Letrac"))
			{
					miboton2 = new JButton();
			        miboton2 = (JButton)e.getSource();
			       
			        String cadena = letra1.getText();
					cadena = cadena + miboton2.getText().toString();
					letra1.setText(cadena);
					
					miboton2.setVisible(false);
					//Obtenemos la letra del boton y la añadimos a la lista
					
					arrayList.add(miboton2.getText().toString());
					
			}
		}
			for(int j=0;j<posicionNumero;j++) 
			{
				//Boton para vocales
				if(e.getActionCommand().equals(j+"Numero"))
				{
					  
						miboton3 = new JButton();
						miboton3 = (JButton)e.getSource();
				      
						arrayBotones.add(miboton3);
						campoNumerico.setText(miboton3.getText().toString());
						
						miboton3.setEnabled(false);
						//miboton3.setActionCommand(numeroClicado++ +"clic");
						
						suma.setEnabled(true);
						resta.setEnabled(true);
						div.setEnabled(true);
						multi.setEnabled(true);
						borrarTodo.setEnabled(true);
						borrarNumero.setEnabled(true);
						
						time.getTimer().restart();
						
				}	
				
			}
			
			/*for(int h=0;h<numeroClicado;h++) 
			{
				//Boton para vocales
				if(e.getActionCommand().equals(h+"clic"))
				{
					  
						miboton4 = new JButton();
						miboton4 = (JButton)e.getSource();
				      
						//campoNumerico.setText(miboton3.getText().toString());
						
						//miboton4.setEnabled(true);
						//miboton3.setActionCommand(numeroClicado++ +"clic");
						
						suma.setEnabled(true);
						resta.setEnabled(true);
						div.setEnabled(true);
						multi.setEnabled(true);
						borrarTodo.setEnabled(true);
						borrarNumero.setEnabled(true);
						
						
				}	
				
				}*/
		
		
		if(e.getSource() == botonVocales){
			//Este método sirve para repintar el JFrame
			repaint();
			numRandon = (int) Math.round(Math.random() * vocales.length -1);
			
			if(numRandon >= 0){
				letraNueva = new JButton(vocales[numRandon]);
				letraNueva.setBackground(Color.decode("#F5B041"));
				letraNueva.addActionListener(this);
				letraNueva.setActionCommand(posicion++ +"Letrav");
				letras.add(letraNueva);
				
				letraNueva.setPreferredSize(new Dimension(60,60));
				letraNueva.setFont(new Font("Calibri", 1, 22));
				
			
				
			}
			
		}
		
		if(e.getSource() == botonConsonante){
			//Este método sirve para repintar el JFrame
			repaint();
			numRandon = (int) Math.round(Math.random() * consonantes.length-1) ;

			if(numRandon >= 0){
				letraNueva2 = new JButton(consonantes[numRandon]);
				letraNueva2.setBackground(Color.decode("#F5B041"));
				letraNueva2.addActionListener(this);
				letraNueva2.setActionCommand(posicion++ +"Letrac");
				letras.add(letraNueva2);
				
				letraNueva2.setPreferredSize(new Dimension(60,60));
				letraNueva2.setFont(new Font("Calibri", 1, 22));
				
			
			}
			
		}
		
	//	if(e.getSource() == comboLista){
			
		//}
		
		if(e.getSource() == botonVerificar){
			//Nos falta aqui comprobar si a palabra es valida o no
			//
			if(!letra1.getText().toString().isEmpty()){
				
				//Comprobar si la palabra ya se ha añadido o no (falta porque esto no va bien)
				/*for(int i = 0; i<arrayList1.size(); i++){
					if(!letra1.getText().toString().equals(arrayList1.get(i))){ 
						JOptionPane.showMessageDialog(this, "Esta palabra ya ha sido añadida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					}
					System.out.print(arrayList1.get(i));
				}*/
				
				String existente = "";
				try {
					FileWriter escrituraFichero = new FileWriter(cargafichero, true);
					PrintWriter escritura = new PrintWriter(escrituraFichero);
					
					for(int i = 1; i < comboLista.getItemCount(); i++){
						//escritura.print(comboLista.getItemAt(i)+"\n");
						existente = comboLista.getItemAt(i);
					}
					escritura.print(existente +"\n");
					escritura.close();
					
					FileReader lecturaFichero = new FileReader(cargafichero);
					BufferedReader lectura1 = new BufferedReader(lecturaFichero);
					String palabra1 = lectura1.readLine();
					palabraExiste = 2;
					
					while(palabra1 !=null){//Aqui leera la segunda linea que es la que contiene la primera palabra
						if(palabra1.toUpperCase().trim().equals(letra1.getText().toString())){
							palabraExiste = 1;
						}
						palabra1 = lectura1.readLine();
					}
					
					switch(palabraExiste){
					case 1:
						JOptionPane.showMessageDialog(this, "Esta palabra ya ha sido añadida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
						break;
					default:
						ComprobarPalabra(anyadir, letra1, numeroClicado, comboLista);
						break;
					}
					
					lectura1.close();
					lecturaFichero.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			
			else{
				JOptionPane.showMessageDialog(this, "No se ha añadido ningunna palabra, el campo se halla vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			}
			letra1.setText("");
		}
		
		if(e.getSource() == botonReordenar){
			PruebaJuego.this.setVisible(false);
			PruebaJuego.this.dispose();
			new PruebaJuego();
			
			//System.exit(0);
			/*arrayList.clear();
			
			letra1.setText("");
			
			//comboLista.addItem("Seleccione la palabra... ");
			//Eliminamos el contenido del panel y lo volvemos a repintar
			letras.removeAll();
			letras.repaint();
				
			
				
			JOptionPane.showMessageDialog(this, "    ¡Empezamos de nuevo!", "Nueva Partida", JOptionPane.INFORMATION_MESSAGE);
			
				arrayList1.clear();
				comboLista.removeAllItems();
				comboLista.addItem("Seleccione la palabra...");
				
				cargafichero.delete();
				try {
					cargafichero.createNewFile();
				} catch (IOException ioe) {
				  ioe.printStackTrace();
				}*/
		
		}
			
		if(e.getSource() == borrarTodo){
			
			campoNumerico.setText("");
			resultado.setText("");
			num1 = 0;
			num2 = 0;
			resultadoFinal = 0;
			//arrayBotones.removeAll(arrayBotones);
			for(int i = 0; i<arrayBotones.size(); i++){
				arrayBotones.get(i).setEnabled(true);
			}
			
		}
		
		
		if(e.getSource() == suma){
			num1 = Integer.parseInt(campoNumerico.getText());
			operacion = 1;//asignamos a este boton un número(de operacion)
			campoNumerico.setText(suma.getText().toString());
			//campoNumerico.setText("");
			
		}
		if(e.getSource() == resta){
			num1 = Integer.parseInt(campoNumerico.getText());
			operacion = 2;
			campoNumerico.setText(resta.getText().toString());
			//campoNumerico.setText("");
			
		}
		if(e.getSource() == div){
			num1 = Integer.parseInt(campoNumerico.getText());
			operacion = 3;
			campoNumerico.setText(div.getText().toString());
			//campoNumerico.setText("");
			
		}
		if(e.getSource() == multi){
			num1 = Integer.parseInt(campoNumerico.getText());
			operacion = 4;
			campoNumerico.setText(multi.getText().toString());
			//campoNumerico.setText("");
			
		}
		
		if(e.getSource() == botonIgual){
			//campoNumerico.setText("");
					
			
			if(!campoNumerico.getText().toString().isEmpty()){
					
				num2 = Integer.parseInt(campoNumerico.getText());//obtenemos el segundo numero que hemos presionado
			//Hacemos uso del switch case para saber que operacion estamos realizando
			switch(operacion)
	            {
	                case 1: resultadoFinal=num1+num2;//sumamos los numeros
	                    break;

	                case 2: resultadoFinal=num1-num2;//restamos los numeros
	                    break;

	                case 3: resultadoFinal=num1/num2;//dividimos los numeros
	                    break;

	                case 4: resultadoFinal=num1*num2;//multiplicamos los numeros
	                    break;
	            }
			}
			
		//Mostraremos en la caja de texto el resultado de la operacion realizada
		campoNumerico.setText(String.valueOf(resultadoFinal));  
						
		
			if(resultadoFinal == numAleatorio){
				
				resultado.setText("CORRECTO");
				resultado.setForeground(Color.green);
				for(int i = 0; i < arrayBotones.size(); i++){
					arrayBotones.get(i).setEnabled(false);
				}
				
			}
			else{
				//resultado.setText("INCORRECTO");
				//resultado.setForeground(Color.red);
			}
			//resultado.setText(String.valueOf(resultadoFinal));
			
		}
		
	}

	 public void itemStateChanged(ItemEvent e) {
         if (e.getStateChange() == ItemEvent.SELECTED) {
        	 if(!comboLista.getSelectedItem().toString().equals("Seleccione la palabra...")){
        	 String palabraSel = comboLista.getSelectedItem().toString();
        	
        	 if(!palabraSel.equals("Seleccione la palabra...")){
        		 try {
        			 Desktop.getDesktop().browse(new URI("http://www.wordreference.com/definicion/" + palabraSel));
        		 } catch (IOException e1) {
        			 // TODO Auto-generated catch block
        			 e1.printStackTrace();
        		 } catch (URISyntaxException e1) {
        			 // TODO Auto-generated catch block
        			 e1.printStackTrace();
        		 }
        	 }
        	 }else{
        		 
        	 }
         }
     }

	

	
}
