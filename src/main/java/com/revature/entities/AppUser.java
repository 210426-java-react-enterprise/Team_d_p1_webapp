package com.revature.entities;

import com.revature.annotations.*;

import java.util.List;
import java.util.Vector;


@Entity
@Table(name="users")
public class AppUser {

    @Column(columnName = "username", unique = true, notNull = true, DataType = "VARCHAR")
    private String username;
    @Column(columnName = "password", notNull = true, unique = false, DataType = "VARCHAR")
    private String password;
    @Column(columnName = "first_name", notNull = true, unique = false, DataType = "VARCHAR")
    private String firstName;
    @Column(columnName = "last_name", notNull = true, unique= false, DataType = "VARCHAR")
    private String lastName;
    @Column(columnName = "email", notNull = true, unique = true, DataType = "VARCHAR")
    private String email;
    @Column(columnName = "age", notNull = true, unique = false, DataType = "INTEGER")
    private int age;

    @PrimaryKey
    @Column(columnName = "userID", notNull = true, DataType = "INTEGER", serialID = true)
    private int userID;

    private List<TaskList> taskList;

    public AppUser(){
    super();
    }

    /**
     * Constructor for initializing username, password, email, firstname, and lastname
     * @param username the username of the user
     * @param password the password of the user
     * @param email the email of the user
     * @param firstName the firstname of the user
     * @param lastName the lastname of the user
     * @param age the age of the user
     *
     */


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
}
