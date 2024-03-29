package com.example.securitynew.controller;

import com.example.securitynew.dto.book.BookDto;
import com.example.securitynew.dto.category.CategoryResponseDto;
import com.example.securitynew.dto.category.CreateCategoryRequestDto;
import com.example.securitynew.service.BookService;
import com.example.securitynew.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PreAuthorize("has_Role('ROLE_ADMIN')")
    @PostMapping()
    public CategoryResponseDto createCategory(@RequestBody CreateCategoryRequestDto
                                                      categoryRequestDto) {
        return categoryService.save(categoryRequestDto);
    }

    @Operation(summary = "get all Category", description = "get all Category")
    @PreAuthorize("has_Role('ROLE_USER')")
    @GetMapping()
    List<CategoryResponseDto> getAllCategory() {
        return categoryService.findAll();
    }

    @Operation(summary = "get Category by id", description = "get Category by id")
    @PreAuthorize("has_Role('ROLE_USER')")
    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PreAuthorize("has_Role('ROLE_ADMIN')")
    @PutMapping("{id}")
    public CategoryResponseDto updateCategory(@PathVariable Long id,
                                              @RequestBody CreateCategoryRequestDto categoryDto) {
        return categoryService.update(id, categoryDto);
    }

    @PreAuthorize("has_Role('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @Operation(summary = "get Book by Category id", description = "get Book by Category id")
    @PreAuthorize("has_Role('ROLE_USER')")
    @GetMapping("/categories/{id}")
    public List<BookDto> getBooksByCategoryId(@PathVariable Long id) {
        return bookService.findAllByCategoryId(id);
    }
}
