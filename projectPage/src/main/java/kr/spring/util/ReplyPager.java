package kr.spring.util;

public class ReplyPager {
	
	public static final int PAGE_SCALE = 10;//페이지당 게시물 수
	public static final int BLOCK_SCALE = 4;//화면당 페이지 수
	private int curPage; //현재 페이지
	private int prevPage; //이전 페이지
	private int nextPage; //다음 페이지
	private int totalPage; //전체 페이지 갯수
	private int totalBlock; // 전체 페이지 블록 갯수
	private int curBlock; // 현재 페이지 블록
	private int prevBlock; // 이전 페이지 블록
	private int nextBlock; // 다음 페이지 블록
	
	//WHERE rn BETWEEN #{start} AND #{end}
	private int pageBegin; // #{start}
	private int pageEnd; // #{end}
	//[이전] 
	private int blockBegin;
	private int blockEnd;
	
	//생성자
	// BoardPager(레코드 갯수, 현재 페이지 번호)
	public ReplyPager(int count, int curPage) {
		curBlock = 1; // 현재 페이지 블록 번호
		this.curPage = curPage;
		setTotalPage(count);
		setPageRange();
		setTotalBlock();
		setBlockRange();
	}
	public void setPageRange() {
		//현재 페이지가 몇번째 페이지 블록에 속하는지 계산
		curBlock = (int)Math.ceil((curPage-1)/BLOCK_SCALE)+1;
		//현재 페이지 블록의 시작, 끝 번호 계산
		blockBegin = (curBlock-1)*BLOCK_SCALE+1;
		//페이지 블록의 끝번호
		blockEnd = blockBegin+BLOCK_SCALE-1;//ex 6번째 블록 : 51+10-1 = 60
		if(blockEnd > totalPage) blockEnd = totalPage;// 53번쨰 페이지까지 있을 경우 마지막 블록도 53으로 맞추기
		//이전버튼 클릭
		prevPage = (curPage == 1) ? 1:(curBlock-1)*BLOCK_SCALE;
		//다음버튼 클릭
		nextPage = curBlock > totalBlock ? (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1;
		//마지막 페이지가 범위를 초과하지 않도록 처리
		if(nextPage >= totalPage) nextPage = totalPage;
	}
	public void setBlockRange() {
		//WHERE rn BETWEEN #{start} and #{end}
		//시작번호
		pageBegin = (curPage-1)*PAGE_SCALE +1;
		//끝번호
		pageEnd = pageBegin+PAGE_SCALE-1;
	}
	

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int count) {
		totalPage = (int) Math.ceil(count*1.0/PAGE_SCALE);//실수 올림처리 91게시물일때 10개 페이지
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock() {
		totalBlock = (int) Math.ceil(totalPage/BLOCK_SCALE);
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getBlockBegin() {
		return blockBegin;
	}

	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}
	
}
