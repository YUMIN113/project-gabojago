# 가보자Go
가보자GO 프로젝트

## 1. 제작 기간 & 참여 인원
- 제작 기간 : 2022.09월 14일 ~ 11월 25일 (시연 및 발표 : 11월 25일)
- 참여 인원 : 6명

## 2. 사용 기술
- Java (Spring Boot, Gradle, Lombok, Thymeleaf, JavaMail)
- Undertow
- MyBatis
- HTML/CSS
- Javascript (jQuery)
- Visual Studio Code
- eXERD
- MariaDb
- Kakao map API, Kakao Login API
- Bootstrap

## 핵심 트러블 슈팅
1. 페이징 처리 문제.
- 문제점 : Notice 게시판에서 게시물을 클릭한 후, 이전 목록으로 되돌아 갈 때, 해당 게시물이 포함된 페이지가 아닌 첫번째 페이지로 이동하는 문제 발생.
- 해결책 : 목록 버튼 클릭 시, 첫번째 페이지를 디폴트 값으로 설정했던 것을 해당 게시물이 포함된 페이지로 이동할 수 있도록 코드 수정.
<details>
<summary><b>기존 코드</b></summary>
<div markdown="1">

1. noticeListPage.html
```java
<a href='detail?no=1'
data-th-href="@{noticeDetail(no=${notice.no})}"
data-th-text="${notice.title == ''} ? '(제목없음)' : ${notice.title}">제목</a>
```
2. NoticeController 
```java
@GetMapping("noticeDetail")
public void noticeDetail(int no, Model model, @RequestParam("page") Integer page) throws Exception {

  noticeService.addHits(no); // 조회수
  Notice notice = noticeService.get(no);

    if (notice == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }

    model.addAttribute("notice", notice);

}
```
3. noticeDetail.html
```java
<button class="btn btn-secondary py-3 px-5"
               th:onclick="|location.href='@{/support/notice/noticeListPage?page=1}'|"
               type="button">목록</button>
               
```
</div>
</details>

<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">

1. noticeListPage.html
- 해결책 : 서버에서 받아 온 page 값을 파라미터로 주기 위해 코드 추가
```java
<a data-th-href="@{noticeDetail(no=${notice.no}, page=${page})}"
                 data-th-text="${notice.title == ''} ? '(제목없음)' : ${notice.title}">제목</a>
```

2. NoticeController
- 해결책 : page 데이터를 뷰단으로 넘기기 위해 코드 추가
```java
@GetMapping("noticeDetail")
public void noticeDetail(int no, Model model, @RequestParam("page") Integer page) throws Exception {

  noticeService.addHits(no); // 조회수
  Notice notice = noticeService.get(no);

    if (notice == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }

    model.addAttribute("notice", notice);
    model.addAttribute("page", page);

}
```
3. noticeDetail.html
- 해결책 : '목록' 버튼 클릭 시, 해당 게시물이 포함된 목록으로 되돌아 가기 위해 코드 수정
```java
 <button class="btn btn-secondary py-3 px-5"
                th:onclick="|location.href='@{/support/notice/noticeListPage(page=${page})}'|"
                type="button">목록</button>
```
</div>
</details>

2. 휴먼 에러 문제.
- 요구사항 : 인스타그램 아이디를 저장하여 마이페이지에서 사용자의 인스타그램에 접속할 수 있게 한다.
- 문제점 : 아이디 입력 시, 휴먼 에러로 공백이 입력되었고 이를 오류로 인식하지 못하여 문제없이 DB에 저장되었다. 이후, 인스타그램에 접속 시 문제 발생.
- 해결책 : 프론트와 백 모두에 trim() 함수 처리하여 실수로 공백이 입력되어도 공백 제거하여 DB에 저장될 수 있도록 처리.

<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">

1. ModifyMyPageController
- 해결책 : ModifyMyPageController 의 profileUpdate() 메서드에 trim() 함수 추가
```java
@PostMapping("profileUpdate")
public String profileUpdate() {
  saveMember.setNickName(member.getNickName().trim());      
}
```

2. profileDetail.html
- 해결책 : trim() 함수를 포함한 snsAddressBlankCheck() 메서드를 추가하고 인스타그램 아이디 입력칸에 onchange 이벤트 사용

```java
<script>
  function snsAddressBlankCheck() {
    let snsAddressInput = document.querySelector("#snsAddress")
	document.querySelector("#snsAddress").value = snsAddressInput.value.trim();
  }  
</script>    
```

```java
<html>
  <div class="form-group">
    <label>INSTAGRAM</label>
      <input name='snsAddress' type="text" class="form-control" id="snsAddress" onchange="snsAddressBlankCheck()"
	     placeholder="인스타그램 아이디를 입력해 주세요."
	     data-th-value="${member.snsAddress}">
  </div>
</html>    
```

</div>
</details>
