package hello;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddPetDialog extends JDialog {
    private JTextField nameTextField;
    private JTextField breedTextField;
    private JTextField ageTextField;
    private JLabel pictureLabel;
    private JButton uploadPictureButton;
    private File selectedPicture;
    private boolean okPressed;
    
    public AddPetDialog(Frame parent) {
        super(parent, "Add Pet", true);
        
        setSize(400, 300);
        setLocationRelativeTo(parent);
        
        okPressed = false;
        
        // Create components
        JLabel nameLabel = new JLabel("Name:");
        JLabel breedLabel = new JLabel("Breed:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel pictureUploadLabel = new JLabel("Upload Picture:");
        pictureLabel = new JLabel();
        pictureLabel.setPreferredSize(new Dimension(150, 150));
        pictureLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        uploadPictureButton = new JButton("Upload");
        
        nameTextField = new JTextField();
        breedTextField = new JTextField();
        ageTextField = new JTextField();
        
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        
        // Create layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameTextField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(breedLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(breedTextField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(ageLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(ageTextField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(pictureUploadLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(pictureLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(uploadPictureButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(cancelButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(okButton, gbc);
        
        // Add action listeners
        uploadPictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif"));
                int result = fileChooser.showOpenDialog(AddPetDialog.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedPicture = fileChooser.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(selectedPicture.getPath());
                    Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    pictureLabel.setIcon(new ImageIcon(image));
                }
            }
        });
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okPressed = true;
                dispose();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    public String getName() {
        return nameTextField.getText();
    }
    
    public String getBreed() {
        return breedTextField.getText();
    }
    
    public String getAge() {
        return ageTextField.getText();
    }
    
    public File getSelectedPicture() {
        return selectedPicture;
    }
    
    public boolean isOkPressed() {
        return okPressed;
    }
}
