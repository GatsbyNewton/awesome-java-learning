package edu.wzm.jdk.reflect;

public class Student {
    public String name;
    private int age;
    private float score;

    public Student(){

    }

    private Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(float score) {
        this.score = score;
    }

    public String getMsg(String msg){
        return msg + ": " + name + "\t" + age + "\t" + score;
    }

    public float getScore(){
        return score;
    }

    private void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
