package com.example.practise;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Repository
public class GreetingRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert greetingInsert;

    public GreetingRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.greetingInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("greetings")
                .usingColumns("message", "created_at")
                .usingGeneratedKeyColumns("id");
    }

    public List<Greeting> findAll() {
        return jdbcTemplate.query(
                "SELECT id, message, created_at FROM greetings ORDER BY id",
                (resultSet, rowNumber) -> new Greeting(
                        resultSet.getLong("id"),
                        resultSet.getString("message"),
                        resultSet.getTimestamp("created_at").toInstant()
                )
        );
    }

    public Greeting save(String message) {
        Instant createdAt = Instant.now();
        Number id = greetingInsert.executeAndReturnKey(Map.of(
                "message", message,
                "created_at", Timestamp.from(createdAt)
        ));
        return new Greeting(id.longValue(), message, createdAt);
    }
}
