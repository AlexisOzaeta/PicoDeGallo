package pico.de.gallo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conector {
    private Connection con = null;
    /**
     * Metodo destinado para crear la conexion
     * @throws ClassNotFoundException 
     */
    public void connect() throws ClassNotFoundException{
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:basePruebaLite.db");
            if (con != null) {
                System.out.println("Conectado");
            }
        }catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
        }
    }
    /**
     * Metodo destinado para cerrar la conexion
     */
    public void close(){
        try {
            con.close();
        }catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrarMiembro(){
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Genero WHERE ID_Genero = 3");
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
// 
//    /**
//     * 
//     * @return regresa la conexion
//     */
//    public Connection crearDB(){
//        Connection con = null;
//        String carpeta = System.getProperty("user.dir")+File.separator+"basePrueba";
//        File url = new File(carpeta);
//        
//        if(url.exists()){
//            System.out.println("Yeah!");
//        }else{
//            try{
//                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//                String DB = "jdbc:derby:"+carpeta+";create=true";
//                con = DriverManager.getConnection(DB);
//            }catch(ClassNotFoundException | SQLException ex){
//                System.out.println("Ups:(");
//            }
//        }
//        return con;
//    }
}
