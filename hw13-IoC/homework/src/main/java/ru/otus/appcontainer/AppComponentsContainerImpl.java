package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.dto.AppComponentDto;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        // You code here...


        List<AppComponentDto> list = new ArrayList<>();
        Method[] declaredMethods = configClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            AppComponent annotationAppComponent = method.getAnnotation(AppComponent.class);
            if (annotationAppComponent != null) {
                int order = annotationAppComponent.order();
                String name = annotationAppComponent.name();

                Parameter[] parameters = method.getParameters();
                Object[] args = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    args[i] = parameters[i].getParameterizedType().getTypeName();
                }

                AppComponentDto dto = new AppComponentDto(order, name, args);

                list.add(dto);
            }
        }


        System.out.println("list = " + list);
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return null;
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return null;
    }
}
