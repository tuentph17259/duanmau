/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NguoiHoc;
import com.edusys.utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String>{
final  String INSERT_SQL = "insert into NguoiHoc(MaNH,HoTen,NgaySinh,GioiTinh,DienThoai,Email,GhiChu,MaNV,NgayDK) values(?,?,?,?,?,?,?,?,?)";
 final  String UPDATE_SQL = "UPDATE NguoiHoc set HoTen = ?,NgaySinh=?,GioiTinh =?, DienThoai = ?,Email=?,GhiChu =?,MaNV=?,NgayDK=? WHERE MaNH = ?";
 final  String DELETE_SQL = "DELETE FROM NguoiHoc WHERE MaNH = ?";
 final  String SELECT_ALL_SQL = "SELECT * FROM NguoiHoc";
 final  String SELECT_BY_ID_SQL = "SELECT * FROM NguoiHoc where MaNH=?";
   String select_by_name = "SELECT * FROM NguoiHoc where HoTen LIKE ? ";  
  
   public List<NguoiHoc> selectByKeyWord(String keyword){
        return this.selectBySql(select_by_name, "%"+keyword+"%");
    }
    
    
    public List<NguoiHoc> selectNotInCourse(int makh,String keyword){
        String sql = "select * from NGUOIHOC "
                + "where HoTen LIKE ? "
                + "AND MaNH NOT IN (SELECT MaNH from HOCVIEN WHERE MaKH=?)";
        return this.selectBySql(sql,"%"+keyword+"%",makh);
    }

 
 @Override
    public void insert(NguoiHoc entity) {
        JDBCHelper.update(INSERT_SQL,entity.getMaNH(),entity.getHoTen(),entity.getNgaySinh(),entity.isGioiTinh(),entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayDK());
    }

    @Override
    public void update(NguoiHoc entity) {
        JDBCHelper.update(UPDATE_SQL,entity.getHoTen(),entity.getNgaySinh(),entity.isGioiTinh(),entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayDK(),entity.getMaNH());
    }

    @Override
    public void delete(String id) {
      JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NguoiHoc> selectAll() {
       return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NguoiHoc selectBYId(String id) {
      List<NguoiHoc> list = selectBySql(SELECT_BY_ID_SQL, id);
      if(list.isEmpty()){
          return  null;
      }
        return  list.get(0);
    
    }


    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {                
                NguoiHoc entity = new NguoiHoc();
                entity.setMaNH(rs.getString("MaNH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setDienThoai(rs.getString("DienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayDK(rs.getDate("NgayDK"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }
    
}
