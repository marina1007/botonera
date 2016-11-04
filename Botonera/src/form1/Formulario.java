package form1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Formulario extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label1,label2, label3, label4;
	private static JButton button1;
	private static JButton button2;
	private static JButton button3;
	private static JButton button4;
	private static JButton button5;
	private static JButton button6;
	private static JButton button7;
	private static JButton button8;
	private static JTextField textfield1;
	private static JTextField textfield2;
	private static JTextField textfield3;
	private static JTextField textfield4;

	public Formulario() {
        setLayout(null);
        
        //Campos
        label1=new JLabel("Legajo: ");
        label1.setBounds(10,20,100,30);
        add(label1);
        textfield1=new JTextField();
        textfield1.setBounds(80,25,60,20);
        add(textfield1);
        textfield1.addActionListener(this);
        
        label2=new JLabel("Nombre: ");
        label2.setBounds(10,40,100,30);
        add(label2);
        textfield2=new JTextField();
        textfield2.setBounds(80,45,200,20);
        add(textfield2);
        
        label3=new JLabel("Dirección: ");
        label3.setBounds(10,60,100,30);
        add(label3);
        textfield3=new JTextField();
        textfield3.setBounds(80,65,200,20);
        add(textfield3);
        
        label4=new JLabel("Teléfono: ");
        label4.setBounds(10,80,100,30);
        add(label4);
        textfield4=new JTextField();
        textfield4.setBounds(80,85,60,20);
        add(textfield4);
        
        //Botones		
        button1=new JButton("Nuevo");
        button1.setBounds(10,200,100,30);
        add(button1);
        button1.addActionListener(this);

        button2=new JButton("Imprimir");
        button2.setBounds(120,200,100,30);
        add(button2);
        button2.addActionListener(this);

        button3=new JButton("Buscar");
        button3.setBounds(230,200,100,30);
        add(button3);
        button3.addActionListener(this);

        button4=new JButton("Modificar");
        button4.setBounds(340,200,100,30);
        add(button4);
        button4.addActionListener(this);
        
        button5=new JButton("Eliminar");
        button5.setBounds(450,200,100,30);
        add(button5);
        button5.addActionListener(this);
        
        button6=new JButton("Salir");
        button6.setBounds(560,200,100,30);
        add(button6);
        button6.addActionListener(this);
        
        button7=new JButton("Guardar");
        button7.setBounds(10,235,100,30);
        add(button7);
        button7.addActionListener(this);
        
        button8=new JButton("Cancelar");
        button8.setBounds(120,235,100,30);
        add(button8);
        button8.addActionListener(this);
    }
/*	
	private void formKeyReleased(java.awt.event.KeyEvent evt) { 
		int code = evt.getKeyCode(); 
		char caracter = evt.getKeyChar(); 
		} 
*/	
	// CAptura de eventos
	public void actionPerformed(ActionEvent e) {
		
		 if (e.getSource()==button7) {
        	
        	// Captura de los datos en los texts en variables locales
            
        	// legajo
        	final String cad1=textfield1.getText();
        	
        	// validacion del legajo
        	final int legajo = Integer.parseInt(cad1);
        	if (legajo < 1 || legajo > 10000){
        		
        	}
            
        	//nombre
        	final String cad2=textfield2.getText();
        	
        	
            //dirección
        	final String cad3=textfield3.getText();
            // 
        	
        	//teléfono
        	final String cad4=textfield4.getText();
        	
        	// validacion del telefono
        	final int telefono = Integer.parseInt(cad4);
        	final int leg =Integer.parseInt(cad1);
            
            escribeArchivo(leg,cad2,cad3,telefono);
            
            textfield1.setText("");
            textfield2.setText("");
            textfield3.setText("");
            textfield4.setText("");
        }
        
        if (e.getSource()==button2) {
        	
        	//Legajo
        	final String cad1=textfield1.getText();
        	
        	leeArchivo(cad1);
        	
        }
        
        if (e.getSource()==button3) {
        	
        	//Legajo
        	final String cad1=textfield1.getText();
        	String nombre= null;
        	
        	if ((nombre =traeRegistro(cad1)) != null){
        	   	//System.out.println(nombre);
        	}
        }
     
        if (e.getSource()==button4) {
        	
        	//Legajo
        	String cad1=textfield1.getText();
        	        	
        	try {
				 modiRegistro(cad1);
				//System.out.println(a);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	
        }
        if (e.getSource()==button5) {
        	
        	//Legajo
        	String cad1=textfield1.getText();
        	        	
        	try {
				 elimRegistro(cad1);
				//System.out.println(a);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        
        if (e.getSource()==button6) {
        	
        	//Salir
        	
        	 System.exit(0);
        	
        	// Cambiar visibilidad Guardar y Cancelar
	 
        	/*
	 		if(button6.getLabel()=="Mostrar Boton"){
	 			button7.setVisible(true);
		 		button8.setVisible(true);
		 		button6.setLabel("Oculta Boton");
	 		}else{
	 			button7.setVisible(false);
		 		button8.setVisible(false);
		 		button6.setLabel("Mostrar Boton");
	 		}
	 		
	 		*/
       }
    }
	
	public void escribeArchivo (int legajo, String nombre, String direccion, int telefono) {
		//final String registro; 
		
		/**Parametros de conexion*/
		   final String bd = "agenda";
		   final String login = "root";
		   final String password = "kqdd7x89p3";
		   final String url = "jdbc:mysql://localhost/"+bd;
		   
		   Connection connection = null;
			
		   try {
			
				  //obtenemos el driver de para mysql
			       Class.forName("com.mysql.jdbc.Driver");
			        
			       //Intentamos la conexión con los parámetros de conexion
			       connection = DriverManager.getConnection(url,login,password);
			       
			       // Si la conexion se realiza con éxito avisa
			         if (connection!=null){
			            System.out.println("Conexión a base de datos "+bd+" OK\n");
			         }else{
			        	 System.out.println("Error en la conexion");
			         }
			         
			         
			         
		   }    
		   catch(SQLException e){
			    System.out.println(e);
		   }catch(ClassNotFoundException e){
		        System.out.println(e);
		   }catch(Exception e){
		        System.out.println(e);
		   }
		
		
		   /// Alta del contacto
		   
		   try {
			       Statement contacto = connection.createStatement();
			   
			       // Generación String SQL para la base de datos
			       String sql ="INSERT INTO contactos VALUES ("+legajo+", '" + nombre+"', '"+direccion+"', " +telefono+")";
			   
			       // Muestra la SQL por consola 
			       System.out.println(sql);
			   
			       // Ejecuta la SQL
			       contacto.executeUpdate(sql);
			   
			       // Muestra en un diálogo como fue la registracion
			       JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
			      
            
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        
        	// Cierre de la conexion
        	
        	try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != connection)
        	   	//contacto.close();
	       		connection=null;
             
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
	
	
	public void leeArchivo (String legajo){
		  File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	 
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File ("e:\\prueba.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	 
	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null)
	            System.out.println(linea);
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	   }

	
	/* 
	 * Este método trae un registro buscado con el numero de legajo 
	 * y lo pinta en el formulario
	 * dejándolo disponilbe para
	 * 
	 * 1. Eliminarlo 
	 * 2. Modificarlo
	 * 3. Imprimirlo
	 */
	public static String traeRegistro(String legajo){
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;
	    String nombre=null;
	 
	    try {
	    		// Apertura del fichero y creacion de BufferedReader para poder
	        	// hacer una lectura comoda (disponer del metodo readLine()).
	        	
	    		archivo = new File ("e:\\prueba.txt");
	        	fr = new FileReader (archivo);
	        	br = new BufferedReader(fr);
	 
	        	// Lectura del fichero
	        	String linea;
	        	String tlegajo =null;
	        	String tnombre = null;
	        	String tdir =null;
	        	String ttelef=null;
	        	
	        	tlegajo=null;
	        	linea=null;
	        	int ncampo=1;
	        	
	        	// Variables de deteccion de registro
	        	int pos=0;
	        	int nleg=0;
	        	int nlegajo=0;
	        	String lega=null;
	        	Boolean esta=false;
	         
	        	while((linea=br.readLine())!= null){
	        		
	        		System.out.println(linea);
	        		// Extraer legajo
	        		pos=linea.indexOf(";");
	        		lega=linea.substring(0,pos);
	        		
	        		nleg=Integer.parseInt(lega);
	        		nlegajo=Integer.parseInt(legajo);
	        		
	        		// Recuperar Registro
	            
	        		if (nleg==nlegajo){
	        			
	        			esta=true;
	        			StringTokenizer st = new StringTokenizer(linea,";");
	        			
	        			while (st.hasMoreTokens()){
	        		         
	        				//System.out.println(st.nextToken());
	        				if (ncampo==1){
	        					tlegajo=st.nextToken();
	        				}
	        				if (ncampo==2){
	        					tnombre=st.nextToken();
	        				}

	        				if (ncampo==3){
	        					tdir=st.nextToken();
	        				}
        		     
	        				if (ncampo==4){
	        					ttelef=st.nextToken();
	        				}
	        				ncampo++;
	        			}
	        			
	        			textfield2.setText(tnombre);
	    	        	textfield3.setText(tdir);
	    	        	textfield4.setText(ttelef);
	    	        	
	    	        	//textfield1.setEnabled(false);
	    	        	textfield2.setEnabled(false);
	    	        	textfield3.setEnabled(false);
	    	        	textfield4.setEnabled(false);
	    	        	
	    	        	// Botonera
	    	        	button2.setEnabled(true);
	                	button4.setEnabled(true);
	                	button5.setEnabled(true);
	                	
	    	        	break;
	        		}	
	        	}
        		if (esta==false){
        			System.out.println("No está");
        		}
	        }
	        catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	               br.close();
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }

	    //if (nombre != null){
		return nombre;
	    //}else{
	    //	return "";
	    //}
	}
	
	/* 
	 * Este método guarda el registro modificado en el archivo.
	 * 1. Copia los registros no modificados del archivo original a un temporal
	 * 2. Guarda un registro con los datos del formulario en el temporal
	 * 
	 *  Este Almacenamiento lo realiza en el mismo orden del archivo Original
	 */
	
	public void modiRegistro(String legajo) throws IOException {
		
		// Variables de Archivo de entrada 
		 File archivo = null;
	     FileReader fr = null;
	     BufferedReader br = null;
			
	   // Variables de archivo de salida
	     FileWriter fichero = null;
	     PrintWriter pw = null;
	     
			String registro =null;
	 
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         
	    	  // Apertura de archivo de entrada
	    	  archivo = new File ("e:\\prueba.txt");
	          fr = new FileReader (archivo);
	          br = new BufferedReader(fr);
	          
	          // Apertura de archivo de salida
	          fichero = new FileWriter("e:\\fichero2.txt",true);
	          pw = new PrintWriter(fichero);
	          
	         
	         // Lectura del fichero
	         String linea;
	         
	        // Variables de deteccion de registro
	        int pos=0;
	        int nleg=0;
	        int nlegajo=0;
	        String lega=null;
	        
	        //variables de traspaso de datos pantalla registro
	        String cad1=null;
	        String cad2=null;
	        String cad3=null;
	        String cad4=null;
	        
	        
	        while((linea=br.readLine())!=null){
	        	
	        	 // Extraer legajo
	        		pos=linea.indexOf(";");
	        		lega=linea.substring(0,pos);
	        		
	        		nleg=Integer.parseInt(lega);
	        		nlegajo=Integer.parseInt(legajo);
	        		
	        		// Recuperar Registro
	            
	        		if (nleg==nlegajo){
	        			
	        			cad1 = textfield1.getText();
	        			cad2 = textfield2.getText();
	        			cad3 = textfield3.getText();
	        			cad4 = textfield4.getText();

	        			registro = cad1+";"+cad2+";"+cad3+";"+cad4;
	        			System.out.println("a"+registro);
	    	           
	        			pw.println(registro); 
	    	            //pw.newLine();
	        			
	        		}else{
	        			
	        			registro = linea;
	        			//registro = cad1+";"+cad2+";"+cad3+";"+cad4;
	        			System.out.println(registro);
	        			pw.println(registro); 
	    	           // bw.newLine();
	        			
	        		}
	         }
	       
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( (null != br) ){   
	               
	            	// Cerrar filestreams	
	            	fr.close();
	    	        br.close();
	    	        pw.close();
	            	
	    	        // Cambiar nombre archivos 
	    	        File f = new File("e:\\prueba.txt"); 
	    	        boolean borro=f.delete();
	    	        
	    	        if(borro==true){
	    	        	System.out.println("Borrado");
	    	        }
	    	       
	    	        try{
	    	        	File f2 = new File("e:\\fichero2.txt");
	    	        	
	    	        	File f1 = new File("e:\\prueba.txt");
	    	        	boolean correcto = f2.renameTo(f1);
	 	    	       
	    	        	System.out.println(correcto);
	    	        
	            	}catch (Exception e4){ 
		            e4.printStackTrace();
	            	}
	            }
	            
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
		//return "0";
	}
	
	/* 
	 * Este método eliminar el registro del archivo que se ve en el formulario.
	 * 1. Copia los registros no modificados del archivo original a un temporal
	 * 2. Guarda un registro con los datos del formulario en el temporal
	 * 
	 *  Este Almacenamiento lo realiza en el mismo orden del archivo Original
	 */
	
	public void elimRegistro(String legajo) throws IOException {
		
		// Variables de Archivo de entrada 
		 File archivo = null;
	     FileReader fr = null;
	     BufferedReader br = null;
			
	   // Variables de archivo de salida
	     FileWriter fichero = null;
	     PrintWriter pw = null;
	     
			String registro =null;
	 
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         
	    	  // Apertura de archivo de entrada
	    	  archivo = new File ("e:\\prueba.txt");
	          fr = new FileReader (archivo);
	          br = new BufferedReader(fr);
	          
	          // Apertura de archivo de salida
	          fichero = new FileWriter("e:\\fichero2.txt",true);
	          pw = new PrintWriter(fichero);
	          
	         
	         // Lectura del fichero
	         String linea;
	         
	        // Variables de deteccion de registro
	        int pos=0;
	        int nleg=0;
	        int nlegajo=0;
	        String lega=null;
	        
	        //variables de traspaso de datos pantalla registro
	        String cad1=null;
	        String cad2=null;
	        String cad3=null;
	        String cad4=null;
	        
	        
	        while((linea=br.readLine())!=null){
	        	
	        	 // Extraer legajo
	        		pos=linea.indexOf(";");
	        		lega=linea.substring(0,pos);
	        		
	        		nleg=Integer.parseInt(lega);
	        		nlegajo=Integer.parseInt(legajo);
	        		
	        		// Recuperar Registro
	            
	        		if (nleg!=nlegajo){
	        			
	        			registro = linea;
	        			//registro = cad1+";"+cad2+";"+cad3+";"+cad4;
	        			System.out.println(registro);
	        			pw.println(registro); 
	    	           // bw.newLine();
	        			
	        		}
	         }
	        // Armar Botonera
	        if(busDatos() == true){
	        	 button1.setEnabled(true);
	             button3.setEnabled(true);
	        	 button6.setEnabled(true);
	        	 
	        	 button2.setEnabled(false);
	        	 button4.setEnabled(false);
	        	 button5.setEnabled(false);
	        	 button7.setEnabled(false);
	        	 button8.setEnabled(false);
	        	 
	             
	        }else{
	    	
	        	button1.setEnabled(true);
	        	button6.setEnabled(true);
	    	
	        	button2.setEnabled(false);
	        	button3.setEnabled(false);
	        	button4.setEnabled(false);
	        	button5.setEnabled(false);
	        	button7.setEnabled(false);
	        	button8.setEnabled(false);
	        } 
	       
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( (null != br) ){   
	               
	            	// Cerrar filestreams	
	            	fr.close();
	    	        br.close();
	    	        pw.close();
	            	
	    	        // Cambiar nombre archivos 
	    	        File f = new File("e:\\prueba.txt"); 
	    	        boolean borro=f.delete();
	    	        
	    	        if(borro==true){
	    	        	System.out.println("Borrado");
	    	        }
	    	       
	    	        try{
	    	        	File f2 = new File("e:\\fichero2.txt");
	    	        	
	    	        	File f1 = new File("e:\\prueba.txt");
	    	        	boolean correcto = f2.renameTo(f1);
	 	    	       
	    	        	System.out.println(correcto);
	    	        
	            	}catch (Exception e4){ 
		            e4.printStackTrace();
	            	}
	            }
	            
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
		//return "0";
	}
	
public static Boolean busDatos() throws IOException {
		
		// Variables de Archivo de entrada 
		 File archivo = null;
	     FileReader fr = null;
	     BufferedReader br = null;
			
	   // Variables de archivo de salida
	     FileWriter fichero = null;
	     PrintWriter pw = null;
	     
		String registro =null;
		
		Boolean esta=false;
	 
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         
	    	  // Apertura de archivo de entrada
	    	  archivo = new File ("e:\\prueba.txt");
	          fr = new FileReader (archivo);
	          br = new BufferedReader(fr);
	         
	         // Lectura del fichero
	         String linea;
	         
	        if((linea=br.readLine())!=null){
	        	esta = true;
	        }
	       
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( (null != br) ){   
	               
	            	// Cerrar filestreams	
	            	fr.close();
	    	        br.close();
	            }
	         }catch (Exception e4){ 
		            e4.printStackTrace();
	         }
	      }
	         return esta;
	   }

   // Método main del proyecto
	public static void main(String[] ar) throws IOException {
        JFrame formulario1=new Formulario();
        formulario1.setBounds(10,20,800,400);
        formulario1.setVisible(true);
        
        
        /*
        // Armar Botonera
        if(busDatos() == true){
        	 button1.setEnabled(true);
             button3.setEnabled(true);
        	 button6.setEnabled(true);
        	 
        	 button2.setEnabled(false);
        	 button4.setEnabled(false);
        	 button5.setEnabled(false);
        	 button7.setEnabled(false);
        	 button8.setEnabled(false);
        	 
             
        }else{
    	
        	button1.setEnabled(true);
        	button6.setEnabled(true);
    	
        	button2.setEnabled(false);
        	button3.setEnabled(false);
        	button4.setEnabled(false);
        	button5.setEnabled(false);
        	button7.setEnabled(false);
        	button8.setEnabled(false);
  }
       */ 
    }
}
