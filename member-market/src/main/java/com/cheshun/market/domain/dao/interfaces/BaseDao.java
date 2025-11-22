package com.cheshun.market.domain.dao.interfaces;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.cheshun.market.domain.entity.BaseEntity;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
public interface BaseDao<T extends BaseEntity> extends IDao<T> {
    Logger LOGGER = LoggerFactory.getLogger(BaseDao.class);

    /**
     * 获取 mapper
     *
     * @return mapper
     */
    BaseMapper<T> getMapper();

    /**
     * 获取 ICache
     *
     * @return ICache
     */
    default ICache<T> getCache() {
        return null;
    }

    /**
     * 是否是假删除
     *
     * @return boolean
     */
    default boolean isFakeDel() {
        return false;
    }

    /**
     * 构造函数执行完之后进行初始化
     * 解决Mybatis LambdaQueryWrapper查询的问题
     */
    default void init() {
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), BaseEntity.class);
    }

    /**
     * 保存
     *
     * @param entity 数据
     * @return 数据(带id)
     */
    @Override
    default T save(T entity) {
        return save(entity, true);
    }

    /**
     * 保存
     *
     * @param entity      数据
     * @param saveToCache 是否保存到缓存
     * @return 数据(带id)
     */
    default T save(T entity, boolean saveToCache) {
        Date nowDate = new Date();
        entity.setUpdateTime(nowDate);
        if (entity.getId() == null || entity.getCreateTime() == null) {
            entity.setCreateTime(nowDate);
            if (getMapper().insert(entity) == 1) {
                if (saveToCache && getCache() != null) {
                    getCache().saveToCache(entity);
                }
                return entity;
            }
        } else {
            getMapper().updateById(entity);
            if (saveToCache && getCache() != null) {
                getCache().saveToCache(entity);
            }
            return entity;
        }
        return null;
    }

    /**
     * 根据id查询单条数据
     *
     * @param id id
     * @return 单条数据
     */
    default T findOne(Long id) {
        if (id <= 0) {
            return null;
        }
        if (getCache() != null) {
            String cacheKey = getCache().getDefaultCacheKeyPrefix() + id;
            T entity = getCache().readFromCache(cacheKey, getCache().getEntityClass());
            if (entity == null) {
                entity = reloadToCache(id);
            }
            return Optional.ofNullable(entity).filter(t -> t.getId() != null).orElse(null);
        }
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(T::getId, id);
        T entity = getMapper().selectOne(wrapper);
        return Optional.ofNullable(entity).filter(t -> t.getId() != null).orElse(null);
    }

    /**
     * 根据id查询单条数据
     *
     * @param id id
     * @return 单条数据
     */
    default T findOneNew(Long id) {
        if (id <= 0) {
            return null;
        }
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(T::getId, id);
        T entity = getMapper().selectOne(wrapper);
        return Optional.ofNullable(entity).filter(t -> t.getId() != null).orElse(null);
    }

    /**
     * 重新加载到缓存
     *
     * @param id 唯一标识
     * @return 缓存中的数据
     */
    default T reloadToCache(Long id) {
        T entity = null;
        if (id != null && id > 0 && getCache() != null) {
            LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(T::getId, id);
            try (HintManager hintManager = HintManager.getInstance()) {
                // 从主库查询
                hintManager.setPrimaryRouteOnly();
                entity = getMapper().selectOne(wrapper);
            } catch (Exception e) {
                LOGGER.error("重新加载到缓存异常", e);
            }
            if (entity != null && entity.getId() != null) {
                getCache().saveToCache(entity);
            }
        }
        return Optional.ofNullable(entity).filter(t -> t.getId() != null).orElse(null);
    }

    /**
     * 删除
     *
     * @param entity 数据
     */
    default void delete(T entity) {
        if (!isFakeDel()) {
            // 真删除
            getMapper().deleteById(entity.getId());
            if (getCache() != null) {
                getCache().deleteFromCache(entity);
            }
        } else {
            // 假删除
            try {
                Method method = entity.getClass().getDeclaredMethod("setDel", Boolean.class);
                method.invoke(entity, true);
                method = entity.getClass().getDeclaredMethod("setDelTime", Date.class);
                method.invoke(entity, new Date());
            } catch (Exception e) {
                LOGGER.error("假删除失败:" + entity, e);
            }
            save(entity);
        }
    }

    /**
     * 根据指定参数分页查询
     *
     * @param page         分页参数
     * @param queryWrapper 指定参数
     * @return 查询结果
     */
    default IPage<T> selectPage(IPage<T> page, Wrapper<T> queryWrapper) {
        return getMapper().selectPage(page, queryWrapper);
    }

    /**
     * 根据指定参数查询
     *
     * @param queryWrapper 指定参数
     * @return 查询结果
     */
    default List<T> selectList(Wrapper<T> queryWrapper) {
        return getMapper().selectList(queryWrapper);
    }
}