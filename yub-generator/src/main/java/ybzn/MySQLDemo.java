package ybzn;

import java.sql.*;

/**
 * @author Hugo
 * @time 2021/1/21
 */
public class MySQLDemo {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/bighospital?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT\n" +
                    "	u.user_id AS `用户编号`,\n" +
                    "	u.user_name AS `姓名`,\n" +
                    "	i.issue_content AS `问题内容`,\n" +
                    "	i.issue_id AS `问题编号`,\n" +
                    "	i.issue_options AS `问题选项`,\n" +
                    "	qi.user_answer AS `用户答案`,\n" +
                    "	i.issue_type AS `问题类型 0表示选择题`,\n" +
                    "	c.collect_full_date AS `填表日期`,\n" +
                    "	c.collect_full_deadline AS `截至日期` \n" +
                    "FROM\n" +
                    "	issue AS i\n" +
                    "	INNER JOIN questionnaire_issue AS qi ON i.issue_id = qi.issue_id\n" +
                    "	INNER JOIN questionnaire AS q ON qi.questionnaire_id = q.questionnaire_id\n" +
                    "	INNER JOIN collect AS c ON qi.questionnaire_id = c.questionaire_id\n" +
                    "	INNER JOIN `user` AS u ON c.user_id = u.user_id \n" +
                    "WHERE\n" +
                    "	u.user_id = 1";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt(1);
                String name = rs.getString(6);
//                String url = rs.getString("url");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
//                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) {
                    stmt.close();
                }
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) {
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
