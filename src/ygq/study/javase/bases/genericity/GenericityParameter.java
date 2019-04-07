package ygq.study.javase.bases.genericity;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Time;

/**
 * Hello world!
 */
public class GenericityParameter 
{
	
	public static void main( String[] args ) throws Exception
    {
//    	testOne();
//    	testTwo();
		
		Method declaredMethod = JavaWorld.class.getDeclaredMethod("show", Time.class);
		
		Parameter[] parameters = declaredMethod.getParameters();
		
		System.out.println(parameters[0].getName());
    	
    }
	public static void testOne() {
		
		JavaWorld world = new JavaWorld();
    	
    	Class<? extends JavaWorld> class1 = world.getClass();
    	
    	Method[] methods = class1.getDeclaredMethods();
    	
    	for (int i = 0; i < methods.length; i++) {
//    		System.out.println("isBridge:"+methods[i].isBridge());
//    		System.out.println("isSynthetic:"+methods[i].isSynthetic());
//    		System.out.println("isAccessible:"+methods[i].isAccessible());
//    		System.out.println("isVarArgs:"+methods[i].isVarArgs());
    		Class<?>[] classs = methods[i].getParameterTypes();
    		if ("show".equals(methods[i].getName()) && !classs[0].equals(Time.class)) {
    			Class<?>[] parameterTypes = methods[i].getParameterTypes();
    			for (int j = 0; j < parameterTypes.length; j++) {
    				System.out.println("泛型1：" + parameterTypes[j]);
				}
			}
		}
	}
	
	public static void testTwo() {
		
		Type[] interfaces = JavaWorld.class.getGenericInterfaces();
    	if (interfaces != null) {
    		for (int i = 0; i < interfaces.length; i++) {
    			Type type = interfaces[i];
    			if (type instanceof ParameterizedType) {
    				ParameterizedType pt = (ParameterizedType)type;
    				Type[] actualTypeArguments = pt.getActualTypeArguments();
    				if (actualTypeArguments != null) {
    					for (int j = 0; j < actualTypeArguments.length; j++) {
    						System.out.println("泛型2：" + actualTypeArguments[j]);
    					}
					}
				}
    		}
		}
	}
    
    
    /**
     * 
     * @param clazz 类
     * @param inter 接口类
     * @return 泛型数组
     */
    public static Type[] getInterActualType(Class<?> clazz, Class<?> inter) {
    	Type[] genericInterfaces = clazz.getGenericInterfaces();
    	if (genericInterfaces == null) {
			return null;
		}
    	for (int i = 0; i < genericInterfaces.length; i++) {
    		if (genericInterfaces[i] instanceof ParameterizedType) {
    			ParameterizedType pt = (ParameterizedType)genericInterfaces[i];
    			if (pt.getRawType() != null && pt.getRawType().equals(inter)) {
    				return pt.getActualTypeArguments();
				}
			}
		}
    	return null;
    }
    /**
     * 获取方法参数类型
     * @param object
     * @return
     */
    public static Type[] getParameterizedTypes(Object object) {
        Type superclassType = object.getClass().getGenericSuperclass();
        if (!ParameterizedType.class.isAssignableFrom(superclassType.getClass())) {
            return null;
        }
        return ((ParameterizedType)superclassType).getActualTypeArguments();
    }
    
    /**
     * 获取方法的返回类型
     * @param clazz
     * @param name
     * @return
     */
    public static Class<?> getMethodReturnType(Class<?> clazz, String name) {
        if (clazz==null || name==null || name.isEmpty()) {
            return null;
        }   

        name = name.toLowerCase();
        Class<?> returnType = null;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals(name)) {
                returnType = method.getReturnType();
                break;
            }
        }

        return returnType;
    }
}

//interface Hw1<T>{
//
//	public abstract T show();
//	
//}
//
//
//class Hello implements Hw1{
//	@Override
//	public Object show() {
//		return null;
//	}
//}

interface World <T> {
	public void show(T event);
}

class JavaWorld implements World<Time>, Serializable{
	
	private static final long serialVersionUID = -5615475039519642257L;

	@Override
	public void show(Time event) {
		
	}
}



