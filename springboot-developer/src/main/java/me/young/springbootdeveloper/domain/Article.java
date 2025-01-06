package me.young.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", updatable=false)
    private Long id;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="content", nullable=false)
    private String content;

    @Builder // 롬복
    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }
    // 빌더 패턴을 써서 객체를 더 유연하고 직관적으로 생성
    /*
    객체 생성 방법
    Article.builder()
    .title("abc")
    .content("def")
    .build();
     */
}
