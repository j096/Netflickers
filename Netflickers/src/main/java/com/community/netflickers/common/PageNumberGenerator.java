package com.community.netflickers.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PageNumberGenerator {
	
    private long totalCount; // 게시글 총 갯수
    private static int size;// 한 페이지당 게시글 수
    private long numberCount;//필요한 총 페이지 번호 버튼 개수
    private static int offset = 10;//한번에 보일 페이지 번호 버튼 개수
    private long now;
    
    public void CalNumberButton(long totalCount, int size, int now) {
    	
    	this.totalCount = totalCount;
    	this.size = size;
    	this.now = now;
    	this.numberCount = (this.totalCount == 0) ? 1 : (this.totalCount / this.size)+1;
    	
    	if(this.numberCount < offset)
    		offset = (int) numberCount;
    }
    

}
