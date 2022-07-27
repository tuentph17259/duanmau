/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.KhoaHoc;
import com.edusys.utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer>{
    final  String INSERT_SQL = "insert into KhoaHoc(MaCD,HocPhi,ThoiLuong,NgayKG,GhiChu,MaNV,NgayTao) values(?,?,?,?,?,?,?)";
 final  String UPDATE_SQL = "UPDATE KhoaHoc set MaCD = ?,HocPhi=?,ThoiLuong =?,NgayKG=?,GhiChu =?,MaNV=?,NgayTao =? WHERE MaKH = ?";
 final  String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKH = ?";
 final  String SELECT_ALL_SQL = "SELECT * FROM KhoaHoc";
 final  String SELECT_BY_ID_SQL = "SELECT * FROM KhoaHoc where MaKH=?";

  public ArrayList<Integer> selectYears(){
        String sql = "select  distinct year(NgayKG) from khoahoc order by year(NgayKG) desc ";
        ArrayList<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql);
            while(rs.next()){
                Integer nam = rs.getInt(1);
                list.add(nam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list ;
    }
 
  public List<KhoaHoc> selectByChuyenDe(String MaCD){
      String sql = "SELECT * FROM KhoaHoc WHERE MaCD=?";
      return this.selectBySql(sql,MaCD);
      
  }
    @Override
    public void insert(KhoaHoc entity) {
        JDBCHelper.update(INSERT_SQL,entity.getMaCD(),entity.getHocPhi(),entity.getThoiLuong(),
                entity.getNgayKG(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayTao());
    }

    @Override
    public void update(KhoaHoc entity) {
          JDBCHelper.update(UPDATE_SQL,entity.getMaCD(),entity.getHocPhi(),entity.getThoiLuong(),
                entity.getNgayKG(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayTao(), entity.getMaKH());
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public void delete(Integer id) {
       JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public KhoaHoc selectBYId(Integer id) {
        List<KhoaHoc> list = selectBySql(SELECT_BY_ID_SQL,id);
        if(list.isEmpty()){
            return  null;
        } 
        return list.get(0);
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
         List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {                
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaCD(rs.getString("MaCD"));
                entity.setHocPhi(rs.getDouble("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLUong"));
                entity.setNgayKG(rs.getDate("NgayKG"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                
                list.add(entity);
            }
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
        return  list;
    }
 
}
