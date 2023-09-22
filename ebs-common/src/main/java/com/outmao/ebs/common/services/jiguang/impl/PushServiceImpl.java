package com.outmao.ebs.common.services.jiguang.impl;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.outmao.ebs.common.services.jiguang.PushService;
import com.outmao.ebs.common.services.jiguang.configuration.JiguangProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/*
 * https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push/#notification
 * 
 * */
@Slf4j
@Service
public class PushServiceImpl implements PushService {


	@Autowired
	private JiguangProperties jiguangProperties;


	@Override
	public void pushAll(String alert, Map<String, String> extras,int badge) {
		PushPayload payload = this.buildPushObject_platformAll_all_alert(alert, extras,badge);
		this.sendPush(payload);
	}

	@Override
	public void pushToAlias(String[] alias, String alert, Map<String, String> extras,int badge) {
		PushPayload payload = this.buildPushObject_platformAll_alias_alert(alias, alert, extras,badge);
		this.sendPush(payload);
	}

	@Override
	public void pushToTags(String[] tags, String alert, Map<String, String> extras,int badge) {
		PushPayload payload = this.buildPushObject_platformAll_tags_alert(tags, alert, extras,badge);
		this.sendPush(payload);
	}

	@Override
	public void pushToTagsAnd(String[] tags, String[] tags2, String alert, Map<String, String> extras,int badge) {
		PushPayload payload = this.buildPushObject_platformAll_tagsAnd_alert(tags, tags2, alert, extras,badge);
		this.sendPush(payload);
	}

	public PushResult sendPush(PushPayload payload) {
		try {
			JPushClient jpushClient = new JPushClient("${jiguang.mastersecret}", "${jiguang.appkey}", null,
					ClientConfig.getInstance());
			PushResult result = jpushClient.sendPush(payload);
			jpushClient.close();
			if (!result.isResultOK()) {
				log.error("推送返回结果出错", result.error);
			}
			return result;
		} catch (APIConnectionException e) {
			log.error("推送出错", e);
		} catch (APIRequestException e) {
			log.error("推送出错", e);
		}
		return null;
	}

	public PushPayload buildPushObject_platformAll_all_alert(String alert, Map<String, String> extras,int badge) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all())
				.setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(badge)
                                .addExtras(extras)
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(alert)
                                .addExtras(extras)
                                .build())
                        .build())
				.setOptions(Options.newBuilder().setApnsProduction(jiguangProperties.isProduction()).build()).build();
	}

	public PushPayload buildPushObject_platformAll_alias_alert(String[] alias, String alert,
			Map<String, String> extras,int badge) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias))
				.setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(badge)
                                .addExtras(extras)
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(alert)
                                .addExtras(extras)
                                .build())
                        .build())
				.setOptions(Options.newBuilder().setApnsProduction(jiguangProperties.isProduction()).build()).build();
	}

	public PushPayload buildPushObject_platformAll_tags_alert(String[] tags, String alert, Map<String, String> extras,int badge) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.tag(tags))
				.setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(badge)
                                .addExtras(extras)
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(alert)
                                .addExtras(extras)
                                .build())
                        .build())
				.setOptions(Options.newBuilder().setApnsProduction(jiguangProperties.isProduction()).build()).build();
	}

	public PushPayload buildPushObject_platformAll_tagsAnd_alert(String[] tags, String[] tags2, String alert,
			Map<String, String> extras,int badge) {
		return PushPayload.newBuilder().setPlatform(Platform.all())
				.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.tag(tags))
						.addAudienceTarget(AudienceTarget.tag(tags2)).build())
				.setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(badge)
                                .addExtras(extras)
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(alert)
                                .addExtras(extras)
                                .build())
                        .build())
				.setOptions(Options.newBuilder().setApnsProduction(jiguangProperties.isProduction()).build()).build();
	}

}
