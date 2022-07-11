package dao;

import connect_MySQL.Connect_MySQL;
import model.NhanVien;
import model.PhongBan;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class NhanVienDao implements CRUD<NhanVien>{
PhongBanDao phongBanDao = new PhongBanDao();

    @Override
    public List<NhanVien> getAll() {
        String sql = "select * from nhanvien";
        List<NhanVien> nhanViens = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("idnv");
                String name = resultSet.getString("tennv");
                Date date = resultSet.getDate("ngaysinh");

                String address = resultSet.getString("diachi");
                String email = resultSet.getString("sdt");
                String phoneNumber = resultSet.getString("email");
                PhongBan phongBan = phongBanDao.findById(resultSet.getInt("idpb"));

                nhanViens.add(new NhanVien(id, name, date, address, email, phoneNumber, phongBan));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nhanViens;
    }

    @Override
    public boolean create(NhanVien nhanVien) {
        String sql = "insert into phongban value (?,?,?,?,?,?,?)";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, nhanVien.getId());
            preparedStatement.setString(2,  nhanVien.getName());
            preparedStatement.setString(3, String.valueOf(nhanVien.getBirthDay()));
            preparedStatement.setString(4, nhanVien.getLocation());
            preparedStatement.setString(5, nhanVien.getNumber());
            preparedStatement.setString(6,nhanVien.getEmail() );
            preparedStatement.setInt(7, nhanVien.getPhongBan().getId());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean edit(int id, NhanVien nhanVien) {
        String sql = "UPDATE phongban SET tennv = ?,ngaysinh = ?, " +
                "diachi = ?,sdt = ?,email = ?, idpb=? WHERE (id = ?)";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(7, nhanVien.getId());
            preparedStatement.setString(1, nhanVien.getName());
            preparedStatement.setString(2, String.valueOf(nhanVien.getBirthDay()));
            preparedStatement.setString(3, nhanVien.getLocation());
            preparedStatement.setString(4, nhanVien.getNumber());
            preparedStatement.setString(5, nhanVien.getEmail());
            preparedStatement.setInt(6, nhanVien.getPhongBan().getId());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from nhanvien WHERE id = ?";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public NhanVien findById(int id) {
        String sql = "select * from nhanvien where id = " + id;
        try (Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.next();
            int idS = resultSet.getInt("idnv");
            String name = resultSet.getString("tennv");
            Date dateOfBirth = resultSet.getDate("ngaysinh");
            String address = resultSet.getString("diachi");
            String phoneNumber = resultSet.getString("sdt");
            String email = resultSet.getString("email");

            PhongBan phongBan = phongBanDao.findById(resultSet.getInt("idpb"));

            return new NhanVien(idS, name, dateOfBirth, address, email, phoneNumber, phongBan);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public List<NhanVien> getAllByName(String name) {
        String sql = "select * from nhanvien where name like concat('%',?,'%')";
        List<NhanVien> nhanViens = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idnv");
                String nameS = resultSet.getString("tennv");
                Date date = resultSet.getDate("ngaysinh");
                String address = resultSet.getString("diachi");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("sdt");
                PhongBan phongBan = phongBanDao.findById(resultSet.getInt("idpb"));

                nhanViens.add(new NhanVien(id, nameS, date, address, email, phoneNumber, phongBan));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nhanViens;
    }
    }

