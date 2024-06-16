import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AsignacionesGUI extends JFrame {

    private JTextField txtIDProyecto, txtIDIngeniero, txtFechaAsignacion;
    private JButton btnNew, btnSave, btnDelete, btnUpdate;
    private JTable table;
    private DefaultTableModel tableModel;

    public AsignacionesGUI() {
        // Configuración del marco (ventana)
        setTitle("ASIGNACIONES");
        setSize(600, 500); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new BorderLayout(10, 10)); // Establecer el diseño de la ventana

        // Crear el título
        JLabel lblTitle = new JLabel("ASIGNACIONES", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setOpaque(true); // Hacer que el fondo sea opaco
        lblTitle.setBackground(Color.BLACK);
        lblTitle.setForeground(Color.WHITE);
        add(lblTitle, BorderLayout.NORTH); // Añadir el título al norte de la ventana

        // Crear el panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añadir un borde vacío alrededor
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("ID del Proyecto:"), gbc);
        txtIDProyecto = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(txtIDProyecto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("ID del Ingeniero:"), gbc);
        txtIDIngeniero = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(txtIDIngeniero, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Fecha de Asignación:"), gbc);
        txtFechaAsignacion = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(txtFechaAsignacion, gbc);

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
        String[] columnNames = {"IDProyecto", "IDIngeniero", "FechaAsignacion"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);

        // Añadir algunos datos de ejemplo
        tableModel.addRow(new Object[]{"1", "2001", "2023-01-01"});
        tableModel.addRow(new Object[]{"2", "2002", "2023-02-01"});
        tableModel.addRow(new Object[]{"3", "2003", "2023-03-01"});

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
                saveAssignment(); // Guardar asignación
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAssignment(); // Eliminar asignación
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAssignment(); // Actualizar asignación
            }
        });

        // Añadir un mouse listener a la tabla
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    txtIDProyecto.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    txtIDIngeniero.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    txtFechaAsignacion.setText(tableModel.getValueAt(selectedRow, 2).toString());
                }
            }
        });

        setResizable(true); // Permitir cambiar el tamaño de la ventana
    }

    private void clearFields() {
        // Limpiar los campos de entrada
        txtIDProyecto.setText("");
        txtIDIngeniero.setText("");
        txtFechaAsignacion.setText("");
        txtIDProyecto.requestFocus(); // Colocar el cursor en el campo de ID del proyecto
    }

    private void saveAssignment() {
        // Guardar una asignación
        String idProyecto = txtIDProyecto.getText();
        String idIngeniero = txtIDIngeniero.getText();
        String fechaAsignacion = txtFechaAsignacion.getText();

        if (!idProyecto.isEmpty() && !idIngeniero.isEmpty()) {
            tableModel.addRow(new Object[]{idProyecto, idIngeniero, fechaAsignacion});
            clearFields(); // Limpiar campos después de guardar
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese al menos el ID del Proyecto y el ID del Ingeniero.", "Información requerida", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteAssignment() {
        // Eliminar una asignación
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            clearFields(); // Limpiar campos después de eliminar
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una asignación para eliminar.", "Selección requerida", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateAssignment() {
        // Actualizar una asignación
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.setValueAt(txtIDProyecto.getText(), selectedRow, 0);
            tableModel.setValueAt(txtIDIngeniero.getText(), selectedRow, 1);
            tableModel.setValueAt(txtFechaAsignacion.getText(), selectedRow, 2);
            clearFields(); // Limpiar campos después de actualizar
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una asignación para actualizar.", "Selección requerida", JOptionPane.WARNING_MESSAGE);
        }
    }

    //public static void main(String[] args) {
       
      //          new AsignacionesGUI().setVisible(true); // Mostrar la interfaz gráfica
            
        
    //}
}
