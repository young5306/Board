package me.young.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.young.springbootdeveloper.domain.Article;
import me.young.springbootdeveloper.dto.AddArticleRequest;
import me.young.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

@Service // (서블릿 컨테이너에) 빈으로 등록
@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
public class BlogService {

    private final BlogRepository blogRepository;

    // 1. 블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }
}
