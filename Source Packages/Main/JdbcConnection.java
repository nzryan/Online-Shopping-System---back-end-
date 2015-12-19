
package Main;


import java.sql.Connection;
import java.sql.SQLException;
import org.h2.jdbcx.JdbcConnectionPool;


/**
 *
 * @author Ryan Campion 2343075
 */
public class JdbcConnection {
    
    private static final String url = "jdbc:h2:tcp://localhost/shopping";
   private static final String username = "sa";
   private static final String password = "";

   private static final JdbcConnectionPool pool = JdbcConnectionPool.create(url, username, password);

   public static Connection getConnection() {
      try {
         return pool.getConnection();
      } catch (SQLException ex) {
         throw new RuntimeException(ex);
      }
   }
    
}
