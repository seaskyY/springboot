package com.tc.pes.service;

import com.tc.common.page.PageInfo;
import com.tc.common.page.PageList;
import com.tc.pes.dao.SingleTableDao;
import com.tc.pes.exception.PesException;
import com.tc.pes.exception.PesObjectMultiException;
import com.tc.pes.model.Page;
import com.tc.pes.model.PageCriteria;
import com.tc.pes.model.PojoWithPkSet;
import com.tc.udc.aaa.api.service.ISequenceService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * AbstractBaseService
 *
 * @author zhangshaojiao
 * @version v2.0.0
 * @date 2016/7/12 16:57
 */
public abstract class AbstractBaseService<U extends PojoWithPkSet, V extends PageCriteria> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractBaseService.class);

    /**
     * 序列号初始化操作锁
     */
    private final static Object init_lock_sequence = new Object();

    @Resource
    private ISequenceService sequenceService;

    private static ISequenceService.SequenceIdsGet2 sequenceGetRealTime = null;

    protected Long getLongUniqueId() {
        if (sequenceGetRealTime == null) {
            synchronized (init_lock_sequence) {
                if (sequenceGetRealTime == null) {
                    sequenceGetRealTime = new ISequenceService.SequenceIdsGet2(sequenceService);
                }
            }
        }
        return sequenceGetRealTime.getSequenceId();
    }

    /**
     * 子类需要注入特定的DAO实现
     */
    protected abstract SingleTableDao<U, V> getMyBatisRepository();

    /**
     * 数据库插入操作
     *
     * @param entity 持久化的对象
     * @return
     */
    public int insertSelectiveWithPK(U entity) {
        entity.setPk(getLongUniqueId());
        return getMyBatisRepository().insertSelective(entity);
    }

    /**
     * 数据库插入操作
     *
     * @param entity 持久化的对象
     * @return
     */
    public int insertWithPK(U entity) {
        entity.setPk(getLongUniqueId());
        return getMyBatisRepository().insert(entity);
    }

    /**
     * 数据库插入操作, 支持其他dao
     * @param entity 持久化的对象
     * @param dao    持久化对象所使用的Dao
     * @return
     */
    public <E extends PojoWithPkSet, C extends PageCriteria> int insertSelectiveWithPK(E entity, SingleTableDao<E, C> dao) {
        entity.setPk(getLongUniqueId());
        return dao.insertSelective(entity);
    }

    /**
     * 数据库插入操作， 支持其他dao
     * @param entity 持久化的对象
     * @param dao    持久化对象所使用的Dao
     * @return
     */
    public <E extends PojoWithPkSet, C extends PageCriteria>  int insertWithPK(E entity, SingleTableDao<E, C> dao) {
        entity.setPk(getLongUniqueId());
        return dao.insert(entity);
    }

    public void modifyById(U entity) throws PesException {
        Assert.notNull(entity, "修改对象不能为空");
        int successCount = getMyBatisRepository().updateById(entity);
        if (1 != successCount) {
            throw new PesException("修改对象-数据库失败");
        }
    }

    public void modifySelectiveById(U entity) throws PesException {
        Assert.notNull(entity, "修改对象不能为空");
        int successCount = getMyBatisRepository().updateByIdSelective(entity);
        if (1 != successCount) {
            throw new PesException("修改对象-数据库失败");
        }
    }

    public List<U> queryEntityList(V queryObject) throws PesException {
        Assert.notNull(queryObject, "查询参数对象不能为空");
        return getMyBatisRepository().selectByCriteria(queryObject);
    }

    public U queryEntity(V queryObject) throws PesException {
        List<U> list = this.queryEntityList(queryObject);
        if(list != null && list.size() > 0){
            if(list.size() == 1){
                return list.get(0);
            }
            throw PesObjectMultiException.create(logger, "找到多个对象");
        }
        return null;
      }

    public PageList<U> queryPageList(V queryObject, Page page, String orderByClause) {
        Assert.notNull(queryObject, "queryObject不能为null");
        int count = getMyBatisRepository().countByCriteria(queryObject);

        if(page == null){
            page = new Page(1, count);
        }
        if (count == 0) {
            return PageList.getPageList(new ArrayList<U>(), new PageInfo(page.getPageIndex(), page.getPageSize()));
        }
        page.setTotalRecords(count);
        //分页
        queryObject.setPage(page);
        //排序
        queryObject.setOrderByClause(orderByClause);

        return PageList.getPageList(getMyBatisRepository().selectByCriteria(queryObject), new PageInfo(count, page.getPageIndex(), page.getPageSize()));
    }


    /**
     * 将当前列表数据和db的数据作比较并分拆为增加，删除， 修改三个列表
     * @param addList           待增加列表
     * @param updateList        待修改列表
     * @param deleteList        待删除列表
     * @param curList           当前列表
     * @param dbList            从数据库中获取的列表
     * @param longPkFieldName   主键field name
     */
    public void splitListForSave(final List<U> addList, final List<U> updateList, final List<U> deleteList, final List<U> curList, final List<U> dbList, String longPkFieldName) throws IllegalAccessException {
        //全部清空
        if(curList.isEmpty()){
            deleteList.addAll(dbList);
            return;
        }

        //全部新增
        if(dbList.isEmpty()){
            addList.addAll(curList);
            return;
        }

        Iterator<U> dbIterator = dbList.iterator();
        while (dbIterator.hasNext()){
            U u = dbIterator.next();
            Iterator<U> curIterator = curList.iterator();

            //是否老的新的有匹配
            boolean found = false;

            while (curIterator.hasNext()){
                U cu = curIterator.next();

                Object cuPk = getLongPkValue(longPkFieldName, cu);
                Object uPk = getLongPkValue(longPkFieldName, u);
                if(cuPk != null && uPk.equals(cuPk)){
                    //找到匹配的项
                    updateList.add(cu);
                    curIterator.remove();
                    found = true;
                    break;
                }
            }

            //找到匹配的项
            if(found){
                dbIterator.remove();
            }
        }
        //当前目录剩下的全部是添加的
        addList.addAll(curList);

        //以前目录剩下的全部是删除的
        deleteList.addAll(dbList);
    }

    /**
     * 获取Long的主键值
     * @param pkName
     * @param curObj
     * @return
     * @throws IllegalAccessException
     */
    private Object getLongPkValue(String pkName, Object curObj) throws IllegalAccessException {
        for(Field fd: curObj.getClass().getDeclaredFields()){
            if(fd.getName().equals(pkName) &&
                    fd.getGenericType().toString().equals(Long.class.toString())){
                fd.setAccessible(true);
                return fd.get(curObj);
            }
        }
        return null;
    }

    /**
     * 组合成list
     * @param hospitalIds
     * @return
     */
    protected List<Long> constructArrayList(Long...hospitalIds){
        ArrayList<Long> retList = new ArrayList<>();
        for(Long hid: hospitalIds){
            retList.add(hid);
        }
        return retList;
    }

    /**
     * 大于0
     * @param curValue
     * @return
     */
    protected boolean greaterThanZero(Long curValue){
        return curValue != null && curValue > 0;
    }

    /**
     * set -> list
     * @param ids
     * @return
     */
    protected List<Long> createFromSet(Set<Long> ids){
        List<Long> retList = new ArrayList<>();
        retList.addAll(ids);
        return retList;
    }

    /**
     * [] -> list
     * @param ids
     * @return
     */
    protected List<Long> createFromArray(Long... ids ){
        List<Long> retList = new ArrayList<>();
        for(Long id: ids) {
            retList.add(id);
        }
        return retList;
    }

    /**
     * 得到排序
     * @param sortColumnName
     * @param descOrAsc
     * @param defaultOrderBy 默认排序
     * @return
     */
    public String getOrderBy(String sortColumnName, String descOrAsc,String defaultOrderBy){
        if(StringUtils.isNotBlank(sortColumnName) && StringUtils.isNotBlank(descOrAsc)){
            return sortColumnName + " " + descOrAsc;
        }else{
            return defaultOrderBy;
        }
    }

    public BigDecimal NullToZeroBigDecimal(BigDecimal bigDecimal){
        return bigDecimal == null ? BigDecimal.ZERO : bigDecimal;
    }
}