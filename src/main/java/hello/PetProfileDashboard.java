package hello;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PetProfileDashboard extends JFrame {
	private JLabel titleLabel;
	private JButton addPetButton;
	private JTable petTable;
	private JButton viewDetailsButton;

	public PetProfileDashboard() {
		setTitle("Pet Profile Management Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);

		// Create components
		titleLabel = new JLabel("Pet Profile Management");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

		addPetButton = new JButton("Add Pet");

		// Sample data for the pet table
		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] { { "1", "Max", "Labrador Retriever", "4" },
				{ "2", "Bella", "Poodle", "3" }, { "3", "Charlie", "Golden Retriever", "2" },
				{ "4", "Lucy", "Shih Tzu", "5" }, { "5", "Cooper", "Bulldog", "6" } },
				new Object[] { "ID", "Name", "Breed", "Age" });

		petTable = new JTable(tableModel);
		// ((Object) tableModel).get();

		viewDetailsButton = new JButton("View Details");
		viewDetailsButton.setEnabled(false);

		// Create layout
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(titleLabel);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(addPetButton);
		buttonPanel.add(viewDetailsButton);

		JScrollPane tableScrollPane = new JScrollPane(petTable);

		add(topPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
		add(tableScrollPane, BorderLayout.SOUTH);

		// Add action listeners
		addPetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Add pet button action
				AddPetDialog dialog = new AddPetDialog(PetProfileDashboard.this);
				dialog.setVisible(true);

				// Check if the dialog was closed with OK button
				if (dialog.isOkPressed()) {
					String id = Integer.toString(tableModel.getRowCount() + 1);
					String name = dialog.getName();
					String breed = dialog.getBreed();
					String age = dialog.getAge();

					tableModel.addRow(new Object[] { id, name, breed, age });

					JOptionPane.showMessageDialog(PetProfileDashboard.this, "Pet added successfully!");
				}
			}
		});

		viewDetailsButton.addActionListener(new ActionListener() { 

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog petDetailsDialog = new JDialog();
				petDetailsDialog.setSize(400, 400);
				petDetailsDialog.setLocationRelativeTo(null);
				petDetailsDialog.setLayout(new BorderLayout());

				JPanel contentPanel = new JPanel();
				contentPanel.setLayout(new BorderLayout());

				JLabel nameLabel = new JLabel("Name: " + "");
				nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
				contentPanel.add(nameLabel, BorderLayout.NORTH);

				JLabel pictureLabel = new JLabel();
				pictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
				contentPanel.add(pictureLabel, BorderLayout.CENTER);

				// Load image from src folder
				try {
					URL imageUrl = getClass().getResource("./" + "hound.jpeg");
					BufferedImage petImage = ImageIO.read(imageUrl);
					int maxWidth = 300;
					int maxHeight = 300;
					Image scaledImage = petImage.getScaledInstance(maxWidth, maxHeight, Image.SCALE_SMOOTH);
					pictureLabel.setIcon(new ImageIcon(scaledImage));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				petDetailsDialog.add(contentPanel, BorderLayout.CENTER);

				JButton closeButton = new JButton("Close");
				closeButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						petDetailsDialog.dispose();
					}
				});

				petDetailsDialog.add(closeButton, BorderLayout.SOUTH);

				petDetailsDialog.setVisible(true);
			}
		});

		petTable.getSelectionModel().addListSelectionListener(event -> {
			// Enable or disable the view details button based on table selection
			viewDetailsButton.setEnabled(petTable.getSelectedRow() >= 0);
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			PetProfileDashboard dashboard = new PetProfileDashboard();
			dashboard.pack();
			dashboard.setVisible(true);
		});
	}
}
