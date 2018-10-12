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
                this.n = setN;
                this.nTF = true;
            }
        } else {
            if (setN >= MIN_N && setN < this.height && this.heightTF && this.widthTF) {
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
        this.n = otherBoard.n;
        this.nTF = true;
        counter++;
        id = counter - 1;
    }
    //FIELDS
    /**
     * player one.
     */
    Player one = new Player("one");
    /**
     * player two.
     */
    Player two = new Player("two");
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
    Player[][] board = new Player[this.height][this.width];
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
        //check if game started
        if (setWidth <= MAX_WIDTH && setWidth >= MIN_WIDTH) {
            this.width = setWidth;
            this.widthTF = true;
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
        //check if game started
        if (setHeight <= MAX_HEIGHT && setHeight >= MIN_HEIGHT) {
            this.height = setHeight;
            this.heightTF = true;
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
        //height and width must be set
        //game can't already have been started
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
                return true;
            }
        } else {
            if (newN >= MIN_N && newN < this.height) {
                this.n = newN;
                this.nTF = true;
                return true;
            }
        }
        return false;
    }
    //COMPARE BOARDS
    /**
     * @param boards as many boards as the user inputs.
     * @return false if boards not equal
     */
    public static boolean compareBoards(final ConnectN... boards) {
        int countThoseBoreds = 0;
        if (boards.length == 0) {
            return false;
        }
        for (int i = 1; i < boards.length; i++) {
            ConnectN bored =  boards[i];
            if (bored.equals(boards[i - 1])) {
                countThoseBoreds++;
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
        if (firstBoard.equals(secondBoard)) {
            return true;
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
        return new ConnectN();
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
        return many;
    }
    //BOARD INTERACTIONS
    /**
     * @return a Player[][]
     */
    public Player[][] getBoard() {
        return this.board;
    }
    /**
     * @param getX the x value on a 2d array
     * @param getY the y value on a 2d array
     * @return a Player
     */
    public Player getBoardAt(final int getX, final int getY) {
        return board[getX][getY];
    }
    /**
     * @param player will get the right game
     * @param setX set the x value for the place
     * @param setY set the y not necessary
     * @return false if invalid
     */
    public boolean setBoardAt(final Player player, final int setX, final int setY) {
        if (player == null || !this.widthTF || !this.heightTF || !this.nTF) {
            return false;
        }
        for (int i = 0; i < width; i++) {
            if (this.board[setX][i] == one || this.board[setX][i] == two) {
                continue;
            } else {
                this.board[setX][i] = player;
                return true;
            }
        }
    }
    /**
     * @param player gets the right game
     * @param setX drop chip on this X
     * @return false if invalid
     */
    public boolean setBoardAt(final Player player, final int setX) {
        int countN = 0;
        if (player == null || !this.widthTF || !this.heightTF || !this.nTF) {
            return false;
        }
        if (setX > this.height - 1 || setX < 0) {
            return false;
        }
        for (int i = 0; i < width; i++) {
            if (this.board[setX][i] == one || this.board[setX][i] == two) {
                continue;
            } else {
                this.board[setX][i] = player;
                this.gameOn = true;
                return true;
            }
        }
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {

            }
        }
    }
    //WINNER
    /**
     * @return the player who won
     */
    public Player getWinner() {
        return one;
    }

}
