package com.outmao.ebs.data.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.data.domain.JisuAreaDomain;
import com.outmao.ebs.data.domain.conver.JisuAreaVOConver;
import com.outmao.ebs.data.entity.QJisuArea;
import com.outmao.ebs.data.vo.JisuAreaVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JisuAreaDomainImpl extends BaseDomain implements JisuAreaDomain {

    private JisuAreaVOConver jisuAreaVOConver=new JisuAreaVOConver();

    @Override
    public List<JisuAreaVO> getJisuAreaVOList() {
        QJisuArea e=QJisuArea.jisuArea;
        List<JisuAreaVO> all=queryList(e,e.id.isNotNull(),jisuAreaVOConver);
        return toLevel(all);
    }

    private List<JisuAreaVO> toLevel(List<JisuAreaVO> all){

        Map<Long,JisuAreaVO> map=all.stream().collect(Collectors.toMap(t->t.getId(), t->t));

        List<JisuAreaVO> list=new ArrayList<>();

        for(JisuAreaVO vo:all){
            if(vo.getParentid()!=0){
                JisuAreaVO parent=map.get(vo.getParentid());
                if(parent.getChildren()==null){
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(vo);
            }else{
                list.add(vo);
            }
        }


        return list;
    }


}
