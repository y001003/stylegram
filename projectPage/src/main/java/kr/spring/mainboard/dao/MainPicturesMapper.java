package kr.spring.mainboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.mainboard.domain.MainPicturesVO;

public interface MainPicturesMapper {
	@Insert("INSERT INTO promainimages VALUES (promainimages_seq.nextval, #{m_num}, #{mb_num}, #{i_photo}, #{i_filename})")
	public void insertPictures(MainPicturesVO mainPictures);
	@Select("SELECT * FROM promainimages WHERE mb_num=#{mb_num}")
	public List<MainPicturesVO> selectPictures(int mb_num);
	@Select("SELECT * FROM promainimages WHERE i_num=#{i_num}")
	public MainPicturesVO select(int i_num);
	
	@Update("UPDATE promainimages SET i_photo=#{i_photo}, i_filename=#{i_filename} WHERE i_num=#{i_num}")
	public void updatePictures(MainPicturesVO mainPictures);
	@Delete("DELETE FROM promainimages WHERE i_num=#{i_num}")
	public void deletePictures(int i_num);
	

	
}
