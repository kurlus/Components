package hackertest.practice.components;

public class Person {
	private int age;
	
	public static void main(String[] args)
	{
		Person p = new Person(3);
		p.amIOld();
	}
	
	public Person(int initialAge) {
		if (initialAge < 0)
		{	
			age = 0;
			System.out.println("Age is not valid, setting age to 0.");
		}	
		else
		{	
			age = initialAge;
		}	
	}
		

	public void amIOld() {
		System.out.println(printAgeBracket(age));
	}

	public void yearPasses() {
		++age;
	}

	private String printAgeBracket(int age)
	{
		if (age < 13)
			return "You are young.";
		else if ((age >= 13) && (age < 18))
			return "You are a teenager.";
		else if (age >= 18)
			return "You are old.";

		return "";
	}

}
