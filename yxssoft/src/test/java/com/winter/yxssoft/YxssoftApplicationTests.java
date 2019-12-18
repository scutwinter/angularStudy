package com.winter.yxssoft;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
class YxssoftApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void datasourceTest() throws SQLException{
        Connection connection = dataSource.getConnection();

        System.out.println(connection != null);
        connection.close();
    }
    @Test
    void contextLoads() {
    }

}
