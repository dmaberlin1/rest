package com.dmadev.rest.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE) //все филды по дефолту private
public class CatDTO {
    String name;
    int weight;
    int age;
}
