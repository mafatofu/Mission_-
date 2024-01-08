package com.example.springboot_mission1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="article")
@Getter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;
    private String password;
    private Long board;

    //하나의 글에는 여러 댓글이 달릴 수 있다.
    //그냥 파이널로 한번 생생해서 쭉 쓰는게 편해보임
    @OneToMany
    private final List<Comment> commentList = new ArrayList<>();

    public Article(Long id, String title, String contents, String password, Long board) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.password = password;
        this.board = board;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBoard(Long board) {
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return board == article.board && Objects.equals(id, article.id) && Objects.equals(title, article.title) && Objects.equals(contents, article.contents) && Objects.equals(password, article.password) && Objects.equals(commentList, article.commentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, contents, password, board, commentList);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", password='" + password + '\'' +
                ", board=" + board +
                ", commentList=" + commentList +
                '}';
    }
}
