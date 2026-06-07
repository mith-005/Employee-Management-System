

public class Employee implements Comparable<Employee>{
    
    private int EmpId;
    private String EmpName;
    private String Department;
    private double Salary;
    
    public int getEmpId(){
        return EmpId;
    }
    public void setEmpId(int id){
        this.EmpId = id;
    }
    public String getEmpName(){
        return EmpName;
    }
    public void setEmpName(String name){
        this.EmpName = name;
    }
    public String getDepartment(){
        return Department;
    }
    public void setDepartment(String dept){
        this.Department = dept;
    }
    public double getSalary(){
        return Salary;
    }
    public void setSalary(double sal){
        this.Salary = sal;
    }
    Employee(int id,String name,String dept, double sal){
        EmpId= id;
        EmpName=name;
        Department=dept;
        Salary=sal;}
    void display(){
        System.out.println("Employee Details:");
        System.out.println("Employee Id: "+ EmpId);
        System.out.println("Employee Name: "+ EmpName);
        System.out.println("Employee Department: "+ Department);
        System.out.println("Employee Salary: "+ Salary);
        System.out.println("------------------------");
    }
    @Override
    public String toString(){
        return "Employee Id: " + EmpId +
           ", Name: " + EmpName +
           ", Department: " + Department +
           ", Salary: " + Salary;
       
    }
    
    @Override
    public int compareTo(Employee other){
        return Integer.compare(this.EmpId, other.EmpId);
    }
    
} 

