package kr.spring.flea.service;

import java.io.IOException;
import java.util.List;

import kr.spring.flea.domain.FleaPicturesVO;

public interface FleaPicturesService {
	public void insert(FleaPicturesVO fleaPictures) throws IOException;
	public List<FleaPicturesVO> selectPictures(int fb_num);
	public FleaPicturesVO select(int i_num);
	public void updatePictures(FleaPicturesVO fleaPictures) throws IOException;
	public void deletePictures(int i_num);
}
