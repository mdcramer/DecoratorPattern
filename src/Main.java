import net.sf.cglib.proxy.Enhancer;

public class Main {

	private static MyClass unwrapped;
	private static WrapperClass wrapped;
	private static MyClass proxified;
	
	public static void main(String[] args) {
		unwrapped = new MyClass();
		System.out.println(">>> Methods from the unwrapped object:");
		unwrapped.method1();
		unwrapped.method2();
		unwrapped.method3();
		wrapped = new WrapperClass(unwrapped);
		System.out.println(">>> Methods from the wrapped object:");
		wrapped.method1();
		wrapped.method2();
		wrapped.method3();
		proxified = createProxy(unwrapped);
		System.out.println(">>> Methods from the proxy object:");
		proxified.method1();
		proxified.method2();
		proxified.method3();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T createProxy(T obj) {
		Enhancer e = new Enhancer();
		e.setSuperclass(obj.getClass());
		e.setCallback(new MyInterceptor(obj));
		T proxifiedObj = (T) e.create();
		return proxifiedObj;
	}

}
