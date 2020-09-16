package dev.joellinder.jupq.quiz;

public class JQRecord {
    private String[] _answers;
    private String _image;

    public JQRecord(String[] answers, String image) {
        this.setAnswers(answers);
        this.setImage(image);
    }

    public String getImage() {
		return _image;
	}

	public void setImage(String image) {
		this._image = image;
	}

	public String[] getAnswers() {
        return _answers;
    }

    public void setAnswers(String[] answers) {
        this._answers = answers;
    }
}
