package models;

import org.json.JSONObject;

public class User {
    private final Long id;
    private final String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public User(Long id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public User(String body) {
        JSONObject obj = new JSONObject(body);

        this.id = obj.getLong("id");
        this.username = obj.getString("username");
        this.firstName = obj.getString("firstName");
        this.lastName = obj.getString("lastName");
        this.email = obj.getString("email");
        this.password = obj.getString("password");
        this.phone = obj.getString("phone");
        this.userStatus = obj.getInt("userStatus");
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id + "," +
                "\"username\":\"" + username + "\"," +
                "\"firstName\":\"" + firstName + "\"," +
                "\"lastName\":\"" + lastName + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"password\":\"" + password + "\"," +
                "\"phone\":\"" + phone + "\"," +
                "\"userStatus\":" + userStatus +
                "}";
    }
}
