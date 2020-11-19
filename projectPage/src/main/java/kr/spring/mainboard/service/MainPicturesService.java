package kr.spring.mainboard.service;

import java.io.IOException;
import java.util.List;

import kr.spring.mainboard.domain.MainPicturesVO;

public interface MainPicturesService {
	public void insert(MainPicturesVO mainPictures) throws IOException;
	public List<MainPicturesVO> selectPictures(int mb_num);
	public MainPicturesVO select(int i_num);
	public void updatePictures(MainPicturesVO mainPictures) throws IOException;
	public void deletePictures(int i_num);
}
