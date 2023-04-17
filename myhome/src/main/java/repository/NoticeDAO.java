package repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Notice;

public class NoticeDAO {

	private SqlSessionFactory factory;
	
	private static NoticeDAO dao = new NoticeDAO();
	private NoticeDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static NoticeDAO getInstance() {
		return dao;
	}
	
	private final String MAPPER = "mybatis.mapper.notice.";
	
	public int selectAllNoticesCnt() {
		SqlSession ss = factory.openSession();
		int cnt = ss.selectOne(MAPPER + "selectAllNoticesCnt");
		ss.close();
		return cnt;
	}
	
	public List<Notice> selectAllNotices(Map<String, Object> map){
		SqlSession ss = factory.openSession();
		List<Notice> notices = ss.selectList(MAPPER + "selectAllNotices", map);
		ss.close();
		return notices;
	}
	
}
