package com.xib.assessment.web.rest;

import com.xib.assessment.domain.Agent;
import com.xib.assessment.domain.Team;
import com.xib.assessment.repository.AgentRepository;
import com.xib.assessment.repository.TeamRepository;
import com.xib.assessment.service.AgentService;
import com.xib.assessment.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class TeamController {

   private final TeamRepository teamRepository;
    private final AgentRepository agentRepository;
private final TeamService teamService;
    @Autowired
    public TeamController(TeamRepository teamRepository,
                          AgentRepository agentRepository, TeamService teamService){
        this.teamRepository = teamRepository;
        this.agentRepository = agentRepository;
        this.teamService = teamService;
    }

    @GetMapping("/teams/free-teams")
    public List<Team> getALlFreeTeams(){
          return teamService.findAllFreeTeams();
    }
    @PostMapping("/team")
    public ResponseEntity<Team> createTeam(@RequestBody Team team){
        log.info("creating new team {}", team);
        team = teamService.createTeam(team);
        return new ResponseEntity<>(team, HttpStatus.CREATED);
        }

    @RequestMapping(value = "/team/{id}/agent", method = RequestMethod.PUT)
    public Agent createTeam(@PathVariable("id") Long id, @RequestBody Team team) {

        Agent agent = agentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("agent not found"));
        teamService.createTeam(team);
        agent.setTeam(team);
      agent = agentRepository.save(agent);
        return agent;
    }
    @GetMapping("/team/{id}")
    public Team findTeam(@PathVariable("id") Long id){
          return teamService.findTeam(id);
    }
    @GetMapping("teams")
    public List<Team> getAllTeams(){
      return teamService.findAll();
    }
}
