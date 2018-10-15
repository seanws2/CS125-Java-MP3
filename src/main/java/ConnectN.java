import java.util.Objects;

/**
 * class that forms a tile based game on a grid like connect 4.
 */
public class ConnectN {
    //CONSTRUCTORS
    /**
     * counts instances.
     */
    public ConnectN() {
        counter++;
        id = counter - 1;
    }
    /**
     * @param setWidth width setter
     * @param setHeight high setter
     */
    ConnectN(final int setWidth, final int setHeight) {
        if (setWidth <= MAX_WIDTH && setWidth >= MIN_WIDTH) {
            this.width = setWidth;
            this.widthTF = true;
        }
        if (setHeight <= MAX_WIDTH && setHeight >= MIN_WIDTH) {
            this.height = setHeight;
            this.heightTF = true;
        }
        assignBoard();
        counter++;
        id = counter - 1;
    }
    /**
     * @param setWidth width setter
     * @param setHeight height setter
     * @param setN N setter
     */
    ConnectN(final int setWidth, final int setHeight, final int setN) {
        if (setWidth <= MAX_WIDTH && setWidth >= MIN_WIDTH) {
            this.width = setWidth;
            this.widthTF = true;
        }
        if (setHeight <= MAX_WIDTH && setHeight >= MIN_WIDTH) {
            this.height = setHeight;
            this.heightTF = true;
        }
        if (this.width > this.height) {
            if (setN >= MIN_N && setN < this.width && this.heightTF && this.widthTF) {
                assignBoard();
                this.n = setN;
                this.nTF = true;
            }
        } else {
            if (setN >= MIN_N && setN < this.height && this.heightTF && this.widthTF) {
                assignBoard();
                this.n = setN;
                this.nTF = true;
            }
        }
        counter++;
        id = counter - 1;
    }
    /**
     * @param otherBoard another instance of ConnectN class
     */
    ConnectN(final ConnectN otherBoard) {
        this.width = otherBoard.width;
        this.widthTF = true;
        this.height = otherBoard.height;
        this.heightTF = true;
        assignBoard();
        this.n = otherBoard.n;
        this.nTF = true;
        counter++;
        id = counter - 1;
    }
    //FIELDS
    /**
     * int counts the instances.
     */
    private static int counter = 0;
    /**
     * max height.
     */
    static final int MAX_HEIGHT = 16;
    /**
     * max width.
     */
    static final int MAX_WIDTH = 16;
    /**
     * min height.
     */
    static final int MIN_HEIGHT = 6;
    /**
     * min N value.
     */
    static final int MIN_N = 4;
    /**
     * min width.
     */
    static final int MIN_WIDTH = 6;
    /**
     * width.
     */
    private int width;
    /**
     * height.
     */
    private int height;
    /**
     * n.
     */
    private int n;
    /**
     * this is the board.
     */
    Player[][] board = new Player[width][height];
    /**
     * this player one.
     */
    private Player player1;
    /**
     * player two.
     */
    private Player player2;
    /**
     * this player won.
     */
    private Player winner;
    /**
     * true if height is set.
     */
    private boolean heightTF = false;
    /**
     * true if width is set.
     */
    private boolean widthTF = false;
    /**
     * true if n set.
     */
    private boolean nTF = false;
    /**
     * true if game started.
     */
    private boolean gameOn = false;
    /**
     * true if game ends.
     */
    private boolean gameOver = false;
    /**
     * ID for the instance / game.
     */
    private int id = 0;
    //MISC
    /**
     * @return total number of games played.
     */
    public static int getTotalGames() {
        return counter;
    }
    /**
     * @return current board's id
     */
    public int getID() {
        return counter - 1;
    }
    //SET AND GET WIDTH
    /**
     *
     * @return current width.
     */
    public int getWidth() {
        return this.width;
    }
    /**
     * @param setWidth int set to specifications within bounds.
     * @return false if width is invalid.
     */
    public boolean setWidth(final int setWidth) {
        if (gameOn) {
            return false;
        }
        if (setWidth <= MAX_WIDTH && setWidth >= MIN_WIDTH) {
            this.width = setWidth;
            this.widthTF = true;
            assignBoard();
        } else {
            return false;
        }
        if (this.n >= this.height && this.n >= this.width) {
            this.n = 0;
        }
        return true;
    }
    //SET AND GET HEIGHT
    /**
     * @return current height.
     */
    public int getHeight() {
        return this.height;
    }
    /**
     *
     * @param setHeight for setting height
     * @return false if invalid
     */
    public boolean setHeight(final int setHeight) {
        if (gameOn) {
            return false;
        }
        if (setHeight <= MAX_HEIGHT && setHeight >= MIN_HEIGHT) {
            this.height = setHeight;
            this.heightTF = true;
            assignBoard();
        } else {
            return false;
        }
        if (this.n >= this.height && this.n >= this.width) {
            this.n = 0;
        }
        return true;
    }
    //SET AND GET N
    /**
     * @return current N value
     */
    public int getN() {
        return this.n;
    }
    /**
     * @param newN is the new n value
     * @return false if invalid
     */
    public boolean setN(final int newN) {
        if (gameOn) {
            return false;
        }
        if (!this.widthTF || !this.heightTF) {
            return false;
        }
        if (this.width > this.height) {
            if (newN >= MIN_N && newN < this.width) {
                this.n = newN;
                this.nTF = true;
                //make board
                assignBoard();
                return true;
            }
        } else {
            if (newN >= MIN_N && newN < this.height) {
                this.n = newN;
                this.nTF = true;
                //make board
                assignBoard();
                return true;
            }
        }
        return false;
    }

    /**
     * assigns board.
     */
    private void assignBoard() {
        if (this.widthTF && this.heightTF) {
            this.board = new Player[this.width][this.height];
        }
    }
    //COMPARE BOARDS
    /**
     * @param boards as many boards as the user inputs.
     * @return false if boards not equal
     */
    public static boolean compareBoards(final ConnectN... boards) {
        int countThoseBoreds = 0;
        int countBoard = 0;
        int countBoardEquals = 0;
        if (boards.length == 0) {
            return false;
        }
        for (int i = 0; i < boards.length - 1; i++) {
            if (boards[i] == null || boards[i + 1] == null) {
                return false;
            }
            if (boards[i].height == boards[i + 1].height && boards[i].width == boards[i + 1].width
                    && boards[i].n == boards[i + 1].n) {
                for (int k = 0; k < boards[i].board.length; k++) {
                    for (int j = 0; j < boards[i].board[k].length; j++) {
                        if (boards[i].board[k][j] == boards[i + 1].board[k][j]) {
                            countBoardEquals++;
                        }
                        countBoard++;
                    }
                }
                if (countBoard == countBoardEquals) {
                    countThoseBoreds++;
                }
            }
        }
        if (countThoseBoreds == boards.length - 1) {
            return true;
        }
        return false;
    }
    /**
     * @param firstBoard is the first
     * @param secondBoard is the one we compare the first two
     * @return false if not equal
     */
    public static boolean compareBoards(final ConnectN firstBoard, final ConnectN secondBoard) {
        int countBoard = 0;
        int countBoardEquals = 0;
        if (firstBoard == null || secondBoard == null) {
            return false;
        }
        if (firstBoard.height == secondBoard.height && firstBoard.width == secondBoard.width
            && firstBoard.n == secondBoard.n) {
            for (int i = 0; i < firstBoard.board.length; i++) {
                for (int j = 0; j < firstBoard.board[i].length; j++) {
                    if (firstBoard.board[i][j] == secondBoard.board[i][j]) {
                        countBoardEquals++;
                    }
                    countBoard++;
                }
            }
            if (countBoard == countBoardEquals) {
                return true;
            }
        }
        return false;
    }
    //EQUALITY
    /**
     * @param o object for equality checking
     * @return false if id field is not equal
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConnectN connectN = (ConnectN) o;
        return id == connectN.id;
    }
    /**
     * hashcode is responsible for the equality.
     * @return integer for the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    //CREATE BOARDS factory static methods
    /**
     * @param setWidth the setter
     * @param setHeight the height setter
     * @param setN the setter of value n
     * @return ConnectN instance
     */
    public static ConnectN create(final int setWidth, final int setHeight, final int setN) {
        if (setWidth <= MAX_WIDTH && setWidth >= MIN_WIDTH && setHeight <= MAX_WIDTH && setHeight >= MIN_WIDTH
                && setN >= MIN_N && (setN < setWidth || setN < setHeight)) {
            return new ConnectN(setWidth, setHeight, setN);
        } else {
            return null;
        }
    }
    /**
     * @param number amount of instances we wanna make
     * @param setWidth setter wide
     * @param setHeight setter high
     * @param setN setter new n
     * @return an array of ConnectN instances
     */
    public static ConnectN[] createMany(final int number, final int setWidth, final int setHeight, final int setN) {
        ConnectN[] many = new ConnectN[number];
        if (number <= 0) {
            return null;
        }
        if (setWidth <= MAX_WIDTH && setWidth >= MIN_WIDTH && setHeight <= MAX_WIDTH && setHeight >= MIN_WIDTH
                && setN >= MIN_N && (setN < setWidth || setN < setHeight)) {
            for (int i = 0; i < many.length; i++) {
                many[i] = new ConnectN(setWidth, setHeight, setN);
            }
            return many;
        } else {
            return null;
        }
    }
    //BOARD INTERACTIONS
    /**
     * @return a Player[][]
     */
    public Player[][] getBoard() {
        if (this.board == null || this.height == 0 || this.width == 0) {
            return null;
        }
        Player[][] freshBoard = new Player[this.width][this.height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                Player fill = this.board[i][j];
                if (fill == null) {
                    freshBoard[i][j] = null;
                } else {
                    board[i][j] = new Player(fill);
                }
            }
        }
        return freshBoard;
    }
    /**
     * @param getX the x value on a 2d array
     * @param getY the y value on a 2d array
     * @return a Player
     */
    public Player getBoardAt(final int getX, final int getY) {
        if (this.height == 0 || this.width == 0 || this.board == null) {
            return null;
        }
        if (getX < this.width && getX >= 0 && getY < this.height && getY >= 0) {
            return this.board[getX][getY];
        }
        return null;
    }
    /**
     * @param player will get the right game
     * @param setX set the x value for the place
     * @param setY set the y not necessary
     * @return false if invalid
     */
    public boolean setBoardAt(final Player player, final int setX, final int setY) {
        if (this.gameOver) {
            return false;
        }
        if (player == null || this.width == 0 || this.height == 0 || this.n == 0) {
            return false;
        }
        if (this.player1 == null) {
            this.player1 = player;
        } else if (this.player2 == null && !player.equals(this.player1)) {
            this.player2 = player;
        }
        if (this.height > setY && setY >= 0 && this.width > setX && setX >= 0 && !gameOver) {
            if (setY == 0) {
                boolean notNull = true;
                if (this.board[setX][setY] == null) {
                    notNull = false;
                }
                if (!notNull && (player.equals(this.player1) || player.equals(this.player2))) {
                    this.board[setX][setY] = player;
                    gameOn = true;
                    return true;
                }
                return false;
            } else {
                boolean isOccupied = true;
                if (this.board[setX][setY] == null) {
                    isOccupied = false;
                }
                Player placeBelow = this.board[setX][setY - 1];
                if (!isOccupied && placeBelow != null && (player.equals(this.player1)
                        || player.equals(this.player2))) {
                    this.board[setX][setY] = player;
                    gameOn = true;
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    /**
     * @param player gets the right game
     * @param setX drop chip on this X
     * @return false if invalid
     */
    public boolean setBoardAt(final Player player, final int setX) {
        int pointsDown = height - 1;
        if (gameOver) {
            return false;
        }
        if (player == null || this.width == 0 || this.height == 0 || this.n == 0) {
            return false;
        }
        if (this.player1 == null) {
            this.player1 = player;
        } else if (this.player2 == null && !player.equals(this.player1)) {
            this.player2 = player;
        }
        while (pointsDown >= 0) {
            if (board[setX][pointsDown] != null) {
                pointsDown--;
            } else {
                break;
            }
        }
        board[setX][pointsDown] = player;
        return true;
    }
    //WINNER
    /**
     * @return the player who won
     */
    public Player getWinner() {
        int countN = 0;
        //CHECK WINNER
        //check the horizontal
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width - 1; j++) {
                if (this.board[i][j] == this.board[i][j + 1]) {
                    countN++;
                } else {
                    countN = 0;
                }
                if (countN == n - 1) {
                    this.gameOver = true;
                    this.winner = this.board[i][j];
                    break;
                }
            }
            countN = 0;
            if (this.gameOver) {
                break;
            }
        }
        //check the vertical
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height - 1; j++) {
                if (this.board[j][i] == this.board[j + 1][i]) {
                    countN++;
                } else {
                    countN = 0;
                }
                if (countN == n - 1) {
                    this.gameOver = true;
                    this.winner = this.board[j][i];
                    break;
                }
            }
            countN = 0;
            if (this.gameOver) {
                break;
            }
        }
        final int startDown = 4;
        //check the positive sloping diagonal
        for (int j = this.height - startDown; j > 0; j--) {
            int i = 0;
            int temp = j;
            while (j < this.height - 2) {
                if (this.board[i][j] == this.board[i + 1][j + 1]) {
                    countN++;
                } else {
                    countN = 0;
                }
                if (countN == n - 1) {
                    this.gameOver = true;
                    this.winner = this.board[i][j];
                    break;
                }
                i++;
                j++;
            }
            if (this.gameOver) {
                break;
            }
            countN = 0;
            j = temp;
        }
        for (int i = 0; i < this.width - startDown; i++) {
            int j = 0;
            while (i < this.width - 2 && j < this.height - 2) {
                if (this.board[i][j] == this.board[i + 1][j + 1]) {
                    countN++;
                } else {
                    countN = 0;
                }
                if (countN == n - 1) {
                    this.gameOver = true;
                    this.winner = this.board[i][j];
                    break;
                }
                i++;
                j++;
            }
            if (this.gameOver) {
                break;
            }
            countN = 0;
        }
        //check the negatively sloping diagonal
        return this.winner;
    }

}
