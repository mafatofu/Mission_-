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
            @PathVariable Long articleId,
            @RequestParam Long board,
            @RequestParam String title,
            @RequestParam String contents,
            @RequestParam String usedPwd,
            @RequestParam String newPwd
    ){
        //비밀번호체크
        Article article = articleService.showArticle(articleId);
        //기존 비밀번호와 새로 입력한 비밀번호 일치여부 체크
        if(usedPwd.equals(article.getPassword())){
            articleService.updateArticle(articleId,board,title,contents,newPwd);
            //일치하면 게시글 페이지
            return "redirect:/article/"+articleId;
        } else{
            //비번틀리면 다시 수정페이지
            return "redirect:/article/"+articleId+"/update-view";
        }

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
            //commentService.deleteComments(articleId);
            return "redirect:/boards";
        } else{
            return "redirect:/article/"+articleId;
        }


    }
    /*****************댓글*****************/

    //TODO 댓글 작성
    @PostMapping("/{articleId}/comment")
    public String createComment
    (
            @PathVariable Long articleId,
            @RequestParam String contents,
            @RequestParam String createCommentPwd
    ) {
        Article article = articleService.showArticle(articleId);

        commentService.createComment(article,contents,createCommentPwd);
        return "redirect:/article/"+articleId;
    }
    //TODO 댓글 삭제
    //비번받음
    @PostMapping("/{articleId}/comment/{commentId}/delete")
    public String deleteComment
    (
            @PathVariable Long articleId,
            @PathVariable Long commentId,
            @RequestParam String deleteCommentPwd
    ) {
        Comment comment = commentService.showComment(commentId);

        //패스워드가 일치할 시
        if(deleteCommentPwd.equals(comment.getPassword())){
            //댓글 삭제
            commentService.deleteComments(commentId);
            return "redirect:/article/"+ articleId;
        } else
            return "redirect:/article/"+articleId;

    }

}
