package GUIS;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/empresa";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private Connection connection;

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a la base de datos establecida.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al conectar a la base de datos.");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión a la base de datos cerrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al cerrar la conexión a la base de datos.");
        }
    }

    public void insertDepartment(String id, String name, String phone, String fax) {
        String query = "INSERT INTO departamentos (IdDepartamento, Nombre, Telefono, Fax) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, phone);
            statement.setString(4, fax);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al insertar departamento.");
        }
    }

    public void deleteDepartment(String id) {
        String query = "DELETE FROM departamentos WHERE IdDepartamento = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al eliminar departamento.");
        }
    }

    public void updateDepartment(String id, String name, String phone, String fax) {
        String query = "UPDATE departamentos SET Nombre = ?, Telefono = ?, Fax = ? WHERE IdDepartamento = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setString(3, fax);
            statement.setString(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al actualizar departamento.");
        }
    }

    public List<String[]> getDepartments() {
        List<String[]> departments = new ArrayList<>();
        String query = "SELECT * FROM departamentos";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("IdDepartamento");
                String name = resultSet.getString("Nombre");
                String phone = resultSet.getString("Telefono");
                String fax = resultSet.getString("Fax");
                departments.add(new String[]{id, name, phone, fax});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al obtener departamentos.");
        }
        return departments;
    }
    
    
    // Métodos para Proyectos

    
    
    public void insertProject(String id, String name, String startDate, String endDate, String deptId) {
        String query = "INSERT INTO proyectos (IdProyecto, Nombre, FechaInicio, FechaFin, IdDepartamento) VALUES (?, ?, ?, ?, ?)";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, startDate);
            statement.setString(4, endDate);
            statement.setString(5, deptId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al insertar proyecto.");
        }
    }

    public void deleteProject(String id) {
        String query = "DELETE FROM proyectos WHERE IdProyecto = ?";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al eliminar proyecto.");
        }
    }

public void updateProject(String idProyecto, String nombre, String fechaInicio, String fechaFin, String idDepartamento) {
    String query = "UPDATE proyectos SET Nombre=?, FechaInicio=?, FechaFin=?, IdDepartamento=? WHERE IdProyecto=?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, nombre);
        statement.setDate(2, Date.valueOf(fechaInicio));
        statement.setDate(3, Date.valueOf(fechaFin));
        statement.setString(4, idDepartamento);
        statement.setString(5, idProyecto);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public List<String[]> getProjects() {
        List<String[]> projects = new ArrayList<>();
        String query = "SELECT * FROM proyectos";
        try ( PreparedStatement statement = connection.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("IdProyecto");
                String name = resultSet.getString("Nombre");
                String startDate = resultSet.getString("FechaInicio");
                String endDate = resultSet.getString("FechaFin");
                String deptId = resultSet.getString("IdDepartamento");
                projects.add(new String[]{id, name, startDate, endDate, deptId});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al obtener proyectos.");
        }
        return projects;
    }
}