package by.belisa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import by.belisa.bean.AnketaDTO;
import by.belisa.bean.FizInfoDTO;
import by.belisa.bean.IspolnitelDTO;
import by.belisa.dao.Dao;
import by.belisa.dao.FizInfoDao;
import by.belisa.dao.KonkursyDao;
import by.belisa.dao.OrgDao;
import by.belisa.dao.UchStepeniDao;
import by.belisa.dao.UchZvanieDao;
import by.belisa.dao.ZayavkaFIDao;
import by.belisa.entity.FizInfo;
import by.belisa.entity.Ispolnitel;
import by.belisa.entity.UchStepeni;
import by.belisa.entity.UchZvaniy;
import by.belisa.entity.ZayavkaFI;
import by.belisa.exception.DaoException;

@Service
public class FizInfoService extends ServiceImpl<FizInfo, Integer>{

	public FizInfoService() {
		super(FizInfo.class);
	}
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	@Autowired
	@Qualifier(value="fizInfoDao")
	private FizInfoDao fizInfoDao;
	@Autowired
	@Qualifier("uchStepeniDao")
	private UchStepeniDao uchStepeniDao;
	@Autowired
	@Qualifier("uchZvanieDao")
	private UchZvanieDao uchZvanieDao;
	@Autowired
	@Qualifier("orgDao")
	private OrgDao orgDao;
	@Autowired
	@Qualifier("zayavkaFIDao")
	private ZayavkaFIDao zayavkaFIDao;
	@Autowired
	@Qualifier("konkursyDao")
	private KonkursyDao konkursyDao;
	
	@Override
	@Autowired
	@Qualifier(value="fizInfoDao")
	protected void setBaseDao(Dao<FizInfo, Integer> baseDao) {
		super.setBaseDao(baseDao);
	}
	public Integer addFizInfo(AnketaDTO dto) throws ParseException, DaoException{
		Date birthday = dateFormat.parse(dto.getBirthday());
		FizInfo fizInfo = fizInfoDao.getByFio(dto.getSurname(),dto.getName(),dto.getPatronymic(),birthday);
		if (fizInfo==null){
			fizInfo = new FizInfo();
			fizInfo.setBirthday(birthday);
			fizInfo.setSurname(dto.getSurname()+" "+dto.getName()+" "+dto.getPatronymic());
			if (dto.getOrgId()!=null){
				fizInfo.setOrg(orgDao.get(dto.getOrgId()));
			}
			
			fizInfo.setPost(dto.getPost());
			
			if (dto.getUchStepeniIdArr()!=null){
				List<UchStepeni> uchStepeniList = new ArrayList<UchStepeni>();
				for (Integer i : dto.getUchStepeniIdArr()){
					uchStepeniList.add(uchStepeniDao.get(i));
				}
				fizInfo.setUchStepeniList(uchStepeniList);
			}
			
			if (dto.getUchZvaniyIdArr()!=null){
				List<UchZvaniy> uchZvaniyList = new ArrayList<UchZvaniy>();
				for (Integer i : dto.getUchZvaniyIdArr()){
					uchZvaniyList.add(uchZvanieDao.get(i));
				}
				fizInfo.setUchZvaniyList(uchZvaniyList);
			}
			
			fizInfoDao.saveOrUpdate(fizInfo);
		}
		return fizInfo.getId();
		
		
		
	}
	public Integer addFizInfo(IspolnitelDTO dto) throws ParseException, DaoException{
		Date birthday = dateFormat.parse(dto.getBirthday());
		FizInfo fizInfo = fizInfoDao.getByFio(dto.getSurname(),dto.getName(),dto.getPatronymic(),birthday);
		if (fizInfo==null){
			fizInfo = new FizInfo();
			fizInfo.setBirthday(birthday);
			fizInfo.setSurname(dto.getSurname()+" "+dto.getName()+" "+dto.getPatronymic());
		}
		if (dto.getOrgId()!=null){
			fizInfo.setOrg(orgDao.get(dto.getOrgId()));
		}
		
		fizInfo.setPost(dto.getPost());
		if (dto.getUchStepeniIdArr()!=null){
			List<UchStepeni> uchStepeniList = new ArrayList<UchStepeni>();
			for (Integer i : dto.getUchStepeniIdArr()){
				uchStepeniList.add(uchStepeniDao.get(i));
			}
			fizInfo.setUchStepeniList(uchStepeniList);
		}
		if (dto.getUchZvaniyIdArr()!=null){
			List<UchZvaniy> uchZvaniyList = new ArrayList<UchZvaniy>();
			for (Integer i : dto.getUchZvaniyIdArr()){
				uchZvaniyList.add(uchZvanieDao.get(i));
			}
			fizInfo.setUchZvaniyList(uchZvaniyList);
		}
		
		if (dto.getUchZvaniyIdArr()!=null){
			List<UchZvaniy> uchZvaniyList = new ArrayList<UchZvaniy>();
			for (Integer i : dto.getUchZvaniyIdArr()){
				uchZvaniyList.add(uchZvanieDao.get(i));
			}
			fizInfo.setUchZvaniyList(uchZvaniyList);
		}
		
		fizInfoDao.saveOrUpdate(fizInfo);
		return fizInfo.getId();
	}
	public void addZayavkaFI(Integer fizInfoId, Integer zayavkaId) throws DaoException{
		fizInfoDao.get(fizInfoId).getZayavki().add(zayavkaFIDao.get(zayavkaId));
	}
	public void removeZayavkaFI(Integer zayavkaId, Ispolnitel ispolnitel) throws DaoException{
		ZayavkaFI zayavkaFI = zayavkaFIDao.get(zayavkaId);
		FizInfo fizInfo = fizInfoDao.getByFio(ispolnitel.getSurname(),ispolnitel.getName(),ispolnitel.getPatronymic(),ispolnitel.getBirthday());
		fizInfo.getZayavki().remove(zayavkaFI);
		fizInfoDao.saveOrUpdate(fizInfo);
	}
	
	public FizInfoDTO getDTO(Integer id, Integer konkursId) throws DaoException{
		FizInfo entity = baseDao.get(id);
		int vidFormaZId = konkursyDao.get(konkursId).getTipKonkursa().getVidFormaZ().getId();
		boolean young = vidFormaZId==2 || vidFormaZId==4;
		
		return new FizInfoDTO(entity, young);
	}

}
