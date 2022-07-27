/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.utils;
import java.sql.*;
/**
 *
 * @author admin
 */
public class JDBCHelper {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
  private static String drurl = "jdbc:sqlserver://localhost\\DESKTOP-NKFKLSL\\SQLEXPRESS:1433;databaseName=EduSys";
   private static String user = "sa";
    private static String pass = "123456789";
   
    static {
        try{
           Class.forName(driver);
        }catch(ClassNotFoundException ex){
           throw  new RuntimeException(ex);
        }
    }
   

    public static PreparedStatement getStmt(String sql,Object... args) throws  SQLException{
        Connection con = DriverManager.getConnection(drurl, user, pass);
        PreparedStatement pstmt = null;
        try {
             if (sql.trim().startsWith("{")) {
                pstmt = con.prepareCall(sql);
            }else{
                pstmt = con.prepareStatement(sql);
            }
            
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i+1, args[i]);
            }
        } catch (Exception e) {
        }
        return  pstmt;
    }
    
    public static int update(String sql,Object... args){
        try {
            PreparedStatement stmt = getStmt(sql, args);
            try{
                return  stmt.executeUpdate();
                
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   
    public static ResultSet query(String sql,Object... args){
            try {
            PreparedStatement stmt = JDBCHelper.getStmt(sql, args);
            return stmt.executeQuery() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Object value(String sql,Object...args){
        try {
            ResultSet rs = query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  null;
    }
}
