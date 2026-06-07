
import java.util.*;
import java.util.Scanner.*;
import java.sql.*;
public class Manage{
    public static void main(String[] args){
        int ch=0;
        boolean res;
        Scanner sc = new Scanner(System.in);
        EmployeeManager Em = new EmployeeManager();
        try{Connection con = Em.getConnection();
        if(con!=null){
            System.out.println("connection successful");
            
        }
    }
    catch(SQLException e){
        System.out.println("connection not connected");
    }
        
        
        System.out.println("===Employee Management System===");
        System.out.println("1.Add employess");
        System.out.println("2.Search employees");
        System.out.println("3.Update employees");
        System.out.println("4.Delete employees");
        System.out.println("5.Display employees");
        System.out.println("6.Employee count");
        System.out.println("7.Display High Salary Employees");
        System.out.println("8.Sorting using Salary in Ascending");
        System.out.println("9.Sorting  using Salary in Descending");
        System.out.println("10.Sorting by names");
        System.out.println("11.Maximum salary Employee");
        System.out.println("12.Minimum salary Employee");
        System.out.println("13.Average salary Employee");
        System.out.println("14.Sort using department by comparator");
        System.out.println("14.Exit");

        while (true){
            System.out.println("Enter your choice");
            try{
             ch = sc.nextInt();
             sc.nextLine();
            }
            catch(Exception ex){
                System.out.println("invalid choice");
                sc.nextLine();
                continue;
            }

            switch(ch){
                case 1:
                    System.out.println("1.Add employee");
                    System.out.println("Enter Employee id: "); 
                    int id=0;
                    try{id = sc.nextInt();}
                    catch(Exception ex){
                        System.out.println(" Invalid Id");
                        sc.nextLine();
                        continue;
                    }
                    res = Em.validateId(id); //to validate the id 
                    if (res==false){
                        System.out.println("Invalid input");
                        continue;                                         
                    }
                    sc.nextLine();
                    System.out.println("Enter Employee Name:  ");
                    String name= sc.nextLine();
                    res = Em.validateName(name);
                    if (!res){
                        System.out.println("Invalid input");
                        continue;
                    }

                    System.out.println("Enter Employee Department:  ");
                    String dept= sc.nextLine();
                    res = Em.validateName(dept);
                    if (!res){
                        System.out.println("Invalid input");
                        continue;
                    }

                    System.out.println("Enter Employee Salary:  ");
                    double sal=0;
                    try{ sal= sc.nextDouble();}
                    catch(Exception ex){
                        System.out.println(" Invalid salary");
                        sc.nextLine();
                        continue;
                    }
                    res = Em.validateSalary(sal);
                    if (!res){
                        System.out.println("Invalid input");
                        continue;
                    }
                    
                   Employee e = new Employee(id,name,dept,sal);
                   Em.addEmployee(e);
                   break;
                case 2:
                    System.out.println("Search Employees ");
                    
                    System.out.println("Enter Empid:" );
                    int EmpId=0;
                    try{EmpId = sc.nextInt();}
                    catch(Exception ex){
                        System.out.println(" Invalid Id");
                        sc.nextLine();
                        continue;
                    }
                    res = Em.validateId(EmpId);
                    if (!res){
                        System.out.println("Invalid input");
                        continue;
                    }
                    sc.nextLine();
                    Em.searchEmp(EmpId);
                    break;
                case 3:
                    System.out.println("Update Employees ");
                    System.out.println("Enter updateid:");
                    int updateId=0;
                    try{updateId = sc.nextInt();}
                    catch(Exception ex){
                        System.out.println("invalid id");
                        sc.nextLine();
                        continue;
                    }
                    res = Em.validateId(updateId);
                    if (!res){
                        System.out.println("Invalid input");
                        continue;
                    }
                    sc.nextLine();
                    System.out.println("Enter Empname:");
                    String newname = sc.nextLine();
                    res = Em.validateName(newname);
                    if (!res){
                        System.out.println("Invalid input");
                        continue;
                    }
                    System.out.println("Enter Empdept:");
                    String newdept = sc.nextLine();
                    res = Em.validateName(newdept);
                    if (!res){
                        System.out.println("Invalid input");
                        continue;
                    }
                    System.out.println("Enter Empsalary");
                    double newsalary =0;
                    try{ newsalary = sc.nextDouble();}
                    catch(Exception ex){
                        System.out.println("invalid salary");
                        sc.nextLine();
                        continue;
                    }
                    res = Em.validateSalary(newsalary);
                    if (!res){
                        System.out.println("Invalid input");
                        continue;
                    }
                    sc.nextLine();
                    boolean updated =Em.updateEmp(updateId, newname,newsalary,newdept);
                    if (updated){
                    System.out.println("Employee updated successfully");
                    }
                    else{
                        System.out.println("Employeeid not found");
                    }
                    break;
                case 4:
                    System.out.println("Delete Employees ");
                    System.out.println("Enter delid:");
                    int delId=0;
                    try{ delId = sc.nextInt();}
                    catch(Exception ex){
                        System.out.println("invalid entry");
                        sc.nextLine();
                        continue;
                    }
                    res = Em.validateId(delId);
                    if (!res){
                        System.out.println("Invalid input");
                        continue;
                    }
                    sc.nextLine();
                    boolean deleted =Em.deleteEmp(delId);
                    if (deleted){
                        System.out.println("Deleted successffuly");
                    }
                    else{
                        System.out.println("Employeeid not found");
                    }
                    
                    break;

                case 5:
                    System.out.println("Display Employees ");
                    Em.displayEmployees();
                    
                    break;
                case 6:
                    System.out.println("Display Employee count");
                    Em.countEmployee();
                   
                    break;
                case 7:
                    System.out.println("Display high salary  employees");
                    Em.displayHighSalaryEmployee();
                    break;
                case 8:
                    System.out.println("Sorting salary asc");
                    Em.sortBySalaryAsc();
                    break;
                case 9:
                    System.out.println("Sorting salary Desc");
                    Em.sortBySalaryDesc();
                    break;
                case 10:
                    System.out.println("Sorting Names");
                    Em.sortByName();
                    break;
                case 11:
                    System.out.println("Max Salary");
                    Em.maxSalary();
                    break;
                case 12:
                    System.out.println("Min Salary");
                    Em.minSalary();
                    break;
                case 13:
                    System.out.println("Average salary");
                    Em.avgSalary();
                    break;
                case 14:
                    System.out.println("Sorting using the Department");
                    Em.sortByDepartment();
                    break;
                    
                case 15:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;    
            
             }
        }
    
    }
}

