package model;

public abstract class User {

    protected int id;
    protected String name;
    protected String phone;

    public User (int id, String name,String phone){
        this.id = id ;
        this.name = name;
        this.phone = phone;
    }

    public int getId(){
        return id;
    }

    public String getPhone(){
        return phone;
    }

    public String getName(){
        return name;
    }

}
