/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.HocVien;
import com.edusys.utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class HocVienDAO extends EduSysDAO<HocVien, Integer>{

    final  String INSERT_SQL = "insert into HocVien(MaKH,MaNH,Diem) values(?,?,?)";
 final  String UPDATE_SQL = "UPDATE HocVien set MaKH = ?,MaNH=?,Diem =? WHERE MaHV = ?";
 final  String DELETE_SQL = "DELETE FROM HocVien WHERE MaHV = ?";
 final  String SELECT_ALL_SQL = "SELECT * FROM HocVien";
 final  String SELECT_BY_ID_SQL = "SELECT * FROM HocVien where MaHV=?";
 
 public List<HocVien> selectByKhoaHoc(int makh){
        String sql = "SELECT * FROM HOCVIEN WHERE MaKH=?";
        return this.selectBySql(sql, makh);
    }
 @Override
    public void insert(HocVien entity) {
      JDBCHelper.update(INSERT_SQL,entity.getMaKH(),entity.getMaNH(),entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
    JDBCHelper.update(UPDATE_SQL,entity.getMaKH(),entity.getMaNH(),entity.getDiem(), entity.getMaHV());
    }

    @Override
    public List<HocVien> selectAll() {
       return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public void delete(Integer id) {
       JDBCHelper.update(DELETE_SQL,id);
    }

    @Override
    public HocVien selectBYId(Integer id) {
      List<HocVien> list = selectBySql(SELECT_BY_ID_SQL,id);
      if(list.isEmpty()){
          return  null;
      }
      return list.get(0);
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... args) {
      
         List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {                
                HocVien entity = new HocVien();
                entity.setMaHV(rs.getInt("MaHV"));
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaNH(rs.getString("MaNH"));
                entity.setDiem(rs.getDouble("Diem"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
        return  list;
    }
    }
    

