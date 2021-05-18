package com.revature.entities;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Table;

import java.util.Date;
import java.util.List;
import java.util.Vector;


@Entity
@Table(name="users")
public class AppUser {
// TODO add @Column tag here for database fields
    @Column(columnName = "username")
    private String username;
    @Column(columnName = "password")
    private String password;
    @Column(columnName = "first_name")
    private String firstName;
    @Column(columnName = "last_name")
    private String lastName;
    @Column(columnName = "email")
    private String email;
    @Column(columnName = "birthday")
    private Date birthday;
// Will user Id be generated by DB as serial primary key? - Everett
//    private int userID;

    private List<TaskList> taskList;

    public AppUser(){

    }

    public AppUser(String username, String password, String firstName, String lastName, String email, Date birthday) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<TaskList> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskList> taskList) {
        this.taskList = taskList;
    }
}
