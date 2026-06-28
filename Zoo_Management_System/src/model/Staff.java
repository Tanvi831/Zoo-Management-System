package model;

public class Staff {
    private int staffID;
    private String name;
    private String role;         
    private long contact;
    private double salary;
    private String dateOfJoining; // Format: YYYY-MM-DD
    private String address;
    private String shift;
    private String dob; // Format: YYYY-MM-DD

    // =================== Constructors ===================
    public Staff() {
    }

    public Staff(int staffID, String name, String role, long contact,
                 double salary, String dateOfJoining, String address, String shift, String dob) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.contact = contact;
        this.salary = salary;
        this.dateOfJoining = dateOfJoining;
        this.address = address;
        this.shift = shift;
        this.dob = dob;
    }

    // =================== Getters and Setters ===================
    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    // =================== ToString Method ===================
    @Override
    public String toString() {
        return "Staff{" +
                "staffID=" + staffID +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", contact='" + contact + '\'' +
                ", salary=" + salary +
                ", dateOfJoining='" + dateOfJoining + '\'' +
                ", address='" + address + '\'' +
                 ", shift='" + shift + '\'' +
                  ", dob='" + dob + '\'' +
                '}';
    }
}

