package chapter02.entity;

public class Developer {
	public String languageSkill;
	String company;
	String developPosition;

	public Developer(){}

	public Developer(String languageSkill,
	String company,
	String developPosition){
		this.languageSkill=languageSkill;
		this.company=company;
		this.developPosition=developPosition;
	}
	
}
