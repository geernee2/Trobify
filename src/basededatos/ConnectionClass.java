package basededatos;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
    
    private static ConnectionClass connectionClass = null;
    private Connection connection;
    
    public static ConnectionClass getInstance(){
       
        if(connectionClass == null)
        {
            connectionClass = new ConnectionClass();
        }
       return connectionClass;
    }
    
    private ConnectionClass(){}//Constructor vacio
    
   
    public Connection getConnection()
    {
         String host = "bjsbugxntookzzyiy37d-mysql.services.clever-cloud.com";
            int port = 3306;
            String dbName = "bjsbugxntookzzyiy37d";
            String user = "ul1apqt5l0fkvpgt";
            String password = "9aMkmWPT3ASQ4Ne0GjuR";
        
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbName, user, password);
            }catch(Exception e) {
                System.out.println(e);
            }
            return connection;
    }
    
    public ConnectionClass clone(){
    try {
        throw new CloneNotSupportedException();
    } catch (CloneNotSupportedException ex) {
        System.out.println("No se puede clonar un objeto de la clase ConnectionClass");
    }
    return null; 
    }
}