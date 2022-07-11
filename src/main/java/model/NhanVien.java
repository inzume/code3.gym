package model;

import java.time.LocalDate;
import java.sql.Date;

public class NhanVien {
    int Id;
    String Name;
    Date BirthDay;
    String Location;
    String Number;
    String Email;
    PhongBan phongBan;

    public NhanVien(int id, String name, Date birthDay, String location, String number, String email, PhongBan phongBan) {
        Id = id;
        Name = name;
        BirthDay = birthDay;
        Location = location;
        Number = number;
        Email = email;
        this.phongBan = phongBan;
    }

    public NhanVien(String name, Date birthDay, String location, String number, String email, PhongBan phongBan) {
        Name = name;
        BirthDay = birthDay;
        Location = location;
        Number = number;
        Email = email;
        this.phongBan = phongBan;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(Date birthDay) {
        BirthDay = birthDay;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
