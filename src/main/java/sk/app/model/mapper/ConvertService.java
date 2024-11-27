package sk.app.model.mapper;

import java.util.List;

public interface ConvertService <T, D>{

    T toDto(D entity);

    List<T> toDtoList(List<D> entityList);
}
