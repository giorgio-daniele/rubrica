// model/ContactTableModel.java
package model;

import javax.swing.table.AbstractTableModel;
import java.util.List;


@SuppressWarnings("serial")
public class ContactTable extends AbstractTableModel {

	 private List<Contact> contacts;              // List of contacts to display
	 private final String[] columns = {           // Column headers for the table
	     "Nome", 
	     "Cognome", 
	     "Telefono"
	 };
	
	 public ContactTable(List<Contact> contacts) {
	     this.contacts = contacts;
	 }
	
	 // Update the contact list and refresh the table view
	 public void setContacts(List<Contact> contacts) {
	     this.contacts = contacts;
	     fireTableDataChanged();
	 }
	
	 // Returns the number of rows (contacts)
	 @Override
	 public int getRowCount() {
	     return contacts.size();
	 }
	
	 // Returns the number of columns
	 @Override
	 public int getColumnCount() {
	     return columns.length;
	 }
	
	 // Returns the value at the specified row and column
	 @Override
	 public Object getValueAt(int rowIndex, int columnIndex) {
	     Contact contact = contacts.get(rowIndex);
	     switch (columnIndex) {
	         case 0:
	             return contact.getFirstName();
	         case 1:
	             return contact.getLastName();
	         case 2:
	             return contact.getPhone();
	         default:
	             return null;
	     }
	 }
	
	 // Returns the name of the specified column
	 @Override
	 public String getColumnName(int column) {
	     return columns[column];
	 }
}

