package com.example.securitynew.service;

import com.example.securitynew.dto.Category.CategoryResponseDto;
import com.example.securitynew.dto.Category.CreateCategoryRequestDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> findAll();
    CategoryResponseDto getById(Long id);
    CategoryResponseDto save(CreateCategoryRequestDto categoryDto);
    CategoryResponseDto update(Long id, CreateCategoryRequestDto categoryDto);
    void deleteById(Long id);
}
