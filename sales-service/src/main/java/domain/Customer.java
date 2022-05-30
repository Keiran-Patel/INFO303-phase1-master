/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author keiranpatel
 */
public class Customer {

    private String id;
    private String firstName;
    private String lastName;
    private String customerCode;
    private String email;
    private String group;

    public Customer() {
    }

    public Customer(String id, String firstName, String lastName, String customerCode, String email, String group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerCode = customerCode;
        this.email = email;
        this.group = group;
    }

    public Customer(String string, String jerry1234gmailcom, String high) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public String setCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {       
        this.customerCode = customerCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {

        return "Customer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ",customerCode=" + customerCode + ",email=" + email + ", group=" + group + '}';
    }

}
