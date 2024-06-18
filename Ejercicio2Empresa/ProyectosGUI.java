import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ProyectosGUI extends JFrame {

    private JTextField txtIdProyecto, txtNombre, txtFechaInicio, txtFechaFin;
    private JComboBox<String> cmbIdDepartamento;
    private JButton btnNew, btnSave, btnDelete, btnUpdate;
    private JTable table;
    private DefaultTableModel tableModel;
    private DatabaseConnection dbConnection;

    public ProyectosGUI() {
        dbConnection = new DatabaseConnection();
        configurarVentana();
        crearTitulo();
        crearPanelEntrada();
        crearPanelBotonesYTabla();
        agregarListeners();
        setResizable(true); // Permitir cambiar el tamaño de la ventana
        cargarProyectos();
    }

    private void configurarVentana() {
        setTitle("PROYECTOS");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new BorderLayout(10, 10)); // Establecer el diseño de la ventana
    }

    private void crearTitulo() {
        JLabel lblTitle = new JLabel("PROYECTOS", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setOpaque(true); // Hacer que el fondo sea opaco
        lblTitle.setBackground(Color.BLACK);
        lblTitle.setForeground(Color.WHITE);
        add(lblTitle, BorderLayout.NORTH); // Añadir el título al norte de la ventana
    }

    private void crearPanelEntrada() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añadir un borde vacío alrededor

        inputPanel.add(new JLabel("Id del Proyecto:"));
        txtIdProyecto = new JTextField(10);
        inputPanel.add(txtIdProyecto);

        inputPanel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(10);
        inputPanel.add(txtNombre);

        inputPanel.add(new JLabel("Fecha de Inicio:"));
        txtFechaInicio = new JTextField(10);
        inputPanel.add(txtFechaInicio);

        inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel(""));

        inputPanel.add(new JLabel("Fecha de Fin:"));
        txtFechaFin = new JTextField(10);
        inputPanel.add(txtFechaFin);

        inputPanel.add(new JLabel("Id del Departamento:"));
        cmbIdDepartamento = new JComboBox<>();
        cargarDepartamentos(); // Cargar los departamentos en el JComboBox
        inputPanel.add(cmbIdDepartamento);

        add(inputPanel, BorderLayout.CENTER); // Añadir el panel de entrada al centro de la ventana
    }

    private void crearPanelBotonesYTabla() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnNew = new JButton("Nuevo");
        btnSave = new JButton("Guardar");
        btnDelete = new JButton("Eliminar");
        btnUpdate = new JButton("Actualizar");

        buttonPanel.add(btnNew);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnUpdate);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(buttonPanel, BorderLayout.NORTH); // Añadir el panel de botones al norte del panel sur

        String[] columnNames = {"IdProyecto", "Nombre", "FechaInicio", "FechaFin", "IdDepartamento"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(780, 150));
        southPanel.add(scrollPane, BorderLayout.CENTER); // Añadir la tabla al centro del panel sur

        add(southPanel, BorderLayout.SOUTH); // Añadir el panel sur al sur de la ventana
    }

    private void agregarListeners() {
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields(); // Limpiar campos
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProject(); // Guardar proyecto
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProject(); // Eliminar proyecto
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProject(); // Actualizar proyecto
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    txtIdProyecto.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    txtNombre.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    txtFechaInicio.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    txtFechaFin.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    cmbIdDepartamento.setSelectedItem(tableModel.getValueAt(selectedRow, 4).toString());
                }
            }
        });
    }

    private void cargarProyectos() {
        List<String[]> proyectos = dbConnection.getProjects();
        tableModel.setRowCount(0); // Limpiar la tabla antes de cargar los datos
        for (String[] proyecto : proyectos) {
            tableModel.addRow(proyecto);
        }
    }

    private void cargarDepartamentos() {
        List<String[]> departamentos = dbConnection.getDepartments();
        for (String[] departamento : departamentos) {
            cmbIdDepartamento.addItem(departamento[0]); // Añadir solo el IdDepartamento al JComboBox
        }
    }

    private void clearFields() {
        txtIdProyecto.setText("");
        txtNombre.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        cmbIdDepartamento.setSelectedIndex(-1); // Desseleccionar el JComboBox
        txtIdProyecto.requestFocus(); // Colocar el cursor en el campo de ID del proyecto
    }

    private void saveProject() {
        String idProyecto = txtIdProyecto.getText();
        String nombre = txtNombre.getText();
        String fechaInicio = txtFechaInicio.getText();
        String fechaFin = txtFechaFin.getText();
        String idDepartamento = (String) cmbIdDepartamento.getSelectedItem();

        // Validar el formato de fecha
        if (!isValidDateFormat(fechaInicio) || !isValidDateFormat(fechaFin)) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Utilice el formato: yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convertir las fechas de inicio y fin a objetos LocalDate
        LocalDate startDate = LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse(fechaFin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Verificar que la fecha de inicio sea menor que la fecha de fin
        if (startDate.isAfter(endDate)) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio debe ser anterior a la fecha de fin", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
         
        if (!idProyecto.isEmpty() && !nombre.isEmpty()) {
            if (!dbConnection.projectExists(idProyecto)) {
                tableModel.addRow(new Object[]{idProyecto, nombre, fechaInicio, fechaFin, idDepartamento});
                dbConnection.insertProject(idProyecto, nombre, fechaInicio, fechaFin, idDepartamento); // Guardar en la base de datos
                clearFields(); // Limpiar campos después de guardar
            } else {
                JOptionPane.showMessageDialog(this, "Ya existe un proyecto con este ID.", "ID duplicado", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese al menos el ID del Proyecto y el Nombre.", "Información requerida", JOptionPane.WARNING_MESSAGE);
        }
    }
    private boolean isValidDateFormat(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void deleteProject() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String id = tableModel.getValueAt(selectedRow, 0).toString();
            tableModel.removeRow(selectedRow);
            dbConnection.deleteProject(id); // Eliminar de la base de datos
            clearFields(); // Limpiar campos después de eliminar
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un proyecto para eliminar.", "Selección requerida", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateProject() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String idProyecto = txtIdProyecto.getText();
            String nombre = txtNombre.getText();
            String fechaInicio = txtFechaInicio.getText();
            String fechaFin = txtFechaFin.getText();
            String idDepartamento = (String) cmbIdDepartamento.getSelectedItem();

            if (!idProyecto.isEmpty() && !nombre.isEmpty() && !fechaInicio.isEmpty() && !fechaFin.isEmpty() && !idDepartamento.isEmpty()) {
                dbConnection.updateProject(idProyecto, nombre, fechaInicio, fechaFin, idDepartamento);
                cargarProyectos();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese todos los datos.", "Información requerida", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un proyecto para actualizar.", "Selección requerida", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProyectosGUI().setVisible(true);
            }
        });
    }
}