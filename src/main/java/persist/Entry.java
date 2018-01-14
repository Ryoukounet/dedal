package persist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Entry.TenHighestScore", query="SELECT e FROM Entry e ORDER BY e.score")
public class Entry {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String toString() {
        return name + " : " + score;
    }
}
