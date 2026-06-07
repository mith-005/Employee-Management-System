import com.mysql.cj.protocol.Resultset;
import java.sql.*;
import java.util.*;
import java.util.stream.*;

public class EmployeeManager
{
    // ArrayList<Employee> emp = new ArrayList<Employee>();
    
    // Employee findEmpId(int EmpId){
    //     for (int i =0;i<emp.size();i++){
    //         Employee e = emp.get(i);

    //         if (e.getEmpId() == EmpId){
    //             return e;
    //         }
    //     }return null;
    // }

    boolean validateId(int EmpId){
        if (EmpId > 0){
            return true;}
        else{
            return false;
        }
    }

    boolean validateSalary(double Salary){
        if (Salary >0){
            return true;}
        else{
            return false;
        }
    }
    boolean validateName(String EmpName)
    {
        EmpName = EmpName.trim();
        if(EmpName.equals("")){
            return false;
        }
        for(int i=0 ;i<EmpName.length();i++){
            char c= EmpName.charAt(i);
            if (!(c>='a' && c<='z')&&!(c>='A'&&c<='Z')&&c!=' '){
                return false;
            }
            
            }return true;
        }

    void addEmployee(Employee e){
        // int id;
        // Employee result;
        // id = e.getEmpId();  
        // result = findEmpId(id);
        
        // if (result!=null){
        //     System.out.println("Employee already exists");
        // }
        // else{
        // emp.add(e);
     try{
        Connection con =getConnection();

        String sql =" INSERT INTO employeedetails VALUES(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,e.getEmpId());
        ps.setString(2,e.getEmpName());
        ps.setString(3,e.getDepartment());
        ps.setDouble(4,e.getSalary());
        int count = ps.executeUpdate();
        System.out.println("count"+ count);
        System.out.println("Employeed added in database");
        con.close();
     }
     catch(SQLException ex){
        System.out.println("it cannot be storedin the database");
     }

        

        System.out.println("Employee added successfully");
        System.out.println("----------------------------------");
        }
    
    boolean searchEmp(int EmpId){
       try{
        Connection con = getConnection();
        String sql =" SELECT * FROM employeedetails WHERE empid=?";
        PreparedStatement ps =con.prepareStatement(sql);
        ps.setInt(1, EmpId);
        ResultSet rs = ps.executeQuery();
         if(rs.next()){
           int EmpID = rs.getInt(1);
           String name =rs.getString(2);
           String department =rs.getString(3);
           double salary=rs.getInt(4);
           Employee e = new Employee(EmpId, name, department, salary);
           e.display();
           con.close();
            return true;

         } 
        
       else{
        System.out.println("Employee no found");
        con.close();
        return false;
       }
       
        
    }
    catch(SQLException ex){
        System.out.println("searched is not happened");
        return false;

    }


            // Employee e = findEmpId(EmpId);
            // if (e!=null){
            //     e.display();
            //     System.out.println("-----------------------------");
            //     return true;
            // }
            // else{
            //     System.out.println("Not found");
            //     return false;
            // }
    }
    boolean updateEmp( int EmpId, String name,double sal, String dept){
         try{
            Connection con = getConnection();
            String sql = "UPDATE employeedetails SET empname =? ,dept =?,salary=? WHERE empid =?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1,name);
            ps.setString(2, dept);
            ps.setDouble(3,sal);
            ps.setInt(4, EmpId);
            int count = ps.executeUpdate();
            if(count>0){
                System.out.println("Employee updated");
                 con.close();
                 return true;
                
            }
           else{
            System.out.println("Employee not updated");
             con.close();
             return false;
           }
    }
    catch(SQLException ex){
        System.out.println("searched is not happened");
        return false;

    }
        
        // Employee e = findEmpId(EmpId);
        //     if (e!=null){
        //         e.setEmpName(name);
        //         e.setSalary(sal);
        //         e.setDepartment(dept);
        //         return true;
                
        //     }
        //     else{
        //         return false;
        //     }
            
    }
    boolean deleteEmp(int EmpId){
        try{
            Connection con = getConnection();
            String sql = "DELETE FROM employeedetails WHERE empid =?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,EmpId);
            int count = ps.executeUpdate();
            if (count >0){
                System.out.println("Employee deleted");
                con.close();
                return true;
            }    
            else{
                System.out.println("Employee not deleted");
                con.close();
                return false;
            }        
        }
        catch(SQLException e){
            System.out.println("Deleted is not happened");
            return false;
        }
        // Employee e = findEmpId(EmpId);
        //     if (e!=null){
        //         emp.remove(e);
        //         System.out.println("Employee deleted successfully");
        //         System.out.println("----------------------------------");
        //         return true;
        //     }
        //     else{
        //          return false;
        //     }
    }
    void displayEmployees(){
        try{
        Connection con = getConnection();
        String sql ="SELECT * FROM employeedetails";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
           int EmpID = rs.getInt(1);
           String name =rs.getString(2);
           String department =rs.getString(3);
           double salary=rs.getInt(4);
           Employee e = new Employee(EmpID, name, department, salary);
           e.display();
           

         } con.close();
           
        
      
       
        
    }
    catch(SQLException ex){
        System.out.println("searched is not happened");
        return;
    }

        // if (emp.size()==0){
        //     System.out.println("No employee available");
        // }
        // for (int i=0;i<emp.size();i++){
        //     emp.get(i).display();
        // }
    }
     
    List<Employee> getAllEmployee(){
        List<Employee> emp = new ArrayList<Employee>();
        try{
        Connection con = getConnection();
        String sql ="SELECT * FROM employeedetails";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs= ps.executeQuery();
        
 
        while(rs.next()){
           int EmpID = rs.getInt(1);
           String name =rs.getString(2);
           String department =rs.getString(3);
           double salary=rs.getDouble(4);
           Employee e = new Employee(EmpID, name, department, salary);
           emp.add(e);
        
        
        }     
        
        con.close();
        return emp;

    }
    catch(SQLException e){
        System.out.println("Employeee list is not created");
        return emp;
    }
}
   void countEmployee(){
    List <Employee> emp = getAllEmployee();

    long count = emp.stream()
                .count();
    System.out.println("count of employee"+count);

   }

   void displayHighSalaryEmployee(){
    List<Employee> emp = getAllEmployee();
    emp.stream()
    .filter(e->e.getSalary()>85000)
    .forEach(System.out::println);

   }
   void sortByName(){
    List<Employee> emp = getAllEmployee();
    emp.stream()
    .sorted((e1,e2)->e1.getEmpName().toLowerCase().compareTo(e2.getEmpName().toLowerCase()))
    .forEach(System.out::println);
   }
   void sortByDepartment(){
    List<Employee> emp = getAllEmployee();                      //here using comparator method so without stream direct access by list
    emp.sort((e1,e2)->e1.getDepartment().compareToIgnoreCase(e2.getDepartment()));
    for (Employee e: emp){
        System.out.println(e);
    }
    
    



   }
   void sortBySalaryAsc(){
    List<Employee> emp = getAllEmployee();
    emp.stream()
    .sorted((e1,e2)->Double.compare(e1.getSalary(),e2.getSalary()))
    .forEach(System.out::println);
   }
   void sortBySalaryDesc(){
    List<Employee> emp = getAllEmployee();
    emp.stream()
    .sorted((e1,e2)->Double.compare(e2.getSalary(),e1.getSalary()))
    .forEach(System.out::println);
   }
   void maxSalary(){
    List<Employee> emp = getAllEmployee();
    Optional<Employee> maximum = emp.stream()
        .max((e1,e2)->Double.compare(e1.getSalary(),e2.getSalary()));
    Employee e = maximum.get();
    if (maximum.isPresent()){
        System.out.println("Maximum Salary Employee");
        e.display();
    }
    else{
        System.out.println("No Employee exist");

    }
   }
   void minSalary(){
    List<Employee> emp = getAllEmployee();
    Optional <Employee> minimum= emp.stream()
                .min((e1,e2)->Double.compare(e1.getSalary(),e2.getSalary()));
    Employee e = minimum.get();
    if (minimum.isPresent()){
        System.out.println("Minimum Salary Employee");
        e.display();
    }
    else{
        System.out.println("No Employee exists");
    }

   }
   void avgSalary(){
    List<Employee> emp = getAllEmployee();
      OptionalDouble avg = emp.stream()
                            .mapToDouble(e->e.getSalary())
                            .average();
    if(avg.isPresent()){
        System.out.println("Average salary"+ avg.getAsDouble());

    }
    else{
        System.out.println("no average calculated");
    }
     

   }
    Connection getConnection()throws SQLException{
        String url ="jdbc:mysql://localhost:3306/project";
        String username ="root";
        String password ="<your_password>";
        Connection con = DriverManager.getConnection(url,username,password);
        return con;
        

    }

    
}

    

