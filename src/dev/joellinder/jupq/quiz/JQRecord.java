package dev.joellinder.jupq.quiz;

public class JQRecord {
    private String[] answers;
    private String image;

    public JQRecord(String[] answers, String image) {
        this.setAnswers(answers);
        this.setImage(image);
    }

    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
