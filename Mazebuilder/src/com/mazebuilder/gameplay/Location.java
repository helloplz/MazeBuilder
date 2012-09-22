package com.mazebuilder.gameplay;

import com.mazebuilder.renderer.BoardRendererOptions;

public interface Location {
	
	int getRow();
	
	int getColumn();
	
	int toPixelX(BoardRendererOptions b);
	
	int toPixelY(BoardRendererOptions b);
	
}
