package me.young.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.young.springbootdeveloper.domain.Article;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;

    // DTO를 엔티티로 변환
    public Article toEntity() {
        // 생성자를 사용해 객체 생성
        return Article.builder()
                .title(title) // 클래스 내부 필드는 클래스 내부 메서드가 직접 사용 가능
                .content(content)
                .build();
    }
}
