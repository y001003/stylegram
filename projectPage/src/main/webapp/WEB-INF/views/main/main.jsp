<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.js"></script>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.js"></script>
<script src="https://unpkg.com/infinite-scroll@3/dist/infinite-scroll.pkgd.js"></script>
<style type="text/css">
    /* reveal grid after images loaded */
    .grid.are-images-unloaded {
      opacity: 0;
    }

    .grid__item,
    .grid__col-sizer {
     width: 15px;
     
    }

    .grid__gutter-sizer { width: 1%; }

    /* hide by default */
    .grid.are-images-unloaded .image-grid__item {
      opacity: 0;
    }

    .grid__item {
      margin: 0 auto;
      float: left;
      width: 300px;
      
    }

    .grid__item img {
      width: 100%;
	  border-radius: 15px;
    }
</style>
<script>
$(document).ready(function() {
    //-------------------------------------//
    // init Masonry

    var $grid = $('.grid').masonry({
      itemSelector: 'none', // select none at first
      columnWidth: '.grid__col-sizer',
      gutter: '.grid__gutter-sizer',
      percentPosition: true,
      stagger: 30,
      // nicer reveal transition
      visibleStyle: { transform: 'translateY(0)', opacity: 1 },
      hiddenStyle: { transform: 'translateY(100px)', opacity: 0 },
    });

    // get Masonry instance
    var msnry = $grid.data('masonry');

    // initial items reveal
    $grid.imagesLoaded( function() {
      $grid.removeClass('are-images-unloaded');
      $grid.masonry( 'option', { itemSelector: '.grid__item' });
      var $items = $grid.find('.grid__item');
      $grid.masonry( 'appended', $items );
    });

    //-------------------------------------//
    // hack CodePen to load pens as pages

    var count = ${count};
    var pageNums = Math.ceil(count/10);
    var nextPenSlugs = [];
    	
    /* alert(pageNums); */	
     for(var i=2;i<=pageNums;i++){
  	   nextPenSlugs.push('main.do?filter=${filter}&keyfield=${keyfield}&keyword=${keyword}&pageNum='+i);
     }	

    function getPenPath() {
      var slug = nextPenSlugs[ this.loadCount ];
      if ( slug ) {
        return './' + slug;
      }
    }

    //-------------------------------------//
    // init Infinte Scroll

    $grid.infiniteScroll({
      path: getPenPath,
      append: '.grid__item',
      outlayer: msnry,
      status: '.page-load-status',
    });
    
    var pwdresult = '${pwdresult}';
    
    if(pwdresult != 0){
    	alert('비밀번호 변경 완료.\n새로운 비밀번호로 다시 로그인해주세요.');
    	pwdresult = 0;
    }
});
</script>
<div id="body">

	<div class="searchForm">
		<form action="main.do" method="get">
			<div id="filter" class="filter">
				<select name="filter" class="filter" onchange="this.form.submit()">
					<option value="">전체</option>
					<option value="top" <c:if test="${filter eq 'top'}">selected</c:if>>상의</option>
					<option value="pants" <c:if test="${filter eq 'pants'}">selected</c:if>>하의</option>
					<option value="cap" <c:if test="${filter eq 'cap'}">selected</c:if>>모자</option>
					<option value="shoes" <c:if test="${filter eq 'shoes'}">selected</c:if>>신발</option>
				</select>
				<select name="keyfield" class="keyfield">
					<option value="title" <c:if test="${keyfield eq 'title'}">selected</c:if>>제목</option>
					<option value="content" <c:if test="${keyfield eq 'content'}">selected</c:if>>내용</option>
					<option value="id" <c:if test="${keyfield eq 'id'}">selected</c:if>>아이디</option>
					<option value="nickname" <c:if test="${keyfield eq 'nickname'}">selected</c:if>>닉네임</option>
				</select>		
				<input type="search" size="16" name="keyword" id="keyword" value="${keyword }">
			</div>
		</form>	
	</div>
	<!-- 로그인 되었을 시 글쓰기 버튼 화면따라다님 -->
	<c:if test="${!empty m_id}">
	<div id="write_btn">
		<a href="${pageContext.request.contextPath}/mainBoard/write.do">
			<img src="${pageContext.request.contextPath}/resources/images/write.png">
		</a>
	</div>
	</c:if>
	<div id="card">
	<c:if test="${count > 0}">
	
	<div class="grid">
	<div class="all_wrap" data-masonry='{ "itemSelector": ".wrap-item", "columnWidth": 200 }'>
	
	<div class="grid__col-sizer"></div>
	
	<c:forEach var="mainList" items="${mainList}">
		<div class="grid__item">
		<div class="entry-content">
		<div class="card-sheet"> 
			<div id="card-img">
			<c:if test="${empty mainList.mb_photo}">
				<a href="${pageContext.request.contextPath}/mainBoard/detail.do?mb_num=${mainList.mb_num}">
					<img src="${pageContext.request.contextPath}/resources/images/noimage.png">
				</a>
			</c:if>
			<c:if test="${!empty mainList.mb_photo}">
				<a href="${pageContext.request.contextPath}/mainBoard/detail.do?mb_num=${mainList.mb_num}">
					<img src="imageView.do?mb_num=${mainList.mb_num}">
				</a>
			</c:if>
			</div>
			<div id="card-body">
				<div id="card-profile">
				<c:if test="${empty mainList.m_image}">
					<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${mainList.m_num}">
					<img src="${pageContext.request.contextPath}/resources/images/basicprofile.png">
					</a>
				</c:if>
				<c:if test="${!empty mainList.m_image}">
					<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${mainList.m_num}">
					<img src="${pageContext.request.contextPath}/member/imageViewProfile.do?m_num=${mainList.m_num}">
					</a>
				</c:if>
				<c:if test="${empty mainList.m_nickname}">
				${mainList.m_id}
				</c:if>
				<c:if test="${!empty mainList.m_nickname}">
				${mainList.m_nickname}
				</c:if>
				</div>
				<div id="card-content">
					<div class="card-title">
						${mainList.mb_title}
					</div>
					<hr size="1" width="100%">
					<div class="card-content">
						${mainList.mb_content}			
					</div>
					<c:if test="${!empty mainList.mb_content}">
					<hr size="1" width="100%">
					</c:if>
					<div class="card-category">
					<c:if test="${!empty mainList.mb_topinfo}">	
						<img src="${pageContext.request.contextPath}/resources/images/tshirts.png">
					</c:if>
					<c:if test="${!empty mainList.mb_pantsinfo}">
						<img src="${pageContext.request.contextPath}/resources/images/pants2.png">
					</c:if>
					<c:if test="${!empty mainList.mb_capinfo}"> 
						<img src="${pageContext.request.contextPath}/resources/images/cap.png">
					</c:if>
					<c:if test="${!empty mainList.mb_shoesinfo}">
						<img src="${pageContext.request.contextPath}/resources/images/shoes.png">
					</c:if>
					<c:if test="${empty mainList.mb_topinfo && empty mainList.mb_pantsinfo && empty mainList.mb_capinfo && empty mainList.mb_shoesinfo}">
						분   류   없   음
					</c:if>
					</div>
				</div>
			</div>
			</div>
		</div>
		</div>
	<div class="grid__gutter-sizer"></div>
	</c:forEach>
	</div>
	</div>
	</c:if>
	</div>
</div>