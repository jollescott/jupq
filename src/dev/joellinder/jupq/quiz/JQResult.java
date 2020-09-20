package dev.joellinder.jupq.quiz;

public class JQResult {
    public class JQResultAnswer {
        String answer;
		String correct;
		
        public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		public String getCorrect() {
			return correct;
		}
		public void setCorrect(String correct) {
			this.correct = correct;
		}
    }

    private int length;
    private int correct;
    private JQResultAnswer[] answers;

    public JQResult(int length) {
        answers = new JQResultAnswer[length];
        this.length = length;
    }

    public void setAnswer(int index, String answer, String correct) {
        var resultAnswer = new JQResultAnswer();
        resultAnswer.answer = answer;
        resultAnswer.correct = correct;

        answers[index] = resultAnswer;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public JQResultAnswer[] getAnswers() {
        return answers;
    }

    public void setAnswers(JQResultAnswer[] answers) {
        this.answers = answers;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
}
