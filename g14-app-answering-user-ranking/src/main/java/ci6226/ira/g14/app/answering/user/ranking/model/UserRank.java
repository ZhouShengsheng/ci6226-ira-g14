package ci6226.ira.g14.app.answering.user.ranking.model;

/**
 * User rank model.
 */
public class UserRank {

    private String userID;
    private String username;
    private long anwseredCount;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getAnwseredCount() {
        return anwseredCount;
    }

    public void setAnwseredCount(long anwseredCount) {
        this.anwseredCount = anwseredCount;
    }
}
