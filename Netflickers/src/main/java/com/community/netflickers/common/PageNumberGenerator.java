package com.community.netflickers.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PageNumberGenerator {
	
    private static final int numberOffset = 10;//한번에 보일 페이지 번호 버튼 개수

    private long totalContent; // 게시글 총 갯수
    private long totalNumber;//필요한 총 페이지 번호 버튼 개수
    private long startNum = 0;
    private long endNum;
    private long now;
    boolean hasNext, hasPrev;
    
    
    public void calNumberButton(long totalContent,long now, long pageSize){
    	this.totalContent = totalContent;
    	this.now = now;
    	
    	this.totalNumber = totalContent/pageSize;
    	this.endNum = startNum+numberOffset;
    	this.endNum = this.endNum > totalContent ? totalContent : this.endNum;
    	if(now+1 < totalNumber) hasNext = true; else hasNext = false;
    	if(now-1 < 0) hasPrev = false; else hasPrev = true;
    	
    }
    
}
