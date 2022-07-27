/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author admin
 */
public class THongKeDao {
    private List<Object[]> gestListOfArray(String sql,String[] cols,Object... args){
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {                
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                    
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Object[]> getBangDiem(Integer makh){
        String sql= "{CALL sp_BangDiem(?)}";
        String[] cols = {"MaNH","HoTen","Diem"};
        return gestListOfArray(sql, cols, makh);
    }
    
    public List<Object[]> getLuongNguoiHoc(){
        String sql= "{CALL sp_ThongKeNguoiHoc}";
        String[] cols = {"Nam","SoLuong","DauTien","CuoiCung"};
        return gestListOfArray(sql, cols);
    }
    
    public List<Object[]> getDiemChuyenDe(){
        String sql= "{CALL sp_ThongKeDiem}";
        String[] cols = {"ChuyenDe","SoHV","ThapNhat","CaoNhat","TrungBinh"};
        return gestListOfArray(sql, cols);
    }
    public List<Object[]> getDoanhThu(Integer nam){
        String sql= "{CALL sp_ThongKeDoanhThu (?)}";
        String[] cols = {"ChuyenDe","SoKh","SoHV","DoanhThu","ThapNhat","CaoNhat","TrungBinh"};
        return this.gestListOfArray(sql, cols, nam);
    }
   
    
}
