package model;

public class User {
    private int id;
    private String name;
    private String password;
    private String mobileNumber;
    private String role; // "Admin" or "Agent"

    public User(int id, String name, String password, String mobileNumber, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
