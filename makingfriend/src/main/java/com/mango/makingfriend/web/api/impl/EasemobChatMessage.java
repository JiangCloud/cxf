package com.mango.makingfriend.web.api.impl;


import com.mango.makingfriend.web.api.ChatMessageAPI;
import com.mango.makingfriend.web.api.comm.EasemobAPI;
import com.mango.makingfriend.web.api.comm.OrgInfo;
import com.mango.makingfriend.web.api.comm.ResponseHandler;
import com.mango.makingfriend.web.api.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.ChatHistoryApi;
import org.springframework.stereotype.Service;


@Service
public class EasemobChatMessage  implements ChatMessageAPI {

    private ResponseHandler responseHandler = new ResponseHandler();
    private ChatHistoryApi api = new ChatHistoryApi();

    @Override
    public Object exportChatMessages(final Long limit,final String cursor,final String query) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatmessagesGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME, TokenUtil.getAccessToken(),query,limit+"",cursor);
            }
        });
    }

    @Override
    public Object exportChatMessages(final String timeStr) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatmessagesTimeGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),timeStr);
            }
        });
    }
}
