package model;

import simulation.data.IAddLeagueFactory;
import simulation.data.ILoadConferenceFactory;
import simulation.data.ILoadPlayerFactory;
import simulation.model.Conference;
import simulation.model.FreeAgent;
import simulation.model.League;
import simulation.model.Player;

import java.util.ArrayList;
import java.util.List;

public class AddLeagueMock implements IAddLeagueFactory {

    public List formConferenceList() throws Exception {
        List<Conference> conferenceList = new ArrayList<>();

        ILoadConferenceFactory conferenceFactory = new LoadConferenceMock();
        Conference conference = new Conference(1, conferenceFactory);
        conferenceList.add(conference);

        conference = new Conference(2, conferenceFactory);
        conferenceList.add(conference);

        return conferenceList;
    }

    private FreeAgent formFreeAgent() throws Exception {
        FreeAgent freeAgent = new FreeAgent();

        freeAgent.setId(1);
        List<Player> playerList = new ArrayList<>();

        ILoadPlayerFactory playerFactory = new LoadPlayerMock();
        Player player = new Player(1, playerFactory);
        playerList.add(player);

        player = new Player(5, playerFactory);
        playerList.add(player);

        freeAgent.setPlayerList(playerList);

        return freeAgent;
    }

    @Override
    public int addLeague(League league) throws Exception {
        league = new League(1);
        return league.getId();
    }
}
