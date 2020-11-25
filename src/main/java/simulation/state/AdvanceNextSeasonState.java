package simulation.state;

import presentation.ConsoleOutput;
import simulation.model.*;

import java.time.LocalDate;

public class AdvanceNextSeasonState implements ISimulateState {

    public static final String SEASON_CURRENT_DATE = "Advanced to next season! Current date is ";
    public static final String AGING_TO_NEXT_SEASON = "Aging all players to the start of next season!";
    private ILeague league;
    private IHockeyContext hockeyContext;
    private IAging aging;
    private LocalDate beforeDate;

    public AdvanceNextSeasonState(IHockeyContext hockeyContext, LocalDate before) {
        this.hockeyContext = hockeyContext;
        this.league = hockeyContext.getUser().getLeague();
        this.aging = league.getGamePlayConfig().getAging();
        this.beforeDate = before;
    }

    @Override
    public ISimulateState action() {

        INHLEvents nhlEvents = league.getNHLRegularSeasonEvents();
        league.setCurrentDate(nhlEvents.getNextSeasonDate());
        ConsoleOutput.getInstance().printMsgToConsole(SEASON_CURRENT_DATE + nhlEvents.getNextSeasonDate());
        aging.agingPlayerPeriod(league,beforeDate);
        ConsoleOutput.getInstance().printMsgToConsole(AGING_TO_NEXT_SEASON);

        return exit();
    }



    private ISimulateState exit() {
        return new PersistState(hockeyContext);
    }
}
