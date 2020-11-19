package kr.spring.mainboard.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.mainboard.dao.MainPicturesMapper;
import kr.spring.mainboard.domain.MainPictureSubVO;
import kr.spring.mainboard.domain.MainPicturesVO;

@Service("mainPicturesService")
public class MainPicturesServiceImpl implements MainPicturesService{
	
	@Resource
	private MainPicturesMapper mainPicturesMapper;
	
	@Override
	public void insert(MainPicturesVO mainPictures) throws IOException {
		// TODO Auto-generated method stub
		
		for (MultipartFile multipartFile : mainPictures.getUploadPicture()) {
			
			if(!multipartFile.isEmpty()) {
				mainPictures.setI_filename(multipartFile.getOriginalFilename());
				mainPictures.setI_photo(multipartFile.getBytes());
				mainPicturesMapper.insertPictures(mainPictures);
			}
		}
					
		
		
	}

	@Override
	public List<MainPicturesVO> selectPictures(int mb_num){
		// TODO Auto-generated method stub
		
		return mainPicturesMapper.selectPictures(mb_num);
	}

	@Override
	public MainPicturesVO select(int i_num) {
		// TODO Auto-generated method stub
		return mainPicturesMapper.select(i_num);
	}

	@Override
	public void updatePictures(MainPicturesVO mainPictures) throws IOException {
		
		
		for (MainPictureSubVO subVO : mainPictures.getMainPictureSubVO()) {
			if(!subVO.getUploadPicture().isEmpty()) {
				mainPictures.setI_num(subVO.getI_num());
				mainPictures.setI_filename(subVO.getUploadPicture().getOriginalFilename());
				mainPictures.setI_photo(subVO.getUploadPicture().getBytes());
			}
			mainPicturesMapper.updatePictures(mainPictures);
		}
		
	}

	@Override
	public void deletePictures(int i_num) {
		mainPicturesMapper.deletePictures(i_num);
	}

}
