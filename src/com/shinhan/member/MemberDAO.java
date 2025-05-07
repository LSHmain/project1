package com.shinhan.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.shinhan.farmer.DBUtil;
import com.shinhan.farmer.FarmerDTO;

public class MemberDAO {
	
	
	public static int SimpleDelete(MemberDTO mem) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				UPDATE SIMPLE_FARMINFO
				SET MEM_COUNT = MEM_COUNT-1
				WHERE FARMER_ID = ?;
				""";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, mem.getFarmer_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
		}finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		return result;
	}
	
	public static int SimpleUpdate(MemberDTO mem) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				UPDATE SIMPLE_FARMINFO
				SET MEM_COUNT = MEM_COUNT+1
				WHERE SIMPLE_FARMINFO.FARMER_ID = ?
				""";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, mem.getFarmer_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
		}finally {
			DBUtil.dbDisConnect(conn, st, null);
		}
		return result;
	}
	
	public static int MemDelete(MemberDTO mem) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				DELETE FROM MEMBER_INFO
				WHERE FARMER_ID = (
				    SELECT
				        F.FARMER_ID
				    FROM
				        FARMER F
				        INNER JOIN DEPOSIT D
				            ON F.FARMER_ID = D.FARMER_ID
				    WHERE
				        F.FARMER_ID = ?
				        AND F.FARMER_ID = ?
				)
				AND MEMBER_INFO.MEM_ID = ?
				""";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, mem.getFarmer_id());
			st.setString(2, mem.getFarmer_password());
			st.setString(3, mem.getMem_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static int MemberInsert(MemberDTO mem) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				INSERT INTO MEMBER_INFO (
				MEM_ID
				,FARMER_ID
				,MEM_NAME
				,BASIC_TARGET
				,FARM_DATE
				,FARM_AREA
				,FARM_EARNINGS
				,NONFARM_EARNINGS
				,ANIMAL_EARNINGS
				,FACILITY_EARNINGS)
				VALUES (?,?,?,?,?,?,?,?,?,?)
				""";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, mem.getMem_id());
			st.setString(2, mem.getFarmer_id());
			st.setString(3, mem.getMem_name());
			st.setInt(4, mem.getBasic_target());
			st.setInt(5, mem.getFarm_date());
			st.setInt(6, mem.getFarm_area());
			st.setInt(7, mem.getFarm_earnings());
			st.setInt(8, mem.getNonfarm_earnings());
			st.setInt(9, mem.getAnimal_earnings());
			st.setInt(10, mem.getFacility_earnings());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static int InfoUpdate(MemberDTO mem) {
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
			st.setString(1, mem.getFarmer_id());
			st.setString(2, mem.getFarmer_id());
			st.setString(3, mem.getFarmer_password());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
