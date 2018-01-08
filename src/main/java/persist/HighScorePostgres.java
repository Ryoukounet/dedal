package persist;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class HighScorePostgres extends HighScore {

    @Override
    public void save(Entry e) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("postgres");
        entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        addEntry(e.getName(), e.getScore());
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }

    @Override
    public ArrayList<Entry> getEntries() {
        return null;
    }

}
