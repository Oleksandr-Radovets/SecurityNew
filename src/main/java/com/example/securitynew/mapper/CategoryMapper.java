package com.example.securitynew.mapper;

import com.example.securitynew.config.MapperConfig;
import com.example.securitynew.dto.category.CategoryResponseDto;
import com.example.securitynew.dto.category.CreateCategoryRequestDto;
import com.example.securitynew.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryResponseDto toDto(Category category);

    Category toModel(CreateCategoryRequestDto categoryDto);
}
