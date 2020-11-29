package simulation.GameSubjectObservers;

import org.junit.BeforeClass;
import org.junit.Test;
import simulation.dao.IGameDao;
import simulation.dao.IUserDao;
import simulation.factory.HockeyContextConcrete;
import simulation.factory.IHockeyContextFactory;
import simulation.mock.GameMock;
import simulation.mock.UserMock;
import simulation.model.User;
import simulation.state.IHockeyContext;
import simulation.trophyPublisherSubsribers.CoachStatAbilitySubscriber;
import simulation.trophyPublisherSubsribers.ITrophyEventListeners;
import simulation.trophyPublisherSubsribers.TrophySystemPublisher;

import static org.junit.Assert.*;

public class TotalGamesSubjectTest {

    private static IHockeyContext hockeyContext;
    private static IUserDao userFactory;
    private static IGameDao gameFactory;
    private static IHockeyContextFactory hockeyContextFactory;

    @BeforeClass
    public static void init() throws Exception {
        userFactory = new UserMock();
        hockeyContextFactory = HockeyContextConcrete.getInstance();
        hockeyContext = hockeyContextFactory.newHockeyContext();
        gameFactory = new GameMock();
        User user = new User(4, userFactory);
        hockeyContext.setUser(user);
        hockeyContext.getUser().getLeague().initializeTeamStats();
    }

    @Test
    public void testAttach() {
        TotalGameObserver totalGameObserver = new TotalGameObserver();
        TotalGamesSubject totalGamesSubject = new TotalGamesSubject();
        totalGamesSubject.attach(totalGameObserver);
        assertTrue(totalGamesSubject.getListeners().size()==1);
    }

    @Test
    public void testDetach() {
        TotalGameObserver totalGameObserver = new TotalGameObserver();
        TotalGamesSubject totalGamesSubject = new TotalGamesSubject();
        totalGamesSubject.attach(totalGameObserver);
        totalGamesSubject.detach(totalGameObserver);
        assertTrue(totalGamesSubject.getListeners().size()==0);
    }

    @Test
    public void testNotifyObservers() {
        TotalGameObserver totalGameObserver = new TotalGameObserver();
        TotalGamesSubject totalGamesSubject = new TotalGamesSubject();
        totalGamesSubject.attach(totalGameObserver);
        totalGamesSubject.notifyObservers(hockeyContext.getUser().getLeague(),"Team11",1);
        assertTrue(hockeyContext.getUser().getLeague().getTeamStatByTeamName("Team11").getNumberOfGamesPlayed()==1);
        assertNotNull(hockeyContext.getUser().getLeague().getTeamStatByTeamName("Team11").getNumberOfGamesPlayed()==1);
    }
}