package com.framework.test.domain.posts;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;
	

	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void board_read() {
		// given
		String title = "게시글 타이틀";
		String content = "게시글 본문";
		
		// insert/update query 
		postsRepository.save(
				Posts.builder() 
					.title(title)
					.content(content) 
					.author("minsoub@gmail.com")
					.build()
		);
		
		// when
		List<Posts> postsList = postsRepository.findAll();
		
		// then
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle()).isEqualTo(title);
		assertThat(posts.getContent()).isEqualTo(content);
	}
}
