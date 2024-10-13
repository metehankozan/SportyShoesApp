package com.sportyshoes.SportyShoesApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySaveResponse {

    private boolean isSuccess;
    private String message;
}
