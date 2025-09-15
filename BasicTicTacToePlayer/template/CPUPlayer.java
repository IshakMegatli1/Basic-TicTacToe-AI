//algorithm MinMax et Alpha-Beta
import java.util.ArrayList;

public class CPUPlayer {
    private Mark playerMark;
    private int numExploredNodes;

    public CPUPlayer(Mark playerMark) {
        this.playerMark = playerMark;
        this.numExploredNodes = 0;
    }

    public int getNumOfExploredNodes() {
        return numExploredNodes;
    }

    public ArrayList<Move> getNextMoveMinMax(Board board) {
        numExploredNodes = 0;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestValue = Integer.MIN_VALUE;

        for (Move move : board.getPossibleMoves()) {
            Board newBoard = copyBoard(board);
            newBoard.play(move, playerMark);
            int moveValue = minimax(newBoard, false);
            if (moveValue > bestValue) {
                bestMoves.clear();
                bestMoves.add(move);
                bestValue = moveValue;
            } else if (moveValue == bestValue) {
                bestMoves.add(move);
            }
        }
        return bestMoves;
    }

    public ArrayList<Move> getNextMoveAB(Board board) {
        numExploredNodes = 0;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestValue = Integer.MIN_VALUE;

        for (Move move : board.getPossibleMoves()) {
            Board newBoard = copyBoard(board);
            newBoard.play(move, playerMark);
            int moveValue = alphaBeta(newBoard, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
            if (moveValue > bestValue) {
                bestMoves.clear();
                bestMoves.add(move);
                bestValue = moveValue;
            } else if (moveValue == bestValue) {
                bestMoves.add(move);
            }
        }
        return bestMoves;
    }

    private int minimax(Board board, boolean isMaximizing) {
        numExploredNodes++;
        Mark currentMark = isMaximizing ? playerMark : (playerMark == Mark.X ? Mark.O : Mark.X);
        int boardValue = board.evaluate(playerMark);

        if (boardValue == 100 || boardValue == -100 || board.getPossibleMoves().isEmpty()) {
            return boardValue;
        }

        if (isMaximizing) {
            int bestValue = Integer.MIN_VALUE;
            for (Move move : board.getPossibleMoves()) {
                Board newBoard = copyBoard(board);
                newBoard.play(move, playerMark);
                bestValue = Math.max(bestValue, minimax(newBoard, false));
            }
            return bestValue;
        } else {
            int bestValue = Integer.MAX_VALUE;
            for (Move move : board.getPossibleMoves()) {
                Board newBoard = copyBoard(board);
                newBoard.play(move, (playerMark == Mark.X ? Mark.O : Mark.X));
                bestValue = Math.min(bestValue, minimax(newBoard, true));
            }
            return bestValue;
        }
    }

    private int alphaBeta(Board board, int alpha, int beta, boolean isMaximizing) {
        numExploredNodes++;
        Mark currentMark = isMaximizing ? playerMark : (playerMark == Mark.X ? Mark.O : Mark.X);
        int boardValue = board.evaluate(playerMark);

        if (boardValue == 100 || boardValue == -100 || board.getPossibleMoves().isEmpty()) {
            return boardValue;
        }

        if (isMaximizing) {
            int bestValue = Integer.MIN_VALUE;
            for (Move move : board.getPossibleMoves()) {
                Board newBoard = copyBoard(board);
                newBoard.play(move, playerMark);
                bestValue = Math.max(bestValue, alphaBeta(newBoard, alpha, beta, false));
                alpha = Math.max(alpha, bestValue);
                if (beta <= alpha) {
                    break;
                }
            }
            return bestValue;
        } else {
            int bestValue = Integer.MAX_VALUE;
            for (Move move : board.getPossibleMoves()) {
                Board newBoard = copyBoard(board);
                newBoard.play(move, (playerMark == Mark.X ? Mark.O : Mark.X));
                bestValue = Math.min(bestValue, alphaBeta(newBoard, alpha, beta, true));
                beta = Math.min(beta, bestValue);
                if (beta <= alpha) {
                    break;
                }
            }
            return bestValue;
        }
    }

    private Board copyBoard(Board original) {
        Board copy = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copy.grid[i][j] = original.grid[i][j];
            }
        }
        return copy;
    }
}































// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
/*class CPUPlayer
{

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){

    }

    // Ne pas changer cette méthode
    public int  getNumOfExploredNodes(){
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board)
    {
        numExploredNodes = 0;

    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes = 0;

    }

}*/
