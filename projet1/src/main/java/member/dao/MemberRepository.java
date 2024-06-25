package member.dao;

import member.dto.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import static common.JDBCTemplate.*;

public class MemberRepository
{
    private Properties pros =new Properties();
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rset = null;

    public MemberRepository() {
        try {
            this.pros.loadFromXML(new FileInputStream("src/main/java/member/mapper/member-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList memberViewAll()
    {
        ArrayList arrayList = new ArrayList();
        String query = pros.getProperty("memberAll");
        con = getConnection();
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            while (rset.next()){
                MemberDTO member = new MemberDTO();
                member.setMemberNum(rset.getString("memberNum"));
                member.setMemberName(rset.getString("memberName"));
                arrayList.add(member);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        return arrayList;
    }

    public MemberDTO memberFindByName(String name) {
        String query = pros.getProperty("memberFindByName");
        con = getConnection();
        MemberDTO member = new MemberDTO();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            rset = pstmt.executeQuery();
            if(rset.next()){
                member.setMemberNum(rset.getString("memberNum"));
                member.setMemberName(rset.getString("memberName"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        return member;
    }


    public MemberDTO memberFindById(String index){
        String query = pros.getProperty("memberFindById");
        con = getConnection();
        MemberDTO member = null;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, index);
            rset = pstmt.executeQuery();
            if(rset.next()){
                member = new MemberDTO();
                member.setMemberNum(rset.getString("memberNum"));
                member.setMemberName(rset.getString("memberName"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }

        return member;
    }

    public int memberInsert(MemberInsertDTO member) {
        // 값을 추가
        // 쿼리 가져옴
        String query = pros.getProperty("memberInsert");
        // connection
        con = getConnection();
        int result = 0;
        // 쿼리를 사용하기 위함
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,member.getMemberNum());
            pstmt.setString(2,member.getMemberName());


            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

        return result;
    }

    public int memberModify(String name, String index) {
        // 쿼리불러오기
        String query = pros.getProperty("memberModify");
        con = getConnection();
        int result = 0;
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, index);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

        return result;



    }

    public int memberDelete(String index)
    {
        String query = pros.getProperty("memberDelete");
        con = getConnection();
        int result = 0;
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, index);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

        return result;

    }
    public ArrayList bookRegistration(int bookQuantity, String[] bookName) {
        String result = "";
        ArrayList bookList = new ArrayList();
        String query = pros.getProperty("bookRegistration");
        con = getConnection();
        try {
            for (int i = 0; i < bookQuantity; i++) {
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, bookName[i]);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    result = "도서가 성공적으로 등록되었습니다.";
                } else {
                    result = "도서등록에 실패 하였습니다.";
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(result);
            close(rset);
            close(pstmt);
            close(con);
        }
        return bookList;
    }

    public ArrayList bookNameSearch(String bookName) {
        ArrayList bookList = new ArrayList();
        String query = pros.getProperty("bookNameSearch");
        con = getConnection();
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, bookName);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                BookDTO book = new BookDTO();
                book.setBookNum(rset.getInt("bookNum"));
                book.setBookName(rset.getString("bookName"));
                book.setBookStatus(rset.getString("bookStatus"));
                bookList.add(book);
                System.out.println(book.toString());
            }
            if (bookList.isEmpty()) {
                System.out.println("조회결과 없는 도서입니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        return bookList;
    }

    public ArrayList bookModify(String bookName, String newBookName) {
        ArrayList bookList = new ArrayList();
        int result = 0;
        String query = pros.getProperty("bookModify");
        con = getConnection();
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newBookName);
            pstmt.setString(2, bookName);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("도서 이름이 성공적으로 수정되었습니다.");
            } else {
                System.out.println("도서 이름을 수정하는데 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        return bookList;
    }

    public ArrayList bookDelete(String bookName) {
        ArrayList bookList = new ArrayList();
        String query = pros.getProperty("bookDelete");
        con = getConnection();
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, bookName);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("도서의 상태가 성공적으로 수정되었습니다.");
            } else {
                System.out.println("도서의 상태를 수정하는데 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        return bookList;
    }

    public ArrayList registeredBookList() {
        ArrayList bookList = new ArrayList();
        String query = pros.getProperty("registeredBookList");
        con = getConnection();
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                BookDTO book = new BookDTO();
                book.setBookNum(rset.getInt("bookNum"));
                book.setBookName(rset.getString("bookName"));
                book.setBookStatus(rset.getString("bookStatus"));
                bookList.add(book);
                System.out.println(book.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        return bookList;
    }






}

