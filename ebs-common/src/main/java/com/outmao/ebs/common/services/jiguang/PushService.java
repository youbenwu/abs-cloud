package com.outmao.ebs.common.services.jiguang;

import java.util.Map;

public interface PushService {
	
	public void pushAll(String alert, Map<String, String> extras, int badge);

	public void pushToAlias(String[] alias, String alert, Map<String, String> extras, int badge);

	public void pushToTags(String[] tags, String alert, Map<String, String> extras, int badge);

	public void pushToTagsAnd(String[] tags, String[] tags2, String alert, Map<String, String> extras, int badge);

}
