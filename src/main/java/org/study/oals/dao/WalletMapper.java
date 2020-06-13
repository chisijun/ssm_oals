package org.study.oals.dao;

import org.study.oals.model.domain.Wallet;
import org.study.oals.model.dto.WalletQueryDto;
import org.study.oals.model.vo.WalletVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface WalletMapper extends Mapper<Wallet> {

    /**
     * 查询个人钱包
     *
     * @param id 钱包id
     *
     * @return the json result
     */
    WalletVo showById(Long id);

    /**
     * 查询钱包列表 - 分页
     *
     * @param walletQueryDto 查询条件
     *
     * @return the json result.
     */
    List<WalletVo> queryListWithPage(WalletQueryDto walletQueryDto);
}