package com.outmao.ebs.wallet.domain.listener.impl;

import com.outmao.ebs.common.services.eventBus.ActionEventListener;
import com.outmao.ebs.message.common.constant.MessageConstant;
import com.outmao.ebs.message.dto.SendMessageByTypeDTO;
import com.outmao.ebs.message.service.MessageService;
import com.outmao.ebs.wallet.common.event.WalletTradeEvent;
import com.outmao.ebs.wallet.dao.TransferDao;
import com.outmao.ebs.wallet.domain.listener.WalletTradeEventListener;
import com.outmao.ebs.wallet.entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("WalletTradeEventListener")
public class WalletTradeEventListenerImpl extends ActionEventListener<WalletTradeEvent> implements WalletTradeEventListener {

    @Autowired
    private MessageService messageService;

    @Autowired
    private TransferDao transferDao;


    @Override
    public void onEvent(WalletTradeEvent event) {
        if(event.getActionKey()==null)
            return;
        List<Transfer> transfers=transferDao.findAllByActionKey(event.getActionKey());
        if(transfers.isEmpty())
            return;
        transfers.forEach(t->{
            sendMessage(t);
        });
    }


    private void sendMessage(Transfer t){
        if(t.getFrom()!=null&&t.getFromType()== Transfer.TransferType.Balance){
            List<Long> tos=new ArrayList<>();
            tos.add(t.getFrom().getUser().getId());
            Map<String,Object> data=new HashMap<>();
            data.put("amount",t.getAmount());
            data.put("balance",t.getFromBalance());
            SendMessageByTypeDTO dto=new SendMessageByTypeDTO();
            dto.setType(MessageConstant.message_type_transfer_out);
            dto.setTos(tos);
            dto.setData(data);
            messageService.sendMessageAsync(dto);
        }
        if(t.getTo()!=null&&t.getToType()==Transfer.TransferType.Balance){
            List<Long> tos=new ArrayList<>();
            tos.add(t.getTo().getUser().getId());
            Map<String,Object> data=new HashMap<>();
            data.put("amount",t.getAmount());
            data.put("balance",t.getToBalance());
            SendMessageByTypeDTO dto=new SendMessageByTypeDTO();
            dto.setType(MessageConstant.message_type_transfer_in);
            dto.setTos(tos);
            dto.setData(data);
            messageService.sendMessageAsync(dto);
        }
    }


}
