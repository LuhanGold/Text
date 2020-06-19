package entity;

/** 
* @ClassName: TestDatePagingEntity 
* @Description: (这个是用于测试List分页使用到的实体类) 
* @author Luhan 
* @date 2017年3月18日 下午7:51:22 
*/
public class TestDatePagingEntity{
	
	private String name;//Sting类型的姓名
	private String sex;//Sting类型的性别
	private int age;//int类型的年龄
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
