package com.preauth.repository;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.preauth.shared.model.CoreSearchCriteria;
import com.preauth.shared.model.IMemberRepository;
import com.preauth.shared.model.Member;
import com.preauth.shared.model.MemberSearchOptions;

@Repository
public class MemberRepository extends BaseRepository implements IMemberRepository {

	RowMapper<Member> rowMapper = new BeanPropertyRowMapper<Member>(Member.class);
	
	@Override
	@Transactional(readOnly = true)
	public List<Member> findActiveMember(CoreSearchCriteria Input) {

		MemberSearchOptions options = new MemberSearchOptions();
		options.setDataLoaded(true);
		
				
		String memberQuery = BuildAnyMemberQuery(Input,options);
		List<Member> activeMemberList = ExecuteAnyMemberQuery(Input.getClientId(), memberQuery);		
		
		return GlobalMemberSearch(Input, activeMemberList);
	}
	
	private List<Member> GlobalMemberSearch(CoreSearchCriteria Input, List<Member> activeMemberList){
		String memberIds = "";

		for (Member member : activeMemberList) {
			memberIds = memberIds + member.getId() + ",";
		}

		if (!StringUtils.isEmpty(memberIds)) {
			memberIds = memberIds.substring(0, memberIds.length() - 1);
			String memberSearch = "select top 10 mbr_id as Id, sub_ssn as SubSsn, first_name as FirstName,last_name as LastName, dob  from ssearch..members where client_id="
					+ Input.getClientId() + " and  mbr_id in (" + memberIds + ")";
			List<Member> memberList = jdbc.query(memberSearch, rowMapper);
			
			Predicate<Member> funcMemberExists = 
				    c -> activeMemberList.stream().anyMatch(u -> u.getId()==c.getId());

		    List<Member> finalMemberList = memberList.stream()
				              .filter(funcMemberExists)
				              .collect(Collectors.toList());
		    return finalMemberList;
		}
		
		return null;
	}
	
	private String BuildAnyMemberQuery(CoreSearchCriteria Input, MemberSearchOptions options  ) {
				
		String memberInstance = null;
		switch (Integer.parseInt(Input.getClientId())) {
		case 85:
			memberInstance = "anthem";
			break;
		case 86:
			memberInstance = "bcbsva";
			break;
		case 183:
			memberInstance = "bcbsga";
			break;
		case 184:					
		case 185:			
		case 186:
			memberInstance = "wlpwest";
			break;
		case 187:
		case 188:
		case 189:
			memberInstance = "wlpne";
			break;
		case 199:
			memberInstance = "empire";
			break;
		case 203:
			memberInstance = "unicare";
			break;
		case 210:			
		case 212:
			memberInstance = "amerigroup";
			break;			
		case 171:
			memberInstance = "bcbsmi";
			break;
		case 176:
			memberInstance = "bcbsla";
			break;
		case 182:
		case 216:
			memberInstance = "flblue";
			break;
		case 190:					
		case 192:
			memberInstance = "premera";
			break;
		case 214:
			memberInstance = "bcbsal";
			break;				
		}
		
		String filter = "";
		
		if(options.isDataLoaded()) {
			filter = filter + "and m.manual != 'Y' ";
		}
		
		if(Input.getProduct().equals("rad")) {
			filter = filter + "and e.rad_therapy_cover_ind !='N'";
		}
		
		if(Input.getProduct().equals("onc")) {
			filter = filter + "and e.oncology_cover_ind != 'N'";
		}
		
		if(options.isFullyFunded()) {
			filter = filter + "and e.srx_funding_type='FULL'";
		}
		
		if(options.isPCCAEnabled()) {
			filter = filter + "and e.base_id=3";
		}
		
		String query = " select top 20 'RAD' as product,m.mbr_id as Id, m.client_mbr_code as SubSsn, m.first_name as FirstName,m.last_name as LastName, m.dob "
				+ " from " + memberInstance +"..members m inner join  " + memberInstance + "..enrollment e on m.mbr_id = e.mbr_id and "
						+ " e.thru_date > getdate() and m.client_id=" +  Input.getClientId() + " " + filter + " order by m.mbr_id asc" ;				
					
		return query;
	}

	private List<Member> ExecuteAnyMemberQuery(String clientId, String query) {
		
		List<Member> memberList = null;

		switch (Integer.parseInt(clientId)) {
		case 85:
		case 86:
		case 183:
		case 184:
		case 185:
		case 186:
		case 187:
		case 188:
		case 189:
		case 199:
		case 203:
		case 210:
		case 212:
			memberList = AimJdbc.query(query, rowMapper);
			break;
		case 171:
		case 176:
		case 182:
		case 190:
		case 192:
		case 216:
			memberList = AnthemJdbc.query(query, rowMapper);
			break;
		}

		return memberList;
	}

}
