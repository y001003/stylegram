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
  	   nextPenSlugs.push('fleaList.do?filter=${filter}&keyfield=${keyfield}&keyword=${keyword}&pageNum='+i);
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
});
</script>
<div id="body">
	<div class="searchForm">
		<form action="fleaList.do" method="get">
			<div id="filter" class="filter">
				<select name="filter" class="filter" onchange="this.form.submit()">
					<option value="">전체</option>
					<option value="top" <c:if test="${filter eq 'top'}">selected</c:if>>상의</option>
					<option value="bottom" <c:if test="${filter eq 'bottom'}">selected</c:if>>하의</option>
					<option value="hat" <c:if test="${filter eq 'hat'}">selected</c:if>>모자</option>
					<option value="shoes" <c:if test="${filter eq 'shoes'}">selected</c:if>>신발</option>
				</select>
				<select name="keyfield" class="keyfield">
					<option value="title" <c:if test="${keyfield eq 'title'}">selected</c:if>>제목</option>
					<option value="content" <c:if test="${keyfield eq 'content'}">selected</c:if>>내용</option>
					<option value="id" <c:if test="${keyfield eq 'id'}">selected</c:if>>아이디</option>
					<option value="nickname" <c:if test="${keyfield eq 'nickname'}">selected</c:if>>닉네임</option>
				</select>		
				<input type="search" size="16" name="keyword" id="keyword" value="${keyword}">
			</div>
		</form>
	</div>
	<!-- 로그인 되었을 시 글쓰기 버튼 화면따라다님 -->
	<c:if test="${!empty m_id}">
		<div id="write_btn">
			<a href="${pageContext.request.contextPath}/flea/fleaWrite.do"> 
				<img src="${pageContext.request.contextPath}/resources/images/write.png">
			</a>
		</div>
	</c:if>
	<div id="card">
	<c:if test="${count > 0}">
	
	<div class="grid">
	<div class="all_wrap" data-masonry='{ "itemSelector": ".wrap-item", "columnWidth": 200 }'>
	
	<div class="grid__col-sizer"></div>
	
	<c:forEach var="flea" items="${list}">
		<div class="grid__item">
		<div class="entry-content">
		<div class="card-sheet">
			<div id="card-img">
				<c:if test="${empty flea.fb_photo}">
					<a href="${pageContext.request.contextPath}/flea/fleaDetail.do?fb_num=${flea.fb_num}">
						<img src="${pageContext.request.contextPath}/resources/images/noimage.png">
					</a>
				</c:if>
				<c:if test="${!empty flea.fb_photo}">
					<a href="${pageContext.request.contextPath}/flea/fleaDetail.do?fb_num=${flea.fb_num}">
						<img class="fleaItem" src="imageView.do?fb_num=${flea.fb_num}">
					</a>
				</c:if>
			</div>
			<div id="card-body">
				<div id="card-profile">
					<c:if test="${empty flea.m_image}">
						<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${flea.fb_usernum}">
							<img src="${pageContext.request.contextPath}/resources/images/basicprofile.png">
						</a>
					</c:if>
					<c:if test="${!empty flea.m_image}">
						<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${flea.fb_usernum}">
							<img src="${pageContext.request.contextPath}/member/imageViewProfile.do?m_num=${flea.fb_usernum}">
						</a>
					</c:if>
					<c:if test="${empty flea.m_nickname}">
					${flea.m_id}
					</c:if>
					<c:if test="${!empty flea.m_nickname}">
					${flea.m_nickname}
					</c:if>
				</div>
				<div id="card-content">
					<div class="card-title">
						${flea.fb_title}
					</div>
					<hr size="1" width="100%">
					<c:if test="${flea.fb_price != 0}">
					<div class="card-content">
						${flea.fb_price} 원
					</div>
					</c:if>
					<c:if test="${flea.fb_price == 0}">
					<div class="card-content">
						가격 미정
					</div>
					</c:if>
					<hr size="1" width="100%">
					<div class="card-category">
					<c:if test="${flea.fb_topcheck eq 1}">	
						<img src="${pageContext.request.contextPath}/resources/images/tshirts.png">
					</c:if>
					<c:if test="${flea.fb_bottomcheck eq 1}">
						<img src="${pageContext.request.contextPath}/resources/images/pants2.png">
					</c:if>
					<c:if test="${flea.fb_hatcheck eq 1}">
						<img src="${pageContext.request.contextPath}/resources/images/cap.png">
					</c:if>
					<c:if test="${flea.fb_shoescheck eq 1}">
						<img src="${pageContext.request.contextPath}/resources/images/shoes.png">
					</c:if>
					<c:if test="${flea.fb_topcheck eq 0 && flea.fb_bottomcheck eq 0 && flea.fb_hatcheck eq 0 && flea.fb_shoescheck eq 0}">
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