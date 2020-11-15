package com.framework.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.framework.test.dto.PostsResponseDto;
import com.framework.test.dto.PostsSaveRequestDto;
import com.framework.test.dto.PostsUpdateRequestDto;
import com.framework.test.service.PostsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

	private final PostsService service;
	
	@PostMapping("/api/v1/posts")
	public Long save(@RequestBody PostsSaveRequestDto requestDto)
	{
		return service.save(requestDto);
	}
	
	@PutMapping("/api/v1/posts/{id}")
	public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto)
	{
		return service.update(id, requestDto);
	}
	
	@GetMapping("/api/v1/posts/{id}")
	public PostsResponseDto findById (@PathVariable Long id)
	{
		return service.findById(id);
	}
}
