package src.CustomSort;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {
        Student s1 = new Student(1,1,1,1,1,1,1,1,1,1);
        Student s2 = new Student(3,3,3,3,3,3,3,3,3,3);
        Student s3 = new Student(4,4,4,4,4,4,4,4,4,4);
        Student s4 = new Student(2,2,2,2,2,2,2,2,2,2);
        Student[] students = new Student[4];
        students[0]=s1;
        students[1]=s2;
        students[2]=s3;
        students[3]=s4;
        Arrays.sort(students);
        //定义一个对象比较器，继承Comparator接口
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return 0;
            }
        });
    }
}

//继承Comparable接口，并实现接口的compareTo()方法
class Student implements Comparable
{
    private int grade1;
    private int grade2;
    private int grade3;
    private int grade4;
    private int grade5;
    private int grade6;
    private int grade7;
    private int grade8;
    private int grade9;
    private int grade10;
    private int average;

    public Student(int grade1, int grade2, int grade3, int grade4, int grade5, int grade6, int grade7, int grade8, int grade9, int grade10) {
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.grade4 = grade4;
        this.grade5 = grade5;
        this.grade6 = grade6;
        this.grade7 = grade7;
        this.grade8 = grade8;
        this.grade9 = grade9;
        this.grade10 = grade10;
        this.average = (this.grade1+this.grade2+this.grade3+this.grade4+this.grade5+this.grade6+this.grade7+this.grade8+this.grade9+this.grade10)/10;
    }
    public float getAverage()
    {
        return this.average;
    }

    @Override
    public int compareTo(Object o) {
        Student s = (Student)o;
        if(this.average>s.getAverage())
        {
            return 1;
        }else if(this.average<s.getAverage())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

}