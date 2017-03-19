package ci6226.ira.g14.search.model;

public class Result {

	private String title;
	private String body;
	private int docId;
	private float score;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}
