import java.sql.Connection;
import java.sql.SQLException;

public class Hackathon {
    public static void main(String [] args){
        Connection db = Database.getConnection();
        try {
            System.out.println(db.getClientInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
