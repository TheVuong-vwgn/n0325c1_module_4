package com.techzen.academy_n0325c1.dto.clazz;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClazzRequest {
    @NotNull(message = "Lớp không được để trống")
    Integer id;
    String name;
}
