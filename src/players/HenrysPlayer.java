package players;

import game.HandRanks;
import game.Player;

public class HenrysPlayer extends Player {
    public HenrysPlayer(String name) {
        super(name);
    }

    int handValue = evaluatePlayerHand().getValue();
    @Override
    protected void takePlayerTurn() {
        if(getGameState().isActiveBet()) {
            if(getGameState().getNumRoundStage() == 0 ) {
                //there is bet pre-flop
            }
            if(handValue >= 1 && getGameState().getNumRoundStage() < 2) {
                allIn();
            }
        }
    }

    @Override
    protected boolean shouldFold() {

        if (evaluatePlayerHand().getValue() >= HandRanks.ROYAL_FLUSH.getValue()) {
            return false;
        } else if (evaluatePlayerHand().getValue() >= HandRanks.STRAIGHT_FLUSH.getValue()) {
            return false;
        } else if (evaluatePlayerHand().getValue() >= HandRanks.FOUR_OF_A_KIND.getValue()) {
            return false;
        } else if (evaluatePlayerHand().getValue() >= HandRanks.FULL_HOUSE.getValue()) {
            return false;
        } else if (evaluatePlayerHand().getValue() >= HandRanks.FLUSH.getValue()) {
            return false;
        } else if (evaluatePlayerHand().getValue() >= HandRanks.STRAIGHT.getValue()) {
            return false;
        } else if (evaluatePlayerHand().getValue() >= HandRanks.THREE_OF_A_KIND.getValue()) {
            return false;
        }
        return !shouldFold();
    }

    @Override
    protected boolean shouldCheck() {
        boolean hasDecentHand = evaluatePlayerHand().getValue() >= HandRanks.HIGH_CARD.getValue();
        boolean hasDecentHand1 = evaluatePlayerHand().getValue() >= HandRanks.PAIR.getValue();
        boolean hasDecentHand2 = evaluatePlayerHand().getValue() >= HandRanks.TWO_PAIR.getValue();

        boolean hasAnyDecent = false;
        if (hasDecentHand == true || hasDecentHand1 == true || hasDecentHand2 == true) {
            hasAnyDecent = true;
        }
        return hasAnyDecent;
    }

    @Override
    protected boolean shouldCall() {

        if (evaluatePlayerHand().getValue() >= HandRanks.ROYAL_FLUSH.getValue()) {
            return false;
        } else if (evaluatePlayerHand().getValue() >= HandRanks.STRAIGHT_FLUSH.getValue()) {
            return false;
        } else if (evaluatePlayerHand().getValue() >= HandRanks.FOUR_OF_A_KIND.getValue()) {
            return false;
        } else if (evaluatePlayerHand().getValue() >= HandRanks.FULL_HOUSE.getValue()) {
            return false;
        } else if (evaluatePlayerHand().getValue() >= HandRanks.FLUSH.getValue()) {
            return false;
        } else if (evaluatePlayerHand().getValue() >= HandRanks.STRAIGHT.getValue()) {
            return false;
        } else if (evaluatePlayerHand().getValue() >= HandRanks.THREE_OF_A_KIND.getValue()) {
            return false;
        }
        return false;
    }

    @Override
    protected boolean shouldRaise() {
        
        return false;
    }

    @Override
    protected boolean shouldAllIn() {

        return false;
    }
}
