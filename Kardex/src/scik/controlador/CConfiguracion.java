package scik.controlador;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import scik.modelo.Conexion;
import scik.vista.UIConfiguracion;
import static scik.KardexIni.con;

/**
 * Controlador de la vista de Configuracion
 * 
 * Modifica valores de la configuracion con la base de datos, además de comprobar
 * si la conexion proporcionada por el usuario es correcta.
 *  
 * @author Yuliana Apaza
 * @version 2.0
 * @since 2015-10-05
 */

public class CConfiguracion implements IConfiguracion
{
    private UIConfiguracion ventana;
    private boolean retornar_login;
    
    public CConfiguracion(boolean retornar_login)
    {
        con.desconectar();
        this.retornar_login = retornar_login;
        ventana = new UIConfiguracion(this);
    }
    
    @Override
    public void cancelar()
    {
        CKardexMenu menu;
        CLogin login;
        if(retornar_login)
            login = new CLogin();
        else
            menu = new CKardexMenu();
        con.conectar(false);
        ventana.dispose();
    }
    
    @Override
    public void cargar(JTextField txtHost, JTextField txtUsuario)
    {
        FileReader fr = null;
        try
        {
            /*String [] conexion_data = new String[3];
            fr = new FileReader("data/conexion.dat");
            BufferedReader br = new BufferedReader(fr);
            String linea = "";
            int number = 0;
            while((linea = br.readLine()) != null)
            {
                conexion_data[number] = linea.substring(linea.indexOf("=") + 1, linea.length());
                number++;
                if(number > 2)
                    break;
            }
            txtHost.setText(conexion_data[0]);
            txtUsuario.setText(conexion_data[1]);*/
        }
        catch (Exception ex)
        {
        }
        finally
        {
            try
            {
                fr.close();
            }
            catch (IOException ex)
            {
            }
        }
    }
    
    @Override
    public void verificar(JTextField txtHost, JTextField txtUsuario, JPasswordField txtPass, JLabel lblEstado)
    {
        Conexion newCon = new Conexion("remotemysql.com", "SWiL8Iu0Mj", "SWiL8Iu0Mj", "iaJNKJHix0");
        if(newCon.conectar(true))
        {
            lblEstado.setForeground(new Color(0, 150, 0));
            lblEstado.setText("Configuración correcta");
        }
        else
        {
            lblEstado.setForeground(new Color(255, 0, 0));
            lblEstado.setText("Configuración incorrecta");
        }
        newCon.desconectar();
    }
    
    @Override
    public void aceptar(JTextField txtHost, JTextField txtUsuario, JPasswordField txtPass)
    {
        PrintWriter out = null;
        try
        {
            con.setHost("remotemysql.com");
            con.setUser("SWiL8Iu0Mj");
            con.setPassword("iaJNKJHix0");
            
            /*out = new PrintWriter("conexion.dat");
            out.print(  "host=" + txtHost.getText() +
                        "\nusuario=" + txtUsuario.getText() +
                        "\npassword=" + String.valueOf(txtPass.getPassword()) +
                        "\n\nNo editar este archivo.");*/
            
            con.conectar(false);
            
            CKardexMenu menu;
            CLogin login;
            if(retornar_login)
                login = new CLogin();
            else
                menu = new CKardexMenu();
            
            ventana.dispose();
        }
        catch (Exception ex)
        {
        }
        finally
        {
            out.close();
        }
    }
}
