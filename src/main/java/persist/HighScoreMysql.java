package persist;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class HighScoreMysql extends HighScore {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
    @Override
    public void save(Entry e) {

        entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        addEntry(e.getName(), e.getScore());
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }

    public ArrayList<Entry> getEntries() {
        Query query = factory.createEntityManager().createQuery("SELECT e FROM Entry e");

        return (ArrayList<Entry>) query.getResultList();
    }

}
