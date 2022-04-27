import annotations.AutoInjectable;

import java.io.File;
import java.io.FileReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Properties;

public class Injector {
	private Properties properties;
	public Injector() {
		try {
			this.properties = new Properties();
			String filename = "props.properties";
			properties.load(new FileReader(new File(this.getClass().getClassLoader().getResource(filename).toURI())));
		} catch (Exception e) {
			System.out.println("Properties file not found!");
		}
	}


	public <T> T inject(T object) {
		try {
			Class<?> clazz = object.getClass();
			Object instance = clazz.getDeclaredConstructor().newInstance();
			Field[] fields = clazz.getDeclaredFields();

			for (Field field: fields) {
				Annotation[] annotations = field.getAnnotations();
				if (Arrays.stream(annotations).anyMatch(annotation -> annotation.annotationType() == AutoInjectable.class)) {
					field.setAccessible(true);
					Class<?> fieldType = field.getType();
					String fieldTypeName = fieldType.getName();
					System.out.println("found field: " + field.getName() + ", type: " + fieldTypeName);

					Object typeToInject = this.properties.get(fieldTypeName);
					String typeToInjectName = typeToInject.toString();
					System.out.println("To inject: " + typeToInjectName);

					Class<?> fieldClass = Class.forName(typeToInjectName);
					System.out.println(fieldClass.getName());
					Object fieldInstance = fieldClass.getDeclaredConstructor().newInstance();
					field.set(instance, fieldInstance);
				}
			}
			return (T)instance;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
}
