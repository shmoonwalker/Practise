package com.example.practise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PractiseApplicationTests {

    @Autowired
    private GreetingRepository greetingRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void savesAndReadsAGreetingWithJdbc() {
        Greeting saved = greetingRepository.save("Hello Neon!");

        assertThat(saved.id()).isNotNull();
        assertThat(greetingRepository.findAll())
                .extracting(Greeting::message)
                .contains("Hello Neon!");
    }
}
