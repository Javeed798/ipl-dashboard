package com.ipldashboard.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long>{

    List<Match> findByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2,Pageable pageable);

    //  instead of having this logic in controllers we r writing here
    // we can even create a seperate DAO package and can have all the methods there
    //  but now we have support of having methods in the interface by using default access modifier we r doing here only
    default List<Match> findLatestMatchesByTeam(String teamName,int count){
        return findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
        
    }
    
}
