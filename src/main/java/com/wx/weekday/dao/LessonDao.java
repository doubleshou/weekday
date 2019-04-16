package com.wx.weekday.dao;

import com.wx.weekday.entity.Lesson;
import com.wx.weekday.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LessonDao {
    //添加
    public int add(Lesson lesson) {
        //获取连接
        Connection conn= DBUtil.getConn();
        //sql语句
        String sql="insert into lesson(number,name,teacher,beginweek,endweek,begin,end,address) "
                + "values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=null;
        int result=0;
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, lesson.getNumber());
            pstmt.setString(2, lesson.getName());
            pstmt.setString(3, lesson.getTeacher());
            pstmt.setString(4, lesson.getBeginweek());
            pstmt.setString(5, lesson.getEndweek());
            pstmt.setString(6, lesson.getBegin());
            pstmt.setString(7, lesson.getEnd());
            pstmt.setString(8, lesson.getAddress());
            result=pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            DBUtil.closePstmt(pstmt);
            DBUtil.closeConn(conn);
        }
        return result;
    }
    // 根据用户名查询
    public List<Lesson> findByNumber(String number) {
        Connection conn = DBUtil.getConn();
        String sql = "select * from lesson where number=?";
        PreparedStatement pstmt = null;
        ResultSet rSet = null;
        Lesson lesson = null;
        List<Lesson> lessonList=null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, number);
            rSet = pstmt.executeQuery();
            int col = rSet.getMetaData().getColumnCount();
            if (rSet.next()) {
                for(int i=0;i<col;i++){
                    lesson = new Lesson();
                    lesson.setNumber(rSet.getString(1));
                    lesson.setName(rSet.getString(2));
                    lesson.setTeacher(rSet.getString(3));
                    lesson.setBeginweek(rSet.getString(4));
                    lesson.setEndweek(rSet.getString(5));
                    lesson.setBegin(rSet.getString(6));
                    lesson.setEnd(rSet.getString(7));
                    lesson.setAddress(rSet.getString(8));
                    lessonList.add(lesson);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.closeRst(rSet);
            DBUtil.closePstmt(pstmt);
            DBUtil.closeConn(conn);
        }
        return lessonList;
    }

    // 修改
    public int chg(String number, Lesson lesson) {
        Connection conn = DBUtil.getConn();
        String sql = "update lesson set name=?,teacher=?,beginweek=?,endweek=?,begin=?,end=?,address=? where number=?";
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lesson.getName());
            pstmt.setString(2, lesson.getTeacher());
            pstmt.setString(3, lesson.getBeginweek());
            pstmt.setString(4, lesson.getEndweek());
            pstmt.setString(5, lesson.getBegin());
            pstmt.setString(6, lesson.getEnd());
            pstmt.setString(7, lesson.getAddress());
            pstmt.setString(8, number);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.closePstmt(pstmt);
            DBUtil.closeConn(conn);
        }
        return result;
    }
}
