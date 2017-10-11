public class Singleton{
	private static firstInstance = null;

	private Singleton(){}

	public Singleton getInstance {
		if (firstInstance == null) {
			firstInstance = new Singleton();
		}

		return firstInstance;
	}
}