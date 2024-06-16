import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProyectosGUI extends JFrame {

    private JTextField txtIdProyecto, txtNombre, txtFechaInicio, txtFechaFin, txtIdDepartamento;
    private JButton btnNew, btnSave, btnDelete, btnUpdate;
    private JTable table;
    private DefaultTableModel tableModel;

    public ProyectosGUI() {
        // Configuración del marco (ventana)
        setTitle("PROYECTOS");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new BorderLayout(10, 10)); // Establecer el diseño de la ventana

        // Crear el título
        JLabel lblTitle = new JLabel("PROYECTOS", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setOpaque(true); // Hacer que el fondo sea opaco
        lblTitle.setBackground(Color.BLACK);
        lblTitle.setForeground(Color.WHITE);
        add(lblTitle, BorderLayout.NORTH); // Añadir el título al norte de la ventana

        // Crear el panel de entrada de datos
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
        txtIdDepartamento = new JTextField(10);
        inputPanel.add(txtIdDepartamento);

        add(inputPanel, BorderLayout.CENTER); // Añadir el panel de entrada al centro de la ventana

        // Crear el panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnNew = new JButton("Nuevo");
        btnSave = new JButton("Guardar");
        btnDelete = new JButton("Eliminar");
        btnUpdate = new JButton("Actualizar");

        buttonPanel.add(btnNew);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnUpdate);

        // Crear un panel para mantener los botones y la tabla
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(buttonPanel, BorderLayout.NORTH); // Añadir el panel de botones al norte del panel sur

        // Crear la tabla
        String[] columnNames = {"IdProyecto", "Nombre", "FechaInicio", "FechaFin", "IdDepartamento"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);

        // Añadir algunos datos de ejemplo
        tableModel.addRow(new Object[]{"1", "Proyecto A", "2023-01-01", "2023-12-31", "101"});
        tableModel.addRow(new Object[]{"2", "Proyecto B", "2023-02-01", "2023-11-30", "102"});
        tableModel.addRow(new Object[]{"3", "Proyecto C", "2023-03-01", "2023-10-31", "103"});

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(780, 150));
        southPanel.add(scrollPane, BorderLayout.CENTER); // Añadir la tabla al centro del panel sur

        add(southPanel, BorderLayout.SOUTH); // Añadir el panel sur al sur de la ventana

        // Añadir action listeners para los botones
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

        // Añadir un mouse listener a la tabla
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    txtIdProyecto.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    txtNombre.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    txtFechaInicio.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    txtFechaFin.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    txtIdDepartamento.setText(tableModel.getValueAt(selectedRow, 4).toString());
                }
            }
        });

        setResizable(true); // Permitir cambiar el tamaño de la ventana
    }

    private void clearFields() {
        // Limpiar los campos de entrada
        txtIdProyecto.setText("");
        txtNombre.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        txtIdDepartamento.setText("");
        txtIdProyecto.requestFocus(); // Colocar el cursor en el campo de ID del proyecto
    }

    private void saveProject() {
        // Guardar un proyecto
        String idProyecto = txtIdProyecto.getText();
        String nombre = txtNombre.getText();
        String fechaInicio = txtFechaInicio.getText();
        String fechaFin = txtFechaFin.getText();
        String idDepartamento = txtIdDepartamento.getText();

        if (!idProyecto.isEmpty() && !nombre.isEmpty()) {
            tableModel.addRow(new Object[]{idProyecto, nombre, fechaInicio, fechaFin, idDepartamento});
            clearFields(); // Limpiar campos después de guardar
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese al menos el ID del Proyecto y el Nombre.", "Información requerida", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteProject() {
        // Eliminar un proyecto
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            clearFields(); // Limpiar campos después de eliminar
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un proyecto para eliminar.", "Selección requerida", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateProject() {
        // Actualizar un proyecto
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.setValueAt(txtIdProyecto.getText(), selectedRow, 0);
            tableModel.setValueAt(txtNombre.getText(), selectedRow, 1);
            tableModel.setValueAt(txtFechaInicio.getText(), selectedRow, 2);
            tableModel.setValueAt(txtFechaFin.getText(), selectedRow, 3);
            tableModel.setValueAt(txtIdDepartamento.getText(), selectedRow, 4);
            clearFields(); // Limpiar campos después de actualizar
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un proyecto para actualizar.", "Selección requerida", JOptionPane.WARNING_MESSAGE);
        }
    }

    //public static void main(String[] args) {
      //  new ProyectosGUI().setVisible(true); // Mostrar la interfaz gráfica
    //}
}
