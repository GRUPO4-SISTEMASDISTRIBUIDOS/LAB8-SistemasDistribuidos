import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Ingeniero extends javax.swing.JFrame {

	public Ingeniero() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		dbConnection = new DatabaseConnection();
		mainPanel = new java.awt.Panel();
		idPanel = new java.awt.Panel();
		namePanel = new java.awt.Panel();
		specialtyPanel = new java.awt.Panel();
		positionPanel = new java.awt.Panel();
		titlePanel = new java.awt.Panel();
		buttonPanel = new java.awt.Panel();
		idLabel = new javax.swing.JLabel();
		nameLabel = new javax.swing.JLabel();
		specialtyLabel = new javax.swing.JLabel();
		positionLabel = new javax.swing.JLabel();
		titleLabel = new java.awt.Label();
		idTextField = new javax.swing.JTextField();
		nameTextField = new javax.swing.JTextField();
		positionTextField = new javax.swing.JTextField();
		specialtyTextField = new javax.swing.JTextField();
		newButton = new javax.swing.JButton();
		saveButton = new javax.swing.JButton();
		deleteButton = new javax.swing.JButton();
		updateButton = new javax.swing.JButton();
		tableScrollPane = new javax.swing.JScrollPane();
		engineerTable = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		mainPanel.setLayout(new java.awt.GridLayout(2, 2, 15, 15));
		idLabel.setText("ID");

		javax.swing.GroupLayout idPanelLayout = new javax.swing.GroupLayout(idPanel);
		idPanel.setLayout(idPanelLayout);
		idPanelLayout.setHorizontalGroup(idPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(idPanelLayout.createSequentialGroup().addContainerGap()
						.addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(34, 34, 34).addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 115,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		idPanelLayout.setVerticalGroup(idPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(idPanelLayout.createSequentialGroup()
						.addGroup(idPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(idLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(0, 12, Short.MAX_VALUE)));

		mainPanel.add(idPanel);
		nameLabel.setText("Nombre");

		javax.swing.GroupLayout namePanelLayout = new javax.swing.GroupLayout(namePanel);
		namePanel.setLayout(namePanelLayout);
		namePanelLayout
				.setHorizontalGroup(namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(namePanelLayout.createSequentialGroup().addContainerGap()
								.addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(34, 34, 34).addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
										115, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		namePanelLayout.setVerticalGroup(namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(namePanelLayout.createSequentialGroup()
						.addGroup(namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(0, 0, Short.MAX_VALUE)));

		mainPanel.add(namePanel);

		specialtyLabel.setText("Especialidad");

		javax.swing.GroupLayout specialtyPanelLayout = new javax.swing.GroupLayout(specialtyPanel);
		specialtyPanel.setLayout(specialtyPanelLayout);
		specialtyPanelLayout
				.setHorizontalGroup(specialtyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(specialtyPanelLayout.createSequentialGroup().addContainerGap()
								.addComponent(specialtyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(34, 34, 34)
								.addComponent(specialtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 115,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		specialtyPanelLayout.setVerticalGroup(specialtyPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(specialtyPanelLayout.createSequentialGroup()
						.addGroup(specialtyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(specialtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(specialtyLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(0, 0, Short.MAX_VALUE)));

		mainPanel.add(specialtyPanel);

		positionLabel.setText("Cargo");

		javax.swing.GroupLayout positionPanelLayout = new javax.swing.GroupLayout(positionPanel);
		positionPanel.setLayout(positionPanelLayout);
		positionPanelLayout
				.setHorizontalGroup(positionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(positionPanelLayout.createSequentialGroup().addContainerGap()
								.addComponent(positionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(34, 34, 34)
								.addComponent(positionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 115,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		positionPanelLayout.setVerticalGroup(positionPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(positionPanelLayout.createSequentialGroup()
						.addGroup(positionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(positionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(positionLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(0, 0, Short.MAX_VALUE)));

		mainPanel.add(positionPanel);

		titlePanel.setBackground(new java.awt.Color(0, 0, 0));

		titleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		titleLabel.setForeground(new java.awt.Color(255, 255, 255));
		titleLabel.setName(""); // NOI18N
		titleLabel.setText("INGENIEROS");
		titlePanel.add(titleLabel);

		java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 5);
		flowLayout1.setAlignOnBaseline(true);
		buttonPanel.setLayout(flowLayout1);

		newButton.setText("Nuevo");
		buttonPanel.add(newButton);

		saveButton.setText("Guardar");
		buttonPanel.add(saveButton);

		deleteButton.setText("Eliminar");
		buttonPanel.add(deleteButton);

		updateButton.setText("Actualizar");
		buttonPanel.add(updateButton);

		String[] columnNames = { "ID", "Nombre", "Especialidad", "Cargo" };
		tableModel = new DefaultTableModel(columnNames, 0);
		engineerTable = new JTable(tableModel);
		engineerTable.setRowHeight(25);

		tableScrollPane.setViewportView(engineerTable);
		cargarIngenieros();
		agregarListeners();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(titlePanel, javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(138, 138, 138).addComponent(buttonPanel,
								javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(57, 57, 57)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 671,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 663,
												javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(64, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(62, 62, 62)
						.addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 130,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(27, 27, 27)
						.addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(36, 36, 36).addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 158,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(25, Short.MAX_VALUE)));

		pack();
	}

	private void agregarListeners() {
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearFields(); // Limpiar campos
			}
		});

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarIngeniero(); // Guardar departamento
			}
		});

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarIngeniero(); // Eliminar departamento
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarIngeniero(); // Actualizar departamento
			}
		});

		engineerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = engineerTable.getSelectedRow();
				if (selectedRow >= 0) {
					idTextField.setText(tableModel.getValueAt(selectedRow, 0).toString());
					nameTextField.setText(tableModel.getValueAt(selectedRow, 1).toString());
					specialtyTextField.setText(tableModel.getValueAt(selectedRow, 2).toString());
					positionTextField.setText(tableModel.getValueAt(selectedRow, 3).toString());
				}
			}
		});

	}

	private void cargarIngenieros() {
		List<String[]> ingenieros = dbConnection.getEngineers();
		for (String[] ingeniero : ingenieros) {
			tableModel.addRow(ingeniero);
		}
	}

	private void clearFields() {
		idTextField.setText("");
		specialtyTextField.setText("");
		nameTextField.setText("");
		positionTextField.setText("");
		idTextField.requestFocus();
	}

	private void guardarIngeniero() {
		String id = idTextField.getText();
		String name = nameTextField.getText();
		String post = positionTextField.getText();
		String specialty = specialtyTextField.getText();

		if (!id.isEmpty() && !name.isEmpty() && !post.isEmpty() && !specialty.isEmpty()) {
			if (dbConnection.engineerExists(id)) {
				JOptionPane.showMessageDialog(this, "Ya existe un Ingeniero con ese ID.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				dbConnection.insertEngineers(id, name, post, specialty); // Guardar en la base de datos
				tableModel.addRow(new Object[] { id, name, post, specialty });
				clearFields(); // Limpiar campos después de guardar
			}
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
		}
	}

	private void actualizarIngeniero() {
		int selectedRow = engineerTable.getSelectedRow();
        if (selectedRow >= 0) {
            String oldId = tableModel.getValueAt(selectedRow, 0).toString(); // Obtener el ID antiguo
            String newId = idTextField.getText(); // Nuevo ID (si se ha modificado)
    		String name = nameTextField.getText();
    		String post = positionTextField.getText();
    		String specialty = specialtyTextField.getText();

            if (!oldId.isEmpty() && !newId.isEmpty() && !name.isEmpty() && !post.isEmpty() && !specialty.isEmpty()) {
                if (!newId.equals(oldId) && dbConnection.engineerExists(newId)) {
                    JOptionPane.showMessageDialog(this, "Ya existe un ingeniero con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                
                List<String> assignment = dbConnection.getAssignmentByEngineer(oldId);
                if (!assignment.isEmpty()) {
                    // Mostrar proyectos asociados en un JOptionPane
                    StringBuilder message = new StringBuilder("No es posible Actualizar el ingeniero porque tiene asignaciones asociados:\nAsignaciones Asociados:\n");
                    for (String assignmentId : assignment) {
                        message.append("- ").append(assignmentId).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, message.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }

                tableModel.setValueAt(newId,selectedRow, 0);
                tableModel.setValueAt(name, selectedRow, 1);
                tableModel.setValueAt(specialty, selectedRow, 2);
                tableModel.setValueAt(post, selectedRow, 3);

                dbConnection.updateEngineer(oldId, newId, name, post, specialty); 
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un departamento para actualizar.");
        }
	}

	private void eliminarIngeniero() {
        int selectedRow = engineerTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = tableModel.getValueAt(selectedRow, 0).toString();

            List<String> assignment = dbConnection.getAssignmentByEngineer(id);
            if (!assignment.isEmpty()) {
                StringBuilder message = new StringBuilder("No es posible eliminar el ingeniero porque tiene asignacion asociados:\nFecha asignación:\n");
                for (String assignmentId : assignment) {
                    message.append("- ").append(assignmentId).append("\n");
                }
                JOptionPane.showMessageDialog(this, message.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Si no hay proyectos asociados, proceder con la eliminación
                tableModel.removeRow(selectedRow);
                dbConnection.deleteEngineer(id); // Eliminar de la base de datos
                clearFields(); // Limpiar campos después de eliminar
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un ingeniero para eliminar.");
        }
	}

	// Variables declaration - do not modify
	private DatabaseConnection dbConnection;
	private java.awt.Panel mainPanel;
	private javax.swing.JButton newButton;
	private javax.swing.JButton saveButton;
	private javax.swing.JButton deleteButton;
	private javax.swing.JButton updateButton;
	private javax.swing.JLabel idLabel;
	private javax.swing.JLabel specialtyLabel;
	private javax.swing.JLabel nameLabel;
	private javax.swing.JLabel positionLabel;
	private javax.swing.JScrollPane tableScrollPane;
	private javax.swing.JTable engineerTable;
	private javax.swing.JTextField idTextField;
	private javax.swing.JTextField specialtyTextField;
	private javax.swing.JTextField nameTextField;
	private javax.swing.JTextField positionTextField;
	private java.awt.Label titleLabel;
	private java.awt.Panel idPanel;
	private java.awt.Panel buttonPanel;
	private java.awt.Panel specialtyPanel;
	private java.awt.Panel positionPanel;
	private java.awt.Panel namePanel;
	private java.awt.Panel titlePanel;
	private DefaultTableModel tableModel;
	// End of variables declaration
}
