package simulation.state;

import config.AppConfig;
import userIO.IConsoleOutputForTeamCreation;
import userIO.IUserInputForTeamCreation;

import java.util.Scanner;

public class PlayerChoiceState implements IHockeyState {

    private String input;
    private String stateName;
    private HockeyContext hockeyContext;
    private String userInput;
    private static final String ONE="1";
    private static final String TWO="2";
    private static final String IMPORTSTATE="importState";
    private static final String CREATEORLOADTEAM="createOrLoadTeam";

    public PlayerChoiceState(HockeyContext hockeyContext, String input, String stateName) {
        this.input = input;
        this.stateName = stateName;
        this.hockeyContext = hockeyContext;
    }

    @Override
    public void entry() {

    }

    @Override
    public void process() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(input);
        userInput = scanner.nextLine();
    }

    @Override
    public IHockeyState exit() {
        switch (stateName) {
            case IMPORTSTATE: {
                if (userInput.equals(ONE)) {
                    IUserInputForTeamCreation inputForTeamCreation = AppConfig.getInstance().getInputForTeamCreation();
                    IConsoleOutputForTeamCreation outputForTeamCreation = AppConfig.getInstance().getOutputForTeamCreation();
                    CreateTeamState createTeamState = new CreateTeamState(hockeyContext,
                            inputForTeamCreation, outputForTeamCreation);
                    return createTeamState;
                } else if (userInput.equals(TWO)) {
                    LoadTeamState loadTeamState = new LoadTeamState(hockeyContext);
                    return loadTeamState;
                }
                break;
            }
            case CREATEORLOADTEAM: {
                InternalState internalState = new InternalState(hockeyContext);
                return internalState;
            }
            default: {

            }
        }
        return null;
    }

}
