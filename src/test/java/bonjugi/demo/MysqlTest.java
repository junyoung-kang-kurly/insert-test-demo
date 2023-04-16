package bonjugi.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
class MysqlInsertTest {


    @Autowired
    private JdbcTemplate mysqlTemplate;
    @Autowired
    private InsertService insertService;

    private Set<Long> executionSeconds = new HashSet<>();

    @Test
    @DisplayName("pg tsid")
    void tsid() {
        List tsids = insertService.getTsids(25000);
        for (int i = 0; i < 1000; i++) {

            long start = System.currentTimeMillis();

            insertService.insertObjects(mysqlTemplate, tsids);

            long executionSeconds = (System.currentTimeMillis() - start) / 1000;
            log.info("{} 회 성공. 소요 시간(초) : {}", i, executionSeconds);
        }
    }

}
