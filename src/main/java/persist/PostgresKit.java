package persist;

public class PostgresKit extends PersistKit {

    @Override
    public HighScore makeKit() {
        return new HighScorePostgres();
    }

}
