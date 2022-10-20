package com.example.jobsearchboardtalika.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class EntityMapper {

    private final ModelMapper mapper = new ModelMapper();

    public <S, T> List<T> mapAsList(final List<S> sourceElements, final Class<T> targetClass) {
        final var lists = new LinkedList<T>();
        sourceElements.forEach(s -> lists.add(mapper.map(s, targetClass)));
        return lists;
    }

    public <S, T> T map(final S element, final Class<T> targetClass) {
        return mapper.map(element, targetClass);
    }
}
