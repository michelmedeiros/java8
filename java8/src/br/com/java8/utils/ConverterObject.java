package br.com.java8.utils;

import java.util.List;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;


//List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());


public interface ConverterObject<R, T> extends Function<R, T> {
    default List<T> convertToList(final List<R> input) {
        return input.stream().map(this::apply).collect(toList());
    }
}
