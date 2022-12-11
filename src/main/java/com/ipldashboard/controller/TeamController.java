package com.ipldashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ipldashboard.model.Team;
import com.ipldashboard.repository.MatchRepository;
import com.ipldashboard.repository.TeamRepository;

@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MatchRepository matchRepository;
    
    public TeamRepository getTeamRepository() {
        return teamRepository;
    }

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }
    
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    
    

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        // 1) if we return only by team name we will return only the details of team
        Team team =  teamRepository.findByTeamName(teamName);
        // 2) but we need to also have to show what are their recent matches and latest matches
        // 3) here we have used count as we need to limit the number of matches to be displayed 
        // 4) The count is simply like a pagination... 
        team.setMatches(matchRepository.findLatestMatchesByTeam(teamName,10));
        return team;
    }



    
    
}
