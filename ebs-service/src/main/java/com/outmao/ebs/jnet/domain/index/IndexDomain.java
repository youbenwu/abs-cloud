package com.outmao.ebs.jnet.domain.index;

import com.outmao.ebs.jnet.vo.index.BannerVO;
import com.outmao.ebs.jnet.vo.index.FunctionVO;
import com.outmao.ebs.jnet.vo.index.IndexVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 首页模块
 * @author yeyi
 * @date 2019/9/23 16:32
 **/
public interface IndexDomain {
	/**
	 * 首页
	 * @author yeyi
	 * @date 2019年10月19日
	 */
	IndexVO getIndex();
    /**
     * 功能模块列表
     * @author yeyi
     * @date 2019/9/23 16:40
     **/
    Page<FunctionVO> getFunctionList(Pageable pageable);

    /**
     * 随机增加功能模块使用数
     * @author yeyi
     * @date 2019/9/24 12:16
     **/
    void randomAddFunctionUseCount();
    
    Page<BannerVO> getBannerList(Pageable pageable);
}
