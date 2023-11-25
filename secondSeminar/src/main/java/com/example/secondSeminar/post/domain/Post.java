package com.example.secondSeminar.post.domain;

import com.example.secondSeminar.category.domain.CategoryId;
import com.example.secondSeminar.common.domain.BaseTimeEntity;
import com.example.secondSeminar.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post")
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    private String content;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // @ManyToOne 사용이 아닌 논리적으로 관계만 맺어둠
    @Column(name = "category_id")
    private CategoryId categoryId;

    @Builder
    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    @Builder(builderMethodName = "builderWithImageUrl") // 빌더 패턴 쓸 때 메서드 이름을 설정
    public Post(String title, String content, String imageUrl, Member member) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.member = member;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void addCategory(CategoryId categoryId) {
        this.categoryId = categoryId;
    }
}
