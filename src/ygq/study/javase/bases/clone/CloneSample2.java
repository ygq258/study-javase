package ygq.study.javase.bases.clone;

/**
 * 对象克隆,验证克隆后对象的引用也不同
 * @author yang
 */
public class CloneSample2 {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		Student s1 = new Student();
		s1.setAge(20);
		s1.setName("学生");
		
		Teacher t = new Teacher();
		t.setAge(30);
		t.setName("老师");
		s1.setTeacher(t);
		
		Student s2 = (Student)s1.clone();
		
		System.out.println(s2.getName());
		System.out.println(s2.getAge());
		t.setName("老师改");
		System.out.println("-------------");
		
		System.out.println(s2.getTeacher().getName());
		System.out.println(s2.getTeacher().getAge());
		
	} 
	
	public static class Teacher implements Cloneable{
		
		private Integer age;
		private String name;
		
		
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}

	public static class Student implements Cloneable{
		
		private int age;
		private String name;
		private Teacher teacher;
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Teacher getTeacher() {
			return teacher;
		}
		public void setTeacher(Teacher teacher) {
			this.teacher = teacher;
		}
		/**
		 * 克隆引用对象，实现深克隆
		 */
		@Override
		public Object clone() throws CloneNotSupportedException {
			Student obj = (Student)super.clone();
			obj.setTeacher((Teacher)obj.getTeacher().clone());
			return obj;
		}
	}

}

