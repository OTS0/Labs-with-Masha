public class Answer {
    private  String answer;
    private boolean check;

    public Answer(boolean check, String answer){
        this.check=check;
        this.answer=answer;
    }

    public String getAnswer() {
        return answer;
    }
    public boolean getCheck(){
        return check;
    }
}
