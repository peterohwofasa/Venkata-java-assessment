package com.xib.assessment.web.rest;

import com.xib.assessment.domain.Agent;
import com.xib.assessment.repository.AgentRepository;
import com.xib.assessment.service.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class AgentController {


    AgentRepository agentRepository;
    AgentService agentService;


    @Autowired
    public AgentController(AgentRepository agentRepository, AgentService agentService) {
        this.agentRepository = agentRepository;
        this.agentService = agentService;
    }

    @PostMapping("/agent")
    public ResponseEntity<Agent> createAgent(@RequestBody Agent agent){
       return new ResponseEntity<>(
               agentService.createAgent(agent), HttpStatus.CREATED);
    }


    @GetMapping("agent/{id}")
    @ResponseBody
    public Agent findAgent(@PathVariable("id") Long id) {
        return agentService.findAgent(id);
    }

    @RequestMapping(value = "/agents", method = RequestMethod.GET)
    @ResponseBody
    public List<Agent> getAllAgents(@RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(50);
        final Page<Agent> paginatedAgents = agentService.getPaginatedAgents(currentPage, pageSize);
        return paginatedAgents.getContent();
    }
}
