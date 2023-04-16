package bonjugi.demo;

import io.hypersistence.tsid.TSID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InsertService {


    public List<Object> getUuids(int count) {
        List<Object> lists = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lists.add(UUID.randomUUID());
        }
        return lists;
    }

    public List<Object> getTsids(int count) {
        List<Object> lists = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lists.add(TSID.Factory.getTsid().toString());
        }
        return lists;
    }


    public void insertToVarchar(JdbcTemplate jdbcTemplate, List<Object> ids) {
        String sql = "INSERT INTO my_temp_tbl (id) VALUES ";
        String values = ids.stream().map(x -> "('" + x.toString() + "')").collect(Collectors.joining(","));
        String completedSql = sql + values;
        jdbcTemplate.update(completedSql);
    }

    public void insertToUuidForPg(JdbcTemplate jdbcTemplate, List<Object> ids) {
        String sql = "INSERT INTO my_temp_tbl_uuid (id) VALUES ";
        String values = ids.stream().map(x -> "('" + x.toString() + "')").collect(Collectors.joining(","));
        String completedSql = sql + values;
        jdbcTemplate.update(completedSql);
    }


    /**
     * @deprecated 이 방식은 multiple 로 처리되질 않고 건건히 insert 된다. (왜지?)
     * 게다가 훨씬 느리다. 0회차부터 45,483가 소요됨
     * 따라서 이방식을 사용하지 않고 위의 string 을 이어붙이는 방식으로 처리한다.
     * @param jdbcTemplate
     * @param ids
     */
//    public void insertToVarcharWithbatchUpdate(JdbcTemplate jdbcTemplate, List<Object> ids) {
//        String sql = "INSERT INTO my_temp_tbl (id) VALUES (?)";
//        jdbcTemplate.batchUpdate(sql, ids, 50, (PreparedStatement ps, Object obj) -> {
//            ps.setString(1, obj.toString());
//        });
//    }

}
