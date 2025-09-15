import java.util.ArrayList;

public class Board {
    Mark[][] grid;

    public Board() {
        grid = new Mark[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = Mark.EMPTY;
            }
        }
    }

    public void play(Move m, Mark mark) {
        if (grid[m.getRow()][m.getCol()] == Mark.EMPTY) {
            grid[m.getRow()][m.getCol()] = mark;
        }
    }

    public int evaluate(Mark mark) {
        //Verifier pour victoire
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == mark && grid[i][1] == mark && grid[i][2] == mark) {
                return 100;
            }
            if (grid[0][i] == mark && grid[1][i] == mark && grid[2][i] == mark) {
                return 100;
            }
        }
        if (grid[0][0] == mark && grid[1][1] == mark && grid[2][2] == mark) {
            return 100;
        }
        if (grid[0][2] == mark && grid[1][1] == mark && grid[2][0] == mark) {
            return 100;
        }

        //verifie defaite
        Mark opponent = (mark == Mark.X) ? Mark.O : Mark.X;
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == opponent && grid[i][1] == opponent && grid[i][2] == opponent) {
                return -100;
            }
            if (grid[0][i] == opponent && grid[1][i] == opponent && grid[2][i] == opponent) {
                return -100;
            }
        }
        if (grid[0][0] == opponent && grid[1][1] == opponent && grid[2][2] == opponent) {
            return -100;
        }
        if (grid[0][2] == opponent && grid[1][1] == opponent && grid[2][0] == opponent) {
            return -100;
        }

        //aucun gagnant
        return 0;
    }

    public ArrayList<Move> getPossibleMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == Mark.EMPTY) {
                    moves.add(new Move(i, j));
                }
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                builder.append(grid[i][j].toString());
                if (j < 2) builder.append("|");
            }
            builder.append("\n");
            if (i < 2) builder.append("-----\n");
        }
        return builder.toString();
    }
}










// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
/*class Board
{
    private Mark[][] board;

    // Ne pas changer la signature de cette méthode
    public Board() {

    }

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){


    }


    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark){

    }
}*/
