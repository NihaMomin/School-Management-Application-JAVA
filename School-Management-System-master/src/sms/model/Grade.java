package sms.model;

public class Grade {

    private int gradeID;
    private String grade;

    public Grade(String grade) {
        this.grade = grade;
    }

    public int getGradeID() {return gradeID;}
    public void setGradeID(int gradeID)
    {
        this.gradeID = gradeID;
    }

    public String getGrade() {return grade;}
    public void setGrade(String grade)
    {
        this.grade = grade;
    }

}
