package models;

public class User {
    private Long id;
    private String username;
    private Long userStatus;

    public User() {

    }

    public User(Long id, String username, Long userStatus) {
        this.id = id;
        this.username = username;
        this.userStatus = userStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Long userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}
