package com.preauth.shared.model;
import java.util.*;
import java.util.function.Function;

import org.springframework.stereotype.Service;

@FunctionalInterface
public interface Func<T1,T2,T3,R> {	
	public R apply(T1 input, T2 taskRequest, T3 dependentData);
}
