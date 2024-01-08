package com.example.springboot_mission1.controller;

import com.example.springboot_mission1.entity.Article;
import com.example.springboot_mission1.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {
    private final ArticleService articleService;
    /*
    * - 게시판 보기: `/boards/{:boardId}/`
- 게시글 작성하기: `/boards/{boardId}/article/`

    * */
    //기본 홈. 전체게시판
    @GetMapping
    public String home(Model model){
        //List<Article> articleList = articleService.showArticlesAll();
        List<Article> articleList = articleService.showArticlesAllDesc();
        model.addAttribute("articleList", articleList);
        model.addAttribute("board", 0);
        return "boards";
    }

    @GetMapping("/{board}")
    public String home
    (
            @PathVariable Long board,
            Model model
    ){

        //List<Article> articleList = articleService.showArticles(board);
        List<Article> articleList = articleService.showArticlesDesc(board);
        model.addAttribute("articleList", articleList);
        return "boards";
    }

    @GetMapping("/create-view")
    public String createView(){
        return "create";
    }

    @PostMapping("/create")
    public String create
    (
      @RequestParam Long board,
      @RequestParam String title,
      @RequestParam String contents,
      @RequestParam String password,
      Model model
    ) {
        articleService.createArticle(board,title,contents,password);
        return "redirect:/boards";
    }
    

}
