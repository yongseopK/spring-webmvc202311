package com.spring.mvc.chap06.jdbc;

import com.spring.mvc.chap06.entity.Person;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcRepository {

    // db 연결 설정정보
    private String url = "jdbc:mariadb://localhost:3306/spring"; // 데이터베이스 URL
    private String userName = "root";
    private String password = "mariadb";


    // JDBC 드라이버 로딩
    public JdbcRepository() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 데이터베이스 커넥션 얻기
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    // INSERT 기능
    public void save(Person person) {

        Connection conn = null;

        //1. DB연결하고 연결 정보를 얻어와야 함
        try {

            conn = DriverManager.getConnection(url, userName, password);
            // 2. 트랜잭션을 시작
            conn.setAutoCommit(false); // 자동 커밋 비활성화

            // 3. SQL을 생성
            String sql = "INSERT INTO person " +
                    "(id, person_name, person_age) " +
                    "VALUES (?, ?, ?)";

            // 4. SQL을 실행시켜주는 객체 얻어와야 함
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 파라미터 세팅
            pstmt.setString(1, person.getId());
            pstmt.setString(2, person.getPersonName());
            pstmt.setInt(3, person.getPersonAge());

            // 6. SQL 실행 명령
            // executeUpdate - 갱신, executeQuery - 조회
            int result = pstmt.executeUpdate();// 리턴값은 성공한 쿼리의 개수

            // 7. 트랜잭션 처리
            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if(conn != null) conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // UPDATE 기능
    public void update(Person person) {

        Connection conn = null;

        //1. DB연결하고 연결 정보를 얻어와야 함
        try {

            conn = DriverManager.getConnection(url, userName, password);
            // 2. 트랜잭션을 시작
            conn.setAutoCommit(false); // 자동 커밋 비활성화

            // 3. SQL을 생성
            String sql = "UPDATE person SET person_name = ?, person_age = ? where id = ?";

            // 4. SQL을 실행시켜주는 객체 얻어와야 함
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 파라미터 세팅
            pstmt.setString(1, person.getPersonName());
            pstmt.setInt(2, person.getPersonAge());
            pstmt.setString(3, person.getId());

            // 6. SQL 실행 명령
            // executeUpdate - 갱신, executeQuery - 조회
            int result = pstmt.executeUpdate();// 리턴값은 성공한 쿼리의 개수

            // 7. 트랜잭션 처리
            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if(conn != null) conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // DELETE 기능
    public void delete(String id) {

        Connection conn = null;

        //1. DB연결하고 연결 정보를 얻어와야 함
        try {

            conn = DriverManager.getConnection(url, userName, password);
            // 2. 트랜잭션을 시작
            conn.setAutoCommit(false); // 자동 커밋 비활성화

            // 3. SQL을 생성
            String sql = "DELETE FROM person WHERE id = ?";

            // 4. SQL을 실행시켜주는 객체 얻어와야 함
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 파라미터 세팅
            pstmt.setString(1, id);

            // 6. SQL 실행 명령
            // executeUpdate - 갱신, executeQuery - 조회
            int result = pstmt.executeUpdate();// 리턴값은 성공한 쿼리의 개수

            // 7. 트랜잭션 처리
            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if(conn != null) conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 전체 회원 조회
    public List<Person> findAll() {

        List<Person> people = new ArrayList<>();

        try {
            // 1. DB에 연결해서 연결정보 획득
            Connection conn = DriverManager.getConnection(url, userName, password);

            // 2. SQL 생성
            String sql = "SELECT * FROM person";

            // 3. SQL 실행을 위한 객체 획득
            PreparedStatement psmt = conn.prepareStatement(sql);

            // 4. ? 파라미터 채우기

            // 5. SQL 실행 명령
            ResultSet rs = psmt.executeQuery();

            // 6. 결과집합 조작하기

            // 커서를 한 행씩 이동시키는 기능
            while (rs.next()){
                // 커서에 위치한 데이터 레코드 읽기
                String id = rs.getString("id");
                String personName = rs.getString("person_name");
                int personAge = rs.getInt("person_age");

                Person p = new Person(id, personName, personAge);

                people.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return people;
    }

    // 단일 조회(id로 조회)
    public Person findOne(String id) {
        try {
            // 1. DB에 연결해서 연결정보 획득
            Connection conn = DriverManager.getConnection(url, userName, password);

            // 2. SQL 생성
            String sql = "SELECT * FROM person WHERE id = ?";

            // 3. SQL 실행을 위한 객체 획득
            PreparedStatement psmt = conn.prepareStatement(sql);

            // 4. ? 파라미터 채우기
            psmt.setString(1, id);

            // 5. SQL 실행 명령
            ResultSet rs = psmt.executeQuery();

            // 6. 결과집합 조작하기

            // 커서를 한 행씩 이동시키는 기능
            while (rs.next()){
                // 커서에 위치한 데이터 레코드 읽기
                String pid = rs.getString("id");
                String personName = rs.getString("person_name");
                int personAge = rs.getInt("person_age");

                return new Person(pid, personName, personAge);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
