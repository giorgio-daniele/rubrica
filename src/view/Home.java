package view;

import model.ContactTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.awt.*;

@SuppressWarnings("serial")
public class Home extends JFrame {

    public static final int WIDTH  = 800;
    public static final int HEIGHT = 600;

    // UI components
    private JTable   table;
    private JButton  addButton;
    private JButton  editButton;
    private JButton  deleteButton;
    private JToolBar toolBar;

    // Deprecated: bottom button panel (no longer used)
    // private JPanel buttonPanel;

    public Home() {
        super("Rubrica");
        initUI();
    }

    private void initUI() {
        // Set up main frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Initialize UI components
        this.table = new JTable();
        
        // Initialize buttons
        this.addButton = new JButton("Nuovo", new ImageIcon(
        		new ImageIcon(getClass().getResource("/icons/add.png"))
        		.getImage()
        		.getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        
        this.editButton = new JButton("Modifica", new ImageIcon(
        		new ImageIcon(getClass().getResource("/icons/edt.png"))
        		.getImage()
        		.getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        
        this.deleteButton = new JButton("Elimina", new ImageIcon(
        		new ImageIcon(getClass().getResource("/icons/del.png"))
        		.getImage()
        		.getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        
        // Set single selection mode
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Increase row height slightly for better readability
        table.setRowHeight(24);

        // Make header font bold and a bit larger
        JTableHeader header = table.getTableHeader();
        Font headerFont = header.getFont().deriveFont(Font.BOLD, header.getFont().getSize() + 2f);
        header.setFont(headerFont);

        // Center-align the header text
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        // Align cell content: left align text columns, center align numeric columns
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Create toolbar and add buttons
        this.toolBar = new JToolBar();
        this.toolBar.setFloatable(false); // disable dragging
        this.toolBar.add(this.addButton);
        this.toolBar.add(this.editButton);
        this.toolBar.add(this.deleteButton);

        // Deprecated: bottom panel with buttons
        // this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // this.buttonPanel.add(this.addButton);
        // this.buttonPanel.add(this.editButton);
        // this.buttonPanel.add(this.deleteButton);

        // Add components to the frame
        add(this.toolBar, BorderLayout.NORTH);                 // toolbar at the top
        add(new JScrollPane(this.table), BorderLayout.CENTER); // table in the center
        // add(this.buttonPanel, BorderLayout.SOUTH);          // optional: bottom panel (not used)
    }

    // Table getter
    public JTable getTable() {
        return table;
    }

    // Table model setter
    public void setTableModel(ContactTable model) {
        this.table.setModel(model);
    }

    // Button getters
    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    // Toolbar getter
    public JToolBar getToolBar() {
        return toolBar;
    }

    // Deprecated: button panel getter
    // public JPanel getButtonPanel() {
    //     return buttonPanel;
    // }
}
