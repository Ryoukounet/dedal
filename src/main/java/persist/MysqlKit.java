package persist;

public class MysqlKit extends PersistKit {

    @Override
    public HighScore makeKit() {
        return new HighScoreMysql();
    }

}
