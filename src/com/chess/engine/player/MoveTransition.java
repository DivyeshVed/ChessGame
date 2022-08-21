package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

// This class is used to look at the board before and after a move is made, and make sure the transition between those two boards works.
public class MoveTransition {
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus){
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }
}