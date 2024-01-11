package com.example.springboot_mission1.controller;

import com.example.springboot_mission1.entity.Article;
import com.example.springboot_mission1.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
//    @GetMapping
//    public String home(Model model){
//        //List<Article> articleList = articleService.showArticlesAll();
//        List<Article> articleList = articleService.showArticlesAllDesc();
//        model.addAttribute("articleList", articleList);
//        //model.addAttribute("board", 0);
//        return "boards";
//    }

    @GetMapping("/{board}")
    public String home
    (
            @PathVariable Long board,
            Model model
    ){
        List<Article> articleList = new ArrayList<>();
        //전체게시판일 경우
        if(board == 0){
            articleList = articleService.showArticlesAllDesc();
        }
        //개별게시판일경우
        else
            articleList = articleService.showArticlesDesc(board);
        model.addAttribute("board", board);
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
        return "redirect:/boards/"+board;
    }

    //검색 - 게시판 컨트롤러 / 게시판번호 / 검색 / 검색유형 / 검색단어
    // /boards/{board}/search/{searchName}/{searchWord}
    // /boards/{board}/search/{searchContents}/{searchWord}
    //전체 게시판 검색
//    @PostMapping("/search/{searchType}/{searchWord}")
//    public String searchForAllBoards
//    (
//            String searchType,
//            String searchWord,
//            Model model
//    ){
//        List<Article> articleList = new ArrayList<>();
//        model.addAttribute("articleList", articleList);
//        return "redirect:/boards";
//    }

    //한 게시판 검색
    @GetMapping("/{board}/search")
    public String searchForOneBoards
            (
                    @PathVariable Long board,
                    @RequestParam String searchType,
                    @RequestParam String searchWord,
                    Model model
            ){
        List<Article> articleList = new ArrayList<>();
        //검색타입 : 제목
        if("title".equals(searchType)){
            //전체게시판검색
            if (board == 0){
                articleList = articleService.selectArticlesAllByTitle(searchWord);
            }
            //개별게시판 검색
            else{
                articleList = articleService.selectArticlesByBoardAndTitle(board, searchWord);
            }
        }
        //검색타입 : 내용
        else if ("contents".equals(searchType)) {
            //전체게시판검색
            if (board == 0){
                articleList = articleService.selectArticlesAllByContents(searchWord);
            }
            // 개별게시판 검색
            else{
                articleList = articleService.selectArticlesByBoardAndContents(board, searchWord);
            }
        }


        model.addAttribute("articleList", articleList);
        model.addAttribute("board", board);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchWord", searchWord);
        return "search";
    }
}
