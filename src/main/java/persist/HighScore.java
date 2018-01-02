package persist;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

public abstract class HighScore {

    protected EntityManager entityManager;
    protected Set<Entry> entries;

    public HighScore() {
        this.entries = new HashSet<>();
    }

    public abstract void save(Entry e);

    public void add(String name, int score) {
        Entry entry = new Entry(name, score);
        this.entries.add(entry);
        this.save(entry);
    }

    protected void addEntry(String name, int score) {
        Query query = entityManager.createNativeQuery("INSERT INTO Entry (name, score) VALUES(?,?)");
        query.setParameter(1, name);
        query.setParameter(2, score);
        query.executeUpdate();
    }

    public Set<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }
}
