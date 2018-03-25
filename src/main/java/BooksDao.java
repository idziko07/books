import java.sql.*;

public class BooksDao {
    private static final String URL = "jdbc:mysql://localhost:3306/?serverTimezone=UTC";
    private static final String ROOT = "root";
    private static final String PASSWORD = "root";
    private static final String CREATE_DATABASE = "create SCHEMA if not exists library DEFAULT CHARACTER SET utf8";
    private static final String USE = "use library";

   private static final String CREATE_TABLE_BOOKS = "create table if not exists books(\n" +
                                                       "id integer auto_increment primary key,\n" +
                                                       "title varchar(50),\n" +
                                                       "author varchar(50),\n" +
                                                       "year integer(4),\n" +
                                                       "isbn VARCHAR(13)\n" +
                                                       ");";

    private Connection connection;

    public BooksDao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, ROOT, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DATABASE);
            preparedStatement.execute();

            PreparedStatement preparedStatementUSE = connection.prepareStatement(USE);
            preparedStatementUSE.execute();

            PreparedStatement preparedStatementTable = connection.prepareStatement(CREATE_TABLE_BOOKS);
            preparedStatementTable.execute();
        } catch (ClassNotFoundException e) {
            System.out.println("Bład podczas ładowania sterownika: " +  e.getMessage());
        } catch (SQLException e) {
            System.out.println("Bład : " + e.getMessage());
        }
    }

    public void librarySave(Books books){
        final String sql ="insert into books values(NULL,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,books.getTitle());
            preparedStatement.setString(2,books.getAuthor());
            preparedStatement.setInt(3,books.getYear());
            preparedStatement.setLong(4,books.getIsbn());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Bład podczas dodwania ksiażki:" + e.getMessage());
        }
    }

    public Books libraryRead(long isbn){
        final  String sql = "SELECT * FROM books WHERE isbn = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,isbn);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Books books = new Books();
                books.setId(resultSet.getLong("id"));
                books.setTitle(resultSet.getString("title"));
                books.setAuthor(resultSet.getString("author"));
                books.setYear(resultSet.getInt("year"));
                books.setIsbn(resultSet.getLong("isbn"));
                return books;
            }

        } catch (SQLException e) {
            System.out.println("Bład podczas wczytywania ksiażki:" + e.getMessage());
        }
        return null;
    }

    public void libraryUpdate(Books books){
        final String sql = "UPDATE books SET title=?, author=?, year=?, isbn=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, books.getTitle());
            preparedStatement.setString(2, books.getAuthor());
            preparedStatement.setInt(3, books.getYear());
            preparedStatement.setLong(4, books.getIsbn());
            preparedStatement.setLong(5,books.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Bład podczas aktualizowania ksiażki:" + e.getMessage());
        }
    }

    public void libraryDelete(long id){
        final String sql = "DELETE FROM books WHERE id= ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Bład podczas usuwania ksiażki:" + e.getMessage());
        }
    }


    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Bład podczas zamykania bazy:" + e.getMessage());
        }
    }
}
