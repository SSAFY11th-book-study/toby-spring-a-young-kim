package springbook.user.domain;

import java.awt.event.MouseAdapter;

public class User {
    String id, name, password;

    public User(){}

    public User(String id, String name, String password){
        this.id  = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
