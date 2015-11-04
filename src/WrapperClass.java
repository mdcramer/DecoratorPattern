
public class WrapperClass extends MyClass {

	private MyClass delagate;
	
    public WrapperClass(MyClass delegate) { this.delagate = delegate; }
    
    @Override
	public void method2() {
    	System.out.println("This is overridden method 2 - " + delagate);
    }

}
