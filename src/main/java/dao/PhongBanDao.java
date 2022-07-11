package dao;

import connect_MySQL.Connect_MySQL;
import model.PhongBan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhongBanDao implements CRUD<PhongBan>{

    @Override
    public List<PhongBan> getAll() {
        String sql = "select * from phongban";
        List<PhongBan> phongBans = new ArrayList<>();
        try(Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                    int idPhongBan = resultSet.getInt(1);
                String namePhongban = resultSet.getString(2);
                phongBans.add(new PhongBan(idPhongBan, namePhongban));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phongBans;
    }

    @Override
    public boolean create(PhongBan phongBan) {
        return false;
    }

    @Override
    public boolean edit(int id, PhongBan phongBan) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public PhongBan findById(int id) {
        String sql = "select * from phongban where idpb = ?";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int idPhongBan = resultSet.getInt(1);
            String namePhongBan = resultSet.getString(2);
            PhongBan classStudent = new PhongBan(idPhongBan, namePhongBan);
            return classStudent;


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
    }

