package com.preauth.shared.model;

import java.util.List;
import java.util.Map;

public interface IMemberRepository {
	List<Member> findActiveMember(CoreSearchCriteria input);	
}
