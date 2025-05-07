package com.shinhan.farmer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.shinhan.member.MemberDTO;


public class FarmerDAO {
	//농민인지 확인하기
	public static int updateFarmCheck(FarmerDTO total) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement st = null;
		String sql = """
				UPDATE DEPOSIT
				SET FARMER_CHECK = '농민'
				WHERE DEPOSIT.FARMER_ID = (
					SELECT
						DISTINCT FARMER_ID
					FROM
						FARMER_DETAIL F
					WHERE AMOUNT > (
				   		SELECT FARM_TARGET
				   		FROM FARM_INFO
				   		WHERE FARM_TYPE = F.FARM_TYPE
				)
				AND FARMER_ID = ?
				)
				""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, total.getFarmer_id());
			rs = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		return result;
		
	}
	
	public static List<FarmerDTO> farmerDetailInfo(String farm) {
		List<FarmerDTO> farmlist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement st = null;
		String sql = """
				SELECT
				    FARM_TYPE
				    ,AMOUNT
				FROM
				    FARMER_DETAIL
				WHERE
				    FARMER_ID = ?
				""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, farm);
			rs = st.executeQuery();
			while (rs.next()) {
				FarmerDTO emp = FarmerDTO.builder()
					    .farm_type(rs.getString("farm_type"))
					    .amount(rs.getInt("amount"))
					    .build();
				farmlist.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbDisConnect(conn, st, rs);
		}
	
		return farmlist;
		
	}
	
	public static Map<String, String> farmerInfo(FarmerDTO total) {
		Map<String,String> farmlist = new HashMap<>();
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement st = null;
		String sql = """
				SELECT
				    FARMER_NAME
				    ,FARMER_DATE
				    ,FARM_EARNINGS
				    ,NONFARM_EARNINGS
				    ,ANIMAL_EARNINGS
				    ,FACILITY_EARNINGS
				FROM
				    FARMER
				WHERE
				    FARMER_ID = ?
				    AND FARMER_PASSWORD = ?
				""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, total.getFarmer_id());
			st.setString(2, total.getFarmer_password());
			rs = st.executeQuery();
			while(rs.next()) {
				farmlist.put("이름", rs.getString("farmer_name"));
				farmlist.put("농사기간", rs.getString("farmer_date"));
				farmlist.put("농업소득", rs.getString("farm_earnings"));
				farmlist.put("농업외소득", rs.getString("nonfarm_earnings"));
				farmlist.put("축산소득", rs.getString("animal_earnings"));
				farmlist.put("시설소득", rs.getString("facility_earnings"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		return farmlist;
		
	}
	
	
	
	public static int farmerDetailDelete(FarmerDTO farm) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				DELETE FROM FARMER_DETAIL
				WHERE FARMER_ID = (
				    SELECT
				        FARMER_ID
				    FROM
				        FARMER
				    WHERE
				        FARMER_ID = ?
				)
				AND FARM_TYPE = ?
				""";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, farm.getFarmer_id());
			st.setString(2, farm.getFarm_type());
			result = st.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
		}finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		
		return result;
	}
	
	
	public static int farmerDelete(FarmerDTO farm) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				DELETE FROM FARMER
				WHERE FARMER_ID = (
				    SELECT
				        FARMER_ID
				    FROM
				        FARMER
				    WHERE
				        FARMER_ID = ?
				        AND FARMER_PASSWORD = ?
				)
				""";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, farm.getFarmer_id());
			st.setString(2, farm.getFarmer_password());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		
		return result;
	}
	
	public static int depositDelete(FarmerDTO farm) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				DELETE FROM DEPOSIT
				WHERE FARMER_ID = (
				    SELECT
				        FARMER_ID
				    FROM
				        FARMER
				    WHERE
				        FARMER_ID = ?
				        AND FARMER_PASSWORD = ?
				)
				""";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, farm.getFarmer_id());
			st.setString(2, farm.getFarmer_password());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		
		return result;
	}
	
	public static int memDelete(FarmerDTO farm) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				DELETE FROM MEMBER_INFO
				WHERE FARMER_ID = (
				    SELECT
				        FARMER_ID
				    FROM
				        FARMER
				    WHERE
				        FARMER_ID = ?
				        AND FARMER_PASSWORD = ?
				)
				""";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, farm.getFarmer_id());
			st.setString(2, farm.getFarmer_password());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		
		return result;
	}
	
	
	public static Map<String, String> CountMoney(FarmerDTO farm) {
		Map<String,String> farmlist = new HashMap<>();
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement st = null;
		String sql = """
				SELECT
				 CASE
				    WHEN F.면적 > 6
				        THEN 215*2+207*2+198*2+(면적-6)*198
				    WHEN F.면적 > 2
				        THEN 215*2+(면적-2)*207
				    WHEN F.면적 > 0 
				        THEN F.면적*215
				    ELSE 0
				    END AS "면적직불금금액"
				FROM
				    (SELECT
				        SUM(AMOUNT)/10000 AS 면적
				    FROM
				        FARMER_DETAIL
					WHERE
				    	FARM_TYPE IN ('논','밭')
				    	AND FARMER_ID = ?
					GROUP BY
				    	FARMER_ID) F
				""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, farm.getFarmer_id());
//			st.setString(2, farm.getFarmer_password());
			rs = st.executeQuery();
			
			while(rs.next()) {
				farmlist.put("멱적직불금 최대 수령 금액", rs.getString("면적직불금금액"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		
		return farmlist;
	}
	
	
	public static Map<String, String> InfoDetailSelect(FarmerDTO farm) {
		Map<String,String> farmlist = new HashMap<>();
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement st = null;
		String sql = """
				SELECT
				    FARMER_ID
				    ,NONFARM_INFO	
					,FARMDATE_INFO
					,AREA_INFO
					,ANIMAL_INFO
					,FACILITY_INFO
					,TOTALMON_INFO	
				FROM
				    DEPOSIT
				WHERE
				    FARMER_ID = ?
				""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, farm.getFarmer_id());
			rs = st.executeQuery();
			while(rs.next()) {
				farmlist.put("아이디", rs.getString("farmer_id"));
				farmlist.put("농업외소득", rs.getString("nonfarm_info"));
				farmlist.put("농사기간", rs.getString("farmdate_info"));
				farmlist.put("면적", rs.getString("area_info"));
				farmlist.put("축산소득",rs.getString("animal_info"));
				farmlist.put("시설소득", rs.getString("facility_info"));
				farmlist.put("전체소득", rs.getString("totalmon_info"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		return farmlist;
	}
	
	public static Map<String, String> InfoSelect(FarmerDTO farm) {
		Map<String,String> farmlist = new HashMap<>();
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement st = null;
		String sql = """
				SELECT
				    FARMER_CHECK
				    ,DEPOSIT_TYPE
				FROM
				    DEPOSIT
				WHERE
				    FARMER_ID = ?
				""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, farm.getFarmer_id());
//			st.setString(2, farm.getFarmer_password());
			rs = st.executeQuery();
			while(rs.next()) {
				farmlist.put("농업인여부", rs.getString("farmer_check"));
				farmlist.put("직불금종류", rs.getString("deposit_type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		return farmlist;
		
	}
	
	public static int InfoUpdate(FarmerDTO farm) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				UPDATE DEPOSIT
				SET FARMER_ID = ?
				WHERE FARMER_ID = (
				    SELECT
				        F.FARMER_ID
				    FROM
				        FARMER F
				        INNER JOIN DEPOSIT D
				            ON F.FARMER_ID = D.FARMER_ID
				    WHERE
				        F.FARMER_ID = ?
				        AND F.FARMER_PASSWORD = ?
				)
				""";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, farm.getFarmer_id());
			st.setString(2, farm.getFarmer_id());
			st.setString(3, farm.getFarmer_password());
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("**직불금은 본인 소유의 논과 밭을 가진사람만 정보를 확인할 수 있습니다.**");
		}finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		return result;
	}
	
	
	
	public static int SimpleDelete(FarmerDTO farm) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				UPDATE SIMPLE_FARMINFO
				SET FARM_COUNT = FARM_COUNT-1
				WHERE FARMER_ID = ?;
				""";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, farm.getFarmer_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
		}finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		return result;
	}
	
	public static int SimpleUpdate(FarmerDTO farm) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				UPDATE SIMPLE_FARMINFO
				SET FARM_COUNT = FARM_COUNT+1
				WHERE SIMPLE_FARMINFO.FARMER_ID = ?
				""";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, farm.getFarmer_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
		}finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		return result;
	}
	
	public static int farmInsert(FarmerDTO farm) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				INSERT INTO FARMER(
				FARMER_ID
				,FARMER_PASSWORD
				,FARMER_NAME
				,FARM_EARNINGS
				,NONFARM_EARNINGS
				,FARMER_DATE
				,ANIMAL_EARNINGS
				,FACILITY_EARNINGS)
				VALUES(?,?,?,?,?,?,?,?)
				""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, farm.getFarmer_id());
			st.setString(2,farm.getFarmer_password());
			st.setString(3, farm.getFarmer_name());
			st.setInt(4, farm.getFarm_earnings());
			st.setInt(5, farm.getNonfarm_earnings());
			st.setInt(6, farm.getFarmer_date());
			st.setInt(7, farm.getAnimal_earnings());
			st.setInt(8, farm.getFacility_earnings());
			result = st.executeUpdate();
		}catch(SQLException e) {
			System.out.println("중복된 아이디 입니다.");
		}finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		return result;
	}
	
	public static int farmDetailInsert(FarmerDTO farm) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				
				INSERT INTO FARMER_DETAIL VALUES (?,?,?,?)
				
					 
				""";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, farm.getFarmer_id());
			st.setString(2, farm.getFarmer_password());
			st.setString(3, farm.getFarm_type());
			st.setInt(4, farm.getAmount());
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		return result;
	}
//	
//	DECLARE
//	농민아이디 VARCHAR2(20);
//BEGIN
//	SELECT
//		FARMER_ID
//	INTO
//	농민아이디
//	FROM
//		FARMER
//	WHERE
//		FARMER_ID = ?
//		AND FARMER_PASSWORD = ?;
//IF 농민아이디 = ? THEN
//	INSERT INTO FARMER_DETAIL VALUES (?,?,?,?);
//END IF;
//END;
//	
//	st.setString(3, farm.getFarmer_id());
//	st.setString(4, farm.getFarmer_id());
//	st.setString(5, farm.getFarmer_password());
}
