package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.Method;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        // You code here...


//        List<AppComponentDto> list = new LinkedList<>();
        Method[] declaredMethods = configClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            AppComponent annotationAppComponent = method.getAnnotation(AppComponent.class);
            if (annotationAppComponent != null) {
//                int order = annotationAppComponent.order();
//                String name = annotationAppComponent.name();
//
//                Parameter[] parameters = method.getParameters();
//                Object[] args = new Object[parameters.length];
//                for (int i = 0; i < parameters.length; i++) {
//                    args[i] = parameters[i].getParameterizedType().getTypeName();
//                }
//
//                AppComponentDto dto = new AppComponentDto(order, name, args);
//
//                list.add(dto);

//                appComponentsByName.put(annotationAppComponent.name(), method);
                appComponents.add(annotationAppComponent);
//                appComponents.add(method);
            }
        }


        System.out.println("appComponents = " + appComponents);

//        for (Object appComponent : appComponents) {
//            if (appComponent instanceof Method) {
//                var m = (Method) appComponent;
//                AppComponent annotation = m.getAnnotation(AppComponent.class);
//                annotation.order()
//            }
//        }

        for (Object o : appComponents) {
            if (o instanceof AppComponent) {
                var appComponent = (AppComponent) o;
                System.out.println("appComponent.order() = " + appComponent.order());
                System.out.println("appComponent.name() = " + appComponent.name());
            }

            if (o instanceof Method) {
                var method = (Method) o;

                AppComponent annotation = method.getAnnotation(AppComponent.class);
                System.out.println("annotation.order() = " + annotation.order());
                System.out.println("annotation.name() = " + annotation.name());

            }
        }

        System.out.println("---------------");

        appComponents.stream()
                .filter(o -> o instanceof AppComponent)
//                        .forEach(System.out::println)
                .sorted(Comparator.comparing(a -> ((AppComponent) a).order()));

        System.out.println("appComponents = " + appComponents);
        System.out.println("-------------------------");

        // Создадим объекты черезе конструкторы
        createObjectsThroughConstructors();

    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return (C) appComponentsByName.get(componentClass.getName());
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }


    private void createObjectsThroughConstructors() {
        //appComponentsByName.put("", )
    }
}
