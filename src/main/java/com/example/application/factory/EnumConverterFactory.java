package com.example.application.factory;

import com.example.application.enumeration.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class EnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnum<>(targetType);
    }

    public static <T extends BaseEnum> Object getEnumConstantByString(Class<T> targetType, String s) {
        for (T enumConstant: targetType.getEnumConstants()) {
            if (s.equals(enumConstant.toString())) {
                return enumConstant;
            }
        }
        return null;
    }

    private static class StringToEnum<T extends BaseEnum> implements Converter<String, T> {
        private final Class<T> targetType;

        private StringToEnum(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String s) {
            if (s.isEmpty()) return null;
            return (T) EnumConverterFactory.getEnumConstantByString(targetType, s);
        }
    }
}
