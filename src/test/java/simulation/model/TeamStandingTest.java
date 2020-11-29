package simulation.model;


import simulation.dao.ILeagueDao;
import simulation.dao.ITeamScoreDao;
import org.junit.BeforeClass;
import org.junit.Test;
import simulation.mock.LeagueMock;
import simulation.mock.TeamScoreMock;
import simulation.state.HockeyContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeamStandingTest {

    private static ITeamScoreDao iTeamScoreDao;
    private static ILeagueDao iLeagueDao;

    @BeforeClass
    public static void setFactoryObj() {
        iTeamScoreDao = new TeamScoreMock();
        iLeagueDao = new LeagueMock();
    }

    @Test
    public void defaultConstructorTest() {
        ITeamStanding teamStanding = HockeyContext.getInstance().getModelFactory().newTeamStanding();
        assertNotNull(teamStanding.getTeamsScoreList());
    }

    @Test
    public void initializeTeamStandingsTest() throws Exception {

        TeamScore teamScore = new TeamScore(1, iTeamScoreDao);
        assertEquals(teamScore.getId(), 1);

        ITeamStanding teamStanding = HockeyContext.getInstance().getModelFactory().newTeamStanding();
        List<ITeam> teamList = new ArrayList<>();
        Team team = new Team();
        teamList.add(team);
        teamStanding.initializeTeamStandings(teamList);
        assertTrue(teamStanding.getTeamsScoreList().size() != 0);
    }

}
