package com.luminiadev.translation.api.parameter;

import java.util.Map;

public interface KeyedTrParameters extends TrParameters {
    Map<String, Object> getParameters();
}
