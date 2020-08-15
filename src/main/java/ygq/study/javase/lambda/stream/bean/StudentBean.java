package ygq.study.javase.lambda.stream.bean;

import java.time.LocalDate;
import java.util.UUID;

public class StudentBean {

	private String id;
	private String name;
	private Integer age;
	private SexEnum sex;
	private String mobile;
	private LocalDate birthday;
	private String address;
	
	public StudentBean() {
		super();
	}
	public StudentBean(String id, String name, Integer age, SexEnum sex, String mobile, LocalDate birthday,
			String address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.mobile = mobile;
		this.birthday = birthday;
		this.address = address;
	}
	public StudentBean(UUID randomUUID, String string, int i, SexEnum female, String string2, LocalDate of,
			String string3) {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public SexEnum getSex() {
		return sex;
	}
	public void setSex(SexEnum sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "StudentBean [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", mobile=" + mobile
				+ ", birthday=" + birthday + ", address=" + address + "]";
	}
}
