package bonjugi.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class TestConfig {

    @Bean("mysqlJdbcTemplate")
    public JdbcTemplate mysqlTemplate(DriverManagerDataSource mysqlDataSource) {
        return new JdbcTemplate(mysqlDataSource);
    }

    @Bean("pgJdbcTemplate")
    public JdbcTemplate pgJdbcTemplate(DriverManagerDataSource mysqlDataSource) {
        return new JdbcTemplate(mysqlDataSource);
    }


    @Bean("mysqlDataSource")
    public DriverManagerDataSource mysqlDataSource() {
        // MySQL 연결 정보 설정
        String mysqlUrl = "jdbc:mysql://localhost:3306/insert-test";
        String mysqlUser = "your_mysql_username";
        String mysqlPassword = "your_mysql_password";


        // MySQL 데이터베이스 연결 DataSource 생성
        DriverManagerDataSource mysqlDataSource = new DriverManagerDataSource();
        mysqlDataSource.setUrl(mysqlUrl);
        mysqlDataSource.setUsername(mysqlUser);
        mysqlDataSource.setPassword(mysqlPassword);
        return mysqlDataSource;
    }

    @Bean("pgDataSource")
    public DriverManagerDataSource postgreSql() {
        // PostgreSQL 연결 정보 설정
        String postgresUrl = "jdbc:postgresql://localhost:5432/insert-test";
        String postgresUser = "your_postgres_username";
        String postgresPassword = "your_postgres_password";

        // PostgreSQL 데이터베이스 연결 DataSource 생성
        DriverManagerDataSource postgresDataSource = new DriverManagerDataSource();
        postgresDataSource.setUrl(postgresUrl);
        postgresDataSource.setUsername(postgresUser);
        postgresDataSource.setPassword(postgresPassword);

        return postgresDataSource;
    }
}
