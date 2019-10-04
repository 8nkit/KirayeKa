package com.example.kirayeka.Model;
public class User {
    private String Name;
    private String Password;
    private String Phone;
    private String sqa;

    public User() {
    }

    public User(String name, String password,String SQA) {
        Name = name;
        Password = password;
        sqa=SQA;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPhone() {

        return Phone;
    }
public String getEmaill()
{
    return sqa;
}
public void setEmaill(String SQA){
        sqa=SQA;
}
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
