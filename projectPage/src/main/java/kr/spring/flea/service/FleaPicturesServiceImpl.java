package kr.spring.flea.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.flea.dao.FleaPicturesMapper;
import kr.spring.flea.domain.FleaPictureSubVO;
import kr.spring.flea.domain.FleaPicturesVO;

@Service
public class FleaPicturesServiceImpl implements FleaPicturesService{

	@Resource
	private FleaPicturesMapper fleaPicturesMapper;
	
	@Override
	public void insert(FleaPicturesVO fleaPictures) throws IOException {
		// TODO Auto-generated method stub
		
		for(MultipartFile multipartFile : fleaPictures.getUploadPicture()) {
			if(!multipartFile.isEmpty()) {
				fleaPictures.setI_filename(multipartFile.getOriginalFilename());
				fleaPictures.setI_photo(multipartFile.getBytes());
				fleaPicturesMapper.insertPictures(fleaPictures);
			}
		}
		
	}

	@Override
	public List<FleaPicturesVO> selectPictures(int fb_num) {
		// TODO Auto-generated method stub
		return fleaPicturesMapper.selectPictures(fb_num);
	}

	@Override
	public FleaPicturesVO select(int i_num) {
		// TODO Auto-generated method stub
		return fleaPicturesMapper.select(i_num);
	}

	@Override
	public void updatePictures(FleaPicturesVO fleaPictures) throws IOException {
		// TODO Auto-generated method stub
		
		for(FleaPictureSubVO subVO : fleaPictures.getFleaPictureSubVO()) {
			if(!subVO.getUploadPicture().isEmpty()) {
				fleaPictures.setI_num(subVO.getI_num());
				fleaPictures.setI_filename(subVO.getUploadPicture().getOriginalFilename());
				fleaPictures.setI_photo(subVO.getUploadPicture().getBytes());
			}
			fleaPicturesMapper.updatePictures(fleaPictures);
		}
		
	}

	@Override
	public void deletePictures(int i_num) {
		// TODO Auto-generated method stub
		fleaPicturesMapper.deletePictures(i_num);
	}
	
		
}
