package players;

import game.GameEngine;
import game.GameState;
import game.HandRanks;
import game.Player;

public class HenrysPlayer extends Player {
    public HenrysPlayer(String name) {
        super(name);
    }
    int RaiseCalled;

    @Override
    protected void takePlayerTurn() {
        if(getGameState().isActiveBet()) {
            if(getGameState().getNumRoundStage() == 0 ) {
                //there is bet pre-flop
            }
            if(getGameState().getNumRoundStage() < 2) {
                allIn();
            }
        }
    }

    @Override
    protected boolean shouldFold() {
        HandRanks handValue = evaluatePlayerHand();
        if (handValue.equals(HandRanks.ROYAL_FLUSH)) { // replace "evaluatePlayerHand().getValue() >= HandRanks.ROYAL_FLUSH.getValue()" with "handValue.equals(HandRanks.ROYAL_FLUSH)"  Replace ROYAL_FLUSH with the others as needed (eg. STRAIGHT or FLUSH)
            return false;
        } else if (handValue.equals(HandRanks.STRAIGHT_FLUSH)) {
            return false;
        } else if (handValue.equals(HandRanks.FOUR_OF_A_KIND)) {
            return false;
        } else if (handValue.equals(HandRanks.FULL_HOUSE)) {
            return false;
        } else if (handValue.equals(HandRanks.FLUSH)) {
            return false;
        } else if (handValue.equals(HandRanks.STRAIGHT)) {
            return false;
        } else if (handValue.equals(HandRanks.THREE_OF_A_KIND)) {
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
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected boolean shouldCall() {
        HandRanks handValue = evaluatePlayerHand();
        if (evaluatePlayerHand().getValue() >= HandRanks.ROYAL_FLUSH.getValue()) {
            return false;
        } else if (handValue.equals(HandRanks.STRAIGHT_FLUSH)) {
            return false;
        } else if (handValue.equals(HandRanks.FOUR_OF_A_KIND)) {
            return false;
        } else if (handValue.equals(HandRanks.FULL_HOUSE)) {
            return false;
        } else if (handValue.equals(HandRanks.FLUSH)) {
            return false;
        } else if (handValue.equals(HandRanks.STRAIGHT)) {
            return false;
        } else if (handValue.equals(HandRanks.THREE_OF_A_KIND)) {
            return false;
        }
        return !shouldCall();
    }

    @Override
    protected boolean shouldRaise() {
        HandRanks handValue = evaluatePlayerHand();
        boolean hasStrongHand = handValue.equals(HandRanks.ROYAL_FLUSH);
        boolean hasStrongHand1 = handValue.equals(HandRanks.STRAIGHT_FLUSH);
        boolean hasStrongHand2 = handValue.equals(HandRanks.FOUR_OF_A_KIND);
        boolean hasStrongHand3 = handValue.equals(HandRanks.FULL_HOUSE);
        boolean hasStrongHand4 = handValue.equals(HandRanks.STRAIGHT);
        boolean hasStrongHand5 = handValue.equals(HandRanks.THREE_OF_A_KIND);

        boolean hasAnyStrong = false;




        if (hasStrongHand == true || hasStrongHand1 == true || hasStrongHand2 == true || hasStrongHand3 == true || hasStrongHand4 == true || hasStrongHand5 == true) {
            hasAnyStrong = true;
            RaiseCalled++;
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    protected boolean shouldAllIn() {
        HandRanks handValue = evaluatePlayerHand();
        boolean hasHand = handValue.equals(HandRanks.ROYAL_FLUSH);
        boolean hasHand1 = handValue.equals(HandRanks.STRAIGHT_FLUSH);
        boolean hasHand2 = handValue.equals(HandRanks.FOUR_OF_A_KIND);
        boolean hasHand3 = handValue.equals(HandRanks.FULL_HOUSE);
        boolean hasHand4 = handValue.equals(HandRanks.STRAIGHT);
        boolean hasHand5 = handValue.equals(HandRanks.THREE_OF_A_KIND);
        boolean hasHand6 = handValue.equals(HandRanks.TWO_PAIR);

        boolean hasAnyHand = false;
        if(getGameState().isActiveBet()) {
            if (hasHand == true || hasHand1 == true || hasHand2 == true || hasHand3 == true || hasHand4 == true || hasHand5 == true || hasHand6 == true && RaiseCalled == 3) {
                hasAnyHand = true;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
