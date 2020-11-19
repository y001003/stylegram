package kr.spring.flea.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.flea.domain.FleaPicturesVO;


public interface FleaPicturesMapper {
	@Insert("INSERT INTO profleaimages VALUES (profleaimages_seq.nextval, #{m_num}, #{fb_num}, #{i_photo}, #{i_filename})")
	public void insertPictures(FleaPicturesVO fleaPictures);
	@Select("SELECT * FROM profleaimages WHERE fb_num=#{fb_num}")
	public List<FleaPicturesVO> selectPictures(int fb_num);
	@Select("SELECT * FROM profleaimages WHERE i_num=#{i_num}")
	public FleaPicturesVO select(int i_num);
	
	@Update("UPDATE profleaimages SET i_photo=#{i_photo}, i_filename=#{i_filename} WHERE i_num=#{i_num}")
	public void updatePictures(FleaPicturesVO fleaPictures);
	@Delete("DELETE FROM profleaimages WHERE i_num=#{i_num}")
	public void deletePictures(int i_num);
	
}
