package org.janis.qa.homework.helpers;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;

public class ObjectHelper {
    private static final String SET = "set";

    public static <T, K> void invokeSetterMethod(final String fieldName,
                                                 final T toSet,
                                                 final K setterValue,
                                                 final Class<K> type) {

        var setterName = SET + StringUtils.capitalize(fieldName);

        try {
            var method = toSet.getClass().getMethod(setterName, type);
            method.invoke(toSet, setterValue);
        } catch (final NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
