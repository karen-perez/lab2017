package presentacion.controlador;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import persistencia.conexion.Conexion;
import presentacion.vista.VentanaConexion;



public class ControladorInicioConex implements ActionListener  {

	private VentanaConexion ventanaConexion;
	private Controlador ctrl;
	public ControladorInicioConex(VentanaConexion ventanaConex, Controlador contr)
	{
		this.ventanaConexion=ventanaConex;
		this.ctrl = contr;
		
	 this.ventanaConexion.getBtnAceptar().addActionListener(this);

	}

	
	
	


	public void actionPerformed(ActionEvent e) {		
		
		
		
		if (this.ventanaConexion != null && e.getSource() == this.ventanaConexion.getBtnAceptar())
		{
			if(this.ventanaConexion.getTxtIP().getText().isEmpty() || this.ventanaConexion.getTxtPuerto().getText().isEmpty()
					
					||this.ventanaConexion.getTxtUsuario().getText().isEmpty() || this.ventanaConexion.getTextPass().getText().isEmpty())
				
			{
				setWarningMsg("Uno o mas campos obligatorios se encuentran vacios.");
			}
			else	
			{
			if(Conexion.getConexion().ConexionAuto(this.ventanaConexion.getTxtPuerto().getText(), this.ventanaConexion.getTxtIP().getText(),
					this.ventanaConexion.getTxtUsuario().getText(),this.ventanaConexion.getTextPass().getText()))
			{
				
				 escribir(this.ventanaConexion.getTxtPuerto().getText(), this.ventanaConexion.getTxtIP().getText(),
							this.ventanaConexion.getTxtUsuario().getText(),
							this.ventanaConexion.getTextPass().getText());
					
				
				
				
				
			setExitoMsg("Conexion Guardada con exito.");
			this.ventanaConexion.dispose();
			
			//abrir Vista.
			this.ctrl.llenarTabla();
			
			}
			else
			{
				setWarningMsg("No se puede mostrar el servidor asegurese que los campos son correctos.");
			}
			
		
		}
		}
	}
	
		
		
public void escribir(String puerto, String ip, String usu, String pw ) {

    JSONObject obj = new JSONObject();
    obj.put("ip", ip);
    obj.put("puerto", puerto);
    obj.put("usuario", usu);
    obj.put("pass", pw);
    obj.put("primerIngreso", false);
  

    try (FileWriter file = new FileWriter("./test.json"))
    		{

        file.write(obj.toJSONString());
        file.flush();

    } catch (IOException e) {
        e.printStackTrace();
    }

    System.out.print(obj);

}


boolean leerArchivo()
{
JSONParser parser = new JSONParser();
boolean ret=false;
try {

    Object obj = parser.parse(new FileReader("test.json"));

    JSONObject jsonObject = (JSONObject) obj;
    System.out.println(jsonObject);

    String ip = (String) jsonObject.get("ip");
    System.out.println(ip);
    String puerto = (String) jsonObject.get("puerto");
    System.out.println(puerto);
    String usuario = (String) jsonObject.get("usuario");
    System.out.println(usuario);
    String pass = (String) jsonObject.get("pass");
    System.out.println(pass);

  /*  // loop array
    JSONArray msg = (JSONArray) jsonObject.get("messages");
    Iterator<String> iterator = msg.iterator();
    while (iterator.hasNext()) {
        System.out.println(iterator.next());
    }*/
    		
    		
  if(Conexion.getConexion().ConexionAuto(puerto, ip,usuario, pass))
					{
    			ret=true;
					}

    

} catch (FileNotFoundException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
} catch (ParseException e) {
    e.printStackTrace();
}
return ret;
}


	
	
	public static void setWarningMsg(String text){
	    Toolkit.getDefaultToolkit().beep();
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
	    JDialog dialog = optionPane.createDialog("Warning!");
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}

	
	
	public static void setExitoMsg(String text){
	    Toolkit.getDefaultToolkit().beep();
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
	    JDialog dialog = optionPane.createDialog("Exito!");
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}

}
