package com.zzjdyf.market.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.market.config.GoodsBrandConfig;
import com.zzjdyf.market.domain.dao.GoodsDao;
import com.zzjdyf.market.domain.dao.GoodsSkuDao;
import com.zzjdyf.market.domain.entity.ClsMarketEtcGoods;
import com.zzjdyf.market.domain.entity.ClsMarketEtcGoodsSku;
import com.zzjdyf.market.domain.entity.enums.PublishStatus;
import com.zzjdyf.market.service.DtoService;
import com.zzjdyf.market.vo.command.AddGoodsCommand;
import com.zzjdyf.market.vo.command.DeleteGoodsCommand;
import com.zzjdyf.market.vo.command.UpdateGoodsCommand;
import com.zzjdyf.market.vo.dto.GoodsAdminDto;
import com.zzjdyf.market.vo.dto.GoodsSkuAdminDto;
import com.zzjdyf.market.vo.query.GoodsPageQuery;
import com.zzjdyf.market.vo.query.IdQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 阿隆
 * Created on 2021/7/26 12:11 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class GoodsAdminService {
    private final GoodsBrandConfig goodsBrandConfig;
    private final GoodsDao goodsDao;
    private final GoodsSkuDao goodsSkuDao;

    /**
     * 获取商品品牌列表
     *
     * @return 商品品牌列表 {@link List<GoodsBrandConfig.Brand>}
     */
    public List<GoodsBrandConfig.Brand> brandList() {
        return goodsBrandConfig.getBrandList();
    }

    /**
     * 新增商品
     *
     * @param command {@link AddGoodsCommand}
     */
    public void add(AddGoodsCommand command) {
        // 保存商品信息
        ClsMarketEtcGoods goods = new ClsMarketEtcGoods();
        goods.setBrand(command.getBrand());
        goods.setModel(command.getModel());
        goods.setImages(goodsDao.objToJson(command.getImageList()));
        goods.setDetails(command.getDetails());
        goods.setStock(command.getStock());
        goods.setSales(0L);
        if (command.getSkuList().stream().noneMatch(t -> t.getStatus() == PublishStatus.ADDED)) {
            // 没有一个规格是上架状态,强制该商品下架
            goods.setStatus(PublishStatus.REMOVED);
        } else {
            goods.setStatus(command.getStatus());
        }
        goods = Optional.ofNullable(goodsDao.save(goods))
                .orElseThrow(() -> new RRException("新增商品失败"));
        // 保存商品规格
        for (AddGoodsCommand.Sku sku : command.getSkuList()) {
            ClsMarketEtcGoodsSku goodsSku = new ClsMarketEtcGoodsSku();
            goodsSku.setGoodsId(goods.getId());
            goodsSku.setQuantity(sku.getQuantity());
            goodsSku.setDeposit(sku.getDeposit());
            goodsSku.setReplRatio(sku.getReplRatio());
            goodsSku.setFullRepl(sku.getFullRepl());
            goodsSku.setStatus(sku.getStatus());
            goodsSkuDao.save(goodsSku);
        }
    }

    /**
     * 更新商品
     *
     * @param command {@link UpdateGoodsCommand}
     */
    public void update(UpdateGoodsCommand command) {
        ClsMarketEtcGoods goods = Optional.ofNullable(goodsDao.findOne(command.getId()))
                .orElseThrow(() -> new RRException("商品不存在"));
        goods.setBrand(command.getBrand());
        goods.setModel(command.getModel());
        goods.setImages(goodsDao.objToJson(command.getImageList()));
        goods.setDetails(command.getDetails());
        if (command.getSkuList().stream().noneMatch(t -> t.getStatus() == PublishStatus.ADDED)) {
            // 没有一个规格是上架状态,强制该商品下架
            goods.setStatus(PublishStatus.REMOVED);
        } else {
            goods.setStatus(command.getStatus());
        }
        goods = Optional.ofNullable(goodsDao.save(goods))
                .orElseThrow(() -> new RRException("更新商品失败"));
        // 获取商品既有规格列表
        List<ClsMarketEtcGoodsSku> skuList = goodsSkuDao.findAllByGoods(goods.getId());
        Map<Long, ClsMarketEtcGoodsSku> skuMap = skuList.stream().collect(Collectors.toMap(ClsMarketEtcGoodsSku::getId, Function.identity()));
        for (UpdateGoodsCommand.Sku sku : command.getSkuList()) {
            ClsMarketEtcGoodsSku goodsSku;
            if (sku.getId() != null && sku.getId() > 0) {
                goodsSku = skuMap.get(sku.getId());
                if (goodsSku == null) {
                    // 该商品规则不存在,跳过
                    continue;
                }
            } else {
                // 新建商品规格
                goodsSku = new ClsMarketEtcGoodsSku();
                goodsSku.setGoodsId(goods.getId());
            }
            // 更新规格信息
            goodsSku.setQuantity(sku.getQuantity());
            goodsSku.setDeposit(sku.getDeposit());
            goodsSku.setReplRatio(sku.getReplRatio());
            goodsSku.setFullRepl(sku.getFullRepl());
            goodsSku.setStatus(sku.getStatus());
            goodsSkuDao.save(goodsSku);
        }
        if (command.getStockIncrease() != 0) {
            // 库存有变化
            if (goodsDao.getMapper().updateStock(goods.getId(), command.getStockIncrease()) != 1) {
                throw new RRException("更新商品库存失败");
            }
            // 重新加载商品信息到缓存
            goodsDao.reloadToCache(goods.getId());
        }
    }

    /**
     * 根据ID查询商品
     *
     * @param query 条件 {@link IdQuery}
     * @return 商品  {@link GoodsAdminDto}
     */
    public GoodsAdminDto get(IdQuery query) {
        ClsMarketEtcGoods goods = Optional.ofNullable(goodsDao.findOne(query.getId()))
                .orElseThrow(() -> new RRException("商品不存在"));
        return toDto(goods);
    }

    /**
     * 分页查询商品
     *
     * @param query 条件 {@link GoodsPageQuery}
     * @return 商品列表  {@link IPage<GoodsAdminDto>}
     */
    public IPage<GoodsAdminDto> query(GoodsPageQuery query) {
        LambdaQueryWrapper<ClsMarketEtcGoods> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(query.getBrand())) {
            queryWrapper.eq(ClsMarketEtcGoods::getBrand, query.getBrand());
        }
        if (StringUtils.hasText(query.getModel())) {
            queryWrapper.eq(ClsMarketEtcGoods::getModel, query.getModel());
        }
        if (query.getStatus() != null) {
            queryWrapper.eq(ClsMarketEtcGoods::getStatus, query.getStatus());
        }
        queryWrapper.orderByDesc(ClsMarketEtcGoods::getCreateTime);
        IPage<ClsMarketEtcGoods> page = new Page<>(query.getPageNum(), query.getPageSize());
        page = goodsDao.selectPage(page, queryWrapper);
        List<GoodsAdminDto> list = page.getRecords().stream().map(this::toDto).collect(Collectors.toList());
        return new Page<GoodsAdminDto>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(list);
    }

    /**
     * 删除商品
     *
     * @param command {@link DeleteGoodsCommand}
     */
    public void delete(DeleteGoodsCommand command) {
        ClsMarketEtcGoods goods = Optional.ofNullable(goodsDao.findOne(command.getId()))
                .orElseThrow(() -> new RRException("商品不存在"));
        if (goods.getSales() > 0) {
            throw new RRException("该商品已有销量,禁止删除");
        }
        // 删除商品规格信息
        List<ClsMarketEtcGoodsSku> skuList = goodsSkuDao.findAllByGoods(goods.getId());
        skuList.forEach(goodsSkuDao::delete);
        // 删除商品信息
        goodsDao.delete(goods);
    }

    private GoodsAdminDto toDto(ClsMarketEtcGoods goods) {
        GoodsAdminDto dto = DtoService.INSTANCE.toDto(goods);
        // 获取商品既有规格列表
        List<ClsMarketEtcGoodsSku> skuList = goodsSkuDao.findAllByGoods(goods.getId());
        dto.setSkuList(skuList.stream().map(DtoService.INSTANCE::toDto).collect(Collectors.toList()));
        return dto;
    }

    /**
     * 获取商品规格列表
     *
     * @return 商品规格列表 {@link GoodsSkuAdminDto}
     */
    public GoodsSkuAdminDto getSku(IdQuery query) {
        ClsMarketEtcGoodsSku goodsSku = Optional.ofNullable(goodsSkuDao.findOne(query.getId()))
                .orElseThrow(() -> new RRException("商品规格不存在"));
        ClsMarketEtcGoods goods = Optional.ofNullable(goodsDao.findOne(goodsSku.getGoodsId()))
                .orElseThrow(() -> new RRException("商品规格关联的商品不存在"));
        return DtoService.INSTANCE.toDto(goodsSku);
    }
}
