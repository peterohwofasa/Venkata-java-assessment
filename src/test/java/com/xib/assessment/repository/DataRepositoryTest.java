package com.xib.assessment.repository;

import com.xib.assessment.domain.Agent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.List;

@DataJpaTest
public class DataRepositoryTest {
    @Autowired
    AgentRepository agentRepository;

    @Test
    public void testDataRepository() throws Exception {

        List<Agent> agents = agentRepository.findAll();

        System.out.println(agents);
        Assert.notNull(agents, "null object");
    }
}