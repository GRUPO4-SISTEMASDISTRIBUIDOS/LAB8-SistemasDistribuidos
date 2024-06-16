import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DepartamentosGUI extends JFrame {

    private JTextField txtId, txtName, txtPhone, txtFax;
    private JButton btnNew, btnSave, btnDelete, btnUpdate;
    private JTable table;
    private DefaultTableModel tableModel;

    public DepartamentosGUI() {
        // Configuración del marco (ventana)
        setTitle("DEPARTAMENTOS");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new BorderLayout(10, 10)); // Establecer el diseño de la ventana

        // Crear el título
        JLabel lblTitle = new JLabel("DEPARTAMENTOS", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setOpaque(true); // Hacer que el fondo sea opaco
        lblTitle.setBackground(Color.BLACK);
        lblTitle.setForeground(Color.WHITE);
        add(lblTitle, BorderLayout.NORTH); // Añadir el título al norte de la ventana

        // Crear el panel de entrada de datos
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
        String[] columnNames = {"Id", "Nombre", "Telefono", "Fax"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);

        // Añadir algunos datos de ejemplo
        tableModel.addRow(new Object[]{"1", "Seguridad", "963458596", "FAX1"});
        tableModel.addRow(new Object[]{"2", "Dinamica", "989384799", "FAX2"});
        tableModel.addRow(new Object[]{"3", "D2", "9864638389", "FAX3"});

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(580, 150));
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

        // Añadir un mouse listener a la tabla
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

        setResizable(true); // Permitir cambiar el tamaño de la ventana
    }

    private void clearFields() {
        // Limpiar los campos de entrada
        txtId.setText("");
        txtName.setText("");
        txtPhone.setText("");
        txtFax.setText("");
        txtId.requestFocus(); // Colocar el cursor en el campo de ID
    }

    private void saveDepartment() {
        // Guardar un departamento
        String id = txtId.getText();
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String fax = txtFax.getText();

        if (!id.isEmpty() && !name.isEmpty()) {
            tableModel.addRow(new Object[]{id, name, phone, fax});
            clearFields(); // Limpiar campos después de guardar
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese al menos el ID y el Nombre.", "Información requerida", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteDepartment() {
        // Eliminar un departamento
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            clearFields(); // Limpiar campos después de eliminar
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un departamento para eliminar.", "Selección requerida", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateDepartment() {
        // Actualizar un departamento
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.setValueAt(txtId.getText(), selectedRow, 0);
            tableModel.setValueAt(txtName.getText(), selectedRow, 1);
            tableModel.setValueAt(txtPhone.getText(), selectedRow, 2);
            tableModel.setValueAt(txtFax.getText(), selectedRow, 3);
            clearFields(); // Limpiar campos después de actualizar
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un departamento para actualizar.", "Selección requerida", JOptionPane.WARNING_MESSAGE);
        }
    }

    //public static void main(String[] args) {
      //  new DepartamentosGUI().setVisible(true); // Mostrar la interfaz gráfica
    //}
}

