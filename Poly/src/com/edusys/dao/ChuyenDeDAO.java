/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.ChuyenDe;
import com.edusys.utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String>{
 final  String INSERT_SQL = "insert into ChuyenDe(MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa) values(?,?,?,?,?,?)";
 final  String UPDATE_SQL = "UPDATE ChuyenDe set TenCD = ?,HocPhi=?,ThoiLuong =?,Hinh=?,MoTa =? WHERE MaCD = ?";
 final  String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaCD = ?";
 final  String SELECT_ALL_SQL = "SELECT * FROM ChuyenDe";
 final  String SELECT_BY_ID_SQL = "SELECT * FROM ChuyenDe where MaCD=?";
    @Override
    public void insert(ChuyenDe entity) {
           JDBCHelper.update(INSERT_SQL, entity.getMaCD(),entity.getTenCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getHinh(),entity.getMoTa());
    }

    @Override
    public void update(ChuyenDe entity) {
 JDBCHelper.update(UPDATE_SQL,entity.getTenCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getHinh(),entity.getMoTa(), entity.getMaCD());
    }

    @Override
    public void delete(String id) {
       JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<ChuyenDe> selectAll() {
       return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ChuyenDe selectBYId(String id) {
 List<ChuyenDe> list = selectBySql(SELECT_BY_ID_SQL, id);
      if(list.isEmpty()){
          return  null;
      }
        return  list.get(0);
    }


    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
         List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {                
                ChuyenDe entity = new ChuyenDe();
                entity.setMaCD(rs.getString("MaCD"));
                entity.setTenCD(rs.getString("TenCD"));
                entity.setHocPhi(rs.getDouble("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                 entity.setHinh(rs.getString("Hinh"));
                  entity.setMoTa(rs.getString("MoTa"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
        return  list;
    }
    
}
