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

public class AsignacionesGUI extends JFrame {

	private JTextField txtFechaAsignacion;
	private JButton btnNew, btnSave, btnDelete, btnUpdate;
    private JComboBox<String> cmbIdProyecto;
    private JComboBox<String> cmbIdIngeniero;
	private JTable table;
	private DefaultTableModel tableModel;
	private DatabaseConnection dbConnection;

	public AsignacionesGUI() {
		// Configuración del marco (ventana)
		dbConnection = new DatabaseConnection();
		setTitle("ASIGNACIONES");
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		cmbIdProyecto= new JComboBox<>();
        cargarProyectos(); 
		gbc.gridx = 1;
		gbc.gridy = 0;
		inputPanel.add(cmbIdProyecto, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		inputPanel.add(new JLabel("ID del Ingeniero:"), gbc);
		cmbIdIngeniero = new JComboBox<>();
        cargarIngenieros(); 
		gbc.gridx = 1;
		gbc.gridy = 1;
		inputPanel.add(cmbIdIngeniero, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		inputPanel.add(new JLabel("Fecha de Asignación:"), gbc);
		txtFechaAsignacion = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 2;
		inputPanel.add(txtFechaAsignacion, gbc);


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
		southPanel.add(buttonPanel, BorderLayout.NORTH); 

		// Crear la tabla
		String[] columnNames = { "IDProyecto", "IDIngeniero", "FechaAsignacion" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setRowHeight(25);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(780, 150));
		southPanel.add(scrollPane, BorderLayout.CENTER); 
		
        add(southPanel, BorderLayout.SOUTH); // Añadir el panel sur al sur de la ventana
		cargarAssignments();

		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearFields(); 
			}
		});

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveAssignment(); 
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteAssignment(); 
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateAssignment(); 
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
                    cmbIdProyecto.setSelectedItem(tableModel.getValueAt(selectedRow, 0).toString());
                    cmbIdIngeniero.setSelectedItem(tableModel.getValueAt(selectedRow, 1).toString());
					txtFechaAsignacion.setText(tableModel.getValueAt(selectedRow, 2).toString());
				}
			}
		});

		setResizable(true);
	}

	private void cargarProyectos() {
		List<String[]> proyectos = dbConnection.getProjects();
        for (String[] proyecto : proyectos) {
            cmbIdProyecto.addItem(proyecto[0]); 
        }	
	}

	private void cargarIngenieros() {
        List<String[]> ingenieros = dbConnection.getEngineers();
        for (String[] ingeniero : ingenieros) {
            cmbIdIngeniero.addItem(ingeniero[0]); 
        }		
	}

	private void cargarAssignments() {
		List<String[]> assignments = dbConnection.getAssignments();
		for (String[] assignment : assignments) {
			tableModel.addRow(assignment);
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

	private void clearFields() {
		cmbIdProyecto.setSelectedIndex(-1); 
		cmbIdIngeniero.setSelectedIndex(-1); 
		txtFechaAsignacion.setText("");
	}

	private void saveAssignment() {
		String idProyect = (String) cmbIdProyecto.getSelectedItem();
		String idEngineer = (String) cmbIdIngeniero.getSelectedItem();
		String fecha = txtFechaAsignacion.getText();
		
        if (!isValidDateFormat(fecha)) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Utilice el formato: yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate startDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		if (!idProyect.isEmpty() && !idEngineer.isEmpty() && !fecha.isEmpty()) {
			if (dbConnection.assignmentExists(idProyect)) {
				JOptionPane.showMessageDialog(this, "Ya existe una asignación con ese ID.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				dbConnection.insertAssignments(idProyect, idEngineer, fecha); 
				tableModel.addRow(new Object[] { idProyect, idEngineer, fecha });
				clearFields(); 
			}
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
		}
	}

	private void deleteAssignment() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			String id = tableModel.getValueAt(selectedRow, 0).toString();
			tableModel.removeRow(selectedRow);
			dbConnection.deleteAssignment(id); 
			clearFields();
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione una asignación para eliminar.",
					"Selección requerida", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void updateAssignment() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
            String oldId = tableModel.getValueAt(selectedRow, 0).toString(); 
            String newId = (String) cmbIdProyecto.getSelectedItem();
    		String idEngineer = (String) cmbIdIngeniero.getSelectedItem();
    		String fecha = txtFechaAsignacion.getText();
    		
            if (!isValidDateFormat(fecha)) {
                JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Utilice el formato: yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate startDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (!oldId.isEmpty() && !newId.isEmpty() && !idEngineer.isEmpty() && !fecha.isEmpty()) {
                if (!newId.equals(oldId) && dbConnection.assignmentExists(newId)) {
                    JOptionPane.showMessageDialog(
                    		this, "Ya existe una asignación con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
    			tableModel.setValueAt(newId, selectedRow, 0);
    			tableModel.setValueAt(idEngineer, selectedRow, 1);
    			tableModel.setValueAt(txtFechaAsignacion.getText(), selectedRow, 2);
                dbConnection.updateAssignment(oldId, newId, idEngineer, fecha); 
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            }
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione una asignación para actualizar.",
					"Selección requerida", JOptionPane.WARNING_MESSAGE);
		}
	}

}
