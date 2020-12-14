package com.framework.test.domain.posts;

import java.time.LocalDateTime;
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
		System.out.println(">>>>>>>>>>>> createdDate = " + posts.getCreatedDate() + ", modifiedDate = "+posts.getModifedDate());
		assertThat(posts.getTitle()).isEqualTo(title);
		assertThat(posts.getContent()).isEqualTo(content);
	}
	
	@Test
	public void BaseTimeEntity_Register()
	{
		// given
		LocalDateTime now = LocalDateTime.of(2019,  6, 4, 0, 0, 0);
		postsRepository.save(
				Posts.builder()
				.title("title")
				.content("content")
				.author("author")
				.build()
		);
		
		// when
		List<Posts> postsList = postsRepository.findAll();
		
		// then
		Posts posts = postsList.get(0);
		
		System.out.println("posts title : " + posts.getTitle());
		System.out.println("posts content : " + posts.getContent());
		
		assertThat(posts.getTitle()).isEqualTo("title");
		
		System.out.println(">>>>>>>>>>>> createdDate = " + posts.getCreatedDate() + ", modifiedDate = "+posts.getModifedDate());
		
		assertThat(posts.getCreatedDate()).isAfter(now);
		assertThat(posts.getModifedDate()).isAfter(now);
	}
}
