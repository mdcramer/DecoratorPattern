import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyInterceptor extends MyClass implements MethodInterceptor {
	
	private Object realObj;
	
	public MyInterceptor(Object obj) { this.realObj = obj; }
	
    @Override
	public void method2() {
    	System.out.println("This is overridden method 2 - " + realObj);
    }

	@Override
	public Object intercept(Object arg0, Method method, Object[] objects,
			MethodProxy methodProxy) throws Throwable {
		Method m = findMethod(this.getClass(), method);
		if (m != null) { return m.invoke(this, objects); }
		Object res = method.invoke(realObj, objects);
		return res;
	}
	
    private Method findMethod(Class<?> clazz, Method method) throws Throwable {
        try {
            return clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

}
