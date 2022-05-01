package dao;

import db.DBConnection;
import model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl {
     public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
          Connection connection = DBConnection.getDbConnection().getConnection();
          Statement stm = connection.createStatement();
          ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
          ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
          while (rst.next()){
               String id = rst.getString(1);
               String name = rst.getString(2);
               String address = rst.getString(3);
               allCustomers.add(new CustomerDTO(id,name,address));

          }
          return allCustomers;
     }

     /*public void updateCustomer{
          Connection connection = null;
          try {
               connection = DBConnection.getDbConnection().getConnection();
          } catch (SQLException throwables) {
               throwables.printStackTrace();
          } catch (ClassNotFoundException e) {
               e.printStackTrace();
          }
          PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
          pstm.setString(1, name);
          pstm.setString(2, address);
          pstm.setString(3, id);
          pstm.executeUpdate();
     }*/

     public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
          Connection connection = DBConnection.getDbConnection().getConnection();
          PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
          ArrayList<CustomerDTO> newCustomers = new ArrayList<>();
          pstm.setString(1, dto.getId());
          pstm.setString(2, dto.getName());
          pstm.setString(3, dto.getAddress());
          return pstm.executeUpdate()>0;
     }

     public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
          Connection connection = DBConnection.getDbConnection().getConnection();
          PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
          pstm.setString(1, dto.getId());
          pstm.setString(2, dto.getName());
          pstm.setString(3, dto.getName());
          return pstm.executeUpdate()>0;
     }

     public boolean deleteCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
          Connection connection = DBConnection.getDbConnection().getConnection();
          PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
          pstm.setString(1, dto.getId());
          return pstm.executeUpdate()>0;
     }
}
