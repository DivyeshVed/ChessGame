package com.chess.engine.board;
import com.chess.engine.piece.Piece;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Divyesh Ved 10th August 2022.
 * Creating the abstract Tile class.
 */

// Making this class immutable by declaring all the variables that could be changed to be final (constants)
// Since it is an abstract class, we cannot instantiate the class. But can instantiate concrete subclasses.
public abstract class Tile {
    // Defines the number of the tile, such that the tile numbers are going to range from 0-63
    protected final int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTile();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTile() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0; i< BoardUtils.NUM_TILES; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        // ImmutableMap is part of the Guava library which is a third party library provided by Google. Had to download and import the class.
        return ImmutableMap.copyOf(emptyTileMap);
    }

    // The only method that can be used to create a tile using this class.
    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece !=  null ? new OccupiedTile(tileCoordinate, piece): EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    // Constructor for Tile class
    private Tile(final int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    // Creating an abstract method to check if the tile we are talking about is occupied or not
    public abstract boolean isTileOccupied();

    // Creating an abstract class to be overriden to get the piece on the tile, if the tile is occupied
    public abstract Piece getPiece();

    // Creating a concrete subclass that extends the Tile class. This class is for an empty tile (without a piece on it)
    public static final class EmptyTile extends Tile{
        // Making a constructor for the class and taking the coordinate from its parent class.
        private EmptyTile(final int coordinate){
            super(coordinate);
        }

        @Override
        public String toString(){
            return "-";
        }

        // Overriding the abstract isTileOccupied class. In this case the return would be false, as the tile is empty.
        @Override
        public boolean isTileOccupied(){
            return false;
        }

        // Overriding the abstract getPiece class. In this case the return value would be null, as there is no piece on the tile.
        @Override
        public Piece getPiece(){
            return null;
        }
    }

    // Creating a concrete subclass that extends the Tile class. This class is for an occupied tile (with a piece on it)
    public static final class OccupiedTile extends Tile{
        // Creating an instance of the Piece class. Private, thus there is no way to reference the variable without calling getPiece.
        private final Piece pieceOnTile;

        // Creating a constructor that takes in the tile coordinate and the piece on the tile and assigns them accordingly.
        private OccupiedTile(int tileCoordinate, Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public String toString(){
            return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() : getPiece().toString();
        }
        // Overriding the isTileOccupied method to return true as there is a piece on the tile.
        @Override
        public boolean isTileOccupied(){
            return true;
        }

        // Overriding the get piece method to give the piece instantiated above as the tile is occupied.
        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }
    }
}
