import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DepartamentosGUI extends JFrame {

    private JTextField txtId, txtName, txtPhone, txtFax;
    private JButton btnNew, btnSave, btnDelete, btnUpdate;
    private JTable table;
    private DefaultTableModel tableModel;
    private DatabaseConnection dbConnection;

    public DepartamentosGUI() {
        dbConnection = new DatabaseConnection();
        configurarVentana();
        crearTitulo();
        crearPanelEntrada();
        crearPanelBotonesYTabla();
        agregarListeners();
        setResizable(true); // Permitir cambiar el tamaño de la ventana
        cargarDepartamentos();
    }

    private void configurarVentana() {
        setTitle("DEPARTAMENTOS");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new BorderLayout(10, 10)); // Establecer el diseño de la ventana
    }

    private void crearTitulo() {
        JLabel lblTitle = new JLabel("DEPARTAMENTOS", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setOpaque(true); // Hacer que el fondo sea opaco
        lblTitle.setBackground(Color.BLACK);
        lblTitle.setForeground(Color.WHITE);
        add(lblTitle, BorderLayout.NORTH); // Añadir el título al norte de la ventana
    }

    private void crearPanelEntrada() {
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añadir un borde vacío alrededor

        inputPanel.add(new JLabel("Id del Departamento:"));
        txtId = new JTextField(10);
        inputPanel.add(txtId);

        inputPanel.add(new JLabel("Nombre:"));
        txtName = new JTextField(10);
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Teléfono:"));
        txtPhone = new JTextField(10);
        inputPanel.add(txtPhone);

        inputPanel.add(new JLabel("FAX:"));
        txtFax = new JTextField(10);
        inputPanel.add(txtFax);

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

        String[] columnNames = {"IdDepartamento", "Nombre", "Telefono", "Fax"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(580, 150));
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
                saveDepartment(); // Guardar departamento
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDepartment(); // Eliminar departamento
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDepartment(); // Actualizar departamento
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    txtName.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    txtPhone.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    txtFax.setText(tableModel.getValueAt(selectedRow, 3).toString());
                }
            }
        });
    }

    private void cargarDepartamentos() {
        List<String[]> departments = dbConnection.getDepartments();
        for (String[] department : departments) {
            tableModel.addRow(department);
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtPhone.setText("");
        txtFax.setText("");
        txtId.requestFocus(); // Colocar el cursor en el campo de ID
    }

    private void saveDepartment() {
        String id = txtId.getText();
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String fax = txtFax.getText();

        if (!id.isEmpty() && !name.isEmpty() && !phone.isEmpty() && !fax.isEmpty()) {
            if (dbConnection.departmentExists(id)) {
                JOptionPane.showMessageDialog(this, "Ya existe un departamento con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                dbConnection.insertDepartment(id, name, phone, fax); // Guardar en la base de datos
                tableModel.addRow(new Object[]{id, name, phone, fax});
                clearFields(); // Limpiar campos después de guardar
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        }
    }

    private void deleteDepartment() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String id = tableModel.getValueAt(selectedRow, 0).toString();

            // Verificar si hay proyectos asociados
            List<String> projects = dbConnection.getProjectsByDepartment(id);
            if (!projects.isEmpty()) {
                // Mostrar proyectos asociados en un JOptionPane
                StringBuilder message = new StringBuilder("No es posible eliminar el departamento porque tiene proyectos asociados:\nIdProyectos Asociados:\n");
                for (String projectId : projects) {
                    message.append("- ").append(projectId).append("\n");
                }
                JOptionPane.showMessageDialog(this, message.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Si no hay proyectos asociados, proceder con la eliminación
                tableModel.removeRow(selectedRow);
                dbConnection.deleteDepartment(id); // Eliminar de la base de datos
                clearFields(); // Limpiar campos después de eliminar
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un departamento para eliminar.");
        }
    }

    private void updateDepartment() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String oldId = tableModel.getValueAt(selectedRow, 0).toString(); // Obtener el ID antiguo
            String newId = txtId.getText(); // Nuevo ID (si se ha modificado)
            String name = txtName.getText();
            String phone = txtPhone.getText();
            String fax = txtFax.getText();

            if (!oldId.isEmpty() && !newId.isEmpty() && !name.isEmpty() && !phone.isEmpty() && !fax.isEmpty()) {
                // Verificar si el nuevo ID es único antes de actualizar
                if (!newId.equals(oldId) && dbConnection.departmentExists(newId)) {
                    JOptionPane.showMessageDialog(this, "Ya existe un departamento con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si el nuevo ID no es único
                }
                // Verificar si hay proyectos asociados
                
                List<String> projects = dbConnection.getProjectsByDepartment(oldId);
                if (!projects.isEmpty()) {
                    // Mostrar proyectos asociados en un JOptionPane
                    StringBuilder message = new StringBuilder("No es posible Actualizar el departamento porque tiene proyectos asociados:\nIdProyectos Asociados:\n");
                    for (String projectId : projects) {
                        message.append("- ").append(projectId).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, message.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si el nuevo ID no es único
                }
                
                tableModel.setValueAt(name, selectedRow, 1);
                tableModel.setValueAt(phone, selectedRow, 2);
                tableModel.setValueAt(fax, selectedRow, 3);

                // Actualizar en la base de datos con el ID antiguo y nuevo (si cambió)
                dbConnection.updateDepartment(oldId, newId, name, phone, fax); 
                clearFields(); // Limpiar campos después de actualizar
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un departamento para actualizar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DepartamentosGUI().setVisible(true);
            }
        });
    }
}