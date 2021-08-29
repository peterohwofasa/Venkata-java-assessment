package com.xib.assessment.service;

import com.xib.assessment.domain.Team;
import com.xib.assessment.repository.AgentRepository;
import com.xib.assessment.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TeamService {
    
    private final AgentRepository agentRepository;
    private final TeamRepository teamRepository;
    
    @Autowired
    public TeamService(AgentRepository agentRepository, TeamRepository teamRepository){
        this.agentRepository = agentRepository;
        this.teamRepository = teamRepository;
    }
    public Team createTeam(Team team){
        if(team != null) return teamRepository.save(team);
        else return new Team();
    }
    public List<Team> findAll(){
        List<Team> teamList =teamRepository.findAll();
        return teamList != null ? teamList : Collections.EMPTY_LIST;
    }
    public List<Team> findAllFreeTeams(){
        List<Team> freeTeams = new ArrayList<>();
        List<Team> agentTeams = agentRepository.findAll().stream().map(a ->
                a.getTeam()).collect(Collectors.toList());

        findAll().stream().forEach( t -> {
            if(!agentTeams.contains(t)){
                freeTeams.add(t);
            }
        });
        log.info("free teams {}", freeTeams);
        return freeTeams;
    }
    public Team findTeam(long id){
        Team t = teamRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("id not found" + id));
        return t;
    }
}
