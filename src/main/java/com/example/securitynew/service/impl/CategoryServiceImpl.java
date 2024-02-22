package com.example.securitynew.service.impl;

import com.example.securitynew.dto.Category.CategoryResponseDto;
import com.example.securitynew.dto.Category.CreateCategoryRequestDto;
import com.example.securitynew.mapper.CategoryMapper;
import com.example.securitynew.model.Category;
import com.example.securitynew.repository.CategoryRepository;
import com.example.securitynew.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    @Override
    public List<CategoryResponseDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Can't find category by id" + id)));
    }

    @Override
    public CategoryResponseDto save(CreateCategoryRequestDto categoryDto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toModel(categoryDto)));
    }

    @Override
    public CategoryResponseDto update(Long id, CreateCategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find category by " + id));
        Category model = categoryMapper.toModel(categoryRequestDto);
       model.setId(category.getId());
        return categoryMapper.toDto(categoryRepository.save(model));
    }

    @Override
    public void deleteById(Long id) {
       categoryRepository.deleteById(id);
    }
}
