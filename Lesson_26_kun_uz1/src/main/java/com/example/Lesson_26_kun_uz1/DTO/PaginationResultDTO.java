package com.example.Lesson_26_kun_uz1.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Setter
@Getter

public class PaginationResultDTO<T> {
    private List<T> list;
    private Long totalElement;

    public PaginationResultDTO() {
    }

    public PaginationResultDTO(List<T> list, Long totalElement) {
        this.list = list;
        this.totalElement = totalElement;
    }
}
