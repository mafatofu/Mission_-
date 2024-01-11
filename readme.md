웹페이지 구축 미션 프로젝트


1. 필수 요구사항을 구현한 방식에 대한 기초적인 설명을 첨부  
   1.1 기본 entity관계. 
   - article(게시글) - comment(댓글)로 이루어져있다.
   - 하나의 게시글(OneToMany)에 여러 댓글(ManyToOne)이 올 수 있다.
      <img src="readmeImg/entity.JPG">
   1.2 기본 구조.
     - 템플릿 : boards / create / show / update / searh
       - boards : 전체게시판 / 자유게시판 / 개발게시판 / 일상게시판 / 사건사고게시판 이 있다.
         - 각 게시판의 글들은 최신글이 맨 위로 올라온다.
       - create : 글을 작성할 수 있다. 게시판 선택이 가능하며, 제목과 내용을 작성할 수 있다. 
         - 비밀번호를 입력하여, 추후 수정 / 삭제 시 본인 체크 가능
       - show : 게시글 보기 기능. 작성한 게시판 / 제목 / 내용이 나타난다.
         - 글 삭제와 수정이 이곳에서 이루어진다.
         - 댓글을 작성할 수 있다.
           - 비밀번호를 입력하여, 추후 삭제 시 본인 체크 가능
           - 댓글 삭제가 가능하다.
       - update : 작성한 글의 수정이 가능하다.
         - 기존 선택했던 게시판 / 작성했던 제목 / 내용이 나타나며, 수정 가능하다.
           - 새로운 비밀번호를 입력해야한다.
           - 수정 사항을 반영하기 위해서는, 글 생성 시 입력하였던 비밀번호를 입력해야만 한다.
       - search : 게시글 검색이 가능하다.
         - 전체게시판 / 게시판별 검색이 가능하다.
         - 제목 / 내용으로 검색이 가능하다.
     - 컨트롤러 : BoardController / ArticleController
       - BoardController : 게시판 관련 기능과 연결해준다.
         - 게시판 보기 / 게시글 작성 / 게시판 내 검색
     - 서비스 : ArticleService / CommentService
       - ArticleService : 게시글 관련 기능 제공
       - CommentService : 댓글 관련 기능 제공
     - 레포지토리 : ArticleRepository / CommentRepository
       - ArticleRepository : 게시글 관련 DB 소통
       - CommentRepository : 댓글 관련 DB 소통
     - 엔티티 : Article / Comment
     - Article : 게시글.
     - Comment : 댓글.
     <img src="readmeImg/기본.PNG">


2. 진행중 발생한 어려움에 대한 기록.
   - 시작을 어떻게 해야할지 어려웠다.  
   - 어떤 페이지를 어떤 컨트롤러로 연결시켜, 어떤 서비스에서 어떤 쿼리로 소통해야하는지 전체적인 틀을 잡는게 어려웠다. 
   - 틀을 잡았음에도, 놓치고 있던 부분이 나타나 수정할 때, 여러 부분들을 뜯어고쳐야하는 부분이 어려웠다.
   - 테스트를 하며 나타나는 각종 에러들이 어디에서 정확히 시작되는지 파악하는것이 쉽지않았다.
3. 완성된 프로젝트를 어떻게 실행하고 테스트 하는지에 대한 기록을 첨부.  
   - 작성기록 : https://github.com/mafatofu/Mission_yeongkyulee/commits/main/
   - 실행 및 테스트 : 영상첨부



최초 실행시 `jpa.hiberante.ddl-auto: create` 의 설정으로 실행


할거 :  
추가기능 해보기  
기능작성 후 readme 수정  
실행 및 테스트방법 영상찍기  

피어리뷰
https://likelion.notion.site/a30ff766154e4a63826aaa386ea71e40#e5c85726ea8c481b9930c004dcba536e