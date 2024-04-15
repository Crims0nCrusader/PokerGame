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
                if(shouldFold()) {
                    fold();
                }
                if(shouldCheck()) {
                    check();;
                }
                if(shouldCall()) {
                    call();
                }
                if(shouldRaise()) {
                    raise(10);
                }
                if(shouldAllIn()) {
                    allIn();
                }
            }
        }
    }

    @Override
    protected boolean shouldFold() {
        HandRanks handValue = evaluatePlayerHand();
        if(getGameState().getNumRoundStage() <= 2) {
            if (handValue.equals(HandRanks.ROYAL_FLUSH)) {
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
            return true;
        }
        return false;
    }

    @Override
    protected boolean shouldCheck() {
        HandRanks handValue = evaluatePlayerHand();
        boolean hasDecentHand = handValue.equals(HandRanks.HIGH_CARD);
        boolean hasDecentHand1 = handValue.equals(HandRanks.PAIR);
        boolean hasDecentHand2 = handValue.equals(HandRanks.TWO_PAIR);
        boolean hasDecentHand3 = handValue.equals(HandRanks.THREE_OF_A_KIND);

        return hasDecentHand || hasDecentHand1 || hasDecentHand2 || hasDecentHand3;
    }

    @Override
    protected boolean shouldCall() {
        HandRanks handValue = evaluatePlayerHand();
        if (evaluatePlayerHand().getValue() >= HandRanks.ROYAL_FLUSH.getValue()) {
            return false;
        } else if (handValue.equals(HandRanks.STRAIGHT_FLUSH)) {
            return false;
        } else if (handValue.equals(HandRanks.FOUR_OF_A_KIND)) {
            return true;
        } else if (handValue.equals(HandRanks.FULL_HOUSE)) {
            return true;
        } else if (handValue.equals(HandRanks.FLUSH)) {
            return true;
        } else if (handValue.equals(HandRanks.STRAIGHT)) {
            return true;
        } else if (handValue.equals(HandRanks.THREE_OF_A_KIND)) {
            return true;
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


        if (hasStrongHand || hasStrongHand1) {
            hasAnyStrong = true;
            RaiseCalled++;
            raise(150);
            return true;
        }
        if (hasStrongHand2 || hasStrongHand3) {
            hasAnyStrong = true;
            RaiseCalled++;
            raise(70);
            return true;
        }
        if (hasStrongHand4 || hasStrongHand5) {
            hasAnyStrong = true;
            RaiseCalled++;
            raise(20);
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

        boolean hasAnyHand = false;
        if(getGameState().isActiveBet()) {
            if (hasHand || hasHand1 || hasHand2 || hasHand3 && RaiseCalled == 3) {
                hasAnyHand = true;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
