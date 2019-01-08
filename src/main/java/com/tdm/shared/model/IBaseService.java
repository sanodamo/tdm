package com.preauth.shared.model;
import java.util.*;
import java.util.function.Function;

import org.springframework.stereotype.Service;

@Service
public interface IBaseService<T1,T2,T3, R> {	
	boolean Register(Map<String, Func<T1,T2,T3, R>> registery);	
}
