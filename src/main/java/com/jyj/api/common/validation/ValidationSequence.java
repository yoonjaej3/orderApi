package com.jyj.api.common.validation;


import com.jyj.api.common.validation.group.MinMaxGroup;
import com.jyj.api.common.validation.group.NotEmptyGroup;
import com.jyj.api.common.validation.group.PatternCheckGroup;
import com.jyj.api.common.validation.group.PositiveGroup;

import javax.validation.GroupSequence;

@GroupSequence({NotEmptyGroup.class, PositiveGroup.class, MinMaxGroup.class, PatternCheckGroup.class})
public interface ValidationSequence {
}
