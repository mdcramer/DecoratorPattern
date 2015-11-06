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
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000000; i++) {
			unwrapped.methodN();
		}
		long end = System.currentTimeMillis();
		System.out.println(">>> unwrapped time = " + (end - start));
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			proxified.methodN();
		}
		end = System.currentTimeMillis();
		System.out.println(">>> proxified time = " + (end - start));
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			wrapped.methodN();
		}
		end = System.currentTimeMillis();
		System.out.println(">>> wrapped time = " + (end - start));
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
