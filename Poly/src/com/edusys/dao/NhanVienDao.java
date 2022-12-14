/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NhanVien;
import com.edusys.utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class NhanVienDao extends EduSysDAO<NhanVien, String>{
 final  String INSERT_SQL = "INSERT INTO NhanVien(MaNV,MatKhau,HoTen,VaiTro) values(?,?,?,?)";
 final  String UPDATE_SQL = "UPDATE NhanVien set MatKhau = ?,HoTen=?,VaiTro =? WHERE MaNV = ?";
 final  String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV = ?";
 final  String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
 final  String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien where MaNV=?";
    @Override
    public void insert(NhanVien entity) {
        JDBCHelper.update(INSERT_SQL, entity.getMaNV(),entity.getMatKhau(),entity.getHoTen(),entity.isVaiTro());
    }

    @Override
    public void update(NhanVien entity) {
          JDBCHelper.update(UPDATE_SQL,entity.getMatKhau(),entity.getHoTen(),entity.isVaiTro(), entity.getMaNV());
    }

    @Override
    public void delete(String id) {
         JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NhanVien> selectAll() {
      return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectBYId(String id) {
      List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, id);
      if(list.isEmpty()){
          return  null;
      }
        return  list.get(0);
    
    }


    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
         List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {                
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
