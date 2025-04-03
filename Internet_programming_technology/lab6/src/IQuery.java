import java.sql.SQLException;

public interface IQuery {
    public String Query1();
    public void Query2(String region);
    public void Query3();
    public void Query4();
    public void QueryTransaction() throws SQLException;
}