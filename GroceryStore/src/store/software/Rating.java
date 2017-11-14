package store.software;

public class Rating {
	private double score;
	private String comment;
	
	public Rating(){
		score = -1;
		comment = "";
	}
	
	public Rating(double score, String comment){
		this.score = score;
		this.comment = comment;
	}
	
	public void setScore(double newScore){
		score = newScore;
	}
	
	public double getScore(){
		return score;
	}
	
	public void setComment(String newComment){
		comment = newComment;
	}
	
	public String getComment(){
		return comment;
	}
}
