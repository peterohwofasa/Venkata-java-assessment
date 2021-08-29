package com.xib.assessment.service;

import com.xib.assessment.domain.Agent;
import com.xib.assessment.repository.AgentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AgentService {


    private final AgentRepository agentRepository;

    @Autowired
    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }
public Agent createAgent(Agent agent){
    log.info("creating new agent {}", agent);
    if(agent != null) return agentRepository.save(agent);
    else return new Agent();
}
public Agent findAgent(Long id){
    Agent a = agentRepository.findById(id).
            orElseThrow(() -> new IllegalArgumentException("id not found: " + id));
    return a;
}
    public Page getPaginatedAgents(final int pageNumber, final int pageSize) {
       log.info("Fetching the paginated agent from the dB.");
        final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
       return agentRepository.findAll(pageable);
    }



}
