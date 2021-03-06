//package com.example.demo.config;
//
//import lombok.Cleanup;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.sql.*;
//import java.util.Optional;
//
///**
// * @author whn
// * 手写jdbc链接mysql
// */
//@Slf4j
//public class JdbcTest {
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        //execute();
//        insert();
//        delete();
//    }
//
////    private static void execute() throws ClassNotFoundException, SQLException {
////        Class.forName("com.mysql.cj.jdbc.Driver");
////        @Cleanup Connection connection = DriverManager.getConnection("jdbc:mysql://自己mysql数据库服务地址:3306/test", "root", "123456");
////        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select * from stu");
////        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
////        Student student = null;
////        while (resultSet.next()) {
////            int id = resultSet.getInt("id");
////            String name = resultSet.getString("name");
////            student = new Student().setId(id).setName(name);
////        }
////        boolean present = Optional.ofNullable(student).isPresent();
////        if (present) {
////            System.out.println(student);
////        } else {
////            System.out.println("查询内容为空");
////        }
////    }
//
//    private static void insert() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        @Cleanup Connection connection = DriverManager.getConnection("jdbc:mysql://自己mysql数据库服务地址:3306/test", "root", "123456");
//        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("insert into stu values(?,?)");
//        preparedStatement.setInt(1, 4);
//        preparedStatement.setString(2, "backCoder4");
//        boolean flag = preparedStatement.execute();
//        String message = !flag ? "成功插入一条数据" : "保存数据失败";
//        log.info("message:{}", message);
//    }
//
//    private static void delete() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://自己mysql数据库服务地址:3306/test", "root", "123456");
//        PreparedStatement preparedStatement = connection.prepareStatement("delete from stu where id =?");
//        preparedStatement.setInt(1, 4);
//        int count = preparedStatement.executeUpdate();
//        String message = count > 0 ? "删除数据库的数据成功" : "删除数据库的数据失败";
//        log.info("message:{}", message);
//    }
//
//    private static  void update() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://自己mysql数据库服务地址:3306/test", "root", "123456");
//        PreparedStatement preparedStatement = connection.prepareStatement("update stu set name =? where id= ?");
//        preparedStatement.setString(1,"backCoder-Coder");
//        preparedStatement.setInt(2,1);
//        int count = preparedStatement.executeUpdate();
//        String message=count>0?"更新数据库数据成功":"更新数据库数据失败";
//        log.info("message:{}",message);
//    }
//}
