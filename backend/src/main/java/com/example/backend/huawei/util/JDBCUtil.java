package com.example.backend.huawei.util;

import java.sql.*;

public class JDBCUtil {
    private String driver="org.postgresql.Driver";
    private String url="jdbc:postgresql://10.168.89.122:26000/yishan_db";
    private String user="yishan";
    private String password="Zjhw@12345";

    public void updateSql(String sql) throws Exception {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        //获取结果集
        boolean hasResultSet = stmt.execute(sql);       //如果该sql语句是否有结果集
        if(hasResultSet){
            //获取结果集
            ResultSet rs = stmt.getResultSet();
            //输出ResultSet对象
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t");
            }
        }else{
            System.out.println("数据库的记录改变了"+stmt.getUpdateCount()+"条");
        }
    }


    public void executeSql(String sql,Object[] param ) throws Exception {
        PreparedStatement pstmt = null;
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        pstmt = conn.prepareStatement(sql);
        if (param != null) {
            for(int i = 0; i < param.length; i++){
                pstmt.setObject(i+1, param[i]);
            }
        }
        pstmt.executeUpdate();
    }
}
