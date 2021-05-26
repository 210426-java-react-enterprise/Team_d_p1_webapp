package com.revature.entities;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.PrimaryKey;
import com.revature.annotations.Table;

import java.util.Date;
import java.util.List;
import java.util.Vector;


@Entity
@Table(name="users")
public class AppUser {
// TODO add @Column tag here for database fields
    @Column(columnName = "username", unique = true, notNull = true)
    private String username;
    @Column(columnName = "password", notNull = true)
    private String password;
    @Column(columnName = "first_name", notNull = true)
    private String firstName;
    @Column(columnName = "last_name", notNull = true)
    private String lastName;
    @Column(columnName = "email", notNull = true, unique = true)
    private String email;
    @Column(columnName = "age")
    private int age;
    @PrimaryKey
    private int userID;

    private List<TaskList> taskList;

    public AppUser(){
        username = "";
    }

    public AppUser(String username, String password, String firstName, String lastName, String email, int age) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        taskList = new Vector<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<TaskList> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskList> taskList) {
        this.taskList = taskList;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppUser{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", age=").append(age);
        sb.append(", taskList=").append(taskList);
        sb.append('}');
        return sb.toString();
    }
}
