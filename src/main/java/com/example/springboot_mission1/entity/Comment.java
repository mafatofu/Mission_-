package com.example.springboot_mission1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;
    private String password;

    //여러 댓글들은 공통적인 작성글에 매달려있다.
    @ManyToOne
    private Article article;

    public Comment(Long id, String contents, String password, Article article) {
        this.id = id;
        this.contents = contents;
        this.password = password;
        this.article = article;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(contents, comment.contents) && Objects.equals(password, comment.password) && Objects.equals(article, comment.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contents, password, article);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", contents='" + contents + '\'' +
                ", password='" + password + '\'' +
                ", article=" + article +
                '}';
    }
}
