package ru.sarmosov.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ClassInfo {
    int id;
    Class<?> clazz;

    public ClassInfo(int id, Class<?> clazz) {
        this.id = id;
        this.clazz = clazz;
    }
}
