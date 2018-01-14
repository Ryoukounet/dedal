package persist;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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
        Entry entry = new Entry();
        entry.setName(name);
        entry.setScore(score);
        this.entries.add(entry);
        this.save(entry);
    }

    protected void addEntry(String name, int score) {
        Entry entry = new Entry();
        entry.setName(name);
        entry.setScore(score);
        entityManager.persist(entry);
    }

    public abstract ArrayList<Entry> getEntries();

    public abstract ArrayList<Entry> getTenHighestEntries();

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }
}
