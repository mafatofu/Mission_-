package com.example.springboot_mission1.controller;

import com.example.springboot_mission1.entity.Article;
import com.example.springboot_mission1.entity.Comment;
import com.example.springboot_mission1.service.ArticleService;
import com.example.springboot_mission1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;
    /*
    * - 게시글 보기: `/article/{:articleId}`
- 게시글 수정하기: `/article/{:articleId}/update/`
- 게시글 삭제하기: `/article/{:articleId}/delete/`
- 댓글 작성하기: `/article/{:articleId}/comment/`
- 댓글 삭제하기: `/article/{:articleId}/comment/{:commentId}/delete/`
    * */
    //게시글 보기
    @GetMapping("/{articleId}")
    public String showArticle
    (
            @PathVariable Long articleId,
            Model model
    ){
        Article article = articleService.showArticle(articleId);
        List<Comment> commentList = commentService.showComments(articleId);
        model.addAttribute("article", article);
        model.addAttribute("commentList", commentList);
        return "show";
    }
    //게시글 수정get
    @GetMapping("/{articleId}/update-view")
    public String updateArticleView
    (
            @PathVariable Long articleId,
            Model model
    ){
        Article article = articleService.showArticle(articleId);
        model.addAttribute("article", article);
        return "update";
    }
    //게시글 수정 post
    @PostMapping("/{articleId}/update")
    public String updateArticle
    (
            @PathVariable Long articleId
            //TODO 여기서부터

    ){
        System.out.println("aaaa");
        return "redirect:/"+articleId;
    }
    //게시글 삭제
    //TODO 게시글이 삭제되면 연관된 댓글들도 삭제
    @PostMapping("/{articleId}/delete")
    public String deleteArticle
    (
            @PathVariable Long articleId,
            @RequestParam String articlePwd,
            Model model
    ){
        //비밀번호체크
        Article article = articleService.showArticle(articleId);
        //일치하면삭제
        if(articlePwd.equals(article.getPassword())){
            articleService.deleteArticle(articleId);
            return "redirect:/boards";
        } else{
            return "redirect:/article/"+articleId;
        }


    }
    /*****************댓글*****************/

    //TODO 댓글 작성
    @GetMapping("/{articleId}/comment")
    public String createComment
    (
            @PathVariable Long articleId
    ) {
        return "redirect:/"+articleId;
    }
    //TODO 댓글 삭제
    //비번받음
    @PostMapping("/{articleId}/comment/{commentId}/delete")
    public String deleteComment(){
        return "redirect:/boards";
    }

}