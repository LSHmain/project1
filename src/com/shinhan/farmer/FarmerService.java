package com.shinhan.farmer;

import java.util.List;
import java.util.Map;

import com.shinhan.member.MemberDTO;

public class FarmerService {
	
	public static int SimpleDelete(FarmerDTO farm) {
		return FarmerDAO.SimpleDelete(farm);
	}
	
	public static int SimpleUpdate(FarmerDTO farm) {
		return FarmerDAO.SimpleUpdate(farm);
	}
	public static int updateFarmCheck(FarmerDTO total) {
		return FarmerDAO.updateFarmCheck(total);
	}
	public static List<FarmerDTO> farmerDetailInfo(String total) {
		return FarmerDAO.farmerDetailInfo(total);
	}
	public static Map<String, String> farmerInfo(FarmerDTO total) {
		return FarmerDAO.farmerInfo(total);
	}
	public static int farmerDetailDelete(FarmerDTO farm) {
		return FarmerDAO.farmerDetailDelete(farm);
	}
	
	
	public static int farmerDelete(FarmerDTO farm) {
		return FarmerDAO.farmerDelete(farm);
	}
	
	public static int depositDelete(FarmerDTO farm) {
		return FarmerDAO.depositDelete(farm);
	}
	
	public static int memDelete(FarmerDTO farm) {
		return FarmerDAO.memDelete(farm);
	}
	public static Map<String, String> CountMoney(FarmerDTO farm) {
		return FarmerDAO.CountMoney(farm);	
	}
	
	public static Map<String, String> InfoDetailSelect(FarmerDTO farm) {
		return FarmerDAO.InfoDetailSelect(farm);
	}

	public static Map<String, String> InfoSelect(FarmerDTO farm) {
		return FarmerDAO.InfoSelect(farm);
	}
	
	public static int farmInsert(FarmerDTO farmerDTO) {
		return FarmerDAO.farmInsert(farmerDTO);
	}

	
	public static int farmDetailInsert(FarmerDTO farm) {
		return FarmerDAO.farmDetailInsert(farm);
	}
	
	public static int InfoUpdate(FarmerDTO farm) {
		return FarmerDAO.InfoUpdate(farm);
	}
}
