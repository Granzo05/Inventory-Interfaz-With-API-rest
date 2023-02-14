package org.granzo.inventory.userInterface;

import org.granzo.inventory.controller.ApiController;
import org.granzo.inventory.entities.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InventoryInterface extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JLabel codeLabel;
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel unitsLabel;
    private JLabel priceLabel;
    private JLabel nextArrivalLabel;
    private JLabel searchLabel;
    private JTextField codeTextField;
    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private JTextField unitsTextField;
    private JTextField priceTextField;
    private JTextField nextArrivalTextField;
    private JTextField searchTextField;
    private JButton uploadButton;
    private JButton updateButton;
    private JButton cleanButton;
    private JButton deleteButton;
    private JButton searchButton;
    private final ApiController api;

    public InventoryInterface() {
        setTitle("My Form");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        initComponents();
        propertiesComponents();

        api = new ApiController();
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    api.uploadProduct(getFields());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    api.updateProduct(getFields());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product();
                try {
                    product = api.getProduct(searchTextField.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                codeTextField.setText(product.getCode());
                nameTextField.setText(product.getProductName());
                descriptionTextField.setText(product.getDescription());
                unitsTextField.setText(String.valueOf(product.getUnits()));
                priceTextField.setText(String.valueOf(product.getPrice()));
                nextArrivalTextField.setText(product.getNextArrival());
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    api.deleteProduct(codeTextField.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codeTextField.setText("");
                nameTextField.setText("");
                descriptionTextField.setText("");
                unitsTextField.setText("");
                priceTextField.setText("");
                nextArrivalTextField.setText("");
            }
        });
    }

    private Product getFields() {
        Product product = new Product();
        product.setCode(codeTextField.getText());
        product.setProductName(nameTextField.getText());
        product.setDescription(descriptionTextField.getText());
        if(unitsTextField.getText().isEmpty()){
            product.setUnits(0);
        }else {
            product.setUnits(Integer.parseInt(unitsTextField.getText()));
        }

        if(priceTextField.getText().isEmpty()){
            product.setPrice(0);
        }else {
            product.setPrice(Double.parseDouble(priceTextField.getText()));
        }
        product.setNextArrival(nextArrivalTextField.getText());

        return product;
    }

    private void propertiesComponents() {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        JSeparator verticalSeparator = new JSeparator();
        verticalSeparator.setPreferredSize(new Dimension(150, 150));

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(codeLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(codeTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(nameLabel, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        panel.add(uploadButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(nameTextField, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        panel.add(updateButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(descriptionLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(descriptionTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(unitsLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(unitsTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(priceLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(priceTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(nextArrivalLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(nextArrivalTextField, constraints);

        constraints.gridx = 2;
        constraints.gridy = 5;
        panel.add(deleteButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(verticalSeparator, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        panel.add(verticalSeparator, constraints);

        constraints.gridx = 2;
        constraints.gridy = 6;
        panel.add(verticalSeparator, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(searchLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        panel.add(searchTextField, constraints);

        constraints.gridx = 2;
        constraints.gridy = 6;
        panel.add(searchButton, constraints);


        setContentPane(panel);
    }

    private void initComponents() {
        frame = new JFrame();
        panel = new JPanel();
        codeLabel = new JLabel("Code");
        nameLabel = new JLabel("Name");
        descriptionLabel = new JLabel("Description");
        unitsLabel = new JLabel("Units");
        priceLabel = new JLabel("Price");
        nextArrivalLabel = new JLabel("Next Arrival");
        searchLabel = new JLabel("Search");
        codeTextField = new JTextField(60);
        nameTextField = new JTextField(60);
        descriptionTextField = new JTextField(60);
        unitsTextField = new JTextField(60);
        priceTextField = new JTextField(60);
        nextArrivalTextField = new JTextField(60);
        searchTextField = new JTextField(60);
        uploadButton = new JButton("Upload");
        updateButton = new JButton("Update");
        cleanButton = new JButton("Clean");
        deleteButton = new JButton("Delete product");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.white);
        searchButton = new JButton("Search");
    }


}

