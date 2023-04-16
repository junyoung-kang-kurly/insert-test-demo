package bonjugi.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootTest
class MysqlTest {


    @Autowired
    @Qualifier("mysqlTemplate")
    private JdbcTemplate template;

    @Autowired
    private InsertService insertService;


    /**
     : 497 회 성공. 소요 시간 : 220
     : 498 회 성공. 소요 시간 : 153
     : 499 회 성공. 소요 시간 : 163
     : 전체 소요 시간 : 107,247  (postgresql 의 varchar tsid가 50초였어서, 2배 느리긴 하지만 상당히 빠른편)
     */
    @Test
    @DisplayName("varchar tsid")
    void tsid() {
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            long start2 = System.currentTimeMillis();
            insertService.insertToVarchar(template, insertService.getTsids(25000));
            log.info("{} 회 성공. 소요 시간 : {}", i, (System.currentTimeMillis() - start2));
        }
        log.info("전체 소요 시간 : {}", (System.currentTimeMillis() - start1));
    }

    /**
     * 뒤로갈수록 급격히 떨어짐. 테스트 불가
     * 71회차 부터는 20초
     * 80회차 부터는 40초
     * 100회차 부터는 90초
     */
    @Test
    @DisplayName("varchar uuid")
    void uuid() {
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            long start2 = System.currentTimeMillis();
            insertService.insertToVarchar(template, insertService.getUuids(25000));
            log.info("{} 회 성공. 소요 시간 : {}", i, (System.currentTimeMillis() - start2));
        }
        log.info("전체 소요 시간 : {}", (System.currentTimeMillis() - start1));
    }

}
