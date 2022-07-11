package model;

public class PhongBan {
int Id;
String NamePhongBan;

    public PhongBan() {
    }

    public PhongBan(int id, String namePhongBan) {
        Id = id;
        NamePhongBan = namePhongBan;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNamePhongBan() {
        return NamePhongBan;
    }

    public void setNamePhongBan(String namePhongBan) {
        NamePhongBan = namePhongBan;
    }
}
