package org.example;

import org.example.db.DBConnection;
import org.example.model.Customers;
import org.example.model.Items;
import org.example.model.Suppliers;
import org.example.model.Users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @PostMapping("/signIn")
    void signIn(@RequestBody Users users) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO signin VALUES(?,?)");
        pstm.setString(1,users.getUserName());
        pstm.setString(2,users.getPassword());
        pstm.executeUpdate();
    }

    @PostMapping("/logIn")
    Boolean logIn(@RequestBody Users users) throws SQLException, ClassNotFoundException {
        System.out.println(users.getUserName());
        System.out.println(users.getPassword());
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM signin WHERE username = ?");
        pstm.setString(1,users.getUserName());
        ResultSet resultSet=pstm.executeQuery();
        String password= users.getPassword();

        if(resultSet.next()){
            String password2 = resultSet.getString(2);

            if(password2.length() == password.length()){
                int count = 0;

                for(int i = 0; i < password.length(); i++){
                    if(password.charAt(i) == password2.charAt(i)){
                        count++;
                    }
                }

                if(count == password2.length()){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @PostMapping("/addCustomer")
    void  addCustomer(@RequestBody Customers customers) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?)");
        pstm.setString(1,generateId());
        pstm.setString(2, customers.getCustomerName());
        pstm.setString(3, customers.getTele());
        pstm.setString(4, customers.getNic());
        pstm.executeUpdate();
    }

    @PostMapping("/addSupplier")
    void  addSupplier(@RequestBody Suppliers supplier) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO supplier VALUES(?,?,?)");
        pstm.setString(1,generateSuppId());
        pstm.setString(2, supplier.getSupplierName());
        pstm.setString(3, supplier.getTele());
        pstm.executeUpdate();
        System.out.println(generateSuppId()+"//"+supplier.getSupplierName()+"//"+supplier.getTele());
    }

    @PostMapping("/addItem")
    void  addItem(@RequestBody Items item) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO items VALUES(?,?,?,?,?)");
        pstm.setString(1,generateItemCode());
        pstm.setString(2, item.getItemName());
        pstm.setInt(3, item.getQty());
        pstm.setDouble(4,item.getUnitPrice());
        pstm.setString(5, item.getSuppId());
        pstm.executeUpdate();
        System.out.println(generateItemCode()+"//"+item.getItemName()+"//"+item.getUnitPrice());
    }

    public String generateId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer ORDER BY custId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()){
            String id = resultSet.getString(1);

            if(id.charAt(1) == '0' && id.charAt(2) == '0'){
                int lastNo = Integer.parseInt(""+id.charAt(3));
                return "C00"+(lastNo+1);
            } else if (id.charAt(1) == '0') {
                int lastNo = Integer.parseInt(""+id.charAt(2)+id.charAt(3));
                return "C0"+(lastNo+1);
            }else{
                int lastNo = Integer.parseInt(""+id.charAt(1)+id.charAt(2)+id.charAt(3));
                return "C"+(lastNo+1);
            }
        }else{
            return "C001";
        }
    }

    public String generateSuppId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM supplier ORDER BY supiD DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()){
            String id = resultSet.getString(1);

            if(id.charAt(1) == '0' && id.charAt(2) == '0'){
                int lastNo = Integer.parseInt(""+id.charAt(3));
                return "S00"+(lastNo+1);
            } else if (id.charAt(1) == '0') {
                int lastNo = Integer.parseInt(""+id.charAt(2)+id.charAt(3));
                return "S0"+(lastNo+1);
            }else{
                int lastNo = Integer.parseInt(""+id.charAt(1)+id.charAt(2)+id.charAt(3));
                return "S"+(lastNo+1);
            }
        }else{
            return "S001";
        }
    }

    public String generateItemCode() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM items ORDER BY itemId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()){
            String id = resultSet.getString(1);

            if(id.charAt(1) == '0' && id.charAt(2) == '0'){
                int lastNo = Integer.parseInt(""+id.charAt(3));
                return "I00"+(lastNo+1);
            } else if (id.charAt(1) == '0') {
                int lastNo = Integer.parseInt(""+id.charAt(2)+id.charAt(3));
                return "I0"+(lastNo+1);
            }else{
                int lastNo = Integer.parseInt(""+id.charAt(1)+id.charAt(2)+id.charAt(3));
                return "I"+(lastNo+1);
            }
        }else{
            return "I001";
        }
    }

    @GetMapping("/viewCustomers")
    List<Customers> viewCustomers() throws SQLException, ClassNotFoundException {
        List list = new ArrayList<>();

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer");
        ResultSet resultSet = pstm.executeQuery();

        while(resultSet.next()){
            list.add(resultSet.getString(1));
            list.add(resultSet.getString(2));
            list.add(resultSet.getString(3));
            list.add(resultSet.getString(4));
        }

        return list;
    }

    @GetMapping("/viewSuppliers")
    List<Suppliers> viewSuppliers() throws SQLException, ClassNotFoundException {
        List list = new ArrayList<>();

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM supplier");
        ResultSet resultSet = pstm.executeQuery();

        while(resultSet.next()){
            list.add(resultSet.getString(1));
            list.add(resultSet.getString(2));
            list.add(resultSet.getString(3));
        }

        return list;
    }
    @GetMapping("/viewItems")
    List<Suppliers> viewItems() throws SQLException, ClassNotFoundException {
        List list = new ArrayList<>();

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM items");
        ResultSet resultSet = pstm.executeQuery();

        while(resultSet.next()){
            list.add(resultSet.getString(1));
            list.add(resultSet.getString(2));
            list.add(resultSet.getString(3));
            list.add(resultSet.getString(4));
            list.add(resultSet.getString(5));
        }

        return list;
    }

    @PostMapping("/deleteCustomer")
    void deleteCustomer(@RequestBody String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM customer WHERE custId=?");
        pstm.setString(1,id);
        pstm.executeUpdate();
    }
}