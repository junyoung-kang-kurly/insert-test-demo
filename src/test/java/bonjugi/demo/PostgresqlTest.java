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
class PostgresqlTest {


    @Autowired
    @Qualifier("postgresqlTemplate")
    private JdbcTemplate template;

    @Autowired
    private InsertService insertService;

    /**
     497 회 성공. 소요 시간 : 130
     498 회 성공. 소요 시간 : 116
     499 회 성공. 소요 시간 : 130
     전체 소요 시간 : 59,680
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
     497 회 성공. 소요 시간 : 792
     498 회 성공. 소요 시간 : 826
     499 회 성공. 소요 시간 : 754
     전체 소요 시간 : 245,189
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

    /**
     * 497 회 성공. 소요 시간 : 242
     * 498 회 성공. 소요 시간 : 251
     * 499 회 성공. 소요 시간 : 249
     * 전체 소요 시간 : 104,424
     */
    @Test
    @DisplayName("uuid type")
    void uuidType() {
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            long start2 = System.currentTimeMillis();
            insertService.insertToUuidForPg(template, insertService.getUuids(25000));
            log.info("{} 회 성공. 소요 시간 : {}", i, (System.currentTimeMillis() - start2));
        }
        log.info("전체 소요 시간 : {}", (System.currentTimeMillis() - start1));
    }



}
